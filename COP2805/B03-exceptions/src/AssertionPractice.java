public class AssertionPractice {

	public static void main(String args[]) {

		try {
			tryAssert(-100);
		} catch (AssertionError ae) {
			System.out.println(ae.getMessage());
		}
	}

	private static void tryAssert(int num) throws AssertionError {

		//This method is private because it should only be able 
		// to throw the error within it's own class.
		assert (num > 0) : ("AssertionError has been thrown! \n" 
							+ num + " is negative, assertion is false.");

	}
}
