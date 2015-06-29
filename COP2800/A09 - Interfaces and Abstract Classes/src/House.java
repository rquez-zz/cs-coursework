
public class House extends Building implements MLSListable{

	private int numberOfBathrooms;
	private Room[] rooms;
	private String modelName;
	
	public House(int numberOfFloors, int numberOfWindows, String modelName, int numberOfBathrooms, Room[] rooms) {
		
		super.setNumberOfFloors(numberOfFloors);
		super.setNumberOfWindows(numberOfWindows);
		
		this.numberOfBathrooms = numberOfBathrooms;
		this.rooms = rooms;
		this.modelName = modelName;
	}
	
	public int getFloorSpace() {
		
		// Initialize the abstract method from the Building Class
		int sum = 0;
		
		for (int i = 0; i < rooms.length; i++) {
			sum += rooms[i].getRoomArea();
		}
		
		return sum;
	}
	
	public double getAverageRoomSize() {
		
		double average = 0.0; 
		for (int i = 0; i < this.rooms.length; i++) {
			average += this.rooms[i].getRoomArea();
		}
		average /= this.rooms.length;
		return average; 
	}

	public int getNumberOfBathrooms() {
		return numberOfBathrooms;
	}

	public void setNumberOfBathrooms(int numberOfBathrooms) {
		this.numberOfBathrooms = numberOfBathrooms;
	}

	public Room[] getRooms() {
		return rooms;
	}

	public void setRooms(Room[] rooms) {
		this.rooms = rooms;
	}
	
	public String getMLSListing() {
		String result = "";
		
		result = "House " + modelName + "\n" 
					+ super.getNumberOfFloors() + " Floor(s)\n" + super.getNumberOfWindows() + " Window(s)\n" 
					+ rooms.length + " Room(s)\n" + numberOfBathrooms + " Bathroom(s)\n" 
					+ this.getFloorSpace() + " Total Floor Space\n" +
					+ ((double)Math.round(this.getAverageRoomSize() * 100) / 100) + " Units Average Room Size\n"; 
		
		return result;
	}
	
	public String toString() {
		
		// Override Building's toString method
		String result = "";
		
		result = super.toString()
				+ rooms.length + " Room(s)\n" 
				+ numberOfBathrooms + " Bathrooms(s)\n"
				+ ((double)Math.round(this.getAverageRoomSize() * 100) / 100) + " Units Average Room Size\n";
		
		return result;
	}
}
