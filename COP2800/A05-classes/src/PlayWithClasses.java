public class PlayWithClasses {


	public static void main(String[] args) {
		Date birthday = new Date(11, 16, 1960);
		
		// birthday.month = 19;
		// birthday.day =  16;
		// birthday.year = 1960;
		
		birthday.setMonth(11);
		
		System.out.println( birthday.toString() );
	}

}
