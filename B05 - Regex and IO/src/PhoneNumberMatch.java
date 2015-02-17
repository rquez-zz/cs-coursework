/**
 * 
 * Constructs an object that cleans up the format of a valid inputed telephone
 * number and stores the number's area code, exchange, and local number. A valid
 * telephone number has 10 digits or 11 digits only if the first digit is 1.
 * 
 * Any string that contains 10 or 11 digits is acceptable by the constructor.
 * The constructor creates a StringBuilder object using the userNumber string
 * and deletes any characters that are not numeric. Then it assigns the area
 * code, exchange, and local number to the appropriate substring of the
 * StringBuilder object.
 * 
 * @param userNumber
 *            The unformatted string input that is formated by the constructor.
 * @return PhoneNumberMatch Returns a PhoneNumberMatch object that contains the
 *         userNumber in the correct format, along with the area code, exchange
 *         and local number.
 * @throws InvalidTelephoneException
 *             An exception thrown by the constructor that tells the user that
 *             the inputted number is not valid. Since the constructor throws
 *             the exception, it needs to be caught in the try/catch block where
 *             the constructor is called.
 * @author Ricardo Vasquez
 * 
 */

@SuppressWarnings("all")
public class PhoneNumberMatch {

	private String userNumber;
	private String localNumber;
	private String areaCode;
	private String exchange;

	public PhoneNumberMatch(String userNumber) throws InvalidTelephoneException {

		StringBuilder userNumberMutable = new StringBuilder(userNumber);

		for (int i = 0; i < userNumberMutable.length(); i++) {
			if (!Character.toString(userNumberMutable.charAt(i)).matches("\\d")) {
				userNumberMutable.deleteCharAt(i);
				i--;
			}
		}

		try {

			if (userNumberMutable.length() == 11) {
				if (Character.getNumericValue(userNumberMutable.charAt(0)) != 1) {
					throw new InvalidTelephoneException("Invalid number.");
				}
			} else if (userNumberMutable.length() != 10) {
				throw new InvalidTelephoneException("Invalid number.");
			}

			if (userNumberMutable.length() == 11) {
				userNumberMutable.deleteCharAt(0);
			}

			this.userNumber = userNumberMutable.toString();
			this.areaCode = userNumberMutable.substring(0, 3);
			this.exchange = userNumberMutable.substring(3, 6);
			this.localNumber = userNumberMutable.substring(6, 10);

		} finally {
		}
	}

	/**
	 * Gets the last 4 digits of the valid userNumber
	 * 
	 * @return this userNumber's localNumber
	 */
	public String getLocalNumber() {
		return localNumber;
	}

	/**
	 * Gets the first 3 digits of the valid userNumber
	 * 
	 * @return this userNumber's area code.
	 */
	public String getAreaCode() {
		return areaCode;
	}

	/**
	 * 
	 * Gets the middle 3 digits of the valid userNumber
	 * 
	 * @return this userNumber's exchange.
	 */
	public String getExchange() {
		return exchange;
	}

	/**
	 * Displays the userNumber in cannonical form.
	 * 
	 * @return a string containing the area code, exchange and local number.
	 */
	public String toString() {

		String result;
		result = "(" + areaCode + ")" + exchange + "-" + localNumber;

		return result;

	}

}
