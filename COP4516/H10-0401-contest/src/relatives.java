import java.text.DecimalFormat;
import java.util.*;

public class relatives {

    private static HashMap<String, Double> map = new HashMap<>();

    public static void main(String... arg) {

        Scanner scanner = new Scanner(System.in);
        int numCases = scanner.nextInt();

        for (int i = 0; i < numCases; i++) {

            int locations = scanner.nextInt();
            double matrix[][] = new double[locations][locations];

            for (int j = 0; j < locations; j++) {
                for (int k = 0; k < locations; k++) {
                    matrix[j][k] = scanner.nextDouble();
                }
            }

            double result = solve(matrix, null, 1, 0);
            DecimalFormat df = new DecimalFormat("##.00");
            System.out.println(df.format(result));
            map.clear();
        }
    }

    public static double solve(double[][] matrix, boolean[] visited, int visitedCount, int location) {

        if (visitedCount == matrix.length) {
            return matrix[location][0];
        }

        if (visited == null) {
            visited = new boolean[matrix.length];
            visited[0] = true;
        }

        double minSum = Double.MAX_VALUE;

        for (int i = 0; i < matrix.length; i++) {

            if (!visited[i] && matrix[location][i] != 0.0) {

                String visitedString = visitedString(visited, i);
                visited[i] = true;

                if (map.containsKey(visitedString)) {
                    minSum = Math.min(minSum, map.get(visitedString));
                } else {
                    double sum = matrix[location][i] + solve(matrix, visited, visitedCount + 1, i);
                    map.put(visitedString, sum);
                    minSum = Math.min(minSum, sum);
                }
                visited[i] = false;
            }
        }

        return minSum;
    }

    public static String visitedString(boolean[] visited, int current) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < visited.length; i++) {
            if (visited[i])
                sb.append(i);
        }
        return sb.toString() + "," + current;
    }
}

