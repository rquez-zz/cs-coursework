import java.util.Scanner;

public class steps {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();
        int[][] m = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
               m[i][j] = scanner.nextInt();
            }
        }

        int numPaths = scanner.nextInt();

        scanner.nextLine();
        for (int i = 0; i < numPaths; i++) {
            String path = scanner.nextLine();
            solve(path.toCharArray(), m);
        }
    }

    private static void solve(char[] path, int[][] m) {

        int sum = 0;
        for (int i = 0; i < path.length - 1; i++) {
            sum += m[path[i] - 65][path[i+1] - 65];
        }
        System.out.println(sum);
    }
}
