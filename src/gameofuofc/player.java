package gameofuofc;
//import java.math.*;
import java.util.Scanner;

public class player {
	private int playerID;
	private String playerName;
	private squares playerLocation;
	Scanner playerInput = new Scanner(System.in);
	
	public player(int id, String name) {
		this.playerID = id;
		this.playerName = name;
		playerLocation = new squares();
	}
	
	public int spin() {
		int min = 1;
		int max = 6;
		int randomInt = (int)(Math.random() * (max - min + 1) + min);
		return randomInt;		
		// Source for random integer generator: https://www.educative.io/edpresso/how-to-generate-random-numbers-in-java 
	}
	
//	public int choosePlayer() {
//		int ModulusCounter = 2;
//		
//			ModulusCounter % 2;
//			if( ModulusCounter == 0) {
//				return playerObject;
//			}
//			else {
//				return player1Object;
//			}
//		
//		ModulusCounter ++;
//	}
	
	//Getters
	
	public int getPlayerId() {
		return this.playerID;
	}
	
	public squares getPlayerLocation() {
		return this.playerLocation;
	}
	public String getName() {
		return this.playerName;
	}
	//Setters
	
	public void setPlayerLocation(squares loc) {
		this.playerLocation = loc;
	}
	public void setPlayerName() {
		System.out.println("Enter a name for player" + playerID);
	   if (playerInput.next()!= "") {
			playerName = playerInput.next();
		}
	}
	
}	
	
