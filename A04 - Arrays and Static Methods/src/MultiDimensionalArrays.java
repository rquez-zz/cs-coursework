import java.text.DecimalFormat;
import java.util.Random;


/**
 * @author Ricardo Vasquez
 *
 */
public class MultiDimensionalArrays {

	public static void main(String[] args) {
		
		Random r = new Random();
		
		double[][] grades;
		grades = new double[4][];
		grades[0] = new double[3];
		grades[1] = new double[5];
		grades[2] = new double[6];
		grades[3] = new double[2];
		
		DecimalFormat f = new DecimalFormat("##.00");

		for (int row = 0; row < grades.length; row++) {
			for (int col = 0; col < grades[row].length; col++) {
				grades[row][col] = r.nextDouble() * 100.0;
			}
		}
		for (int row = 0; row < grades.length; row++) {
			for (int col = 0; col < grades[row].length; col++) {
				System.out.print(f.format(grades[row][col]) + "\t");
			}
			System.out.println();
		}

	}

}
