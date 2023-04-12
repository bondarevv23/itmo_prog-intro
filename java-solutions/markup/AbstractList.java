package markup;

import java.util.List;

public abstract class AbstractList implements ListElement {
    protected List<ListItem> list;

    AbstractList(List<ListItem> list) {
        this.list = list;
    }

    AbstractList(ListItem item) {
        this.list = List.of(item);
    }

    protected void toBBCodeChar(StringBuilder box, String openTag, String closeTag) {
        box.append("[" + openTag + "]");
        for (ListElement element : list) {
            element.toBBCode(box);
        }
        box.append("[/" + closeTag + "]");
    }
}
