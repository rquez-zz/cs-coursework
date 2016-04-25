import java.util.Scanner;

@SuppressWarnings("all")

public class PhoneTest {

	public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in);
		String userNumber;

		System.out.print("Input your telephone number: ");
		userNumber = scanner.next();

		try {

			PhoneNumberMatch phoneMatcher = new PhoneNumberMatch(userNumber);
			System.out.println(phoneMatcher.toString());

		} catch (InvalidTelephoneException ite) {

			System.out.println(ite.getMessage());
		}

	}

}
