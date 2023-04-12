package markup;

import java.util.List;

public class Strikeout extends AbstractText {

    public Strikeout(List<MarkupElement> list) {
        super(list);
    }

    public Strikeout(MarkupElement item) {
        super(item);
    }

    public void toMarkdown(StringBuilder box) {
        toMarkdownBBCodeChar(box, "~", 0);
    }

    public void toBBCode(StringBuilder box) {
        toMarkdownBBCodeChar(box, "s", 1);
    }
}
