import java.util.LinkedHashMap;
import java.util.Map;
import java.io.*;
import java.lang.ArrayIndexOutOfBoundsException;
import java.nio.charset.StandardCharsets;

public class Wspp {
    public static void main (String[] args){
        try{
            int[] typeChars = new int[]{Character.DASH_PUNCTUATION,
                                        Character.LOWERCASE_LETTER,
                                        Character.UPPERCASE_LETTER};
            char[] specialChars = new char[]{'\''};
            MyScanner scan = new MyScanner(new FileReader(args[0], StandardCharsets.UTF_8), typeChars, specialChars);
            try {
                Map<String, IntList> map = new LinkedHashMap<>();
                int wordCounter = 1;
                while (true) {
                    int request = scan.moveNext();
                    if (request == -1) {
                        break;
                    } else if (request == 1) {
                        String word = scan.next().toLowerCase();
                        IntList list = map.get(word);
                        if (list != null) {
                            list.add(wordCounter);
                        } else {
                            IntList newList = new IntList(wordCounter);
                            map.put(word, newList);
                        }
                        wordCounter++;
                    }
                }
                BufferedWriter out = new BufferedWriter(
                    new OutputStreamWriter(new FileOutputStream(args[1]), StandardCharsets.UTF_8.name())
                );
                try {
                    for (Map.Entry<String, IntList> entry :  map.entrySet()) {
                        IntList arr = entry.getValue();
                        StringBuilder result = new StringBuilder(" " + arr.getLength());
                        for (int i = 0; i < arr.getLength(); i++) {
                            result.append(" " + arr.getElement(i));
                        }
                        out.write(entry.getKey() + result.toString());
                        out.newLine();
                    }
                } finally {
                    out.close();
                }
            } finally {
                scan.close();
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