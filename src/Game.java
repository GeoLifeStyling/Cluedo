import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Game {
	// To store all of the players, cards on board,
	Character c;
	Room r;
	Weapon w;
	Board board;
	int numPlayers;
	boolean gamePlaying = true;

	ArrayList<Card> solutionCards = new ArrayList<Card>(); // Murder mystery cards

	ArrayList<Card> deckCards = new ArrayList<Card>();
	ArrayList<Player> players = new ArrayList<Player>();

	ArrayList<Card> charCards = new ArrayList<Card>();
	ArrayList<Card> roomCards = new ArrayList<Card>();
	ArrayList<Card> weapCards = new ArrayList<Card>();

	/**
	 * Starts new Game Generates all new initial cards, players and board.
	 */
	public void start() throws IOException{
		this.board = new Board();
		this.board.create();
		createCards();
		generateMurder();
		makeDeck();
		startGameInterface();
		initializePlayers();
		addPlayerToBoard();
		this.board.draw();
		startGame();
		
	}
	
	private void startGame(){

		while (gamePlaying) {
			System.out.println("Move your character with w/a/s/d for up/left/down/right respectively");
			for(int i = 0; i < numPlayers; i++) {
				if(i == numPlayers){ i = 0; }
				Player player = players.get(i);
				player.addMoves(rollDice());
				int maxMoves = player.getMoves();
				
				System.out.println(player.toString() + " roll: " + player.getMoves());

						for(int j = 0; j < maxMoves; j++) {
							
							Position p = movePlayer();
							
							while(checkTile(player, p)) {
								p = movePlayer();
							}
							
							player.newPosition(p.x, p.y);
							player.moved();
							board.placePlayer(player);
							board.draw();
						}
			}
		}
	}
	
	private boolean checkTile(Player player, Position p) {
		String s  = "";
		s = board.checkBoard(player.getXPosition()+p.x, player.getYPosition()+p.y);
		System.out.println("Checktile - board value: " + s);
		switch (s) {
			case " ":
				return false;
			case "^":
				return false;
			case ">":
				return false;
			case "<":
				return false;
			case "v":
				return false;
		}
		System.out.println("invalid direction please try again");
		return true;
	}
	
	private int rollDice() {
		int dice1 =(int) (Math.random()*(7-1)+1);
		int dice2 =(int) (Math.random()*(7-1)+1);
		return (dice1 + dice2);
	}

	private Position movePlayer() {
		System.out.println("Move direction: \n");
		Scanner move = new Scanner(System.in);
		String l = move.nextLine();

		int dx = 0;
		int dy = 0;

		if(l.equals("w")) {
			System.out.println("up");
			dx = -1;
		}else if(l.equals("a")) {
			System.out.println("left");
			dy = -1;
		}else if(l.equals("s")) {
			System.out.println("down");
			dx = 1;
		}else if(l.equals("d")) {
			System.out.println("right");
			dy = 1;
		}

		Position p = new Position(dx, dy);
		return p;
	}
	
	private void addPlayerToBoard() {
		for (int i = 0; i < numPlayers; i++) {
			board.placePlayer(players.get(i));
			Player p = players.get(i);
		}
	}

	private void startGameInterface() {
		Scanner s = new Scanner(System.in);
		boolean incorrectPlayerSize = true;
		int input = 0;
		String cache = "";
		
		System.out.println("Welcome to Cluedo!\n");
		System.out.println("You need 3 - 6 players,");
		
		System.out.println("How many Players would like to play?\n"
				+ "Please type 3, 4, 5 or 6 and hit enter.\n");
		try {
		input = s.nextInt();
		}catch (InputMismatchException e) {
			System.out.print("Invalid number\n");
		}
		
		if(input >= 3 && input <= 6) {
			numPlayers = input;
			incorrectPlayerSize = false;
		}else {
			while (incorrectPlayerSize) {
				System.out.println("How many Players would like to play?\n"
					+ "Please type 3, 4, 5 or 6 and hit enter.\n");
				try {
				input = s.nextInt();
				}catch(InputMismatchException e) {
					System.out.print("Invalid number\n");
					cache = s.nextLine();
				}
				if(input >= 3 && input <= 6) { 
					numPlayers = input;
					incorrectPlayerSize = false;
				}
			}
		}

	}

	/**
	 * Generates the 3 cards for the murder solution Chooses 1 card at random from
	 * each card type, Adds theses cards to the solution card array Removes these
	 * cards from there respective decks
	 */
	private void generateMurder() {
		this.c = (Character) charCards.get((int) (Math.random() * (6 - 0)));
		if (charCards.contains(this.c)) {
			charCards.remove(this.c);
		}

		// TEST System.out.println("After Removal: " + charCards.size() + "\n"); --WORKS
		// TEST System.out.println(this.c.toString()); -- WORKS

		this.r = (Room) roomCards.get((int) (Math.random() * (9 - 0)));
		if (roomCards.contains(this.r)) {
			roomCards.remove(this.r);
		}

		// TEST System.out.println("After Removal: " + roomCards.size() + "\n"); --WORKS
		// TEST System.out.println(this.r.toString()); -- WORKS

		this.w = (Weapon) weapCards.get((int) (Math.random() * (6 - 0)));
		if (weapCards.contains(this.w)) {
			weapCards.remove(this.w);
		}

		// TEST System.out.println("After Removal: " + weapCards.size() + "\n"); --WORKS
		// TEST System.out.println(this.w.toString()); -- WORKS

		solutionCards.add(this.c);
		solutionCards.add(this.r);
		solutionCards.add(this.w);

		// TEST System.out.println("Envelope Len: " + hiddenEnvelope.size()); -- WORKS
	}

	/**
	 * Makes the deck with the remaining cards ready to be handed out
	 */
	private void makeDeck() {
		deckCards.addAll(charCards);
		deckCards.addAll(roomCards);
		deckCards.addAll(weapCards);

		Collections.shuffle(deckCards); // Works sufficiently Shuffle Deck

		// TEST System.out.println(deckCards.size()); --WORKS
	}

	/**
	 * Checks to see if choosen cards are correct to solve the mystery
	 * 
	 * @param c
	 *            - Character Card c
	 * @param r
	 *            - Room Card r
	 * @param w
	 *            - Weapon Card w
	 * @return - returns true or false depending if all parameter cards match
	 *         solution cards
	 */
	private boolean checkHiddenEnvelope(Character c, Room r, Weapon w) {
		if (c == this.c && r == this.r && w == this.w) {
			return true;
		}
		return false;
	}

	/**
	 * Creates all the playing cards int the game
	 */
	private void createCards() {
		// Character Cards Created
		Card missScar = new Character("Miss Scarlet");
		Card profPlum = new Character("Professor Plum");
		Card mrsPea = new Character("Mrs. Peacock");
		Card mrsWhi = new Character("Mrs. White");
		Card colMus = new Character("Colonel Mustard");
		Card mrGreen = new Character("Mr. Green");
		charCards.add(missScar);
		charCards.add(profPlum);
		charCards.add(mrsPea);
		charCards.add(mrsWhi);
		charCards.add(colMus);
		charCards.add(mrGreen);

		// Room Cards Created
		Card kitchen = new Room("Kitchen");
		Card ballRoom = new Room("Ball Room");
		Card conserv = new Room("Conservatory");
		Card billiard = new Room("Billiard Room");
		Card library = new Room("Library");
		Card study = new Room("Study");
		Card hall = new Room("Hall");
		Card lounge = new Room("Lounge");
		Card dining = new Room("Dining Room");
		roomCards.add(kitchen);
		roomCards.add(ballRoom);
		roomCards.add(conserv);
		roomCards.add(billiard);
		roomCards.add(library);
		roomCards.add(study);
		roomCards.add(hall);
		roomCards.add(lounge);
		roomCards.add(dining);

		// Weapon Cards created
		Card candle = new Weapon("Candle Stick");
		Card dagger = new Weapon("Dagger");
		Card leadPipe = new Weapon("Lead Pipe");
		Card revolver = new Weapon("Revolver");
		Card rope = new Weapon("Rope");
		Card spanner = new Weapon("Spanner");
		weapCards.add(candle);
		weapCards.add(dagger);
		weapCards.add(leadPipe);
		weapCards.add(revolver);
		weapCards.add(rope);
		weapCards.add(spanner);

		// TEST System.out.println(cards.toString());

	}
	
	private void initializePlayers() {
		
		Position p1pos = new Position(25,8);
		Position p2pos = new Position(20,24);
		Position p3pos = new Position(7,24);
		Position p4pos = new Position(1,10);
		Position p5pos = new Position(18,1);
		Position p6pos = new Position(1,15);
		
		Player p1 = new Player("Miss Scarlet", "1", p1pos, true);
		Player p2 = new Player("Professor Plum", "2", p2pos, false);
		Player p3 = new Player("Mrs. Peacock", "3", p3pos, false);
		Player p4 = new Player("Mrs. White", "4", p4pos, false);
		Player p5 = new Player("Colonel Mustard", "5", p5pos, false);
		Player p6 = new Player("Mr. Green", "6", p6pos, false);
		
		players.add(p1);
		players.add(p2);
		players.add(p3);
		players.add(p4);
		players.add(p5);
		players.add(p6);
		
		// Gives out remaining deck cards to players
		for(int i = 0, j = 0; i < deckCards.size(); i++, j++) {
			if (j == (numPlayers)) {
					j = 0;
			}
			players.get(j).addCard(deckCards.get(i));
		}
	
			/* TEST  - WORKS - handing out of cards ie. splitting cards
		for (int i = 0; i < numPlayers; i++) {
			System.out.println(players.get(i).toString() + players.get(i).showCards());
		}
		*/
		
		
			
	}

}


/*

	public Position keyPressed(KeyEvent e) throws InterruptedException {
		System.out.println("now wait");
		Thread.sleep(700);
		System.out.println("now begin");

		int dx = 0;
		int dy = 0;
		int key = e.getKeyCode();

		if(key == KeyEvent.VK_LEFT) {
			System.out.println("left ");
			dx = -1;
		}else if(key == KeyEvent.VK_RIGHT) {
			System.out.println("left ");
			dx = 1;
		}else if(key == KeyEvent.VK_UP) {
			System.out.println("left ");
			dy = -1;
		}else if(key == KeyEvent.VK_DOWN) {
			System.out.println("left ");
			dy = -1;
		}

		Position p = new Position(dx, dy);
		return p;
	}
 */