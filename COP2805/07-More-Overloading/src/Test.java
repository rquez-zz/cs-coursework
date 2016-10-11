public class Test{

	public static void main(String[] args){
		Triangle t1;
		Triangle t2;
		Triangle t3;
		Triangle t4;
		try {
			t1 = new Triangle(10, 20, 25); 
			t2 = new Triangle(10.1, 20.5, 25.1);
			t3 = new Triangle(10.1, 20.5, 25.1);
			t4 = new Triangle(3,4,5);
    			
			System.out.println("T1: " + t1.toString());
			System.out.println("T2: " + t2.toString());
			boolean equalsResult = t1.equals(t2);
			int compareResult = t1.compareTo(t2);
			System.out.println("Compare T1 to T2 is " + compareResult);
			System.out.println("T1 is equal to T2 is " + equalsResult + "\n");
			
			System.out.println("T2: " + t2.toString());
			System.out.println("T3: " + t3.toString());
			equalsResult = t2.equals(t3);
			compareResult = t2.compareTo(t3);
			System.out.println("Compare T2 to T3 is " + compareResult);
			System.out.println("T2 is equal to T3 is " + equalsResult + "\n");
			
			System.out.println("T4: " + t4.toString());
			System.out.println("T1: " + t1.toString());
			equalsResult = t4.equals(t1);
			compareResult = t4.compareTo(t1);
			System.out.println("Compare T4 to T1 is " + compareResult);
			System.out.println("T4 is equal to T1 is " + equalsResult + "\n");
			
			System.out.println("T4: " + t4.toString());
			System.out.println("T2: " + t2.toString());
			equalsResult = t4.equals(t2);
			compareResult = t4.compareTo(t2);
			System.out.println("Compare T4 to T2 is " + compareResult);
			System.out.println("T4 is equal to T2 is " + equalsResult + "\n");
			
			System.out.println("This won't print");
			

		} catch (InvalidTriangleException ite) {
			System.out.println(ite.getMessage()); 			
		}
		
	}

}