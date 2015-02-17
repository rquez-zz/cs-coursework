
public class Date {

	private int month; // instance
	private int day;
	private int year;
	
	public Date(int monthSet, int daySet, int yearSet) {
		month = monthSet;
		day = daySet;
		year = yearSet;
	}
	
	public int getMonth() { // accessor (getters)
		return month;
	}
	
	public void setMonth(int monthSet) {
		if(monthSet >= 1 && monthSet <= 12){
			month = monthSet;
		}
	}
	
	public int getDay() {
		return day;
	}
	
	public void setDay(int daySet) {
		if(daySet >= 1 && daySet <= 31) {
			day = daySet;
		}
	}
	
	public int getYear() {
		return year;
	}
	
	public void setYear(int yearSet) {
		year = yearSet;
	}
	
	public String toString() {
		String result;
		result = month + "/" + day + "/" + year;
		return result;
	}
	
}
