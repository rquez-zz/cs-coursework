/**
 * @author Ricardo Vasquez
 *
 */
public class StaticMethods {

	public static void main(String[] args) {

		int cubeOfSeven;

		cubeOfSeven = cube(7);
		
		System.out.println("Volume of Cube of Seven is " + cubeOfSeven);

		outputMessages();
		
		int[] array = { 12, 23, 34, 56 };
		
		double average = findAverage(array);

		System.out.println("Average: " + average);

	}

	public static double findAverage(int[] array) {
		double result;
		int sum = 0;

		for (int i = 0; i < array.length; i++) {
			sum += array[i];
		}
		result = (double) sum / array.length;
		return result;
	}

	public static void outputMessages() {
		System.out.print("Hello, ");
		System.out.print("from outputMessages()\n");
	}

	public static int cube(int parameter) {
		int result;
		result = parameter * parameter * parameter;
		return result;
	}

}
