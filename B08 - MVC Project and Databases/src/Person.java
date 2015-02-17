/**
 * @author Ricardo Vasquez
 * 
 * Person object contains information about a person
 */
public class Person {
	private int id;
	private String firstName;
	private String lastName;
	private String number;
	private String email;
	private boolean isStudent;
	private boolean isEmployed;
	
	public Person(int id, String firstName, String lastName,String number, String email, boolean isStudent,boolean isEmployed) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.number = number;
		this.email = email;
		this.isStudent = isStudent;
		this.isEmployed = isEmployed;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public String getNumber() {
		return number;
	}
	
	public void setNumber(String number) {
		this.number = number;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public boolean isStudent() {
		return isStudent;
	}
	
	public void setStudent(boolean isStudent) {
		this.isStudent = isStudent;
	}
	
	public boolean isEmployed() {
		return isEmployed;
	}
	
	public void setEmployed(boolean isEmployed) {
		this.isEmployed = isEmployed;
	}
	
	@Override
	public String toString() {
		String result;
		result = "ID: " + id + " Name: " + firstName + lastName
				+ " Number: " + number + " Email: " + email;
		return result;
	}
}
