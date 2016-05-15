import java.util.Scanner;

public class russell {

    private static final double EPSILON = 0.01;

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        while (scanner.hasNextDouble()) {

            // Read Yield Sign
            Point[] yield = new Point[4];
            for (int i = 1; i < 4; i++) {
                yield[i] = new Point(scanner.nextDouble(), scanner.nextDouble(), scanner.nextDouble());
            }

            // Read Position of Telescope
            Point telescope = new Point(scanner.nextDouble(), scanner.nextDouble(), scanner.nextDouble());

            // Read Position Russell Wants To Look At
            Point target = new Point(scanner.nextDouble(), scanner.nextDouble(), scanner.nextDouble());

            // Move all points so that the yield sign is at the origin
            translate(yield[2], yield[1]);
            translate(yield[3], yield[1]);
            translate(telescope, yield[1]);
            translate(target, yield[1]);
            translate(yield[1], yield[1]);

            if (Math.abs(yield[3].z) > EPSILON) {

                double sine = (-1) * sine(yield[3].x, yield[3].z);
                double cosine = cosine(yield[3].x, yield[3].z);

                yield[2] = rotateXZ(yield[2], sine, cosine);
                yield[3] = rotateXZ(yield[3], sine, cosine);
                telescope = rotateXZ(telescope, sine, cosine);
                target = rotateXZ(target, sine, cosine);
            }

            if (Math.abs(yield[3].y) > EPSILON) {

                double sine = (-1) * sine(yield[3].x, yield[3].y);
                double cosine = cosine(yield[3].x, yield[3].y);

                yield[2] = rotateXY(yield[2], sine, cosine);
                yield[3] = rotateXY(yield[3], sine, cosine);
                telescope = rotateXY(telescope, sine, cosine);
                target = rotateXY(target, sine, cosine);
            }

            double sine = (-1) * sine(yield[2].y, yield[2].z);
            double cosine = cosine(yield[2].y, yield[2].z);

            yield[2] = rotateYZ(yield[2], sine, cosine);
            yield[3] = rotateYZ(yield[3], sine, cosine);
            telescope = rotateYZ(telescope, sine, cosine);
            target = rotateYZ(target, sine, cosine);

            if (telescope.z * target.z > EPSILON)
                System.out.println("Yes");
            else {
                double distance = Math.abs( telescope.z / (telescope.z - target.z) );
                double x = distance * telescope.x + (1.0 - distance) * target.x;
                double y = distance * telescope.y + (1.0 - distance) * target.y;
                double z = 0.0;

                Point newTarget = new Point(x, y, z);

                if (newTarget.y * yield[2].y < EPSILON)
                    System.out.println("Yes");
                else {

                    newTarget.y = Math.abs(newTarget.y);
                    yield[2].y = Math.abs(yield[2].y);

                    double Slope12 = slope(yield[1], yield[2]);
                    double Slope23 = slope(yield[2], yield[3]);

                    double SlopeS1 = slope(newTarget, yield[1]);
                    double SlopeS3 = slope(newTarget, yield[3]);

                    if ((SlopeS1 > Slope12) && (SlopeS3 < Slope23)) {
                        System.out.println("No");
                    } else {
                        System.out.println("Yes");
                    }
                }
            }
        }
    }

    public static void translate(Point p1, Point p2) {
        p1.x = p1.x - p2.x;
        p1.y = p1.y - p2.y;
        p1.z = p1.z - p2.z;
    }

    public static double sine(double a, double b) {
        return b / Math.sqrt(a * a + b * b);
    }

    public static double cosine(double a, double b) {
        return a / Math.sqrt(a * a + b * b);
    }

    public static Point rotateXY(Point p, double sine, double cosine) {

        double x = p.x * cosine - p.y * sine;
        double y = p.y * cosine + p.x * sine;

        return new Point(x, y, p.z);
    }

    public static Point rotateXZ(Point p, double sine, double cosine) {

        double x = p.x * cosine - p.z * sine;
        double z = p.z * cosine + p.x * sine;

        return new Point(x, p.y, z);
    }

    public static Point rotateYZ(Point p, double sine, double cosine) {

        double y = p.y * cosine - p.z * sine;
        double z = p.z * cosine + p.y * sine;

        return new Point(p.x, y, z);
    }

    public static double slope(Point p1, Point p2) {

        if (Math.abs(p1.y - p2.y) < EPSILON)
            return 0.0;
        else
            return (p1.x - p2.x) / (p1.y - p2.y);
    }

}

class Point {
    double x;
    double y;
    double z;

    public Point(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }
}

