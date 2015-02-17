import java.util.Random;

/**
 * @author Ricardo Vasquez
 *
 */
public class RandomNumbers {

	public static void main(String[] args) {
		
		int die1 = 0, die2 = 0, die3 = 0,die4 = 0,die5 = 0,die6 = 0, totalRolls = 1000000;
		
		Random generator = new Random();

		
		for(int i = 0; i < totalRolls; i++){
			
			int die = generator.nextInt(6) + 1;
			
			switch (die) {
				case 1:
					die1++;
					break;
				case 2:
					die2++;
					break;
				case 3:
					die3++;
					break;
				case 4:
					die4++;
					break;
				case 5:
					die5++;
					break;
				case 6:
					die6++;
					break;
			}
		}
		
		System.out.printf("Die 1: %d\t%.3f%%\n", die1, getPercentage(die1,totalRolls));
		System.out.printf("Die 2: %d\t%.3f%%\n", die2, getPercentage(die2,totalRolls));
		System.out.printf("Die 3: %d\t%.3f%%\n", die3, getPercentage(die3,totalRolls));
		System.out.printf("Die 4: %d\t%.3f%%\n", die4, getPercentage(die4,totalRolls));
		System.out.printf("Die 5: %d\t%.3f%%\n", die5, getPercentage(die5,totalRolls));
		System.out.printf("Die 6: %d\t%.3f%%\n", die6, getPercentage(die6,totalRolls));
		System.out.println("Total Rolls: " + totalRolls);
	}
	
	public static double getPercentage(int die, int total) {
		
		double result = 0.0;
		
		result = (((double)die) / ((double)total) * 100);
		
		return result; 
	}

}
