import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Game {
	// To store all of the players, cards on board,
	private Character c;
	private Room r;
	private Weapon w;
	private Board board;
	private int numPlayers;
	private boolean gamePlaying = true;
	private int maxMoves;
	private boolean ifAccusation = false;
	private boolean madeAccusation = false;

	private ArrayList<Card> solutionCards = new ArrayList<Card>(); // Murder mystery cards

	private ArrayList<Player> players = new ArrayList<Player>();
	private ArrayList<RoomTile> rooms = new ArrayList<>();

	private ArrayList<Card> allCards = new ArrayList<Card>();
	private ArrayList<Card> deckCards = new ArrayList<Card>();
	private ArrayList<Card> charCards = new ArrayList<Card>();
	private ArrayList<Card> roomCards = new ArrayList<Card>();
	private ArrayList<Card> weapCards = new ArrayList<Card>();

	/**
	 * Starts new Game Generates all new initial cards, players and board.
	 */
	public void start() throws IOException{
		this.board = new Board();
		this.board.create();
		createCards();
		createRoomTiles();
		addWeaponsToRooms();
		generateMurder();
		makeDeck();
		startGameInterface();
		initializePlayers();
		addPlayerToBoard();
		this.board.draw();
		startGame();
		
	}

	/**
	 * This the where the game is run from and controls the game as the players progress
	 */
	private void startGame(){

		while (gamePlaying) {

			System.out.println("Move your character with w/a/s/d for up/left/down/right respectively\nOr make an accusation with 'g' But beware you will lose if incorrect\n");
			for(int i = 0; i < numPlayers; i++) {
				if(i == numPlayers){ i = 0; }
				Player player = players.get(i);
				player.addMoves(rollDice());
				this.maxMoves = player.getMoves();
				
				System.out.println(player.toString() + " roll: " + player.getMoves());

						for(int j = 0; j < this.maxMoves; j++) {
							
							Position p = movePlayer();
							if(this.ifAccusation){
								makeAccusation(player);
							}
							while(checkTile(player, p)) {
								p = movePlayer();
								if(this.ifAccusation){
									makeAccusation(player);
								}
							}
							if(this.madeAccusation == false) {
								player.newPosition(p.x, p.y);
								player.moved();
								this.board.placePlayer(player);
								this.board.draw();
								checkInRoom(player);
							}
						}
			}
		}
	}

	/**
	 * This checks to see if the player is in a room
	 * Does this by checking the original board with the current
	 * players position
	 * @param p - This is the current in play character
	 */
	private void checkInRoom(Player p){
		String s = board.getBoardLetter(p.getXPosition(), p.getYPosition());
for (int i = 0; i < rooms.size();i++){
	if(s.equals(rooms.get(i).getToken())){
		System.out.println("Your in " + rooms.get(i).toString());
		System.out.println("Make a suggestion!");
		makeSuggestion(p, rooms.get(i).toString());
		this.maxMoves = 0;
	}
}
	}

	/**
	 *The player currently in play and in a room can make a suggestion
	 * This handles the suggestion
	 * @param p - Player - Current in play character
	 * @param room - String -  Room name that the player is currently in.
	 *
	 */
	private void makeSuggestion(Player p, String room){
		Scanner sc = new Scanner(System.in);
		int num;
		boolean refuted;

		System.out.println("Choose your character murder suspect number and hit enter");
		for (int i = 0; i < allCards.size(); i++) {
			System.out.println(i + ") " + allCards.get(i).toString());
		}

		num = sc.nextInt();
		String character = allCards.get(num).toString();


		System.out.println("Now choose a murder weapon number and hit enter");
		for (int i = 0; i < allCards.size(); i++) {
			System.out.println(i + ") " + allCards.get(i).toString());
		}

		num = sc.nextInt();
		String weapon = allCards.get(num).toString();
		refuted = checkRefutes(character, room, weapon);
		if(!refuted) {
			checkHiddenEnvelope(character, room, weapon);
		}
	}

	private boolean checkRefutes(String c, String r, String w){
		for(int i = 0; i < deckCards.size();i++){
			if(deckCards.get(i).toString().equals(c)){
				System.out.println("Your Character choice has been Refuted!! \nNext players turn\n");
				return true;
			}
			if(deckCards.get(i).toString().equals(r)){
				System.out.println("Your Room choice has been Refuted!!\nNext players turn\n");
				return true;
			}
			if(deckCards.get(i).toString().equals(w)){
				System.out.println("Your Weapon choice has been Refuted!!\nNext players turn\n");
				return true;
			}
		}
		return false;
	}

	private void makeAccusation(Player p) throws InputMismatchException{
		Scanner sc = new Scanner(System.in);
		int num;

		System.out.println("Choose your character murder suspect number and hit enter");
		for (int i = 0; i < allCards.size(); i++) {
			System.out.println(i + ") " + allCards.get(i).toString());
		}

		num = sc.nextInt();
		String character = allCards.get(num).toString();

		System.out.println("Now choose a room number and hit enter");
		for (int i = 0; i < allCards.size(); i++) {
			System.out.println(i + ") " + allCards.get(i).toString());
		}

		num = sc.nextInt();
		String room = allCards.get(num).toString();

		System.out.println("Now choose a murder weapon number and hit enter");
		for (int i = 0; i < allCards.size(); i++) {
			System.out.println(i + ") " + allCards.get(i).toString());
		}

		num = sc.nextInt();
		String weapon = allCards.get(num).toString();

		checkHiddenEnvelope(character, room, weapon);
		if(gamePlaying == false){
			this.ifAccusation = false;
			return;
		}

		this.maxMoves = 0;
		System.out.println("num players before:" + this.players.size());
		this.players.remove(p);
		System.out.println("num players after:" + this.players.size());
		this.ifAccusation = false;
		this.madeAccusation = true;
		board.draw();
	}
	
	private boolean checkTile(Player player, Position p) {
		String s  = board.checkBoard(player.getXPosition()+p.x, player.getYPosition()+p.y);
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
		System.out.println("Move direction: ");
		Scanner move = new Scanner(System.in);
		String l = move.nextLine();

		int dx = 0;
		int dy = 0;

		if(l.equals("w")) {
			dx = -1;
		}else if(l.equals("a")) {
			dy = -1;
		}else if(l.equals("s")) {
			dx = 1;
		}else if(l.equals("d")) {
			dy = 1;
		}else if (l.equals("g")){
			System.out.println("You have chosen to make an accusation!");
			this.ifAccusation = true;
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

	public void addWeaponsToRooms(){
		int j = (int) Math.random()*(8-0);
		for(int i = 0; i < weapCards.size(); i++){
				rooms.get(j).addWeapon(weapCards.get(i));
				if (j == 8){
					j = 0;
			}
			j++;
		}
	}

	public void createRoomTiles(){
		// Add Room Tile as well as convenient
		RoomTile kitchen = new RoomTile("Kitchen", "K");
		RoomTile ballRoom = new RoomTile("Ball Room", "B");
		RoomTile conserv = new RoomTile("Conservatory", "C");
		RoomTile billiard = new RoomTile("Billiard Room", "I");
		RoomTile library = new RoomTile("Library", "L");
		RoomTile study = new RoomTile("Study", "S");
		RoomTile hall = new RoomTile("Hall", "H");
		RoomTile lounge = new RoomTile("Lounge", "O");
		RoomTile dining = new RoomTile("Dining Room", "D");
		rooms.add(kitchen);
		rooms.add(ballRoom);
		rooms.add(conserv);
		rooms.add(billiard);
		rooms.add(library);
		rooms.add(study);
		rooms.add(hall);
		rooms.add(lounge);
		rooms.add(dining);

	}

	private void startGameInterface() {
		Scanner s = new Scanner(System.in);
		boolean incorrectPlayerSize = true;
		int input = 0;

		
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

		}else {
			while (incorrectPlayerSize) {
				System.out.println("How many Players would like to play?\n"
					+ "Please type 3, 4, 5 or 6 and hit enter.\n");
				try {
				input = s.nextInt();
				}catch(InputMismatchException e) {
					System.out.print("Invalid number\n");
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
		this.deckCards.addAll(this.charCards);
		this.deckCards.addAll(this.roomCards);
		this.deckCards.addAll(this.weapCards);

		Collections.shuffle(this.deckCards); // Works sufficiently Shuffle Deck

		this.allCards.addAll(this.deckCards);
		this.allCards.addAll(this.solutionCards);
		// TEST System.out.println(deckCards.size()); --WORKS
	}

	/**
	 * Checks to see if choosen cards are correct to solve the mystery
	 * 
	 * @param - Character Card c
	 * @param - Room Card r
	 * @param - Weapon Card w
	 * @return - returns true or false depending if all parameter cards match
	 *         solution cards
	 */
	private void checkHiddenEnvelope(String character, String room, String weapon) {
		if (solutionCards.get(0).toString().equals(character) && solutionCards.get(1).toString().equals(room) && solutionCards.get(2).toString().equals(weapon) ) {
			System.out.println("YOU WIN!!!");
			this.gamePlaying = false;
		}
		System.out.println("This is not the solution!\n Next players turn \n");
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
		Card kit = new Room("Kitchen");
		Card bal = new Room("Ball Room");
		Card con = new Room("Conservatory");
		Card bil = new Room("Billiard Room");
		Card lib = new Room("Library");
		Card stu = new Room("Study");
		Card hal = new Room("Hall");
		Card lou = new Room("Lounge");
		Card din = new Room("Dining Room");
		roomCards.add(kit);
		roomCards.add(bal);
		roomCards.add(con);
		roomCards.add(bil);
		roomCards.add(lib);
		roomCards.add(stu);
		roomCards.add(hal);
		roomCards.add(lou);
		roomCards.add(din);

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
		
		Player p1 = new Player("Miss Scarlet", "1", p1pos);
		Player p2 = new Player("Professor Plum", "2", p2pos);
		Player p3 = new Player("Mrs. Peacock", "3", p3pos);
		Player p4 = new Player("Mrs. White", "4", p4pos);
		Player p5 = new Player("Colonel Mustard", "5", p5pos);
		Player p6 = new Player("Mr. Green", "6", p6pos);
		
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