

import java.util.ArrayList;

public class Player {

	// Add a position class, for keeping track of position
	// add all starting weapons intialised
	private ArrayList<Card> myCards = new ArrayList<Card>();
	private String name;
	private int moves; 
	private String token;
	private Position pos;
	private Position oldPos;
	
	public Player(String name, String token, Position pos) {
		this.name = name;
		this.token = token;
		this.moves = 0;
		this.pos = pos;
		this.oldPos = pos;
	}
	
	public void addCard(Card c) {
		myCards.add(c);
	}

	/**
	 * Updates position of player and stores old position
	 * @param x - position x
	 * @param y - position y
	 */
	public void newPosition(int x, int y){
		this.oldPos = new Position(this.pos.x, this.pos.y);
		this.pos.x += x;
		this.pos.y += y;
	}

	public int getOldXPosition(){ return this.oldPos.x; }

	public int getOldYPosition(){ return this.oldPos.y; }
	
	public void addMoves(int moves){
		this.moves = moves;
	}

	public void moved(){ this.moves--;
		System.out.println("moves left: " + this.moves);
	}
	
	public int getMoves() {
		return this.moves;
	}
	
	public int getXPosition() {
		return this.pos.x;
	}
	
	public int getYPosition() {
		return this.pos.y;
	}

	/**
	 * this is the player token used to display this player on the board
	 * @return String - ASCII value to represent player
	 */
	public String getToken() {
		return this.token;
	}

	/**
	 * For when the player makes a refute
	 */
	public void displayCards() {
		for (int i = 0; i < myCards.size(); i++) {
			System.out.println(i + ") " + myCards.get(i).toString());
		}
	}
	
	public String toString() {
		return ("Player Name: " + this.name);
	}
}
