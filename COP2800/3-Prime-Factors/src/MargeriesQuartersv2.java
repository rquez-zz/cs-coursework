import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * @author Ricardo Vasquez
 *
 */
public class MargeriesQuartersv2 {

	public static void main(String[] args) {
		
		// Initialize
		int quartersInJar = 0, timesPlayedSlotSinceWin1 = 0, timesPlayedSlotSinceWin2 = 0, timesPlayedSlotSinceWin3 = 0, attempts = 0;
		char response = 'N'; 
		boolean isPlaying = true, validResponse = false;
		
		//Read in input
		Scanner scanner = new Scanner(System.in);
		
		// Start Game Loop
		while (isPlaying) {
		
			//Reset attempts
			attempts = 0;
			
			do {
				System.out.print("How many quarters  does Marge have in the jar?");

				try {
					quartersInJar = scanner.nextInt();
					validResponse = true;
				} catch (InputMismatchException ime) {
					System.out.println("Integers Only Please.");
					scanner.nextLine();
					validResponse = false; 
				} 
			} while (!validResponse);

			
			do {

				System.out.print("How many times has the first machine been played since paying out?");
		
				try {
					timesPlayedSlotSinceWin1 = scanner.nextInt();
					validResponse = true; 
				} catch (InputMismatchException ime) {
					System.out.println("Integers Only Please.");
					scanner.nextLine();
					validResponse = false; 
				}
			} while (!validResponse);

			
			do {

				System.out.print("How many times has the second machine been played since paying out?");
				
				try {
					timesPlayedSlotSinceWin2 = scanner.nextInt();
					validResponse = true;
				} catch (InputMismatchException ime) {
					System.out.println("Integers Only Please.");
					scanner.nextLine();
					validResponse = false;
				}
			} while (!validResponse);
			
			do {

				System.out.print("How many times has the third machine been played since paying out?");
		
				try {
					timesPlayedSlotSinceWin3 = scanner.nextInt();
					validResponse = true;
				} catch (InputMismatchException ime) {
					System.out.println("Integers Only Please.");
					scanner.nextLine();
					validResponse = false; 
				}
			} while (!validResponse);

			
			do {

				System.out.print("Dislay losses? (Y or N): ");
				
				try {
					response = scanner.next().charAt(0);
					validResponse = true; 
				} catch (NoSuchElementException nsee) {
					System.out.println("Invalid Response.");
					scanner.nextLine();
					validResponse = false;
				}
			} while (!validResponse);

							
			while(quartersInJar != 0) {
				
				// Play the 1st Machine
				timesPlayedSlotSinceWin1++;
				attempts++;
				quartersInJar--;
				
				// If the times played is 35 then add 30 quarters to the jar
				if (timesPlayedSlotSinceWin1 == 35) {
					quartersInJar+= 30;
					System.out.printf("M1 - Win - %d quarters left\n", quartersInJar);
					timesPlayedSlotSinceWin1 = 0;
				} else {
					if (response == 'Y')
						System.out.printf("M1 - Lose - %d quarters left\n", quartersInJar);
		     	}
				
				// Continue to machine 2 only if there's quarters
				if (quartersInJar > 0) {
				
					// Play the 2nd Machine
					timesPlayedSlotSinceWin2++;
					attempts++;
					quartersInJar--;
					
					//If the times played is 100 then add 70 quarters to the jar
					if (timesPlayedSlotSinceWin2 == 100) {
						quartersInJar+= 70;
						System.out.printf("M2 - Win - %d quarters left\n", quartersInJar);
						timesPlayedSlotSinceWin2 = 0;
					} else {
						if (response == 'Y')
							System.out.printf("M2 - Lose - %d quarters left\n", quartersInJar);
					}
					
					// Continue only if there's quarters
					if (quartersInJar > 0) {
					
						// Play the 3rd Machine
						timesPlayedSlotSinceWin3++;
						attempts++;
						quartersInJar--;
						
						//If the times played is 10 then add 9 quarters
						if (timesPlayedSlotSinceWin3 == 10) {
							quartersInJar += 11;
							System.out.printf("M3 - Win - %d quarters left\n", quartersInJar);
							timesPlayedSlotSinceWin3 = 0;
						} else {
							if (response == 'Y')
								System.out.printf("M3 - Lose - %d quarters left\n", quartersInJar);
						}
					}
				}
			}
					
			//Output number of tries
			System.out.printf("Game over. Margerie played %d times.\n", attempts); 
			
			do {  

				// Prompt to Play Again
				System.out.print("Play Again? (Y or N)"); 
				
				try {
					response = scanner.next().charAt(0);
					validResponse = true;
				} catch (NoSuchElementException nsee) {
					System.out.print("Not a valid response.");
					scanner.nextLine();
					validResponse = false;
				}
				
			} while (!validResponse);
			
			
			if (response == 'Y') {
				isPlaying = true;
			} else if (response == 'N') {
				isPlaying = false; 
				scanner.close();
				System.out.println("Thanks for playing.");
			}
		} // End Game Loop
	}
}
