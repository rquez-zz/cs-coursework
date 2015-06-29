
public class Room implements Comparable<Object>{
	
	private int length;
	private int width; 
	private String floorCovering;
	private int numberOfClosets;
	
	public Room (int length, int width, Flooring floorCovering, int numberOfClosets) {
		
		this.length = length;
		this.width = width;
		this.floorCovering = floorCovering.name();
		this.numberOfClosets = numberOfClosets;
	}
	
	public int getRoomArea() {
		return (this.length * this.width);
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
		String result;
		
		result = length + "x" + width + " room with " 
				+ floorCovering + " flooring and " 
				+ numberOfClosets + " closet(s).";
		
		return result; 
	}

	public int compareTo(Object obj) {
				
		if(!(obj instanceof Room)) {
			return 100;
		}
		
		if ( this.getRoomArea() > ((Room)obj).getRoomArea()) {
			return 1;
		} else if ( this.getRoomArea() < ((Room)obj).getRoomArea()) {
			return -1;
		} else {
			return 0;
		}
		
	}
	
	public boolean equals(Object obj) {
		
		if(!(obj instanceof Room)) {
			return false;
		}
		
		boolean result = false;
		
		if( this.getRoomArea() == ((Room)obj).getRoomArea() ) {
			result = true;
		}
		
		return result;
		
	}

}
