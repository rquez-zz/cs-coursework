import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * @author Ricardo Vasquez
 *
 */
public class Primes {

	public static void main(String[] args) {
		
		// Open Scanner
		Scanner scanner = new Scanner(System.in);
		
		char response = 'N'; 
		
		do {
		
			// Initialize
			int integer = 0;
			boolean validResponse = false;

			do {
				// Ask user for an integer
				System.out.print("Enter an integer:");
				
				try {
					integer = scanner.nextInt();
					validResponse = true;
				} catch (Exception e) {
					System.out.println("Integers Only");
					scanner.nextLine();
					validResponse = false;
				} 
			} while (!validResponse);
			
								
			if (isPrime(integer)) {
				System.out.printf("%d is a prime number", integer);
			} else {
				System.out.printf("%d is not a prime number", integer);
			}
			
			do {
				// Ask user to continue
				System.out.print("\nContinue? (Y or N):");
								
				try {
					response = scanner.next("n|y").charAt(0);
					validResponse = true;
				} catch (NoSuchElementException nsee) {
					System.out.println("Not a valid response");
					scanner.nextLine();
					validResponse = false;
				} 
			} while (!validResponse);
			
		} while (response == 'Y' || response == 'y');
		
		scanner.close();
		System.out.println("Thank you.");
	}
	
	public static boolean isPrime (int value) {
		
		boolean result = false; 
		
		for (int i = 2; i < (value / 2); i++) {
			if (value % i == 0) {
				result = false;
				i = value; 
			} else {
				result = true;
			}
		}
		
		return result; 
	}

}
