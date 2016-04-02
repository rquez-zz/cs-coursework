import java.util.HashMap;
import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.Scanner;

public class christmas {

    static HashMap<Integer,Long> daySums = new HashMap<>();
    static HashMap<Integer,Long> triangleNumbers = new HashMap<>();

    public static void main(String[] args) {

        //fillMap();

        Scanner scanner = new Scanner(System.in);

        int day = scanner.nextInt();

        while (day != 0) {
            System.out.println(sum(day));
            day = scanner.nextInt();
        }
    }

    public static long getTriangleNumber(int n) {

        if (n == 1) return 1;

        if (triangleNumbers.containsKey(n)) {
            return triangleNumbers.get(n);
        } else {
            long out = n + getTriangleNumber(n - 1);
            triangleNumbers.put(n, out);
            return out;
        }
    }

    public static long sum(int days) {

        if (days == 1) return 1;

        if (daySums.containsKey(days)) {
            return daySums.get(days);
        }

        long sum = 0;
        for (int i = 1; i <= days; i++) {
            sum += getTriangleNumber(i);
            daySums.put(i, sum);
        }

        return sum;
    }

    public static void fillMap() {
        for (int i = 5000; i < 1000001; i += 500) {
            getTriangleNumber(i);
        }
    }
}
