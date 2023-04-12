package markup;

import java.util.List;

public class Paragraph implements ListElement {
    protected List<MarkupElement> list;

    public Paragraph(List<MarkupElement> list) {
        this.list = list;
    }

    public Paragraph(MarkupElement item) {
        this.list = List.of(item);
    }

    public void toMarkdown(StringBuilder box) {
        for (MarkupElement text : list) {
            StringBuilder textSB = new StringBuilder();
            text.toMarkdown(textSB);
            box.append(textSB);
        }
    }

    public void toBBCode(StringBuilder box) {
        for (MarkupElement text : list) {
            StringBuilder textSB = new StringBuilder();
            text.toBBCode(textSB);
            box.append(textSB);
        }
    }
}
