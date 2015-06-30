
public class House extends Building implements MLSListable {

	private Room[] rooms;
	private int numberOfBathrooms;
	
	public House(Room[] rooms, int numberOfBathrooms, int numberOfWindows, int numberOfFloors) {
		
		super.setNumberOfFloors(numberOfFloors);
		super.setNumberOfWindows(numberOfWindows);
		
		this.numberOfBathrooms = numberOfBathrooms;
		this.rooms = rooms;
	}
	
	public String genRoomInfo() {
		
		String result = "";
		
		for (int i = 0; i < rooms.length; i++) {
			result += rooms[i].toString();
		}
		
		return result;
	}
	public Room[] getRooms() {
		return rooms;
	}
	public void setRooms(Room[] rooms) {
		this.rooms = rooms;
	}
	public int getNumberOfBathrooms() {
		return numberOfBathrooms;
	}
	public void setNumberOfBathrooms(int numberOfBathrooms) {
		this.numberOfBathrooms = numberOfBathrooms;
	}

	public int getAvgRoomSize() {
		int result;
		int sum = 0;
		
		for (int i = 0; i < this.rooms.length; i++) {
			sum += rooms[i].getLength() * rooms[i].getWidth();
		}
		
		result = sum / this.rooms.length;
		
		return result;
	}
	
	public String toString() {
		String result = "I am a House!" + "\n\tAverage Room Size: " + this.getAvgRoomSize() +
				"\n\tBathrooms: " + this.getNumberOfBathrooms() +
				"\n\tFloors: " + this.getNumberOfFloors() +
				"\n\tWindows: " + this.getNumberOfWindows() +
				"\n\tRooms: " + this.rooms.length + this.genRoomInfo() + "\n";

		return result; 
	}

	public String getMLSListing() {
		return "MLSListing - " + this.toString();
	}
}
