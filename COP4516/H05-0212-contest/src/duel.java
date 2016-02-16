import java.io.IOException;
import java.util.*;

public class duel {

    private static boolean [] [] matrix;
    private static int [] degree;
    private static int size;
    private static boolean foundMultipleArrangements;

    private static boolean allAssigned() {
        for( int x : degree) {
            if (x != -1)
                return false;
        }
        return true;
    }

    private static int findNext() {
        int count = 0;
        int result = -1;
        for( int i = 0; i < size; ++i ) {
            if( degree[i] == 0 ) {
                result = i;
                foundMultipleArrangements = foundMultipleArrangements || (count > 0);
                ++count;
            }
        }
        return result;
    }

    private static void assign( int u ) {
        for(int d = 0; d < size; ++d) if(matrix[d][u]) --degree[d];
        degree[u] = -1;
    }

    private static int solve() {
        while(!allAssigned() ) {
            int x = findNext();
            if( x == -1 )
                return 0;
            assign(x);
        }
        return foundMultipleArrangements ? 2 : 1;
    }

    public static void main( String[] args ) {

        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();
        int m = scanner.nextInt();
        while(n != 0 && m != 0) {

            size = n;
            matrix = new boolean[size][size];
            degree = new int[size];
            foundMultipleArrangements = false;
            for(int i = 0; i < size; i++) {
                degree[i] = 0;
                for(int j = 0; j < size; j++) {
                    matrix[i][j] = false;
                }
            }

            for(int i = 0; i < m; ++i) {
                int d = scanner.nextInt() - 1;
                int u = scanner.nextInt() - 1;
                matrix[d][u] = true;
                degree[d]++;
            }

            System.out.println(solve());

            n = scanner.nextInt();
            m = scanner.nextInt();
        }
    }
}