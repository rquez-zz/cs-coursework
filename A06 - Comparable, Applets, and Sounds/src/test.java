import java.util.Arrays;


public class test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		//Names of the drivers
		String[] drivers = {"Schumacher", "Prost", "Senna", "Alonso", "Mansell", 
				"Fangio", "Hill", "Ascari", "Andretti", "Montoya"};
		Car[] cars = new Car[10];
		
		//Fill cars array with the names of drivers
		for(int i = 0; i < drivers.length; i++) {
			cars[i] = new Car(drivers[i], 30);
		}
		
		for(int j = 0; j < cars.length; j++) {
			cars[j].randomSpeedChange();
			cars[j].setLocation(cars[j].getLocation() + cars[j].getSpeed());
		}
		
		for(int i = 0; i < cars.length; i++) {
			System.out.println(cars[i].toString());
		}
		
		System.out.println("");
		
		Arrays.sort(cars);
		
		for(int i = 0; i < cars.length; i++) {
			System.out.println(cars[i].toString());
		}
		
	}

}
