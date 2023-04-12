package markup;

public interface MarkupElement {
    public void toMarkdown(StringBuilder box);

    public void toBBCode(StringBuilder box);
}
