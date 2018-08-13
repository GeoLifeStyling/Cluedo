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
	Game g;

	public Board(Game g){
		this.g = g;
	}
	
	public void draw(){
		System.out.println("new READ x: " + g.getP().getXPosition()+ "\n" + "new y: " + g.getP().getYPosition() + "\n" +
				"old x: " + g.getP().getOldXPosition() + "\n" + "old y: " + g.getP().getOldYPosition() + "\n");
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
	 * @throws IOException 
	 */
	@SuppressWarnings("resource")
	public void create() throws IOException{
		int i = 0;
		int j = 0;

		File f = new File("src/board");
		Scanner sc = new Scanner(f).useDelimiter("\\s");
		while (sc.hasNext()) {
			String s = sc.next();
			stdBoard[i][j] = s;
			gameBoard[i][j] = translateTile(s);
			
			j++;
			if(j == 26) {
			i++;
			j = 0;
			}
		}
		sc.close();
	}
	
	public void placePlayer(Player p) {
		System.out.println("place player new x: " + p.getXPosition()+ "\n" + "new y: " + p.getYPosition() + "\n" +
				"old x: " + p.getOldXPosition() + "\n" + "old y: " + p.getOldYPosition() + "\n");
		gameBoard[p.getXPosition()][p.getYPosition()] = p.getToken();
		gameBoard[p.getOldXPosition()][p.getOldYPosition()] = " ";
	}
	
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
