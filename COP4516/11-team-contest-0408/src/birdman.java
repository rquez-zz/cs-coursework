import java.util.*;
import java.awt.geom.Line2D;


public class birdman {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        int numCases = input.nextInt();

        for(int i = 0; i < numCases; i++) {

            System.out.println(Line2D.linesIntersect((double)input.nextInt(),(double)input.nextInt(),(double)input.nextInt(),
                    (double)input.nextInt(),(double)input.nextInt(),(double)input.nextInt(),(double)input.nextInt(),(double)input.nextInt())
                    ? "Move to the left or right!" : "Good picture, Birdman!");
        }

    }
}
