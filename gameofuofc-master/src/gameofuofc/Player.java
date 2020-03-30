package gameofuofc;
import java.io.Serializable;
//import java.math.*;

public class Player implements Serializable {
// Players instance variables
	private static final long serialVersionUID = 1L;
	private int playerID;
	private String playerName;
	private Square playerLocation;
	private int Grades;
	private int Social;
	private boolean isAI;
	private String[] bots = {"Bot Htet", "Bot Tin", "Bot Mohommad", "Bot Roubert", "Bot Nick", "Bot Yang"};
	
	
	/**
	 * Constructor for creating a player
	 * @param id gives the player its ID number which is unique for every player
	 * @param name gives the player its name
	 */
	
	public Player(int id, String name) {
		this.playerID = id;
		this.playerName = name;
		this.Grades = 0;
		this.Social = 0;
		playerLocation = new Square();
	}
	
	//Determines which bot out of our list to play
	/** 
	 * Method for selecting a bot
	 * @return the bots name which is selected
	 */
	
	public String randomBot() {
		int min = 0;
		int max = 5;
		int randomInt = (int)(Math.random() * (max - min + 1) + min);
		return bots[randomInt];	
	}

	
	//Getters
	
	public int getPlayerId() {
		return this.playerID;
	}
	
	public Square getPlayerLocation() {
		return this.playerLocation;
	}
	public String getName() {
		return this.playerName;
	}
	public int getGrades() {
		return this.Grades;
	}
	public int getSocial() {
		return this.Social;
	}
	
	public boolean getIsAI() {
		return this.isAI;
	}
	//Setters
	
	public void setPlayerLocation(Square loc) {
		this.playerLocation = loc;
	}
	
	public void setPlayerName(String input) {
		

			this.playerName = input;
		
	}
	
	public void setPlayerGrades(int newGradeVal) {
		this.Grades = newGradeVal;
	}
	
	public void setPlayerSocial(int newSocialVal) {
		this.Social = newSocialVal;
	}
	
	public void setIsAI(boolean AI) {
		this.isAI = AI;
	}

}	
	
