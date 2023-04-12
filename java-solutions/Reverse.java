import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Reverse {
    public static void main (String[] args) {
        int[] typeChars = new int[]{Character.DECIMAL_DIGIT_NUMBER,
            Character.LOWERCASE_LETTER,
            Character.UPPERCASE_LETTER};
        char[] specialChars = new char[]{'-'};
        try (
            MyScanner scan = new MyScanner(new InputStreamReader(System.in), typeChars, specialChars);
        ) {
            int[] numbers = new int[10];
            int numInd = 0;
            int[] countPerLine = new int[10];
            int linInd = 0;
    
            int lineCounter = 0;
    
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
                } else if (request == 1){
                    lineCounter++;
                    if (numInd >= numbers.length) {
                        numbers = Arrays.copyOf(numbers, numInd*2);
                    }
                    numbers[numInd++] = Integer.parseInt(scan.next());
                }
            }
            
            for (int i = linInd-1; i >= 0; i--) {
                for (int j = 0; j < countPerLine[i]; j++) {
                    System.out.print(numbers[--numInd] + " ");
                }
                System.out.println();
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
}