import java.util.Arrays;
import java.util.Scanner;

public class fermat {

    private final static boolean[] PRIMES = primeSieve(1000000);

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        int l = scanner.nextInt();
        int u = scanner.nextInt();

        while (l != -1 || u != -1) {

            int originalU = u;
            int originalL = l;

            int lPrimeCount = 0;
            int uPrimeCount = 0;

            if (l < 0 && u < 0) { // Both Negative, do nothing
            } else if ((l ^ u) < 0) { // Different Signs

                // L must be the negative number
                // Count from 0 to U
                int[] count = getCount(0, u, PRIMES);
                lPrimeCount = count[0];
                uPrimeCount = count[1];
            } else { // Both Positive

                // Count from |U| to |L|
                u = Math.abs(u);
                l = Math.abs(l);
                int[] count = getCount(Math.min(u, l), Math.max(u,l), PRIMES);
                lPrimeCount = count[0];
                uPrimeCount = count[1];
            }

            System.out.format("%d %d %d %d\n", originalL, originalU, lPrimeCount, uPrimeCount);

            l = scanner.nextInt();
            u = scanner.nextInt();
        }
    }

    private static int[] getCount(int start, int end, boolean[] sieve) {
        int uCount = 0;
        int lCount = 0;

        for (int p = start; p <= end; p++) {
            if (sieve[p]) {
                lCount++;
            }
            if (sieve[p] && p % 2 == 1 && (p - 1) % 4 == 0) {
                uCount++;
            }
        }

        if (start <= 2 && end >= 2) {
            uCount++;
        }

        return new int[]{lCount, uCount};
    }

    public static boolean[] primeSieve(int n) {
        boolean[] isPrime = new boolean[n+1];
        Arrays.fill(isPrime, true);
        isPrime[0]= false;
        isPrime[1] = false;

        for (int i = 2; (i * i) <= n; i++) {
            for (int j = (2 * i); j <= n; j+=i)
                    isPrime[j] = false;
        }

        return isPrime;
    }
}
