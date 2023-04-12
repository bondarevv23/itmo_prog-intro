import java.util.TreeMap;
import java.util.Map;
import java.io.*;
import java.lang.ArrayIndexOutOfBoundsException;

public class WordStatWords {
    static boolean isWordPart(char ch) {
        return (Character.isLetter(ch) ||
                ch == '\'' ||
                Character.getType(ch) == Character.DASH_PUNCTUATION);
    }

    public static void main(String[] args) {
        try{
            BufferedReader in = new BufferedReader(
                new InputStreamReader(new FileInputStream(args[0]), "utf8")
            );
            try {
                TreeMap<String, Integer> map = new TreeMap<>();
                int ch = in.read();
                StringBuilder word = new StringBuilder();
                while (ch != -1) {
                    if (isWordPart((char) ch)) {
                        word.append((char) ch);
                    } else if (word.length() > 0){
                        String letter = word.toString().toLowerCase();
                        if (map.containsKey(letter)) {
                            map.replace(letter, map.get(letter) + 1);
                        } else {
                            map.put(letter, 1);
                        }
                        word = new StringBuilder();
                    }
                    ch = in.read();
                }
                BufferedWriter out = new BufferedWriter(
                    new OutputStreamWriter(new FileOutputStream(args[1]), "utf8")
                );
                try {
                    for (Map.Entry<String, Integer> entry :  map.entrySet()) {
                        out.write(entry.getKey() + " " + entry.getValue() + "\n");
                    }
                } finally {
                    out.close();
                }
            } finally {
                in.close();
            }
        } catch (FileNotFoundException e){
            System.out.println("Cannot find file");
            System.out.println(e.getMessage());
        } catch (IOException e){
            System.out.println("Cannot read / write file");
            System.out.println(e.getMessage());
        } catch (ArrayIndexOutOfBoundsException e){
            System.out.println("The specified arguments are not enough for the program work");
            System.out.println(e.getMessage());
        }
    }
}