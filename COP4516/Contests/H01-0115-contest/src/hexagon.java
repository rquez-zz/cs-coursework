import java.util.Scanner;

public class hexagon {

    private final static int HEXES = 7;
    private final static int HEXSIDE = 6;

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int numCases = scanner.nextInt();

        // Array of Hexagon Sets
        int[][][] cases = new int[numCases][][];

        for (int i = 0; i < numCases; i++) {

            int[][] hexagons = new int[HEXES][];
            for (int j = 0; j < HEXES; j++) {

                int[] hexagon = new int[HEXSIDE];
                for (int k = 0; k < HEXSIDE; k++) {
                    hexagon[k] = scanner.nextInt();
                }
                hexagons[j] = hexagon;
            }
            cases[i] = hexagons;
        }

        for (int i = 0; i < cases.length; i++) {

            String solution = null;

            int[] arrangement = new int[cases[i].length];
            for (int j = 0; j < cases[i].length; j++)
                arrangement[j] = j;

            solution = permutateArrangements(cases[i], arrangement, 0, i+1);

            if (solution != null)
                System.out.println("Case " + (i+1)  + ": " + solution);
            else
                System.out.println("Case " + (i+1)  + ": No solution");
        }
    }

    public static String permutateArrangements(int[][]hexagons, int[] arrangement, int k, int caseNum) {

        String solution = null;

        if (k == hexagons.length) {
            rotateNumToPosition(hexagons, 0, 1, 0);
            rotateNumToPosition(hexagons, 1, hexagons[0][0], 3);
            rotateNumToPosition(hexagons, 2, hexagons[0][1], 4);
            rotateNumToPosition(hexagons, 3, hexagons[0][2], 5);
            rotateNumToPosition(hexagons, 4, hexagons[0][3], 0);
            rotateNumToPosition(hexagons, 5, hexagons[0][4], 1);
            rotateNumToPosition(hexagons, 6, hexagons[0][5], 2);
            return verify(hexagons, arrangement);
        }

        for (int i = k; i < HEXES; i++) {
            swap(arrangement, i, k);
            swap(hexagons, i, k);
            solution = permutateArrangements(hexagons, arrangement, k+1, caseNum);
            if (solution != null)
                return solution;
            swap(arrangement, k, i);
            swap(hexagons, k, i);
        }

        return solution;
    }

    public static void rotateNumToPosition(int[][] hexagons, int hexagon, int num, int position) {
        while (hexagons[hexagon][position] != num)
            rotateClockwise(hexagons[hexagon]);
    }

    public static String verify(int[][] hexagons, int[] arrangement) {

        if (
                // Center Piece
                hexagons[0][0] == hexagons[1][3] &&
                hexagons[0][1] == hexagons[2][4] &&
                hexagons[0][2] == hexagons[3][5] &&
                hexagons[0][3] == hexagons[4][0] &&
                hexagons[0][4] == hexagons[5][1] &&
                hexagons[0][5] == hexagons[6][2] &&
                // Top Piece
                hexagons[1][4] == hexagons[6][1] &&
                hexagons[1][2] == hexagons[2][5] &&
                // 2th Piece
                hexagons[2][3] == hexagons[3][0] &&
                // 3rd Piece
                hexagons[3][4] == hexagons[4][1] &&
                // 4th Piece
                hexagons[4][5] == hexagons[5][2] &&
                // 5th Piece
                hexagons[5][0] == hexagons[6][3]
                ) {
            return stringify(arrangement);
        } else
            return null;
    }

    public static String stringify(int[] arrangement) {
        StringBuilder sb = new StringBuilder();
        for (int i : arrangement) {
            sb.append(i + " ");
        }
        return sb.toString();
    }

    public static void rotateClockwise(int[] hexagon) {

        int last = hexagon[hexagon.length - 1];

        int temp = hexagon[0];
        for (int i = 0; i < hexagon.length - 1; i++) {
            int loopTemp = hexagon[i+1];
            hexagon[i+1] = temp;
            temp = loopTemp;
        }
        hexagon[0] = last;
    }

    public static void swap(int[] arrangement, int a, int b) {
        int temp = arrangement[a];
        arrangement[a] = arrangement[b];
        arrangement[b] = temp;
    }

    public static void swap(int[][] hexagons, int a, int b) {
        int[] temp = hexagons[a];
        hexagons[a] = hexagons[b];
        hexagons[b] = temp;
    }
}
