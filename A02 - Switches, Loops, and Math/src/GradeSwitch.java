import java.util.Scanner;

/**
 * @author Ricardo Vasquez
 *
 */
public class GradeSwitch {

	public static void main(String[] args) {

		//Initialize Variables
		Scanner scanner = new Scanner(System.in);
		char letter;
		
		//Read in letter grade
		System.out.print("Enter a letter grade: ");
		letter = scanner.next().charAt(0);
		
		//Close Scanner
		scanner.close();
		
		switch (letter) {
			case 'A':
				System.out.println("You must have scored 90.0 - 100.0");
				break;
			case 'B':
				System.out.println("You must have scored 80.0 - 89.9");
				break;
			case 'C':
				System.out.println("You must have scored 70.0 - 79.9");
				break;
			case 'D':
				System.out.println("You must have scored 60.0 - 69.0");
				break;
			case 'F':
				System.out.println("You must have scored 0.0 - 59.0");
				break;
			default: 
				System.out.println("You have entered an invalid letter grade.");
				break;
		
		}
				
	}

}