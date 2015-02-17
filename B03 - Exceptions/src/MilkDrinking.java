import java.util.Random;

public class MilkDrinking {

	public static void main(String args[]) {

		System.out.println("PART IV\n");

		try {
			drinkMilk();
		} catch (OutOfMilkException oome) {
			System.out.println(oome.getMessage());
		}

	}

	public static void drinkMilk() throws OutOfMilkException {

		long startTime = System.currentTimeMillis();

		try {
			Random rand = new Random();
			int newRandInt = 0;

			while (true) {

				newRandInt = rand.nextInt(1000);
				System.out.print("Glup");
				newRandInt = 10000 / newRandInt;

			}

		} catch (ArithmeticException ae) {

			throw new OutOfMilkException("\n\nOut of Milk!",
					System.currentTimeMillis() - startTime);
		}
	}

}
