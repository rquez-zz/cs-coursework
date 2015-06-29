public enum Flooring {
	
	Wood, Stone, Tile, Carpet, Metal, Aluminum, Brick, Cement, Marble;
	
	// Returns a random Flooring enum value
	public static Flooring getRandom() {
		return values()[(int)(Math.random() * values().length)];
	}
	
}
