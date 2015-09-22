
public class RaceCar {

	private int speed;
	private String name;
	private int location = 0;
	
	public int getLocation() {
		return location;
	}

	public void setLocation(int location) {
		this.location = location;
	}

	private static int maxSpeedForAll = 120;
	
	public RaceCar(int speed, String name) {
		setSpeed(speed);
		setName(name);
	}
	
	public static int getMaxSpeedForAll() {
		return maxSpeedForAll;
	}
	
	public static void setMaxSpeedForAll(int maxSpeedForAll) {
		// speed = 40; not allocated!
		RaceCar.maxSpeedForAll = maxSpeedForAll;
	}
	
	public int getSpeed() {
		return speed;
	}
	
	public void setSpeed(int speed) {
		if (speed <= maxSpeedForAll){
			this.speed = speed;
		} else {
			this.speed = maxSpeedForAll;
		}	
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name; 
	}
	
	public String toString() {
		return "RaceCar [name=" + name + ", speed=" + speed + ", location=" + location + "]";
	}

}
