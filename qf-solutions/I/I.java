import java.util.Scanner;

public class I {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int xl = Integer.MAX_VALUE;
        int xr = Integer.MIN_VALUE;
        int yl = Integer.MAX_VALUE;
        int yr = Integer.MIN_VALUE;
        int n = scanner.nextInt();
        for (int i = 0; i < n; i++) {
            int x, y, h;
            x = scanner.nextInt();
            y = scanner.nextInt();
            h = scanner.nextInt();
            xl = Math.min(xl, x - h);
            xr = Math.max(xr, x + h);
            yl = Math.min(yl, y - h);
            yr = Math.max(yr, y + h);
        }
        System.out.println((xl + xr) / 2 + " " +
                           (yl + yr) / 2 + " " +
                           (Math.max(xr - xl, yr - yl) + 1) / 2);
        scanner.close();
    }
}
