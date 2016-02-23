import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;

public class perfect {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        int numCases = scanner.nextInt();

        for (int i = 0; i < numCases; i++) {
            long num = scanner.nextLong();
            solve(num);
        }
    }

    private static void solve(long num) {

        long sum = 1;
        int bound = (int) Math.sqrt(num);
        for (int i = 2; i <= bound; i++) {
            if (num % i == 0) {
                sum += i;
                if (i != num / i)
                    sum += (num / i);
            }
        }

        if (sum > num)
            System.out.println("abundant");
        else if (sum < num)
            System.out.println("defective");
        else
            System.out.println("perfect");
    }
}
