import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.StringTokenizer;

public class walking {

    static int INF = 9999999;

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        int numCases = scanner.nextInt();
        for (int i = 0; i < numCases; i++) {

            // Get Elevations
            int[] elevations = new int[scanner.nextInt()];
            for (int j = 0; j < elevations.length; j++)
                elevations[j] = scanner.nextInt();

            // Build matrix
            int numPaths = scanner.nextInt();
            int[][] matrix = new int[numPaths][numPaths];
            for (int j = 0; j < matrix.length; j++) {
                for (int k = 0; k < matrix.length; k++) {
                    matrix[j][k] = INF;
                }
                matrix[j][j] = 0;
            }
            for (int j = 0; j <= numPaths; j++) {
                StringTokenizer st = new StringTokenizer(scanner.nextLine(), ", ");

                while (st.hasMoreElements()) {
                    String a = st.nextToken().substring(1);
                    String b = st.nextToken();
                    String c = b.substring(0, b.length() - 1);
                    int x = Integer.parseInt(a) - 1;
                    int y = Integer.parseInt(c) - 1;
                    int xElevation = elevations[x];
                    int yElevation = elevations[y];
                    int work = Math.abs(xElevation - yElevation);
                    matrix[x][y] = work;
                    matrix[y][x] = work;
                }
            }

            // Get distances for each vertex
            int[][] distances = floydWarshall(matrix, elevations.length);

            // Get the specified path
            int[] orderedPaths = new int[scanner.nextInt()];
            for (int j = 0; j < orderedPaths.length; j++) {
                orderedPaths[j] = scanner.nextInt() - 1;
            }

            // Calculate the minimum work required
            int work = getMinWork(distances, orderedPaths);

            System.out.println("The least amount of work on trip " + (i+1) + " is: " + work);
        }
    }

    private static int getMinWork(int[][] result, int[] orderedPaths) {
        int work = 0;
        for (int i = 0; i < orderedPaths.length - 1; i++) {
            work += result[orderedPaths[i]][orderedPaths[i+1]];
        }
        return work;
    }

    private static int[][] floydWarshall(int[][] matrix, int vertices) {
        for (int k = 0; k < vertices; k++) {
            for (int i = 0; i < vertices; i++) {
                for (int j = 0; j < vertices; j++) {
                    if (matrix[i][k] + matrix[k][j] < matrix[i][j]) {
                        matrix[i][j] = matrix[i][k] + matrix[k][j];
                    }
                }
            }
        }
        return matrix;
    }
}

