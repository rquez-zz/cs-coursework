
public class Clerk extends Person {

	private double salary;
	private String employementGrade;
	
	public Clerk(String name, Address address, Telephone number, String email, double salary, String employementGrade) {
		super(name, address, number, email);
		this.setSalary(salary);
		this.setEmployementGrade(employementGrade); 
	}
	
	public boolean equals(Object obj) {
		
		if (!(obj instanceof Clerk)){
			return false; 
		}
		
		boolean result = false; 
		
		if ( this.salary == ((Clerk)obj).salary && this.employementGrade.equals( ((Clerk)obj).employementGrade ) ) {
			result = true;
		}
		
		return result;
	}
	
	public double getSalary() {
		return salary;
	}
	public void setSalary(double salary) {
		this.salary = salary;
	}
	public String getEmployementGrade() {
		return employementGrade;
	}
	public void setEmployementGrade(String employementGrade) {
		this.employementGrade = employementGrade;
	} 
	
	public String toString() {
		return super.toString() + "\nSalary: " + salary + " Employment Grade: " + employementGrade + "\n";
	}
	
}
