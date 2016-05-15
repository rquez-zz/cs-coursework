public class Room implements Comparable<Room>{

	private int length;
	private int width; 
	private String floorCovering; 
	private int numberOfClosets;
	
	public Room(int length, int width, String floorCovering, int numberOfClosets) {
		this.length = length;
		this.width = width;
		this.floorCovering = floorCovering;
		this.numberOfClosets = numberOfClosets; 
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
	public String getFloorCovering() {
		return floorCovering;
	}
	public void setFloorCovering(String floorCovering) {
		this.floorCovering = floorCovering;
	}
	public int getNumberOfClosets() {
		return numberOfClosets;
	}
	public void setNumberOfClosets(int numberOfClosets) {
		this.numberOfClosets = numberOfClosets;
	} 

	
	public String toString() {
		String result = "\n\t\tLength: " + this.getLength() + " Width: " + this.getWidth() +
						" Closets: " + this.getNumberOfClosets() + " Floor Covering: " + this.getFloorCovering();
		return result;
	}

	public int compareTo(Room arg0) {
		
		if(this.getLength() * this.getWidth() < arg0.getLength() * arg0.getWidth()) {
			return -1;
		} else if(this.getLength() * this.getWidth() > arg0.getLength() * arg0.getWidth()) {
			return 1;
		} else {
			return 0;
		}
		
	}
	
	public boolean equals(Object obj) {
		
		boolean result = false;
		
		if(!(obj instanceof Room)) {
			return false;
		}
				
		if(this.getLength() * this.getWidth() == ((Room)obj).getLength() * ((Room)obj).getWidth()) {
			result = true;
		}
		
		return result;
	}
	
}
