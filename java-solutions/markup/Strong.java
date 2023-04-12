package markup;

import java.util.List;

public class Strong extends AbstractText {

    public Strong(List<MarkupElement> list) {
        super(list);
    }

    public Strong(MarkupElement item) {
        super(item);
    }

    public void toMarkdown(StringBuilder box) {
        toMarkdownBBCodeChar(box, "__", 0);
    }

    public void toBBCode(StringBuilder box) {
        toMarkdownBBCodeChar(box, "b", 1);
    }
}
