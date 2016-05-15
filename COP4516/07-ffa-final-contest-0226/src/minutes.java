import java.util.Scanner;

public class minutes {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        int numCases = scanner.nextInt();

        for (int i = 0; i < numCases; i++) {

            int d = scanner.nextInt();
            int h = scanner.nextInt();
            int m = scanner.nextInt();

            System.out.println(m + (h*60) + (d * 24 * 60));
        }
    }
}
