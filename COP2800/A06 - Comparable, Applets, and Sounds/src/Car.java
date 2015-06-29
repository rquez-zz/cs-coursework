import java.util.Random;

public class Car implements Comparable<Car>{

	public String name;
	public int speed;
	public int location;
	
	public Car(String name, int speed) {
		setName(name);
		setSpeed(speed);
		setLocation(0);
	}
	
	public void accelerate(int changeInSpeed) {
		this.speed += changeInSpeed;
	}
	
	public void decelerate(int changeInSpeed) {
		this.speed -= changeInSpeed;
	}
	
	public void randomSpeedChange() {
		Random randomGenerator = new Random();
		this.speed += randomGenerator.nextInt(21) - 10;
	}
	
	public int compareTo(Car other) {
		//Used to compare cars' locations in an array
		return other.getLocation() - this.getLocation();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public int getLocation() {
		return location;
	}

	public void setLocation(int location) {
		this.location = location;
	}
	
	public String toString() {
		return  String.format("%-10s [Speed: %-4d Location: %-3d]", name, speed, location);
	}

}
