

public class Sum {
    public static void main(String[] args) {
        int ans = 0;
        for (int i = 0; i < args.length; i++) {
            for (int j = 0; j < args[i].length(); j++) {
                if (!Character.isWhitespace(args[i].charAt(j))) {
                    int pointer = j;
                    while (j < args[i].length() && !Character.isWhitespace(args[i].charAt(j))) {
                        j++;
                    }
                    ans += Integer.parseInt(args[i], pointer, j, 10);
                }
            }
        }
        System.out.println(ans);
    }
}