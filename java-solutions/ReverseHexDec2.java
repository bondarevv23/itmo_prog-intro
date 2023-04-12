import java.util.Arrays;
import java.io.IOException;
import java.io.InputStreamReader;

public class ReverseHexDec2 {
    
    private static int toInt(String s) {
        if (s.length() > 2 && s.substring(0, 2).toLowerCase().equals("0x")) {
            return Integer.parseUnsignedInt(s.substring(2, s.length()), 16);
        }
        return Integer.parseInt(s);
    }

    public static void main(String[] args) {
        int[] numbers = new int[10];
        int numInd = 0;
        int[] countPerLine = new int[10];
        int linInd = 0;
        int lineCounter = 0;

        int[] typeChars = new int[]{Character.DECIMAL_DIGIT_NUMBER,
            Character.LOWERCASE_LETTER,
            Character.UPPERCASE_LETTER};
        char[] specialChars = new char[]{'-'};

        try (
            MyScanner scan = new MyScanner(new InputStreamReader(System.in), typeChars, specialChars);
        ) {
            while (true) {
                int request = scan.moveNext();
                if (request == 0 || request == -1) {
                    if (linInd >= countPerLine.length) {
                        countPerLine = Arrays.copyOf(countPerLine, linInd*2);
                    }
                    countPerLine[linInd] = lineCounter;
                    if (request == -1) {
                        break;
                    }
                    linInd++;
                    lineCounter = 0;
                } else if (request == 1) {
                    lineCounter++;
                    if (numInd >= numbers.length) {
                        numbers = Arrays.copyOf(numbers, numInd*2);
                    }
                    numbers[numInd++] = toInt(scan.next());
                }
            }
            
            for (int i = linInd-1; i >= 0; i--) {
                for (int j = 0; j < countPerLine[i]; j++) {
                    System.out.print("0x" + Integer.toHexString(numbers[--numInd]) + " ");
                }
                System.out.println();
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}