package gameofuofc;
//import java.math.*;
import java.util.Scanner;

public class Player {
	private int playerID;
	private String playerName;
	private Square playerLocation;
	private int Grades;
	private int Social;
	Scanner playerInput = new Scanner(System.in);
	
	public Player(int id, String name) {
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
		System.out.println("Enter a name for player " + playerID);

			this.playerName = playerInput.nextLine();
		
	}
	
	public void setPlayerGrades(int newGradeVal) {
		this.Grades = newGradeVal;
	}
	
	public void setPlayerSocial(int newSocialVal) {
		this.Social = newSocialVal;
	}
	public void decisionEffects(int eff1, int eff2) {
		this.Social = this.Social + eff1;
		this.Grades = this.Grades + eff2;
		System.out.println(Integer.toString(eff1) + " has been added to social");
		System.out.println(Integer.toString(eff2) + " has been added to grades");
	}
}	
	
