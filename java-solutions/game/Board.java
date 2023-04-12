package game;

public interface Board extends Position {
    GameResult makeMove(Move move);

    default Position getPosition() {
        Position boardCopy = (Position) this;
        return new Position() {
            Position board = boardCopy;

            @Override
            public Cell getTurn() {
                return board.getTurn();
            }

            @Override
            public boolean isValid(Move move) {
                return board.isValid(move);
            }

            @Override
            public String getString() {
                return board.getString();
            }
        };
    }
}
