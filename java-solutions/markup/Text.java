package markup;

public class Text implements MarkupElement{
    private StringBuilder text;

    public Text(String text) {
        this.text = new StringBuilder(text);
    }

    public void toMarkdown(StringBuilder box) {
        box.append(text);
    }

    public void toBBCode(StringBuilder box) {
        box.append(text);
    }
}
