package game;

public class Move {
    private Cell value;
    private final int row;
    private final int column;

    Move(Cell value, int row, int column) {
        this.value = value;
        this.row = row;
        this.column = column;
    }

    public Cell getValue() {
        return this.value;
    }

    public int getRow() {
        return this.row;
    }

    public int getColumn() {
        return this.column;
    }

    public String getString() {
        return String.format("Move(%s, %d, %d)", value, row + 1, column + 1);
    }
}
