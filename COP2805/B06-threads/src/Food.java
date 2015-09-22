
public class Food {
	
	public  void eat(int eatTime, String name) {
		
		System.out.println("-" + name + " is eating.");
		
		try {
			Thread.sleep(eatTime);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.println("-" + name + " is done eating.");
		
	}
	
}
