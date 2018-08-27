import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.lang.*;

public class Board {
	
	/**
	 * Board that is grid size 24(columns) x 25(rows)
	 */
	private static final int BOARD_LEN = 27;
	private String[][] stdBoard = new String[BOARD_LEN][BOARD_LEN];
	private String[][] gameBoard = new String[BOARD_LEN][BOARD_LEN];

	/**
	 * Draws the 2d array board
	 */
	public void draw(){
		String s = "";
		for(int i = 0; i < BOARD_LEN; i++) {
			for (int j = 0; j < gameBoard[i].length-1; j++) {
				s += gameBoard[i][j];
				s+= " ";
			}s += "\n";
		}System.out.println(s);
	}
	
	/**
	 * Reads board file and saves into 2d array 
	 * @throws IOException - To allow user input error handling
	 */
	@SuppressWarnings("resource")
	public void create() throws IOException{
		int i = 0;
		int j = 0;
		File f = new File("C:\\Users\\teccl\\IdeaProjects\\Cluedo\\src\\main\\java\\board"); // C:\Users\teccl\IdeaProjects\Cluedo\src\main\java\Board.java
		Scanner sc = new Scanner(f).useDelimiter("\\s");
		while (sc.hasNext()) {
			String s = sc.next();
			stdBoard[i][j] = s;
			gameBoard[i][j] = translateTile(s);
			
			j++;
			if(j == 27) {
			i++;
			j = 0;
			}
		}
		sc.close();
	}

	public String getBoardLetter(int x, int y){
		return stdBoard[x][y];
	}

	/**
	 * Checks to see what is in this spot on the visual board
	 * @param x - position x
	 * @param y - position y
	 * @return String - the string value of the position
	 */
	public String checkBoard(int x, int y) {
		return gameBoard[x][y];
	}

	/**
	 * Plces the player token on the visual board and replaces the last position with
	 * the original board token
	 * @param p - Player - in current play
	 */
	public void placePlayer(Player p) {
		String s = stdBoard[p.getOldXPosition()][p.getOldYPosition()];
		s = translateTile(s);
		gameBoard[p.getOldXPosition()][p.getOldYPosition()] = s;
		gameBoard[p.getXPosition()][p.getYPosition()] = p.getToken();
	}

	/**
	 * This is the original board translater to new visual board for better graphics
	 * @param s - String - token to replace
	 * @return - String - token replacing original board token
	 */
	public String translateTile(String s) {
		switch(s) {
			case "K":
				return " ";
			case "B":
				return " ";
			case "C":
				return " ";
			case "D":
				return " ";
			case "I":
				return " ";
			case "L":
				return " ";
			case "O":
				return " ";
			case "H":
				return " ";
			case "S":
				return " ";
			case "W":
				return " ";
			case "_":
				return "_";
			case "^":
				return "^";
			case ">":
				return ">";
			case "<":
				return "<";
			case "v":
				return "v";
			
				// As character.isAlpha or isLowerCase didn't work
			case "b":
				return "b";
			case "l":
				return "l";
			case "r":
				return "r";
			case "m":
				return "m";
			case "c":
				return "c";
			case "o":
				return "o";
			case "n":
				return "n";
			case "s":
				return "s";
			case "i":
				return "i";
			case "t":
				return "t";
			case "u":
				return "u";
			case "d":
				return "d";
			case "y":
				return "y";
			case "h":
				return "h";
			case "a":
				return "a";
			case "g":
				return "g";
			case "e":
				return "e";
			case "k":
				return "k";
			case "!":
				return "!";
				
		}
		
		return "|";
	}	
	
}
