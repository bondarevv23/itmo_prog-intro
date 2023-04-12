import java.util.Scanner;

public class B {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int step = -(710 * (n / 2));
        for (int i = 0; i < n; i++) {
            System.out.println(step);
            step += 710;
        }
        scanner.close();
    }
}
