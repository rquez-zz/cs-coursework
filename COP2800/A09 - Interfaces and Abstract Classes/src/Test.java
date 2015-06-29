
public class Test {

	public static void main(String[] args) {
		
		Room[] roomSetOne = new Room[3];
		roomSetOne[0] = new Room(2,3,Flooring.getRandom(),1);
		roomSetOne[1] = new Room(3,1,Flooring.getRandom(),1);
		roomSetOne[2] = new Room(4,2,Flooring.getRandom(),2);

		Room[] roomSetTwo = new Room[4];
		roomSetTwo[0] = new Room(1,4,Flooring.getRandom(),2);
		roomSetTwo[1] = new Room(3,2,Flooring.getRandom(),3);
		roomSetTwo[2] = new Room(5,1,Flooring.getRandom(),2);
		roomSetTwo[3] = new Room(2,2,Flooring.getRandom(),4);

		House testHouseOne = new House(5, 2, "Pacific", 3, roomSetOne);
		House testHouseTwo = new House(7,4, "Atlantic", 5, roomSetTwo);
		
		Garage testGarageOne = new Garage(2, 5, 6, 4, Flooring.getRandom());
		Garage testGarageTwo = new Garage(1, 10, 10, 9, Flooring.getRandom());

		System.out.println("House One (House.toString)");
		System.out.println(testHouseOne.toString());
		
		System.out.println("House One Rooms (Room.toString)");
		for (int i=0; i < testHouseOne.getRooms().length; i++) {
			System.out.println(testHouseOne.getRooms()[i].toString());
		}
		
		System.out.println("\nGarage One (Garage.toString)");
		System.out.println(testGarageOne.toString());
		
		System.out.println("House Two (House.toString)");
		System.out.println(testHouseTwo.toString());
		
		System.out.println("House Two Rooms (Room.toString)");
		for (int i=0; i < testHouseTwo.getRooms().length; i++) {
			System.out.println(testHouseTwo.getRooms()[i].toString());
		}
	
		System.out.println("\nGarage Two (Garage.toString)");
		System.out.println(testGarageTwo.toString());	
		
		// Part I
		housePassMethod(testHouseOne);
		//housePassMethod(testGarageOne);  // Will not compile
		//housePassMethod(roomSetTwo[0]); // Will not compile
		
		// Part II
		Room testRoomOne = new Room(3,1, Flooring.Aluminum, 1);
		Room testRoomTwo = new Room(1,3, Flooring.Carpet, 2);
		
		System.out.println("Comparing test Room 1 and test Room 2: " + testRoomOne.compareTo(testRoomTwo));
		System.out.println("Are test Room 1 and test Room 2 equal? " + testRoomOne.equals(testRoomTwo));
		
		Room testRoomThree = new Room(3,1, Flooring.Aluminum, 1);
		Room testRoomFour = new Room(2,3, Flooring.Carpet, 2);
		
		System.out.println("Comparing test Room 3 and test Room 4: " + testRoomThree.compareTo(testRoomFour));
		System.out.println("Are test Room 3 and test Room 4 equal? " + testRoomThree.equals(testRoomFour));


	}
	
	public static void housePassMethod(MLSListable house) {
		
		System.out.println(house.getMLSListing());
	}

}
