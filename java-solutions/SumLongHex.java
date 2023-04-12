
public class SumLongHex {  
    public static void main (String[] args) {
        long ans = 0;
        for (int i = 0; i < args.length; i++) {
            for (int j = 0; j < args[i].length(); j++) {
                if (!Character.isWhitespace(args[i].charAt(j))) {
                    int pointer = j;
                    while (j < args[i].length() && !Character.isWhitespace(args[i].charAt(j))) {
                        j++;
                    }
                    if (args[i].charAt(pointer) == '0' && 
                        (args[i].charAt(pointer + 1) == 'x' || args[i].charAt(pointer + 1) == 'X')) {
                        ans += Long.parseUnsignedLong(args[i], pointer + 2, j, 16);
                    } else {
                        ans += Long.parseLong(args[i], pointer, j, 10);
                    }
                }
            }
        }
        System.out.println(ans);
    }
}