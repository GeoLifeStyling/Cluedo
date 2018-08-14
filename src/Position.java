/**
 * This is a class that holds a x and y value to store the position of objects within
 * the 2d array of the board
 */
public class Position {

	int x, y;
	
	public Position(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public int getXPosition() {
		return this.x;
	}
	
	public int getYPosition() {
		return this.y;
	}
	
}
