
public class Triangle implements Comparable<Triangle>{ 

	private double sideA = 1.0;
	private double sideB = 1.0; 
	private double sideC = 1.0;

	public Triangle(){
		super();		
	}

	public Triangle(double sideA, double sideB, double sideC) 
				throws InvalidTriangleException{
		super();
		this.sideA = sideA;
		this.sideB = sideB;
		this.sideC = sideC; 
		isValid();
	}

	private boolean isValid() throws InvalidTriangleException {
		if (sideA < 0.0 || sideB < 0.0 || sideC < 0.0) {
			throw new InvalidTriangleException(
				"Sides cannot be less than 0." 
				+ toString());
		}
		
		if(sideA + sideB < sideC 
			|| sideA + sideC < sideB
			|| sideB + sideC < sideA){
				throw new InvalidTriangleException(
					"Any 2 sides must be more"
					+ " than the other side.\n" 
					+ toString());

		}
		
		return true;
	}

	public int compareTo(Triangle that){
		int diff = 0; 
		double dDiff; 
		
		if (this.equals(that)) {
			return 0;
		}
			
		dDiff = (calculatePerimeter() -
			 	 that.calculatePerimeter());

		if(dDiff > 0.0 && dDiff < 1.0) {
		    diff = 1;
		} else if (dDiff < 0 && dDiff > -1) {
		    diff = -1;
		} else {
		    diff = (int)dDiff;
		}
		
		return diff; 
	}

	public boolean equals(Object obj){ 
	
		if (!(obj instanceof Triangle)) {
			return false;
		}
		boolean result; 
		
		result = (this.calculatePerimeter() == ((Triangle)obj).calculatePerimeter());

		return result; 
	}

	public double getSideA(){
		return sideA;
	}

	public void setSideA(double sideA) 
				throws InvalidTriangleException{
		this.sideA = sideA;
		isValid(); 
	}
	
	public double getSideB(){
	    	return sideB;
	}
	
	public void setSideB(double sideB)
				throws InvalidTriangleException{
	    	this.sideB = sideB; 
	    	isValid(); 
	}
	
	public double getSideC(){
	    	return sideC; 
	}
	
	public void setSideC(double sideC)
				throws InvalidTriangleException{
	    	this.sideC = sideC; 
	    	isValid();
	}

	public double calculatePerimeter(){ 
		return sideA + sideB + sideC;
	}	

	public String toString() {
		String result;
		result = "SideA: " + sideA + " SideB: " + sideB +
			" SideC: " + sideC; 
		result += " Perimeter: " + calculatePerimeter();

		return result;

	}

}



