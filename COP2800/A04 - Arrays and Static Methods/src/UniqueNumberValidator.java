import java.util.Scanner;

/**
 * @author Ricardo Vasquez
 *
 */
public class UniqueNumberValidator {

	public static void main(String[] args) {
		
		final int UNIQUE_LIMIT = 5;
		
		Scanner scanner = new Scanner(System.in);
		
		// Initialize
		boolean validResponse = false; 
		int userInt = 0, validCounter = 0;
		int[] userIntArray = new int[5];
		
		//Loop until 5 valid integers have been inputed
		while(validCounter < 5) {
			
			do {
				
				//Prompt user for integer
				System.out.print("Enter an integer [50 - 100]: ");
				
				try {
					userInt = scanner.nextInt();
					validResponse = true;
				} catch (java.util.InputMismatchException ime) {
					System.out.println("Integers only please.");
					scanner.nextLine();
					validResponse = false; 
				}
			
			} while (!validResponse); 
			
	
			//Validate if user_int is between [50,100]
			if (isValid(userInt)){
				
				//Augment the valid amount of numbers
				validCounter++;
				
				/*
				 * Store the valid integer in the array
				 * validCounter also refers to the index for 
				 * the userInt to be placed in.
				 */
				userIntArray[validCounter - 1] = userInt;
				
				//Check for uniqueness
				if (!isUnique(userIntArray, userInt)){
					System.out.println("That is not unique.");
				}
				
				/*
				 * Display unique integers so far. 
				 * This method will print a closing message
				 * 	if the array is filled. 
				 */
				displayUniques(userIntArray, UNIQUE_LIMIT);
				
			} else {
				
				//Integer is not valid, display it
				errorMessage();
			}
		} //End While Loop
		
		//Close Scanner 
		scanner.close();
		
	} // End main
	
	/**
	 * @param num The integer to check if in [50,100]
	 * @return boolean showing validity
	 */
	public static boolean isValid(int num) {
		if (num >= 50 && num <= 100){
			return true;
		} else {
			return false;
		}
	} // End isValid
	
	/**
	 * @param array The integer array with inputed values
	 * @param lastInputedInt The integer to bounce off the array
	 * @return Returns T/F depending if it already exists in 
	 * 				the array 
	 */
	public static boolean isUnique(int[] array, int lastInputedInt) {
		
		// Get the amount of valid elements in the array
		int validSize = findValidSize(array);
		
		/*
		 * Compare the lastInputInt with the valid elements in the array
		 * excluding the last element, so it doesn't compare itself
		 */
		for(int i = 0; i < (validSize -1); i++) {
			
			//If a match is found, then lastInputedInt is not unique
			if (lastInputedInt == array[i]){
				return false; 
			} 
		}
		
		//If the loop doesn't return a false, the lastInputedInt is unique
		return true;

	} // end isUnique
	
	public static void displayUniques(int[] array, int uniqueLimit) {
		
		/*
		 * Create an array with a size that is equal
		 * to the valid elements in the intUserArray
		 */
		int size = findValidSize(array);
		int[] uniqueInt = new int[size];
		
		// Counts the unique elements
		int uniqueCounter = 0;
	
		/*
		 * Iterate the loop for the amount of 
		 * elements in the uniqueInt array. 
		 */
		for(int i = 0; i < uniqueInt.length; i++) {
			
			//Determines if the element has been repeated
			boolean isRepeated = false;
			
			/*
			 * Compare the current element with the previous elements
			 * by looping backwards.
			 * 
			 * So the first element is always added to the 
			 * unique array and the second element is compared
			 * to the first and so on. 
			 * 
			 */
			for (int g = i; g >= 0; g--) {
				
				// Short-circuits when it tries to compare element to itself
				if (g != i && array[i] == array[g]){
					
					//The current i element is repeated already, so exit loop
					isRepeated = true;
					break;
				}
			}
			
			/*
			 * If the element has not been previously repeated, 
			 * then add it to the unique array
			 */
			if (!isRepeated) {
				uniqueInt[i] = array[i];
				uniqueCounter++;
			}
			
		}
		
		//Print results
		System.out.println("Unique so far:  " + uniqueCounter);
		printArray(uniqueInt);
		System.out.println("");
		
		/*
		 * Since the default value for an integer array is 0 
		 * when initialized, we can check if the array is filled
		 * by looking at the last element and checking if it is 
		 * not zero. 
		 * 
		 * This could break should element actually be 0
		 * 
		 * Commented block is what I had before. 
		 */
//		if (array[array.length - 1] != 0) {
//			System.out.printf("You've Entered %d Unique Valid Numbers.\n", uniqueCounter);
//			printArray(uniqueInt);
//			System.out.println("Goodbye.");
//		}
		if (uniqueCounter >= uniqueLimit) {
			System.out.printf("You've Entered %d Unique Valid Numbers.\n", uniqueCounter);
			printArray(uniqueInt);
			System.out.println("Goodbye.");
		}

		
	} // end displayUniques
	
	/**
	 * @param array: The integer array to check. 
	 * @return Returns the amount of "valid" integers
	 * 			in the array
	 * 
	 */
	public static int findValidSize(int[] array) {
		
		int size = 0; 
		
		/*
		 * Loop through the array and find valid elements
		 * and augment size each time a valid element is 
		 * found. 
		 */
		for(int i = 0; i < array.length; i++) {
			if (isValid(array[i])) {
				size++;
			}
		}
		
		return size; 
	} // End findValidSize
	
	public static void printArray(int[] array) {
		
		for (int num: array) {
			System.out.print(num + " ");
		}
		
		System.out.println("");
		
	} // End printArray
	
	public static void errorMessage() {
		System.out.println("This is an Invalid Number");
	} // End errorMessage

}
