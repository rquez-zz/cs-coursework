
public abstract class Building {

	private int numberOfWindows;
	private int numberOfFloors;
		
	abstract protected int getFloorSpace();

	protected int getNumberOfWindows() {
		return numberOfWindows;
	}

	protected void setNumberOfWindows(int numberOfWindows) {
		this.numberOfWindows = numberOfWindows;
	}

	protected int getNumberOfFloors() {
		return numberOfFloors;
	}

	protected void setNumberOfFloors(int numberOfFloors) {
		this.numberOfFloors = numberOfFloors;
	}
	
	public String toString() {
		String result;
		result = numberOfWindows + " Window(s), " 
				+ numberOfFloors + " Floor(s), "
				+ this.getFloorSpace() + " Units Total Floor Space"
				+ " (Building.toString)\n";
		return result; 
	}
	
}
