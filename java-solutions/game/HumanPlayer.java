package game;

import java.util.NoSuchElementException;
import java.util.Scanner;

public class HumanPlayer implements Player {
    private final Scanner in;

    HumanPlayer(Scanner in) {
        this.in = in;
    }

    @Override
    public Move makeMove(Position position) {
        String line;
        System.out.println(position.getString());
        System.out.println("Enter 2 numeric arguments of coordinates your move respectively separated by a space");
        while (true) {
            try {
                line = in.nextLine();
            } catch (NoSuchElementException e) {
                System.err.println("It is too bad that you lost");
                return null;
            }
            int[] numbers = Main.check(line, 2);
            if (numbers != null) {
                if (numbers[0] > 0 && numbers[1] > 0) {
                    final Move move = new Move(position.getTurn(), numbers[0] - 1, numbers[1] - 1);
                    if (position.isValid(move)) {
                        return move;
                    } else {
                        System.err.println("Wrong input: this move is not valid");
                    }
                } else {
                    System.err.println("Wrong input: all arguments must be greater than zero");
                }
            } else {
                System.err.println("Try another one");
            }
        }
    }
}
