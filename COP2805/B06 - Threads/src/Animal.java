
public class Animal implements Runnable {
	
	private String name;
	private int position; 
	private int speed;
	private int restMax; //time between rests
	private static boolean winner; 
	private static int distance; 
	
	public Animal(String name, int speed, int restMax) {
		
		this.position = 0;
		this.name = name;
		this.speed = speed; 
		this.restMax = restMax; 
		winner = false; 
		distance = 100; 
	}
	
	public void run() {
		
		while(!winner) {
			
			this.position += this.speed;
			
			System.out.println(name + " is running! ");
			System.out.println(name + " Postion: " + this.position);
			
			try {
				Thread.sleep((long)Math.random() * (this.restMax + 1));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			if (this.position >= distance) {
				winner = true;
				
				System.out.println(this.name + " is the winner!");
			}
			
			
		}
		
	}

}
