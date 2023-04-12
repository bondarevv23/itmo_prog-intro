import java.util.LinkedHashMap;
import java.util.Map;
import java.io.*;
import java.lang.ArrayIndexOutOfBoundsException;
import java.nio.charset.StandardCharsets;

public class WordStatInput {
    public static void main (String[] args){
        int[] typeChars = new int[]{Character.DASH_PUNCTUATION,
            Character.LOWERCASE_LETTER,
            Character.UPPERCASE_LETTER};
        char[] specialChars = new char[]{'\''};
        try (
            MyScanner scan = new MyScanner(new FileReader(args[0], StandardCharsets.UTF_8), typeChars, specialChars);
        ) {
            Map<String, Integer> map = new LinkedHashMap<>();
            while (true) {
                int request = scan.moveNext();
                if (request == -1) {
                    break;
                } else if (request == 1) {
                    String word = scan.next().toLowerCase();
                    if (map.containsKey(word)) {
                        map.replace(word, map.get(word) + 1);
                    } else {
                        map.put(word, 1);
                    }
                }
            }
            try (
                BufferedWriter out = new BufferedWriter(
                    new OutputStreamWriter(new FileOutputStream(args[1]), StandardCharsets.UTF_8.name())
                );
            ) {
                for (Map.Entry<String, Integer> entry :  map.entrySet()) {
                    out.write(entry.getKey() + " " + entry.getValue());
                    out.newLine();
                }
            }
        } catch (FileNotFoundException e){
            System.out.println("Cannot find file. " + e.getMessage());
        } catch (IOException e){
            System.out.println("Cannot read / write file. " + e.getMessage());
        } catch (ArrayIndexOutOfBoundsException e){
            System.out.println("The specified arguments are not enough for the program work. " + e.getMessage());
        }
    }
}