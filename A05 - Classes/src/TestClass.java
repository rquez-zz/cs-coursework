public class TestClass {

	public static void main(String[] args) {
		System.out.printf( "%d contacts have been created.", Contact.getContactsCreated() );

		

		//Create Object Instances
		Telephone eastCampusNumber = new Telephone(407, "299-5000");
		Address eastCampusLocation = new Address("701", "N Econlockhatchee Trail", "Orlando","Florida","USA","32825");
		Contact eastCampusContact = new Contact("Valencia College East Campus", eastCampusLocation, eastCampusNumber, "east@valenciacollege.edu");
		
		Telephone westCampusNumber = new Telephone(407, "000-5992");
		Address westCampusLocation = new Address("1800", "South Kirkman Road", "Orlando","Florida","USA","32811");
		Contact westCampusContact = new Contact("Valencia College West Campus", westCampusLocation, westCampusNumber, "west@valenciacollege.com");
		
		Telephone whiteHouseNumber = new Telephone("202", "456-1111");
		Address whiteHouseLocation = new Address("1600", "Pennsylvania Avenue NW", "Washington","DC","USA","32811");
		Contact whiteHouseContact = new Contact("The White House", whiteHouseLocation, whiteHouseNumber, "contact@whitehouse.gov");
		
		//Print Objects as String
		System.out.println( eastCampusContact.toString() );
		System.out.println( westCampusContact.toString() );
		System.out.println( whiteHouseContact.toString() );

		System.out.printf( "%d contacts have been created.", Contact.getContactsCreated() );
				
	}

}
