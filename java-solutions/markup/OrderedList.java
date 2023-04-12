package markup;

import java.util.List;

public class OrderedList extends AbstractList {

    public OrderedList(List<ListItem> list) {
        super(list);
    }

    public OrderedList(ListItem item) {
        super(item);
    }

    private String openTag = "list=1";
    private String closeTag = "list";

    @Override
    public void toBBCode(StringBuilder box) {
        toBBCodeChar(box, openTag, closeTag);
    }
}
