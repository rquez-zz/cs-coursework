import java.text.DecimalFormat;
import java.util.Scanner;

public class mice {

    public static void main(String[] args) {

        Scanner fin = new Scanner(System.in);

        int numCases = fin.nextInt();

        DecimalFormat df = new DecimalFormat("##.00");

        for (int i = 1; i <= numCases; i++) {

            int radiusPond = fin.nextInt();
            int radiusHole = fin.nextInt();
            int xCenter = fin.nextInt();
            int yCenter = fin.nextInt();

            double distCenter = Math.sqrt(xCenter*xCenter + yCenter*yCenter);

            System.out.println("Pond #" + i + ":");

            if (distCenter >= radiusPond/Math.sqrt(5) + radiusHole) {
                double calc = 8.0 * radiusPond * radiusPond / 5;
                System.out.println("ICE CLEAR!!! " + df.format(calc) + "\n");
            } else {
                double x = distCenter - radiusHole;
                double y = quadraticEq(2, (-2*x), (x*x-radiusPond*radiusPond));
                double calc = 2 * y * y;
                System.out.println("OBSTRUCTION! " + df.format(calc) + "\n");
            }
        }
    }

    public static double quadraticEq(double a, double b, double c) {
        return (-b + Math.sqrt(b * b-4 * a * c)) / (2 * a);
    }
}
