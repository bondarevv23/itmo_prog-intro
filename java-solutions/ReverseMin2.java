import java.util.Scanner;
import java.util.Arrays;

public class ReverseMin2 {
    public static void main(String[] args) {
        Scanner scanGlobal = new Scanner(System.in);
        
        int[] numbers = new int[3];
        int numi = 0;
        int[] countPerLine = new int[3];
        int lini = 0;
        
        int maxLC = 0;
        while (scanGlobal.hasNextLine()) {
            int lineCounter = 0;
            Scanner scanLocal = new Scanner(scanGlobal.nextLine());
            while (scanLocal.hasNext()) {
                if (numi == numbers.length) {
                    numbers = Arrays.copyOf(numbers, numi * 2);
                }
                numbers[numi++] = scanLocal.nextInt();
                lineCounter++;
            }
            if (lini == countPerLine.length) {
                countPerLine = Arrays.copyOf(countPerLine, lini * 2);
            }
            countPerLine[lini++] = lineCounter;
            maxLC = Math.max(maxLC, lineCounter);
        }

        int[] minPrevUp = new int[maxLC + 1];
        Arrays.fill(minPrevUp, Integer.MAX_VALUE);

        int out = 0;
        for (int i = 0; i < lini; i++) {
            for (int j = 0; j < countPerLine[i]; j++) {
                int minValue = Math.min(numbers[out++], minPrevUp[j + 1]);
                minValue = Math.min(minValue, minPrevUp[j]);
                minPrevUp[j + 1] = minValue;
                System.out.print(minValue);
                System.out.print(" ");
            }
            System.out.println();
        }
    }
}