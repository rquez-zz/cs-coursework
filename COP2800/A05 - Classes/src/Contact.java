
public class Contact {

	// Instance Variables
	private String name;
	private Address contactAddress;
	private Telephone contactNumber;
	private String emailAddress;
	
	// Counts how many contacts have been created
	private static int contactsCreated = 0;
	
	public static int getContactsCreated() {
		return Contact.contactsCreated;
	}

	// Constructor Method
	public Contact(String name, Address contactAddress, Telephone contactNumber, String emailAddress) {
		setName(name);
		setContactAddress(contactAddress);
		setContactNumber(contactNumber);
		setEmailAddress(emailAddress);
		
		//Augments the number of Contacts Created
		contactsCreated++;
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
		return name + "\n" + contactAddress + "\n" + contactNumber + "\n" + emailAddress + "\n";
	}
	
}
