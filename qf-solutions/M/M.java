import java.io.Reader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.HashMap;

public class M {
    public static void main(String[] args) {
        try (MyScanner scanner = new MyScanner(
            new InputStreamReader(System.in),
            new int[]{Character.DECIMAL_DIGIT_NUMBER},
            new char[0]
        )) {
            int t = Integer.parseInt(scanner.next());
            for (int q = 0; q < t; q++) {
                int n = Integer.parseInt(scanner.next());
                int[] array = new int[n];
                for (int i = 0; i < n; i++) {
                    array[i] = Integer.parseInt(scanner.next());
                }
                HashMap<Integer, Integer> count = new HashMap<>();
                int ans = 0;
                for (int j = n - 1; j > 0; j--) {
                    for (int i = 0; i < j; i++) {
                        Integer request = count.get(2 * array[j] - array[i]);
                        if (request != null) {
                            ans += request;
                        }
                    }
                    Integer request = count.get(array[j]);
                    if (request == null) {
                        count.put(array[j], 1);
                    } else {
                        count.put(array[j], request + 1);
                    }
                }
                System.out.println(ans);
            }
            scanner.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}

class MyScanner implements java.lang.AutoCloseable{
    private final Reader reader; 
    private final char[] buffer = new char[1024];
    private final int[] validChars;
    private final char[] specialChars;
    private int bufferInd;
    private int bufferCount;

    MyScanner(InputStreamReader input, int[] chars, char[] specialChars) throws IOException{
        this.reader = input;
        this.validChars = chars;
        this.specialChars = specialChars;
        pointNextChar();
    }

    MyScanner(FileReader input, int[] chars, char[] specialChars) throws IOException {
        this.reader = input;
        this.validChars = chars;
        this.specialChars = specialChars;
        pointNextChar();
    }

    public void close() throws IOException {
        this.reader.close();
    }

    private void pointNextChar() throws IOException{
        bufferInd++;
        if (bufferInd >= bufferCount) {
            bufferCount = reader.read(buffer);
            bufferInd = 0;
        }
    }
 
    private boolean isValid(char ch) {
        int chType = Character.getType(ch);
        for (int i = 0; i < specialChars.length; i++) {
            if (specialChars[i]  == ch) {
                return true;
            }
        }      
        for (int i = 0; i < validChars.length; i++) {
            if (chType == validChars[i]) {
                return true;
            }
        }
        return false;
    }

    public int moveNext() throws IOException {
        while (bufferInd < bufferCount) {
            if (buffer[bufferInd] == '\r') {
                pointNextChar();
                if ((bufferInd < bufferCount) && buffer[bufferInd] == '\n') {
                    pointNextChar();
                }
                return 0;
            } else if (buffer[bufferInd] == '\n') {
            	pointNextChar();
            	return 0;
            } else if (isValid(buffer[bufferInd])) {
                return 1;
            } else {
                pointNextChar();
            }
        }
        return -1;
    }

    public String next() throws IOException {
        while (true) {
            int request = moveNext();
            if (request == -1) {
                return null;
            } else if (request == 1) {
                StringBuilder word = new StringBuilder();
                while (bufferInd < bufferCount) {
                    if (isValid(buffer[bufferInd])) {
                        word.append(buffer[bufferInd]);
                        pointNextChar();
                    } else {
                        break;
                    }
                }
                if (word.length() > 0) {
                    return word.toString();
                } else {
                    return null;
                }
            }
        }
    }
}