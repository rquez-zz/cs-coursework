import java.util.Arrays;


public class GrandPrix {

	public static void main(String[] args) {
		
		//Names of the drivers
		String[] drivers = {"Schumacher", "Prost", "Senna", "Alonso", "Mansell", 
				"Fangio", "Hill", "Ascari", "Andretti", "Montoya"};
		Car[] cars = new Car[10];
		
		//Fill cars array with the names of drivers
		for(int i = 0; i < drivers.length; i++) {
			cars[i] = new Car(drivers[i], 30);
		}
		
		System.out.println("...And they're off!");
		
		//Main Race Loop
		for(int i = 0; !isRaceOver(cars); i++) {
						
			//Adjust speed and location of all cars
			for(int j = 0; j < cars.length; j++) {
				
				cars[j].randomSpeedChange();
				cars[j].setLocation(cars[j].getLocation() + cars[j].getSpeed());
				
				//Accelerate if car is at or below 0
				if (cars[j].getSpeed() <= 0) {
					cars[j].accelerate(5);
				}
			}
			
			//Sort the cars array according to descending location order
			Arrays.sort(cars);
			
			//Announce positions and status
			announceRaceStatus(cars, i);
		}
		
		System.out.printf("...and %s crossess the finish line! Going %dmph!", cars[0].name, cars[0].speed);
		
	}
	
	public static boolean isRaceOver(Car[] cars) {
		
		boolean carHasFinished = false;
		
		//Check if any of the cars have reached or past the finish line
		for (int i = 0; !carHasFinished && i < cars.length; i++) {
			if (cars[i].getLocation() >= 1000) {
				carHasFinished = true;
			}
		}
		
		return carHasFinished;
	}
	
	public static void announceRaceStatus(Car[] cars, int lap){
		
		System.out.print("\nLap " + (lap + 1) + "\t");
		
		int locationDifference = cars[0].getLocation() - cars[1].getLocation();
		
		//Announce first place status
		if (locationDifference == 0) {
			System.out.printf("%s and %s are neck and neck!\n", cars[0].name, cars[1].name);
		} else if (locationDifference <= 5 ) {
			System.out.printf("%s leads %s by just a hair!\n", cars[0].name, cars[1].name);
		} else if (locationDifference <= 10) {
			System.out.printf("%s has taken first place!\n", cars[0].name);
		} else if (locationDifference <= 40) {
			System.out.printf("%s is on a steady lead!\n", cars[0].name);
		} else if (locationDifference <= 80) {
			System.out.printf("%s leads the race in first place!\n", cars[0].name);
		} else if (locationDifference <= 110) {
			System.out.printf("%s is really leading this race!\n", cars[0].name);
		} else if (locationDifference <= 150) {
			System.out.printf("%s is just blowing everyone away!\n", cars[0].name);
		} else if (locationDifference <= 250) {
			System.out.printf("%s has left all the other races in the dust!\n", cars[0].name);
		} else {
			System.out.printf("%s is unstoppable!\n", cars[0].name);
		}
		
		
		//Display positions of the cars 
		for (int i = 0; i < cars.length; i++) {
			
			String numberedPosition;
			
			//Determines the correct ordinal number, offset for i = 0;
			if ( i == 0 || i % 10 == 0){
				numberedPosition = "st";
			} else if ( i == 1 || (i - 1) % 10 == 0) {
				numberedPosition = "nd";
			} else if ( i == 2 || (i - 2) % 10 == 0) {
				numberedPosition = "rd";
			} else {
				numberedPosition = "th";
			}
			
			System.out.println((i+1) + numberedPosition + "\t" + cars[i].toString());
		}
				
	}

}
