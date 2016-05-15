import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;

public class dinner {

    private static long[] table = new long[101];

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        int cases = scanner.nextInt();

        //Arrays.fill(table, -1);
        table[0] = 1;
        for (int i = 0; i < cases; i++) {
            int checkTotal = scanner.nextInt();
            System.out.println("Dinner #" + (i + 1) + ": " + solve(checkTotal));
        }
    }

    public static long solve(int total) {

        if (table[total] != 0) {
           return table[total];
        }

        if (total >= 10) {
            table[total] += solve(total - 10);
        }

        if (total >= 5) {
            table[total] += solve(total - 5);
        }

        if (total >= 2) {
            table[total] += solve(total - 2);
        }

        return table[total];
    }
}
