import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * @author Ricardo Vasquez
 *
 */
public class Factors {

	public static void main(String[] args) {

		// Initialize
		int integer = 0; 
		
		// Ask user for an integer
		System.out.print("Enter an integer:");

		// Open Scanner
		Scanner scanner = new Scanner(System.in);
		
		// Get Integer
		try {
			integer = scanner.nextInt();
		} catch (InputMismatchException e) {
				System.out.print("Integers Only");
		}
		
		// Close Scanner
		scanner.close();
				
		// Print all factors
		System.out.printf("The factors of %d are: ", integer);
		for(int i = 1; i <= integer; i++) {
			if (integer % i == 0) {
				System.out.print(i + " ");
			}
		}
	}

}
