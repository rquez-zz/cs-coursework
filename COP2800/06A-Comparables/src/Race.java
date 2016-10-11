
public class Race {

	public static void main(String[] args) {
		
		Car andretti = new Car("Andretti", 30);
		Car montoya = new Car("Montoya", 30);
		int trackLength = 1000;
		
		System.out.println("...And they're off!");

		//Race Loop
		for(int i = 1; andretti.location < trackLength && montoya.location < trackLength; i++) {
		
			//Randomly change speed of cars
			andretti.randomSpeedChange();
			montoya.randomSpeedChange();
			
			andretti.setLocation(andretti.getSpeed() + andretti.getLocation());
			montoya.setLocation(montoya.getSpeed() + montoya.getLocation());
			
			//Change speeds in respect to current position
			shiftSpeeds(andretti, montoya); 
			
			//Announce positions and status
			announceRaceStatus(andretti, montoya, trackLength, i);
			
		}

	}
	
	public static void announceRaceStatus(Car car1, Car car2, int length, int lap) {
		
		System.out.print("Lap " + lap + ": ");
		
		String firstPlace = "", secondPlace = ""; 
		int locationDifference = Math.abs(car1.getLocation() - car2.getLocation());
		
		//Car 1 is in the lead
		if ( car1.getLocation() > car2.getLocation() ) {
			firstPlace = car1.toString();
			secondPlace = car2.toString();
			
			if ( locationDifference <= 5) {
				System.out.println(car1.name + " is leading " + car2.name + " by just a hair!");
			} else if ( locationDifference > 200) {
				System.out.println(car1.name + " has left " + car2.name + " in the dust!");
			} else if ( locationDifference > 100) {
				System.out.println(car1.name + " is really leading this race!");
		    } else if ( locationDifference > 50) {
		    	System.out.println(car1.name + " has a steady lead against " + car2.name);
		    } else {
				System.out.println(car1.name + " is in the lead!");
			}
			
		}
		
		//Car 2 is in the lead
		if ( car1.getLocation() < car2.getLocation() ) {
			firstPlace = car2.toString();
			secondPlace = car1.toString(); 
			
			if ( locationDifference <= 5) {
				System.out.println(car2.name + " is leading " + car1.name + " by just a hair!");
			} else if ( locationDifference >= 200) {
				System.out.println(car2.name + " has left " + car1.name + " in the dust!");
			} else if ( locationDifference > 100) {
				System.out.println(car2.name + " is really leading this race!");
			} else if ( locationDifference > 50) {
				System.out.println(car2.name + " has a steady lead against " + car1.name);
			} else {
				System.out.println(car2.name + " is in the lead!");
			}
		}
		
		//Both cars are tied
		if ( car1.getLocation() == car2.getLocation()) {
			firstPlace = car1.toString() + " vs " + car2.toString();
			System.out.println(car1.name + " and " + car2.name + " are head to head!");
		}
		
	
		//Announce when the race has finished
		if (car1.getLocation() >= length && car1.getLocation() > car2.getLocation()) {
			System.out.println(car1.name + " crossess the finish line and wins!");
		} else if (car2.getLocation() >= length && car2.getLocation() > car1.getLocation()) {
			System.out.println(car2.name + " crossess the finish line and wins!");
		}
		
		// Display Positions
		System.out.println("1st: " + firstPlace);
		System.out.println("2nd: " + secondPlace + "\n");
		
	}

	public static void shiftSpeeds(Car car1, Car car2) {
		
		if (car1.getLocation() > car2.getLocation()) {
			car1.decelerate(2);
			car2.accelerate(2);
		} else if (car2.getLocation() > car1.getLocation()) {
			car2.decelerate(2);
			car1.accelerate(2);
		}
	}
}
