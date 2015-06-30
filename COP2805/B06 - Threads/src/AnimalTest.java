

public class AnimalTest {
	
	public static void main (String[] args) {
		
		Thread rabbit = new Thread(new Animal("Rabbit", 5, 150));
		Thread turtle = new Thread(new Animal("Turtle", 3, 100));
		
		turtle.setPriority(Thread.NORM_PRIORITY);
		rabbit.setPriority(Thread.NORM_PRIORITY);
		
		turtle.start();
		rabbit.start();
		
				
	}

}
