import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * @author Ricardo Vasquez
 *
 */
public class Loops {

	public static void main(String[] args) {
		
		// A. 
		int whileCounter = 1; 
		while ( whileCounter < 26 ){
			System.out.println(whileCounter++);
		}
		
		System.out.print("\n");
		
		// B. 
		whileCounter = 0;
		while ( whileCounter < 126) { 
			if (whileCounter % 7 == 0) {
				System.out.println(whileCounter);
			}
			whileCounter++;
		}
		
		System.out.print("\n");
		
		// C. 
		int sum = 0;
		for(int i = 234; i < 19349; i++ ) {
			sum += i;
		}
		System.out.println(sum);
		
		System.out.print("\n");
		
		// D. 
		//Start Scanner 
		Scanner scanner = new Scanner(System.in);

		int value = 0, sumInputs = 0;
		
		// Sentinel loop with sentinel value of -999
		while (value != -999){
			
			System.out.print("Enter a Value (-999 exits): ");
				
				try {
					value = scanner.nextInt();
				} catch(InputMismatchException ime) {
					System.out.println("Integers only");
					value = 0;
					//Skip this input
					scanner.nextLine();
				}
		
			
			// Don't add the sentinel to sum if inputed.
			sumInputs += (value != -999 ? value : 0);
		}
		
		scanner.close();
			
		System.out.println("The sum is " + sumInputs);

		
		
		// E.
		long result = 1;
		int base = 2;
		/*
		 * Loop until result becomes a negative number. 
		 * Java outputs a negative number when long overflows.
		 */
		for(int j = 0; result > 0; j++) {
			
			/*
			 * First iteration is 1 since 2^0 is 1
			 */
			System.out.printf("%d to the power of %d is %d.\n", base, j, result);
			
			result *= base;
			
			//Print when result becomes negative
			System.out.print((result < 0 ? "Long variable limit has been reached" : ""));
		}
		
	}

}
	