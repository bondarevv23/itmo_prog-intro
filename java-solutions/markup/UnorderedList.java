package markup;

import java.util.List;

public class UnorderedList extends AbstractList {
    private String openTag = "list";
    private String closeTag = "list";

    public UnorderedList(List<ListItem> list) {
        super(list);
    }

    public UnorderedList(ListItem item) {
        super(item);
    }

    @Override
    public void toBBCode(StringBuilder box) {
        toBBCodeChar(box, openTag, closeTag);
    }
}
