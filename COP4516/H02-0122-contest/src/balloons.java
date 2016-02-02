import java.util.*;

public class balloons {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        int numTeams = scanner.nextInt();
        int numBalloonsA = scanner.nextInt();
        int numBalloonsB = scanner.nextInt();

        while ( numBalloonsA != 0 || numBalloonsB != 0 || numTeams != 0) {

            List<team> teams = new ArrayList();
            for (int i = 0; i < numTeams; i++) {
                teams.add(new team(scanner.nextInt(), scanner.nextInt(), scanner.nextInt()));
            }

            PriorityQueue<team> queue = new PriorityQueue(teams);
            int totalDistance = 0;

            while (!queue.isEmpty()) {

                team t = queue.poll();
                int balloonsNeeded = t.balloonsNeeded;
                int distanceFromA = t.distanceFromA;
                int distanceFromB = t.distanceFromB;

                if (distanceFromA < distanceFromB) {
                    if (numBalloonsA != 0) {
                        if (numBalloonsA > balloonsNeeded) {
                            totalDistance += balloonsNeeded * distanceFromA;
                            numBalloonsA -= balloonsNeeded;
                        } else {
                            totalDistance += numBalloonsA * distanceFromA;
                            balloonsNeeded -= numBalloonsA;
                            numBalloonsA = 0;
                            totalDistance += balloonsNeeded * distanceFromB;
                            numBalloonsB -= balloonsNeeded;
                        }
                    } else {
                        totalDistance += balloonsNeeded * distanceFromB;
                        numBalloonsB -= balloonsNeeded;
                    }
                } else {
                    if (numBalloonsB != 0) {
                        if (numBalloonsB > balloonsNeeded) {
                            totalDistance += (balloonsNeeded * distanceFromB);
                            numBalloonsB -= balloonsNeeded;
                        } else {
                            totalDistance += numBalloonsB * distanceFromB;
                            balloonsNeeded -= numBalloonsB;
                            numBalloonsB = 0;
                            totalDistance += balloonsNeeded * distanceFromA;
                            numBalloonsA -= balloonsNeeded;
                        }
                    } else {
                        totalDistance += balloonsNeeded * distanceFromA;
                        numBalloonsA -= balloonsNeeded;
                    }
                }
            }
            System.out.println(totalDistance);
            numTeams = scanner.nextInt();
            numBalloonsA = scanner.nextInt();
            numBalloonsB = scanner.nextInt();
        }
    }
}

class team implements Comparable {
    int distanceFromA;
    int distanceFromB;
    int balloonsNeeded;

    public team(int balloonsNeeded, int distanceFromA, int distanceFromB) {
        this.balloonsNeeded = balloonsNeeded;
        this.distanceFromA = distanceFromA;
        this.distanceFromB = distanceFromB;
    }

    @Override
    public int compareTo(Object o) {
        team t = (team)o;
        int diff1 = Math.abs(distanceFromA - distanceFromB);
        int diff2 = Math.abs(t.distanceFromA - t.distanceFromB);
        return diff1 < diff2 ? 1 : -1;
    }
}
