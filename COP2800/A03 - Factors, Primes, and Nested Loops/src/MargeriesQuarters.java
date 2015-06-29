import java.util.InputMismatchException;
import java.util.Scanner;


/**
 * @author Ricardo Vasquez
 *
 */
public class MargeriesQuarters {

	public static void main(String[] args) {
				
		//Initialize
		int quartersInJar = 0, timesPlayed = 0, attempts = 0;
		boolean validResponse = false; 


		//Read in input
		Scanner scanner = new Scanner(System.in);
	
		// Prompt until response is valid
		while (!validResponse) {
			
			// Prompt quarters in jar
			System.out.print("How many quarters  does Marge have in the jar?");
			
			try {
				quartersInJar = scanner.nextInt();
				validResponse = true; 
			} catch (InputMismatchException ime) {
				System.out.println("Integers only please");
				validResponse = false; 
				scanner.nextLine();			}
		}
		
		// Reset boolean
		validResponse = false; 
		
		// Prompt until response is valid 
		while (!validResponse) {
			
			// Prompt times played 
			System.out.print("How many times has the machine been played?"); 
			
			try {
				timesPlayed = scanner.nextInt();
				validResponse = true;
			} catch (InputMismatchException ime) {
				System.out.println("Integers only please");
				validResponse = false;
				scanner.nextLine();
			}
		}
		
		//Close Scanner
		scanner.close();
		
		// Play the game while there's quarters in the jar
		while(quartersInJar != 0){
			
			/*
			 * Augment the the number of times played and attempts
			 * Reduce the number of quarters in the jar
			 */
			timesPlayed++;
			attempts++;
			quartersInJar--;
			
			/*
			 * When the player plays 3 times, it's a win. 
			 * So add 2 quarters to the jar, an extra one 
			 * 	to make up for the one played. 
			 * Then reset the times played
			 */
			if (timesPlayed == 3) {
				quartersInJar+= 2;
				
				System.out.printf("Win - %d quarters left\n", quartersInJar);
				
				timesPlayed = 0;
				
			} else {
				System.out.printf("Lose - %d quarters left\n", quartersInJar);
			}
		}
		
		//Output number of tries
		System.out.printf("Game over. Margerie played %d times.", attempts); 
		
	}

}
