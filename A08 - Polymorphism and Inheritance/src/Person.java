
public class Person {

	// Instance Variables
	private String name;
	private Address contactAddress;
	private Telephone contactNumber;
	private String emailAddress;
	
	// Counts how many persons have been created
	private static int contactsCreated = 0;
	
	public static int getContactsCreated() {
		return Person.contactsCreated;
	}

	// Constructor Method
	public Person(String name, Address contactAddress, Telephone contactNumber, String emailAddress) {
		setName(name);
		setContactAddress(contactAddress);
		setContactNumber(contactNumber);
		setEmailAddress(emailAddress);
		
		//Augments the number of Contacts Created
		contactsCreated++;
	}
	
	public boolean equals(Object obj) {
		
		if(!(obj instanceof Person)) {
			return false;
		}
		
		boolean result = false;
		
		if ( this.name.equals( ((Person)obj).name )) {
			result = true;
		} 
		
		return result;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Address getContactAddress() {
		return contactAddress;
	}

	public void setContactAddress(Address contactAddress) {
		this.contactAddress = contactAddress;
	}

	public Telephone getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(Telephone contactNumber) {
		this.contactNumber = contactNumber;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
	
	public String toString() {
		return "Name: " + name + "\nAddress: " + contactAddress + "\nNumber: " + contactNumber + " Email: " + emailAddress + " ";
	}

}
