import java.io.InputStreamReader;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * @author Ricardo Vasquez
 *
 */
public class SoftSales {
	
	public static void main(String[] args) {
	
		// Initialize variables
		double discount, total, subtotal, quantity = 0; 	
		
		// Read user quantity of packages
		System.out.print("Enter the number of packages purchased: ");
		
		// Open Scanner
		Scanner scanner = new Scanner(new InputStreamReader(System.in));
		
		// Get quantity integer
		try { 
			quantity = scanner.nextInt();
		} catch (InputMismatchException ime) {
			System.out.print("Integers Only");
		}
		
		// Close Scanner
		scanner.close();
		
		// Determine Discount using else-if
		if (quantity >= 100) {
			discount = .49;
		} else if (quantity >= 50) {
			discount = .42;
		} else if (quantity >= 20) {
			discount = .33; 
		} else if (quantity >= 10) {
			discount = .20;
		} else {
			discount = 0;
		}
		
		// Calculate the discount
		subtotal = 99.0 * quantity;
		total = subtotal - (discount * subtotal);
		
		//Print results
		System.out.printf("Subtotal (Without Discount): $%.2f\n", subtotal);
		System.out.printf("Discount: %.0f%%\n", (discount * 100.00));
		System.out.printf("Total (Discount Applied): $%.2f\n",total);
		
	}

}
