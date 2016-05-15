import sun.awt.image.ImageWatched;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Scanner;

public class relatives {

    static int INF = 9999999;

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        int numPeople = scanner.nextInt();
        int numRelations = scanner.nextInt();
        int n = 0;

        while (numPeople != 0 && numRelations != 0) {

            // Build Initial Matrix
            int[][] matrix = new int[numPeople][numPeople];
            for (int i = 0; i < matrix.length; i++) {
                for (int j = 0; j < matrix.length; j++) {
                    matrix[i][j] = INF;
                }
                matrix[i][i] = 0;
            }

            // Get Relations
            LinkedHashMap<String, Integer> hashMap = new LinkedHashMap<>();
            int k = 0;
            for (int i = 0; i < numRelations; i++) {
                String x = scanner.next();
                if (!hashMap.containsKey(x))
                    hashMap.put(x, k++);
                String y = scanner.next();
                if (!hashMap.containsKey(y))
                    hashMap.put(y, k++);
                matrix[hashMap.get(x)][hashMap.get(y)] = 1;
                matrix[hashMap.get(y)][hashMap.get(x)] = 1;
            }

            floydWarshall(matrix);
            int maxDegree = getMaxDegree(matrix);
            if (maxDegree == INF)
                System.out.println("Network " + (n+1) + ": DISCONNECTED\n");
            else
                System.out.println("Network " + (n + 1) + ": " + maxDegree + "\n");
            n++;

            numPeople = scanner.nextInt();
            numRelations = scanner.nextInt();
        }
    }

    private static int getMaxDegree(int[][] matrix) {
        int max = 0;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                max = Math.max(matrix[i][j], max);
            }
        }
        return max;
    }

    private static int[][] floydWarshall(int[][] matrix) {
        for (int k = 0; k < matrix.length; k++) {
            for (int i = 0; i < matrix.length; i++) {
                for (int j = 0; j < matrix.length; j++) {
                    if (matrix[i][k] + matrix[k][j] < matrix[i][j]) {
                        matrix[i][j] = matrix[i][k] + matrix[k][j];
                    }
                }
            }
        }
        return matrix;
    }
}
