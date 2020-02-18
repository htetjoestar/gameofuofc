package gameofuofc;
import java.math.*;

public class player {
	private int playerID;
	private String playerName;
	private String playerLocation;
	
	player(int id, String name) {
		this.playerID = id;
		this.playerName = name;
		playerLocation = "0";
	}
	
	int spin() {
		int min = 1;
		int max = 6;
		int randomInt = (int)(Math.random() * (max - min + 1) + min);
		return randomInt;		
		// Source for random integer generator: https://www.educative.io/edpresso/how-to-generate-random-numbers-in-java 
	}
	
	//Getters
	
	int getPlayerId() {
		return this.playerID;
	}
	
	String getPlayerName() {
		return this.playerName;
	}
	
	String getPlayerLocation() {
		return this.playerLocation;
	}
	
	//Setters
	
	void setPlayerLocation(String loc) {
		this.playerLocation = loc;
	}
}	
	
