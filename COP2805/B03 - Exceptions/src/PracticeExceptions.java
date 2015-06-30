import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.awt.print.*;

public class PracticeExceptions {

	@SuppressWarnings({ "unused", "null", "resource" })
	public static void main(String[] args) {

		System.out.println("PART I\n");

		// ArrayIndexOutOfBoundsException
		try {
			int[] integerArray = { 5, 6, 7 };
			for (int i = 0; i < 4; i++) {
				System.out.print(integerArray[i] + " ");
			}
		} catch (ArrayIndexOutOfBoundsException aioobe) {
			System.out.println("\nArrayIndexOutOfBoundsException "
					+ "has been thrown!"
					+ "\nArray index can't go beyond "
					+ aioobe.getMessage() + "\n");
		}

		// ClassCastException
		try {
			String text = (String) new Object();
			text = "Hello World";
		} catch (ClassCastException cce) {
			System.out.println("ClassCastException has been thrown!"
					+ "\nObject is a not subclass of String.\n");
		}

		// IllegalArgumentException
		try {
			PageFormat orientation = new PageFormat();
			orientation.setOrientation(50);
		} catch (IllegalArgumentException iae) {
			System.out.println("IllegalArgumentException has been thrown!"
					+ "\nInteger argument is a not page orientation "
					+ "constant for Page Format.\n");
		}

		// NullPointerException
		try {
			String emptyWord = null;
			emptyWord.charAt(5);
		} catch (NullPointerException npe) {
			System.out.println("NullPointerException has been thrown!"
					+ "\nNull String object doesn't have a "
					+ "character at the index.\n");
		}

		// NumberFormatException
		try {
			String word = "Hello";
			Integer wordInteger = Integer.parseInt(word);
		} catch (NumberFormatException nfe) {
			System.out.println("NumberFormatException has been thrown!"
					+ "\nString object can't " + "be parsed into an int.\n");
		}

		// FileNotFoundException
		try {
			FileReader reader = new FileReader("File");
		} catch (FileNotFoundException fnfe) {
			System.out.println("FileNotFoundException has been thrown!"
					+ "\nFile doesn't exist.\n");
		}

		// AssertionError
		try {
			ClassLoader.getSystemClassLoader().setDefaultAssertionStatus(true);
			int num = 0;
			assert (num > 1);
		} catch (AssertionError ae) {
			System.out.println("AssertionError has been thrown!"
					+ "\nConditional statement (0 > 1) is false.\n");
		}

		// NoClassDefFoundError (Assume ClassToBeDeleted.class exists)
		try {
			File file = new File("bin/ClassToBeDeleted.class");
			file.delete();
			ClassToBeDeleted ClassThatIsAlreadyDeleted = new ClassToBeDeleted();
		} catch (NoClassDefFoundError ncdfe) {
			System.out.println("NoClassDefFoundError has been thrown!"
					+ "\nClass has be removed after compile time.\n");
		}

		// StackOverFlowError
		try {
			badRecursiveMethod();
		} catch (StackOverflowError sofe) {
			System.out.println("StackOverflowError has been thrown!"
					+ "\nStack has collided with the heap due "
					+ "to a bad recursive call.\n");
		}

	}

	// StackOverFlowError
	public static int badRecursiveMethod() {

		return badRecursiveMethod();
	}

}
