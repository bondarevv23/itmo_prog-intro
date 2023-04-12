package game;

public class HexBoard extends AbstractBoard {

    HexBoard(int m, int n, int k) {
        super(m, n, k, 6);
    }

    @Override
    public String getString() {
        final StringBuilder[] matrix = new StringBuilder[m + 1];
        for (int i = 0; i < m + 1; i++) {
            matrix[i] = new StringBuilder();
        }
        int length = Integer.toString(n).length() + 1;
        for (int row = 0; row < m; row++) {
            for (int i = 0; i < row; i++) {
                appendSpace(" ", matrix[row + 1], length);
            }
            appendSpace(Integer.toString(row + 1), matrix[row + 1], length);
            for (int column = 0; column < n; column++) {
                appendSpace(CELL_TO_STRING.get(field[row][column]), matrix[row + 1], length);
            }
            matrix[row + 1].append(System.lineSeparator());
        }
        for (int column = 1; column < n + 1; column++) {
            appendSpace(Integer.toString(column), matrix[0], length);
        }
        matrix[0].append(System.lineSeparator());
        StringBuilder boardSb = new StringBuilder();
        for (int i = 0; i < m + 1; i++) {
            boardSb.append(matrix[i]);
        }
        return boardSb.toString();
    }
}
