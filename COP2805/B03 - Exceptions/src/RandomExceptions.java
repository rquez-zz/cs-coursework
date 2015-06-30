import java.util.Random;

public class RandomExceptions {

	@SuppressWarnings("unused")
	public static void main(String[] args) {

		System.out.println("PART II\n");

		try {
			Random r = new Random();
			int[] array = { 10, 20 };
			int result = array[r.nextInt(array.length + 1)] / r.nextInt(2);

			System.out.println("Risky code ran without exceptions!\n");

		} catch (ArithmeticException ae) {
			System.out.println("An ArithmeticException has been thrown! Caused by "
					+ ae.getMessage() + "\n");

		} catch (ArrayIndexOutOfBoundsException aioobe) {
			System.out.println("An ArrayIndexOutOfBoundsException "
					+ "has been thrown! \nIndex "
					+ aioobe.getMessage() + " is out of bounds!\n");

		}
	}

}
