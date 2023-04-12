package markup;

import java.util.List;

public class Emphasis extends AbstractText {

    public Emphasis(List<MarkupElement> list) {
        super(list);
    }

    public Emphasis(MarkupElement item) {
        super(item);
    }

    public void toMarkdown(StringBuilder box) {
        toMarkdownBBCodeChar(box, "*", 0);
    }

    public void toBBCode(StringBuilder box) {
        toMarkdownBBCodeChar(box, "i", 1);
    }
}
