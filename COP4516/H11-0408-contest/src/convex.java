import java.util.ArrayList;
import java.util.Scanner;

class Point {

    public int x;
    public int y;
    public double radsAngle;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void setRadsAngle(Point origin) {
        radsAngle = getAngle(origin);
    }

    public double getAngle(Point origin) {
        if (this.equals(origin))
            return 0;
        else {
            double tmp = Math.atan2(this.y-origin.y, this.x-origin.x);
            if (tmp <= 0)
                tmp += (2*Math.PI);
            return tmp;
        }
    }

    public double dist(Point other) {
        return Math.sqrt(Math.pow(this.x-other.x,2) + Math.pow(this.y-other.y,2));
    }
}

public class convex {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        int numCases = scanner.nextInt();

        for (int i = 1; i <= numCases; i++) {

            scanner.nextInt();
            int numPoints = scanner.nextInt();

            Point[] points = new Point[numPoints];
            for (int j = 0; j < numPoints; j++)
                points[j] = new Point(scanner.nextInt(),scanner.nextInt());

            solve(points, i);
        }

    }

    public static void solve(Point[] points, int i) {

        ArrayList<Point> convexHull = new ArrayList<Point>();

        int prevPoint = getStartingPoint(points);
        int startPoint = prevPoint;
        convexHull.add(points[prevPoint]);
        int nextPoint = getNextPoint(points, prevPoint, 2*Math.PI+.1);

        while (nextPoint != startPoint) {
            convexHull.add(points[nextPoint]);
            double angle = points[nextPoint].getAngle(points[prevPoint]);
            prevPoint = nextPoint;
            nextPoint = getNextPoint(points, nextPoint, angle);
        }

        System.out.println(i + " " + convexHull.size());

        for (int j = 0; j < convexHull.size(); j++)
            System.out.println(convexHull.get(j).x + " " + convexHull.get(j).y);
    }

    public static int getNextPoint(Point[] points, int currentPoint, double maxAngle) {

        for (int i = 0; i < points.length; i++)
            points[i].setRadsAngle(points[currentPoint]);

        int	bestChoice = -1;
        double currentAngle = -1;

        for (int i = 0; i < points.length; i++) {
            if (i == currentPoint) continue;
            if (points[i].radsAngle > maxAngle - (1e-10)) continue;
            if (bestChoice == -1) {
                bestChoice = i;
                currentAngle = points[i].radsAngle;
            } else if ( points[i].radsAngle > currentAngle +1e-10) {
                bestChoice = i;
                currentAngle = points[i].radsAngle;
            } else if (Math.abs(points[i].radsAngle - currentAngle) < 1e-10) {
                if (points[i].dist(points[currentPoint]) > points[bestChoice].dist(points[currentPoint])) {
                    bestChoice = i;
                    currentAngle = points[i].radsAngle;
                }
            }
        }

        return bestChoice;
    }

    public static int getStartingPoint(Point[] all) {

        int best = 0;
        for (int i = 1; i < all.length; i++) {
            if (all[i].y > all[best].y)
                best = i;
            else if (all[i].y == all[best].y && all[i].x < all[best].x)
                best = i;
        }
        return best;
    }
}

