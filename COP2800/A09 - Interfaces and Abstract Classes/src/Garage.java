
public class Garage extends Building{

	private int numberOfCars;
	private String floorCovering;
	private int length;
	private int width;
	
	
	public Garage (int numberOfWindows, int length, int width, int numberOfCars, Flooring floorCovering) {
		
		super.setNumberOfFloors(1);
		super.setNumberOfWindows(numberOfWindows);
		
		this.length = length;
		this.width = width;
		this.numberOfCars = numberOfCars;
		this.floorCovering = floorCovering.name(); 
		
	}
	
	public int getFloorSpace() {
		
		int area = 0; 
		area = length * width;
		
		return area;
	}

	public int getNumberOfCars() {
		return numberOfCars;
	}

	public void setNumberOfCars(int numberOfCars) {
		this.numberOfCars = numberOfCars;
	}

	public String getFloorCovering() {
		return floorCovering;
	}

	public void setFloorCovering(String floorCovering) {
		this.floorCovering = floorCovering;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}
	
	public String toString(){
		
		// Override Building class's toString method
		String result = ""; 
		
		result = super.toString()
				+ length + "x" + width
				+ numberOfCars + " Car Capacity\n" 
				+ floorCovering + " Flooring\n";
		
		return result;
	}
}
