import java.util.Scanner;


/**
 * @author Ricardo Vasquez
 *
 */
public class Rectangle {

	public static void main(String[] args) {
		
		// Initialize Variables
		int length_ft = 0, width_ft = 0, sq_ft = 0;
		double acres = 0.0;
		
		// Square Feet to Acre Conversion
		final double SQFT_TO_ACRE = 43560.0;
		
		// Prompt for values
		System.out.print("Enter a length in feet: ");
		
		// Start Scanner
		Scanner scanner = new Scanner(System.in);
		
		// Get length
		try {
			length_ft = scanner.nextInt();
		} catch (java.util.InputMismatchException ime) {
			System.out.print("Integers only");
			scanner.nextLine(); 
		}
				
		// Check if length is negative 
		if (length_ft < 0) {
			System.out.println("Negative numbers can't be converted.");
			System.exit(0);
		}
		
		// Prompt for Width
		System.out.print("Enter a width in feet: " );
		
		// Get Width
		try {
			width_ft = scanner.nextInt();
		} catch (java.util.InputMismatchException ime) {
			System.out.print("Integers Only");
			scanner.nextLine();
		}
		
		// Close Scanner
		scanner.close();
		
		// Check if width is negative 
		if (width_ft < 0) {
			System.out.println("Negative numbers can't be converted.");
			System.exit(0);
		}		
		
		// Calculate the conversion from feet to acres.
		sq_ft = length_ft * width_ft;
		acres = sq_ft / SQFT_TO_ACRE;
		
		// Output
		System.out.printf("A rectangle with dimensions %dft x %dft is %.6f acres.",length_ft, width_ft, acres);
		
	}

}
