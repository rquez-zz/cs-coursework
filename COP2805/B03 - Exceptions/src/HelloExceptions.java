import java.io.FileNotFoundException;
import java.io.FileReader;

public class HelloExceptions {

	@SuppressWarnings("unused")
	public static void main(String[] args) {

		int a = 10, b = 6, c;

		// a = b / 0;

//		if (a > b) {
//			ArithmeticException ae = new ArithmeticException(
//					" horrible problem");
//			throw ae;
//		}

		System.out.println("in main");
		try{
			foo();
		} catch (CustomerRecordsMissingException crme) {
			System.out.println(crme.getMessage());
		}
		System.out.println("didn't get here");

	}

	@SuppressWarnings("unused")
	private static void foo() throws CustomerRecordsMissingException {
		if (10 > 5) {
			throw new CustomerRecordsMissingException(
					"I'm in foo with an important message");
		}

		System.out.println("in foo");
		try {
			foo2();
		} catch (ArithmeticException ae) {
			System.out.println("dealing with a problem");
		} catch (FileNotFoundException fnfe) {
			System.out.println("Hey the file not found was thrown!");
		} finally {
			System.out.println("I will always happen");
		}
		System.out.println("didn't get here in foo");

	}

	private static void foo2() throws FileNotFoundException {
		@SuppressWarnings("unused")
		int e = 45;
		System.out.println("in foo2");

		@SuppressWarnings({ "unused", "resource" })
		FileReader fileReader = new FileReader("Bob");

		e = 1 / 0;

		System.out.println("didn't get here in foo2");
	}

}
