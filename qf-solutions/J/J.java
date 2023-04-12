import java.util.Scanner;

public class J {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[][] matrix = new int[n][n];
        for (int i = 0; i < n; i++) {
            String line = scanner.next();
            for (int j = 0; j < n; j++) {
                matrix[i][j] = line.charAt(j) - '0';
            }
        }
        int[][] ans = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (matrix[i][j] != 0) {
                    ans[i][j] = 1;
                    for (int k = j + 1; k < n; k++) {
                        matrix[i][k] = matrix[i][k] - matrix[j][k];
                        matrix[i][k] %= 10;
                    }
                }
            }
        }

        for (int i = 0; i < n; i++) {
            StringBuilder line = new StringBuilder();
            for (int j = 0; j < n; j++) {
                line.append(ans[i][j]);
            }
            System.out.println(line.toString());
        }
        scanner.close();
    }
}
