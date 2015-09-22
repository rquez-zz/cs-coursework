import java.util.ArrayList;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		ArrayList<Building> buildingsArray = new ArrayList<Building>();
		
		Room squareRoom = new Room(2, 2, "Carpet", 2);
		Room blueRoom = new Room(2, 3, "Marble", 1);
		Room redRoom = new Room(3, 2, "Wood", 1);
		Room greenRoom = new Room(3, 3, "Marble", 2);
		Room diningRoom = new Room(4, 5, "Wood", 3);
		Room livingRoom = new Room(5, 5, "Carpet", 4);
		
		Room[] roomSet1 = { squareRoom, blueRoom, redRoom }; 
		Room[] roomSet2 = { diningRoom, livingRoom };
		Room[] roomSet3 = { blueRoom, greenRoom, redRoom };
		Room[] roomSet4 = { livingRoom, redRoom, squareRoom };
		
		House house1 = new House(roomSet1, 4, 6, 2);
		House house2 = new House(roomSet2, 2, 4, 1);
		House house3 = new House(roomSet3, 3, 2, 2);
		House house4 = new House(roomSet4, 4, 4, 3);
		
		Garage garage1 = new Garage(1, 5, 5, 2, "Cement");
		Garage garage2 = new Garage(1, 6, 6, 3, "Gravel");
		Garage garage3 = new Garage(2, 6, 7, 4, "Cement");
		Garage garage4 = new Garage(2, 5, 6, 3, "Gravel");
		
		buildingsArray.add(house1);
		buildingsArray.add(house2);
		buildingsArray.add(house3);
		buildingsArray.add(house4);
		buildingsArray.add(garage1);
		buildingsArray.add(garage2);
		buildingsArray.add(garage3);
		buildingsArray.add(garage4);
		
		for (Building aBuilding : buildingsArray) {
		
			if (aBuilding instanceof House) {
				housePassMethod((House)aBuilding);
			} else {
				System.out.println(aBuilding.toString());
			}
		}
		
		System.out.println("Red Room compare to Blue Room: " + blueRoom.compareTo(redRoom));

		if (blueRoom.equals(redRoom)) {
			System.out.println("Blue Room is equal to Red Room in size.");
		} else {
			System.out.println("Blue Room is not equal to Red Room in size.");
		}
		
		System.out.println("Green Room compared to Blue Room: " + greenRoom.compareTo(blueRoom));

		if (greenRoom.equals(blueRoom)) {
			System.out.println("Green Room is equal to Blue Room in size.");
		} else {
			System.out.println("Green Room is not equal to Blue Room in size.");
		}
		
		

	}
	
	public static void housePassMethod(MLSListable house) {
		
		System.out.println(house.getMLSListing());
		
	}

}
