
public class Address {

	// Instance Variables
	private String city;
	private String state;
	private String country;
	private String street;
	private String houseNumber;
	private String postalCode;
	
	// Constructor Method
	public Address(String houseNumber, String street, String city, String state, String country, String postalCode) {
		setHouseNumber(houseNumber);
		setStreet(street);
		setCity(city);
		setState(state);
		setCountry(country);
		setPostalCode(postalCode);
	}
	
	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
	
	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public String getCity() {
		return city;
	}
	
	public void setCity(String city) {
		this.city = city;
	}
	
	public String getCountry() {
		return country;
	}
	
	public void setCountry(String country) {
		this.country = country;
	}
	
	public String getStreet() {
		return street;
	}
	
	public void setStreet(String street) {
		this.street = street;
	}
	
	public String getHouseNumber() {
		return houseNumber;
	}
	
	public void setHouseNumber(String houseNumber) {
		this.houseNumber = houseNumber;
	} 
	
	public String toString() {
		return houseNumber + " " + street + "\n" + city + ", " + state + " " + country + " " + postalCode;
	}
	
}
