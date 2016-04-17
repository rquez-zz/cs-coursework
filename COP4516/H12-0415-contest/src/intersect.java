import java.util.Scanner;

public class intersect {

    private static final double EPSILON = 1e-6;

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        int numCases = scanner.nextInt();

        for (int i = 0; i < numCases; i++) {

            Point3D pLine1 = new Point3D(scanner.nextInt(), scanner.nextInt(), scanner.nextInt());
            Point3D pLine2 = new Point3D(scanner.nextInt(), scanner.nextInt(), scanner.nextInt());
            Point3D pPlane1 = new Point3D(scanner.nextInt(), scanner.nextInt(), scanner.nextInt());
            Point3D pPlane2 = new Point3D(scanner.nextInt(), scanner.nextInt(), scanner.nextInt());
            Point3D pPlane3 = new Point3D(scanner.nextInt(), scanner.nextInt(), scanner.nextInt());

            System.out.println("Data Set #" + (i+1) + ":");
            intersection(pLine1, pLine2, pPlane1, pPlane2, pPlane3);

        }
    }

    public static void intersection(Point3D R1, Point3D R2, Point3D S1, Point3D S2, Point3D S3) {

        Point3D dS21 = S2.subtract(S1);
        Point3D dS31 = S3.subtract(S1);
        Point3D n = dS21.cross(dS31);

        Point3D dR = R2.subtract(R1);

        double ndotdR = n.dotProduct(dR);

        if (Math.abs(ndotdR) < EPSILON) {

            // ax + by + cz + d  = 0
            double d = (-1) * ((n.x * S1.x) + (n.y * S1.y) + (n.z * S1.z));

            // Determine if line lies in plane
            boolean isZero = (((R1.x * n.x) + (R1.y * n.y) + (R1.z * n.z) + d) == 0);

            if (isZero) {
                System.out.println("The line lies on the plane.\n");
            } else {
                System.out.println("There is no intersection.\n");
            }
            return;
        }

        double t =  -n.dotProduct(R1.subtract(S1)) / ndotdR;
        Point3D intersection = R1.add(dR.scale(t));

        System.out.printf("The intersection is the point (%.1f, %.1f, %.1f).\n\n",
                (float)intersection.x, (float)intersection.y, (float)intersection.z);

    }

}

class Point3D {

    double x;
    double y;
    double z;

    public Point3D(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Point3D add(Point3D other) {
        return new Point3D(other.x + x, other.y + y, other.z + z);
    }

    public Point3D scale(double mag) {
        return new Point3D(x * mag, y * mag, z * mag);
    }

    public double dotProduct(Point3D other) {
        return (other.x * x) + (other.y * y) + (other.z * z);
    }

    public Point3D subtract(Point3D other) {
        return new Point3D(x - other.x, y - other.y, z - other.z);
    }

    public Point3D cross(Point3D v) {
        double xu = (this.y * v.z - this.z * v.y);
        double yu = (this.z * v.x - this.x * v.z);
        double zu = (this.x * v.y - this.y * v.x);
        return new Point3D(xu, yu, zu);
    }
}
