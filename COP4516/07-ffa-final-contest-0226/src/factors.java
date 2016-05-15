import java.util.*;

public class factors {

    private static final long LIMIT = (long) Math.pow(10,15);
    private static HashMap<Integer, HashSet<Integer>> CACHE = new HashMap<>();
    private final static boolean[] PRIMES = primeSieve(100000);

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        int numCases = scanner.nextInt();

        for (int i = 0; i < numCases; i++) {

            int minFactors = scanner.nextInt();

            for (int k = 2; k < LIMIT; k++) {

                if (!CACHE.containsKey(k))
                    CACHE.put(k, factorize(k, minFactors));

                HashSet<Integer> factors = CACHE.get(k);

                if (factors != null && factors.size() == minFactors) {
                    System.out.println(k);
                    break;
                }
            }
        }
    }

    public static HashSet<Integer> factorize(int num, int min) {

        HashSet<Integer> factors = new HashSet<>();
        int primeCount = 0;
        int bound = (int) Math.sqrt(num);
        for (int i = bound; i >= 2; i--) {
            if (num % i == 0) {
                if (PRIMES[i]) primeCount++;
                if (primeCount > 2) return null;
                if (CACHE.containsKey(i)) {
                    HashSet<Integer> subFactors = CACHE.get(i);
                    if (subFactors != null)
                        factors.addAll(subFactors);
                    else
                        return null;
                } else {
                    factors.add(i);
                }
                factors.add(num / i);
                if (factors.size() > min) return null;
            }
        }

        factors.add(1);
        factors.add(num);
        return factors;
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
