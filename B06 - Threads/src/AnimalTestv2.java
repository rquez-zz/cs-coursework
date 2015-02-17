
public class AnimalTestv2 {

	public static void main(String[] args) {
		
		Food sharedFood = new Food();
		Thread rabbit = new Thread(new Animalv2("Rabbit", 7, 160, sharedFood));
		Thread turtle = new Thread(new Animalv2("Turtle", 5, 100, sharedFood));
		
		turtle.setPriority(Thread.NORM_PRIORITY);
		rabbit.setPriority(Thread.NORM_PRIORITY);
		
		turtle.start();
		rabbit.start();
		
	
		

	}

}
