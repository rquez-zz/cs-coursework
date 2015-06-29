import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * @author Ricardo Vasquez
 *
 */
public class Deserts {

	public static void main(String[] args) {
		
		//Initialize
		int input = 0;
		double sum = 0.0;
		boolean isBuying = true; 
		char response = 'Y'; 
		
		// Open Scanner
		Scanner scanner  = new Scanner(System.in);

		while (isBuying) {
			
			input = 0;
	
			while (input != 4) {
				
				
				//Print menu
				System.out.println("1. Ice Cream\t$1.50");
				System.out.println("2. Apple Pie\t$2.00");
				System.out.println("3. Cheese Cake\t$3.50");
				System.out.println("4. Exit");			
				System.out.print("Choose one of these desserts: ");
				
				// Get Input
				try {
					input = scanner.nextInt();
				} catch (InputMismatchException ime) {
					System.out.println("Integers Only Please");
				}
				
				// Calculate Sum and Print Message
				switch(input) {
					case 1: 
						sum += 1.50;
						System.out.printf("So far your total is %.2f\n\n", sum);
						break;
					case 2:
						sum += 2.00;
						System.out.printf("So far your total is %.2f\n\n", sum);
						break;
					case 3:
						sum += 3.50; 
						System.out.printf("So far your total is %.2f\n\n", sum);
						break;
					case 4:
						break;
					default:
						System.out.println("Please enter a valid selection");
				}
			}
			
			//Output result
			System.out.printf("Thank you. Your order is $%.2f.\n", sum);
			
			// Prompt user to continue
			System.out.print("Would you like to make another purchase?");
			
			// Get Response
			try {
				response = scanner.next().charAt(0);
			} catch (NoSuchElementException nsee) {
				nsee.printStackTrace();
			} 
			
			// Decide Next Action
			if (response == 'Y') {
				isBuying = true;
				continue;
			} else if (response == 'N') {
				isBuying = false;
				continue;
			} else {
				System.out.println("Not a valid response");
				scanner.nextLine();
			} 
		}
		
		System.out.println("Have a nice day.");
		
		//Close Scanner
		scanner.close();
	}

}
