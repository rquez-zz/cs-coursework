
public class TestClerk {


	public static void main(String[] args) {

		Clerk[] clerks = new Clerk[5];
		Address sharedHouse = new Address("1234", "Clerk Drive", "Clerkville", "Clerkifornia", "United States of Clerks", "99999");
		Telephone houseTelephone = new Telephone("123", "4567");
		
		clerks[0] = new Clerk("Carlos", sharedHouse, houseTelephone, "zero@clerk.com", 45000, "CR-4");
		clerks[1] = new Clerk("Jaime", sharedHouse, houseTelephone, "one@clerk.com", 30000, "CR-3");
		clerks[2] = new Clerk("Luis", sharedHouse, houseTelephone, "two@clerk.com", 20000, "CR-2");
		clerks[3] = new Clerk("Juan", sharedHouse, houseTelephone, "three@clerk.com", 10000, "CR-1");
		clerks[4] = new Clerk("Jose", sharedHouse, houseTelephone, "four@clerk.com", 10000, "CR-1");
		
		//List all clerks using toString
		for (int i = 0; i < clerks.length; i++) {
			System.out.println(clerks[i].toString());
		}
		
		//Check for clerk equality 
		if (clerks[0].equals(clerks[1])) {
			System.out.println(clerks[0].getName() + " is equal to " + clerks[1].getName());
		} else {
			System.out.println(clerks[0].getName() + " is not equal to " + clerks[1].getName());
		}
		
		if (clerks[3].equals(clerks[4])) {
			System.out.println(clerks[3].getName() + " is equal to " + clerks[4].getName());
		} else {
			System.out.println(clerks[3].getName() + " is not equal to " + clerks[4].getName());
		}
		
		if (clerks[1].equals(clerks[2])) {
			System.out.println(clerks[1].getName() + " is equal to " + clerks[2].getName());
		} else {
			System.out.println(clerks[1].getName() + " is not equal to " + clerks[2].getName());
		}
		
		
		//Change employee grades
		System.out.println("\nClerks go through a corporate reorganization\n");
		clerks[0].setEmployementGrade("CR-1");
		clerks[0].setSalary(10000);
		clerks[4].setEmployementGrade("CR-4");
		clerks[4].setSalary(45000);
		clerks[2].setEmployementGrade("CR-3");
		clerks[2].setSalary(30000);
		
		//Display list of clerks
		for (int i = 0; i < clerks.length; i++) {
			System.out.println(clerks[i].toString());
		}
		
		//Check for clerk equality again
		if (clerks[0].equals(clerks[1])) {
			System.out.println(clerks[0].getName() + " is equal to " + clerks[1].getName());
		} else {
			System.out.println(clerks[0].getName() + " is not equal to " + clerks[1].getName());
		}
		
		if (clerks[3].equals(clerks[4])) {
			System.out.println(clerks[3].getName() + " is equal to " + clerks[4].getName());
		} else {
			System.out.println(clerks[3].getName() + " is not equal to " + clerks[4].getName());
		}
		
		if (clerks[1].equals(clerks[2])) {
			System.out.println(clerks[1].getName() + " is equal to " + clerks[2].getName());
		} else {
			System.out.println(clerks[1].getName() + " is not equal to " + clerks[2].getName());
		}
		
		//Display getClass() results
		String someString = new String("Hello");
		Integer someInteger = new Integer(100);
		Clerk someClerk = new Clerk("Jimmy", sharedHouse, houseTelephone, "jimmy@clerk.com", 10000, "CR-1");

		System.out.println( "\n" + someString.getClass().toString() );
		System.out.println( someInteger.getClass().toString() );
		System.out.println( someClerk.getClass().toString() );
		
		//Verify if these objects are instances of an object
		if (clerks[1] instanceof Person) {
			System.out.println("\n" + clerks[1].getClass().toString() + " " + clerks[1].getName() + " IS A Person");
		}
		if (clerks[1] instanceof Clerk) {
			System.out.println(clerks[1].getClass().toString() + " " + clerks[1].getName() +  " IS A Clerk");
		}
		if (someString instanceof String) {
			System.out.println(someString.getClass().toString() + " someString IS A String");
		}
		if (someInteger instanceof Integer) {
			System.out.println(someInteger.getClass().toString() + " someInteger IS A Integer");
		}
		if (someInteger instanceof Object) {
			System.out.println(someInteger.getClass().toString() + " someInteger IS A Object");
		}

	}

}
