
public class SlotMachine {

	private int timesPlayed;
	private int timesPlayedToWin;
	private int quartersPaid;
	
	private static int totalTimesPlayed = 0;
	
	public SlotMachine (int timesPlayed, int timesPlayedToWin, int quartersPaid) {
		this.timesPlayed = timesPlayed;
		this.timesPlayedToWin = timesPlayedToWin;
		this.quartersPaid = quartersPaid;
	}
	
	// This method returns quarter amount after playing the slot
	public int playSlotYield(int quarterJar) {
		
		// When there are no quarters
		if (quarterJar == 0){
			return 0;
		}
		
		// Increment times played and take away a quarter
		this.timesPlayed++;
		totalTimesPlayed++;
		quarterJar--;
		
		// Pay out and reset times played
		if (this.timesPlayed == this.timesPlayedToWin) {
			quarterJar += this.quartersPaid;
			this.setTimesPlayed(0);
			return quarterJar;
		} else {
			// Returns a jar amount with a quarter less
			return quarterJar;
		}
		
	}
	
	public static int getTotalTimesPlayed() {
		return totalTimesPlayed;
	}

	public int getTimesPlayed() {
		return timesPlayed;
	}

	public void setTimesPlayed(int timesPlayed) {
		this.timesPlayed = timesPlayed;
	}

	public int getTimesPlayedToWin() {
		return timesPlayedToWin;
	}

	public void setTimesPlayedToWin(int timesPlayedToWin) {
		this.timesPlayedToWin = timesPlayedToWin;
	}

	public int getQuartersPaid() {
		return quartersPaid;
	}

	public void setQuartersPaid(int quartersPaid) {
		this.quartersPaid = quartersPaid;
	}
	
	public String toString() {
		String result;
		result = "Times Played: " + this.timesPlayed + " Times to Win: " + this.timesPlayedToWin + " Payout: " + this.quartersPaid;
		return result;
	}
	
}
