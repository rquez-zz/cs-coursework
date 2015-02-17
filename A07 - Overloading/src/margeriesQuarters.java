import java.util.Scanner;

public class margeriesQuarters {

	public static void main(String[] args) {
		
		SlotMachine[] Slots = new SlotMachine[3];
		Slots[0] = new SlotMachine(0, 35, 30);
		Slots[1] = new SlotMachine(0,100, 60);
		Slots[2] = new SlotMachine(0, 10, 11);
		
		int quarterJar = 0;
		
		//Read in input
		Scanner scanner = new Scanner(System.in);
		System.out.print("How many quarters  does Marge have in the jar?");
		quarterJar = scanner.nextInt();
		System.out.print("How many times has the first machine been played since paying out?");
		Slots[0].setTimesPlayed(scanner.nextInt());
		System.out.print("How many times has the second machine been played since paying out?");
		Slots[1].setTimesPlayed(scanner.nextInt());
		System.out.print("How many times has the third machine been played since paying out?");
		Slots[2].setTimesPlayed(scanner.nextInt());
		
		//Loop through the slots
		while (quarterJar > 0) {
			quarterJar = Slots[0].playSlotYield(quarterJar);
			quarterJar = Slots[1].playSlotYield(quarterJar);
			quarterJar = Slots[2].playSlotYield(quarterJar);
		}
		
		System.out.println("Martha plays " + SlotMachine.getTotalTimesPlayed() + " times.");
		
		scanner.close();
		
	
		
	}

}
