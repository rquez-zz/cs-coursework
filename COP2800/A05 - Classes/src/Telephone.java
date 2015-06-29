
public class Telephone {

	// Instance Variables
	private String areaCode;
	private String localNumber;
	
	// Constructor method
	public Telephone(String areaCode, String localNumber) {
		setAreaCode(areaCode);
		setLocalNumber(localNumber);
	}
	
	// Overloaded constructor method 
	public Telephone(int areaCode, String localNumber) {
		setAreaCode(String.valueOf(areaCode));
		setLocalNumber(localNumber);
	}
	
	public String getAreaCode() {
		return areaCode;
	}
	
	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}
	
	public String getLocalNumber() {
		return localNumber;
	}
	
	public void setLocalNumber(String localNumber) {
		this.localNumber = localNumber;
	}
	
	public String toString() {
		return "(" + areaCode + ") " + localNumber;
	}
	
}
