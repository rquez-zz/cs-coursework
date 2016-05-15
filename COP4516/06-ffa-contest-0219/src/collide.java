import java.util.Scanner;

public class collide {

    private final static long LIMIT = 2147483647;

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        int numVisiblePlanets = scanner.nextInt();
        int solarSystemNum = 1;

        while (numVisiblePlanets > 0) {

            long[] planets = new long[numVisiblePlanets];
            for (int i = 0; i < numVisiblePlanets; i++) {
                planets[i] = scanner.nextLong();
            }

            long days = lcm(planets, 0);

            if (Math.abs(days) < LIMIT)
                System.out.println(solarSystemNum + ": THE WORLD ENDS IN " + days + " DAYS");
            else
                System.out.println(solarSystemNum + ": NOT TO WORRY");

            numVisiblePlanets = scanner.nextInt();
            solarSystemNum++;
        }
    }

    private static long lcm(long[] planets, int i) {

        if (i == planets.length) {
            return 1;
        }
        return lcm(planets[i], lcm(planets, i+1));
    }

    private static long lcm(long a, long b) {
        return (a * b) / gcd(a,b);
    }

    private static long gcd(long a, long b) {
        return b == 0 ? a : gcd(b, a % b);
    }
}
