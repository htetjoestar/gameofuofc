package gameofuofc;
import java.math.*;
import java.util.Scanner;

public class player {
	private int playerID;
	private String playerName;
	private String playerLocation;
	Scanner playerInput = new Scanner(System.in)
	
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
	
	public int choosePlayer() {
		int ModulusCounter = 2;
		
			ModulusCounter % 2;
			if( ModulusCounter = 0) {
				return playerObject;
			}
			else {
				return player1Object;
			}
		
		ModulusCounter ++;
	}
	
	//Getters
	
	int getPlayerId() {
		return this.playerID;
	}
	
	String getPlayerName() {
		System.out.println("Enter a name for player" + playerID);
		return if (playerInput.next()!= "") {
			playerName = playerInput.next();
		}
	}
	
	String getPlayerLocation() {
		return this.playerLocation;
	}
	
	//Setters
	
	void setPlayerLocation(String loc) {
		this.playerLocation = loc;
	}
}	
	
