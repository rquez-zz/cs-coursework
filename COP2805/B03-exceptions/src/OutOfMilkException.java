
public class OutOfMilkException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4448601474340033010L;

	public OutOfMilkException(String message, long timeElapsed) {
		super(message + " Drank milk for " + timeElapsed + " miliseconds.");
	}

}
