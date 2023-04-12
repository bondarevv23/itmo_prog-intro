package game;

import java.util.Map;
import java.util.Arrays;

public abstract class AbstractBoard implements Board {
    protected static final Map<Cell, String> CELL_TO_STRING = Map.of(
        Cell.E, ".",
        Cell.X, "X",
        Cell.O, "O"
    );
    protected final Cell[][] field;
    protected Cell turn;
    private int movesCounter;
    private int checkCounter;

    protected int m;
    protected int n;
    protected int k;

    AbstractBoard(int m, int n, int k, int checkCounter) {
        this.movesCounter = 0;
        this.checkCounter = checkCounter;
        this.m = m;
        this.n = n;
        this.k = k;
        field = new Cell[m][n];
        for (Cell[] row : field) {
            Arrays.fill(row, Cell.E);
        }
        this.turn = Cell.X;
    }

    @Override
    public GameResult makeMove(Move move) {
        field[move.getRow()][move.getColumn()] = move.getValue();
        movesCounter++;
        int result = isGameOver(move);
        turn = (turn == Cell.X ? Cell.O : Cell.X);
        switch (result) {
            case 1:
                return GameResult.WIN;
            case 0:
                return GameResult.UNKNOW;
            case -1:
                return GameResult.DRAW;
            default:
                throw new AssertionError("Unknow gameOverResult " + result);
        }
    }

    protected void appendSpace(String element, StringBuilder box, int length) {
        box.append(element);
        for (int i  = 0; i < length - element.length(); i++) {
            box.append(" ");
        } 
    }

    @Override
    public Cell getTurn() {
        return this.turn;
    }
    
    @Override
    public boolean isValid(final Move move) {
        return (0 <= move.getRow() && move.getRow() < this.m
                && 0 <= move.getColumn() && move.getColumn() < this.n
                && field[move.getRow()][move.getColumn()] == Cell.E
                && turn == move.getValue());
    }

    private int isGameOver(Move move) {
        int[] xDirect = new int[]{0, 0, 1, -1, 1, -1, -1, 1};
        int[] yDirect = new int[]{-1, 1, -1, 1, 0, 0, -1, 1};
        int maxLineCount = 1;
        int lineCount = 1;
        for (int i = 0; i < checkCounter; i++) {
            int x = move.getColumn() + xDirect[i];
            int y = move.getRow() + yDirect[i];
            int k = this.k - 1;
            while(k > 0 && x >= 0 && x < n && y >= 0 && y < m) {
                if (field[y][x] == move.getValue()) {
                    lineCount++;
                    x += xDirect[i];
                    y += yDirect[i];
                } else {
                    break;
                }
            }
            if (i % 2 == 1) {
                maxLineCount = Math.max(maxLineCount, lineCount);
                lineCount = 1;
            }
        }
        if (maxLineCount >= k) {
            return 1;
        } else if (movesCounter == this.m * this.n) {
            return -1;
        } else {
            return 0;
        }
    }
}
