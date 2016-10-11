import java.text.DecimalFormat;

/**
 * @author Ricardo Vasquez
 *
 */
public class MyArrays {

	public static void main(String[] args) {
		
		System.out.println("********Integers***********");

		
		int size = 13;		
		int grades[] = new int[size];
		
		for (int i = 0; i < grades.length; i++) {
			grades[i] = (int)(Math.random() * 100);
		}
		
		for(int grade: grades) {
			System.out.println(grade);
		}
		
		System.out.println("********Doubles***********");

		
		size = 10;
		double[] data = new double[size];
		DecimalFormat df = new DecimalFormat("##.0000");
		for(int i = 0; i < data.length; i++) {
			data[i] = Math.random() * 500.00;
		}
		
		for(double d: data){
			System.out.println(df.format(d));
		}
		
		double result = findAverage(data);
		
		System.out.println("The average is: " + df.format(result));
		
		System.out.println("********Strings***********");

		String[] names;
		
		names = new String[4];
		names[0] = "Bob";
		names[1] = new String("Colin");
		names[2] = "Jay";
		names[3] = "Wayne";
		
		for(String name : names) { 
			System.out.println(name);
		}
	
		
	} //End main

	static double findAverage(double[] array){
		
		double average = 0.0, sum = 0.0; 
		
		for(int i = 0; i < array.length; i++){
			sum += array[i];
		}
		
		average = sum / array.length;
		
		return average;
		
	} //End findAverage
}
