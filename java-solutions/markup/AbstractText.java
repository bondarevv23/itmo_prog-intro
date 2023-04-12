package markup;

import java.util.List;

public abstract class AbstractText implements MarkupElement {
    protected List<MarkupElement> list;

    AbstractText(List<MarkupElement> list) {
        this.list = list;
    }

    AbstractText(MarkupElement item) {
        this.list = List.of(item);
    }

    protected void toMarkdownBBCodeChar(StringBuilder box, String ch, int type) {
        if (type == 1) {
            box.append("[" + ch + "]");
        } else {
            box.append(ch);
        }
        for (MarkupElement text : list) {
            StringBuilder textSB = new StringBuilder();
            if (type == 0) {
                text.toMarkdown(textSB);
            } else {
                text.toBBCode(textSB);
            }
            box.append(textSB);
        }
        if (type == 1) {
            box.append("[/" + ch + "]");
        } else {
            box.append(ch);
        }
    }
}
