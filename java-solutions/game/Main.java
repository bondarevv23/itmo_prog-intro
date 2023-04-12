package game;

import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.lang.NumberFormatException;

public class Main {
    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);

        boolean correctInput = false;
        int m = -1;
        int n = -1;
        int k = -1;

        System.out.println("Enter 3 numeric arguments for m, n and k respectively separated by a space");
        while (!correctInput) {
            String line;
            try {
                line = in.nextLine();
            } catch (NoSuchElementException e) {
                System.err.println("You have completed the game");
                return;
            }
            int[] numbers = check(line, 3);
            if (numbers != null) {
                m = numbers[0];
                n = numbers[1];
                k = numbers[2];
                if (m > 0 && n > 0 && k > 0) {
                    correctInput = true;
                } else {
                    System.err.println("Wrong input: all arguments must be greater than zero");
                }
            }
            if (!correctInput) {
                System.err.println("Try another one");
            }
        }

        // final int result = new Game(
        //     new HexBoard(m, n, k),
        //     new RandomPlayer(m, n),
        //     new HumanPlayer(new Scanner(System.in))
        // ).play(false);
        // switch(result) {
        //     case 1:
        //         System.out.println("First player won");
        //         break;
        //     case 2:
        //         System.out.println("Second player won");
        //         break;
        //     case 0:
        //         System.out.println("Draw");
        //         break;
        //     default:
        //         throw new AssertionError("Unknown result");
        // }

        final String[] table = new Tournament(
            Games.HEX, m, n, k,
            new RandomPlayer(m, n),
            //new HumanPlayer(new Scanner(System.in)),
            new RandomPlayer(m, n)
        ).play();
        for (String line : table) {
            System.out.println(line);
        }
    }

    public static int[] check(String line, int count) {
        final StringTokenizer lineTokens = new StringTokenizer(line);
        final int countTokens = lineTokens.countTokens();
        int[] num = null;
        if (countTokens != count) {
            System.err.print("Wrong input: ");
            if (countTokens < count) {
                System.err.println("not enough arguments");
            } else {
                System.err.println("too much arguments");
            }
        } else {
            try {
                num = new int[count];
                for (int i = 0; i < count; i++) {
                    num[i] = Integer.parseInt(lineTokens.nextToken());
                }
            } catch (NumberFormatException e) {
                System.err.println("Wrong input: wrong format");
                return null;
            }
        }
        return num;
    }
}
