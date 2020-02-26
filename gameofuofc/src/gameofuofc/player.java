package gameofuofc;
//import java.math.*;
import java.util.Scanner;

public class player {
	private int playerID;
	private String playerName;
	private Square playerLocation;
	private int Grades;
	private int Social;
	Scanner playerInput = new Scanner(System.in);
	
	public player(int id, String name) {
		this.playerID = id;
		this.playerName = name;
		this.Grades = 0;
		this.Social = 0;
		playerLocation = new Square();
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
	//Setters
	
	public void setPlayerLocation(Square loc) {
		this.playerLocation = loc;
	}
	
	public void setPlayerName() {
		System.out.println("Enter a name for player" + playerID);
	   if (playerInput.next()!= "") {
			playerName = playerInput.nextLine();
		}
	}
	
	public void setPlayerGrades(int newGradeVal) {
		this.Grades = newGradeVal;
	}
	
	public void setPlayerSocial(int newSocialVal) {
		this.Social = newSocialVal;
	}

}	
	
