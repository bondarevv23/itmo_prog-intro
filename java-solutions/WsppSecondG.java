import java.util.LinkedHashMap;
import java.util.Map;
import java.io.*;
import java.lang.ArrayIndexOutOfBoundsException;
import java.nio.charset.StandardCharsets;

public class WsppSecondG {
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
                int stringCounter = 1;
                while (true) {
                    int request = scan.moveNext();
                    if (request == -1) {
                        break;
                    } else if (request == 1) {
                        String word = scan.next().toLowerCase();
                        IntList list = map.get(word);
                        if (list != null) {
                            if (stringCounter == list.getElement(0)) {
                                if (list.getElement(1) % 2 == 1) {
                                    list.add(wordCounter);
                                }
                                list.changeElement(1, list.getElement(1) + 1);
                            } else {
                                list.changeElement(0, stringCounter);
                                list.changeElement(1, 1);
                            }
                            list.changeElement(2, list.getElement(2) + 1);
                        } else {
                            IntList newList = new IntList();
                            newList.add(stringCounter);
                            newList.add(1);
                            newList.add(1);
                            map.put(word, newList);
                        }
                        wordCounter++;
                    } else {
                        stringCounter++;
                    }
                }
                BufferedWriter out = new BufferedWriter(
                    new OutputStreamWriter(new FileOutputStream(args[1]), StandardCharsets.UTF_8.name())
                );
                try {
                    for (Map.Entry<String, IntList> entry :  map.entrySet()) {
                        IntList arr = entry.getValue();
                        StringBuilder result = new StringBuilder();
                        for (int i = 2; i < arr.getLength(); i++) {
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