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
	private boolean playing;
	
	public Player(String name, String token, Position pos, boolean playing) {
		this.name = name;
		this.token = token;
		this.moves = 0;
		this.pos = pos;
		this.oldPos = pos;
		this.playing = playing;
	}
	
	public void addCard(Card c) {
		myCards.add(c);
	}

	public void newPosition(int x, int y){
		System.out.println("OLDpos x: " + this.oldPos.x);
		this.oldPos = new Position(this.pos.x, this.pos.y);//this.pos;
		System.out.println("OLDpos x: " + this.oldPos.x);
		System.out.println("OLDpos y: " + this.oldPos.y);
		System.out.println("OLDpos x: " + this.getOldXPosition());
		System.out.println("OLDpos y: " + this.getOldYPosition());
		this.pos.x += x;
		this.pos.y += y;
		System.out.println("pos x: " + pos.x);
		System.out.println("pos y: " + pos.y);
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
	
	public Position getPosition() {
		return this.pos;
	}
	
	public boolean isPlaying() {
		return this.playing;
	}
	
	public String getToken() {
		return this.token;
	}
	
	public String showCards() {
		String s = "";
		for (int i = 0; i < myCards.size(); i++) {
			s += myCards.get(i).toString();
		}
		return s;
	}
	
	public String toString() {
		return ("Player Name: " + this.name + "\n");
	}
}
