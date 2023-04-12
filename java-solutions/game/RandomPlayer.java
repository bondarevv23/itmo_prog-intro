package game;

import java.util.Random;

public class RandomPlayer implements Player {
    private final Random random = new Random();
    private int m;
    private int n;

    RandomPlayer(int m, int n) {
        this.m = m;
        this.n = n;
    }

    @Override
    public Move makeMove(Position position) {
        while (true) {
            final Move move = new Move(
                position.getTurn(),
                random.nextInt(m),
                random.nextInt(n)
            );
            if (position.isValid(move)) {
                return move;
            }
        }
    }
    
}
