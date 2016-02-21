import java.util.Scanner;

public class profits {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        int numDays = scanner.nextInt();

        while (numDays > 0) {
            int[] days = new int[numDays];
            for (int i = 0; i < numDays; i++) {
                days[i] = scanner.nextInt();
            }
            solve(days);
            numDays = scanner.nextInt();
        }
    }

    private static void solve(int[] days) {
        int max = days[0] < 0 ? days[0] : 0;
        int sum = 0;

        for (int j = 0; j < days.length; j++) {

            sum += days[j];
            if (sum > max) {
                max = sum;
            } else if (sum < 0) {
                sum = 0;
            }
        }

        System.out.println(max);
    }
}
