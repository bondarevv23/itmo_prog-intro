package md2html;

public class Replacement implements Comparable<Replacement> {
    int position;
    Style type;
    boolean isOpen;

    Replacement(final int position, final Style type, final boolean isOpen) {
        this.position = position;
        this.type = type;
        this.isOpen = isOpen;
    }

    Replacement(final int position, final Style type) {
        this(position, type, true);
    }

    @Override
    public int compareTo(final Replacement x) {
        return this.position - x.position;
    }
}
