package game;

public class Game {
    private final Board board;
    private final Player player1;
    private final Player player2;

    Game(Board board, Player player1, Player player2) {
        this.board = board;
        this.player1 = player1;
        this.player2 = player2;
    }

    public int play(boolean log) {
        while(true) {
            final int result1 = makeMove(player1, 1, log);
            if (result1 != -1) {
                return result1;
            }
            final int result2 = makeMove(player2, 2, log);
            if (result2 != -1) {
                return result2;
            }
        }
    }

    public int makeMove(Player player, int number, boolean log) {
        final Move move = player.makeMove(board.getPosition());
        final GameResult result;
        if (move == null) {
            result = GameResult.LOOSE;
        } else {
            result = board.makeMove(move);
        }
        if (log && move != null) {
            System.out.println();
            System.out.println("Player " + number);
            System.out.println(move.getString());
            System.out.println(board.getString());
            System.out.println("Result: " + result);

        }
        if (result != GameResult.UNKNOW) {
            System.out.println(board.getString());
        }
        switch (result) {
            case WIN:
                return number;
            case LOOSE:
                return 3 - number;
            case DRAW:
                return 0;
            case UNKNOW:
                return -1;
            default:
                throw new AssertionError("Unknow makeMove result: " + result);
        }
    }
}
