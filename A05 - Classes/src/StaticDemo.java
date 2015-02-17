
public class StaticDemo {

	public static void main(String[] args) {
		int raceLength = 1000;
		
		RaceCar andretti = new RaceCar(60, "Andretti");
		
		andretti.setSpeed(100);
		
		// andretti.getMaxSpeedForAll(); 
		
		while(andretti.getLocation() < raceLength) {
			
			andretti.setLocation(andretti.getLocation() + andretti.getSpeed());
			System.out.println(andretti + " is in the first place!");
			
		}

	}

}
