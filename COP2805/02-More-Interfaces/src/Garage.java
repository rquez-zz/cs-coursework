
public class Garage extends Building {

	private int numberOfCars;
	private String floorCovering;
	private int length;
	private int width;
	
	public Garage (int numberOfWindows, int length, int width, int numberOfCars, String floorCovering) {
		
		super.setNumberOfFloors(1);
		super.setNumberOfWindows(numberOfWindows);
		
		this.length = length;
		this.width = width;
		this.numberOfCars = numberOfCars;
		this.floorCovering = floorCovering;
		
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
	
	public String toString() {
		String result = "I am a Garage!"+ "\n\tLength: " + this.getLength() + "\n\tWidth: " + this.getWidth()
				+ "\n\tFloor Covering: " + this.getFloorCovering() + "\n\tNumber of Cars: " 
				+ this.getNumberOfCars() + "\n";
		return result; 
	}
	
	
}
