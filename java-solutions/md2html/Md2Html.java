package md2html;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class Md2Html {
    public static void main(final String[] args) {
        final String inputFileName = args[0];
        final String outputFileName = args[1];
        try (final BufferedReader reader = new BufferedReader(
                new InputStreamReader(new FileInputStream(inputFileName), StandardCharsets.UTF_8));
             final BufferedWriter writer = new BufferedWriter(
                     new OutputStreamWriter(new FileOutputStream(outputFileName), StandardCharsets.UTF_8))
        ) {
            StringBuilder block = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                if (!line.isEmpty()) {
                    do {
                        if (!block.isEmpty()) {
                            block.append(System.lineSeparator());
                        }
                        block.append(line);
                    } while ((line = reader.readLine()) != null && !line.isEmpty());
                    List<Replacement> replacements = findReplacements(block.toString());
                    Collections.sort(replacements);
                    final StringBuilder paragraph = new StringBuilder();
                    replace(paragraph, block, replacements);
                    writer.write(paragraph + System.lineSeparator());
                    block = new StringBuilder();
                }
            }
        } catch (final IOException e) {
            System.err.println(e.getMessage());
        }
    }

    private static class Replaceable {
        String openTag;
        String closeTag;
        int SHIFT;

        Replaceable(String openTag, String closeTag, int shift) {
            this.openTag = openTag;
            this.closeTag = closeTag;
            this.SHIFT = shift;
        }
    }
    private static Map<Style, Replaceable> styles = Map.of(
        Style.EMPHASIS1, new Replaceable("<em>", "</em>", 1),
        Style.STRONG1, new Replaceable("<strong>", "</strong>", 2),
        Style.EMPHASIS2, new Replaceable("<em>", "</em>", 1),
        Style.STRONG2, new Replaceable("<strong>", "</strong>", 2),
        Style.SHIELDING, new Replaceable("", "", 1),
        Style.STRIKEOUT, new Replaceable("<s>", "</s>", 2),
        Style.CODE, new Replaceable("<code>", "</code>", 1),
        Style.PRE, new Replaceable("<pre>", "</pre>", 3)
    );

    private static void replace(final StringBuilder box,
                                final StringBuilder sbText,
                                List<Replacement> replacements) {
        final String openTag;
        final String closeTag;
        int lastAdd;
        final int request = isHeader(sbText);
        if (request > 0) {
            openTag = "<h" + request + ">";
            closeTag = "</h" + request + ">";
            lastAdd = request + 1;
        } else {
            openTag = "<p>";
            closeTag = "</p>";
            lastAdd = 0;
        }
        final String text = sbText.toString();
        box.append(openTag);
        for (final Replacement item : replacements) {
            box.append(htmlCode(text.substring(lastAdd, item.position)));
            if (item.isOpen) {
                box.append(styles.get(item.type).openTag);
            } else {
                box.append(styles.get(item.type).closeTag);
            }
            lastAdd = item.position + styles.get(item.type).SHIFT;
        }
        box.append(htmlCode(text.substring(lastAdd)));
        box.append(closeTag);
    }

    private static Map<Character, String> codes = Map.of(
        '<', "&lt;",
        '>', "&gt;",
        '&', "&amp;"
    );
    private static String htmlCode (final String text) {
        final StringBuilder box = new StringBuilder();
        for (int i = 0; i < text.length(); i++) {
            final String request = codes.get(text.charAt(i));
            if (request != null) {
                box.append(request);
            } else {
                box.append(text.charAt(i));
            }
        }
        return box.toString();
    }

    private static int isHeader(final StringBuilder SBtext) {
        final String text = SBtext.toString();
        int headerType = 0;
        for (int i = 0; i < Math.min(7, text.length()); i++) {
            if (text.charAt(i) == '#') {
                headerType++;
            } else if (text.charAt(i) == ' ') {
                return headerType;
            } else {
                break;
            }
        }
        return 0;
    }

    private static String[] mdTags = new String[]{"**", "*", "__", "_", "--", "```", "`"};
    private static Map<String, Style> mdStyles = Map.of(
        "\\", Style.SHIELDING,
        "**", Style.STRONG1,
        "*", Style.EMPHASIS1,
        "__", Style.STRONG2,
        "_", Style.EMPHASIS2,
        "--", Style.STRIKEOUT,
        "```", Style.PRE,
        "`", Style.CODE
    );
    private static List<Replacement> findReplacements(final String text) {
        List<Replacement> replacements = new ArrayList<>();
        Deque<Replacement> parseStack = new ArrayDeque<>();
        boolean previousPre = false; 
        for (int i = 0; i < text.length(); i++) {
            if (previousPre) {
                if (text.startsWith(mdTags[5], i)) {
                    stackAdd(new Replacement(i, mdStyles.get(mdTags[5])), replacements, parseStack);
                    i += (mdTags[5].length() - 1);
                    previousPre = false;
                }
                continue;
            }
            for (int j = 0; j < 7; j++) {
                if (text.startsWith("\\", i)) {
                    replacements.add(new Replacement(i, Style.SHIELDING));
                    i++;
                    break;
                }
                if (text.startsWith(mdTags[j], i)) {
                    if (j == 5) {
                        previousPre = true;
                    }
                    stackAdd(new Replacement(i, mdStyles.get(mdTags[j])), replacements, parseStack);
                    i += (mdTags[j].length() - 1);
                    break;
                }
            }
        }
        return replacements;
    }

    private static void stackAdd(final Replacement rep,
                                List<Replacement> replacements,
                                Deque<Replacement> parseStack) {
        if (parseStack.size() > 0 && parseStack.peek().type == rep.type) {
            replacements.add(parseStack.pop());
            rep.isOpen = false;
            replacements.add(rep);
        } else {
            parseStack.push(rep);
        }
    }
}
