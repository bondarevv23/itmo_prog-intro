package markup;

import java.util.List;

public class ListItem implements ListElement {
    protected List<ListElement> list;

    public ListItem(List<ListElement> list) {
        this.list = list;
    }

    public ListItem(ListElement item) {
        this.list = List.of(item);
    }

    @Override
    public void toBBCode(StringBuilder box) {
        for (ListElement element : list) {
            box.append("[*]");
            element.toBBCode(box);
        }
    }
}
