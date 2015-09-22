
public class Dog extends Animal{

	private String name;
	
	public Dog (int weight, String name){
		super(weight);
		setName(name);
	}
	
	public boolean equals(Object obj) {
		
		if(!(obj instanceof Dog)) {
			return false;
		}
		
		boolean result = false;
		
		if(this.getWeight() == ((Dog)obj).getWeight()) {
			result = true;
		}
		
		return result;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String toString() {
		String result;
		result = "Dog: " + name + super.toString() + getWeight();
		return result;
	}
}
