package gameofuofc;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;



public class Main{
		

// Main instance variables

		public static Board boardObject = new Board();
		static Player playerObject = new Player(0, "Johnny");
		static Player player1Object = new Player(1, "Joestar");
		public static Player[] players = {playerObject, player1Object};
		public static int currentPlayer = 0;
		static int selection;
		static int ModulusCounter = 2;
		static int checkPlayer = 0;
		static int decisionMade = 0;
		static ArrayList<wildcards> wildcards = new ArrayList<wildcards>();
		static ArrayList<Decisions> decisions = new ArrayList<Decisions>();
		
		
		public static void main(String[] args) throws IOException {


//By default, both players start at square 0, unless another game is loaded later on

			playerObject.setPlayerLocation(boardObject.getSquare(0));
			
			player1Object.setPlayerLocation(boardObject.getSquare(0));

			initializeDecisions();
			shuffleWildcards();

//Open input scanner
			Scanner playerin = new Scanner(System.in);
			
//Starting messages
			System.out.println("Welcome to the game of UOFC! ");
			System.out.println("Press '1' to continue ");
			System.out.println("or input '2' to load a game");
			
// If loading a game, the players from the save file (along with all their stats) replace the players in the current game
			if (playerin.nextInt() == 2) {
				ArrayList<Player> TempArrList4Load = new ArrayList<Player>();
				TempArrList4Load = serializeDataIn();
				players[0] = TempArrList4Load.get(0);
				players[1] = TempArrList4Load.get(1);		
			} else {			
				inputPlayerNames();
			}


			players[0].setPlayerName("htet");
			players[1].setPlayerName("tin");
			
			
			

						
//Main gameplay code

			while(seeIfBothPlayersFinished() ==  false) {
				currentPlayer = choosePlayer();
				currentPlayer = seeIfPlayerFinished(currentPlayer);
				selection = 0;
				while(selection != 1) {
					System.out.println(players[currentPlayer].getName()+ "'s turn");
					System.out.println("1: spin the spinner and move player");
					System.out.println("2: print player stats");
					System.out.println("3: save game");
					selection = playerin.nextInt();
					if(selection == 2) {
						printPlayersStats();
					}
					if(selection == 3) {
						// Export info about each player in the game into object.txt
						ArrayList<Player> TempArrList = new ArrayList<Player>();
						TempArrList.add(players[0]);
						TempArrList.add(players[1]);
						dataout(TempArrList);
					}
				}
				
				//Show where the current player is at
				System.out.println("---------------------------------------");
				movePlayer(currentPlayer, spin());
				System.out.print(players[currentPlayer].getName() + " is currently at ");
				System.out.println(players[currentPlayer].getPlayerLocation().getSquareId());
				System.out.println("---------------------------------------");
			}
				
			seeWhoWon();
			System.exit(0);
				
					}
				
			
		//Spin method, returns a random integer
		public static int spin() {
			int min = 1;
			int max = 6;
			int randomInt = (int)(Math.random() * (max - min + 1) + min);
			return randomInt;		
			// Source for random integer generator: https://www.educative.io/edpresso/how-to-generate-random-numbers-in-java 
		}
		
		//Method for moving players
		public static void movePlayer(int numPlayer, int numMove) {
			int moveLoc;    // location to move
			
			moveLoc = players[numPlayer].getPlayerLocation().getSquareId() + numMove; // location to move = current location + distance to move
			for(int index = players[numPlayer].getPlayerLocation().getSquareId(); index < moveLoc;index++) {
				if (boardObject.getSquare(index).getType() == 'd') {   // check if landed on a decision spot
					System.out.println("You have arrived at a decison spot");
					decisionMade = decisions.get(boardObject.getSquare(index).getEffectVal()).makeDecision();
					if (decisionMade == 1) {
						decisionEffects(decisions.get(boardObject.getSquare(index).getEffectVal()).getEffg1(), decisions.get(boardObject.getSquare(index).getEffectVal()).getEffs1(), players[numPlayer]);
					}
					if (decisionMade == 2) {
						decisionEffects(decisions.get(boardObject.getSquare(index).getEffectVal()).getEffg2(), decisions.get(boardObject.getSquare(index).getEffectVal()).getEffs2(), players[numPlayer]);
					}
				} 
				if (boardObject.getSquare(index).getType() == 'e') {   // check if the end has been reached
					System.out.println(players[numPlayer].getName() + " has reached the finish point");
					players[numPlayer].setPlayerLocation(boardObject.getSquare(index));
					return;
				}
			}
			players[numPlayer].setPlayerLocation(boardObject.getSquare(moveLoc));
			switch (players[numPlayer].getPlayerLocation().getType()) {    // cases for different square types
			case 'g':
				//call method to add to or subtract from grade metric
				players[numPlayer].setPlayerGrades(players[numPlayer].getGrades() + boardObject.getSquare(moveLoc).getEffectVal());
				System.out.println(players[numPlayer].getName() +" has landed on a grade boost square");
				System.out.println(Integer.toString(boardObject.getSquare(moveLoc).getEffectVal()) + " boost to grade");
				break;
			case 's':
				//call method to add to or subtract from social metric
				players[numPlayer].setPlayerSocial(players[numPlayer].getSocial() + boardObject.getSquare(moveLoc).getEffectVal());
				System.out.println(players[numPlayer].getName() +" has landed on a social boost square");
				System.out.println(Integer.toString(boardObject.getSquare(moveLoc).getEffectVal()) + " boost to social");
				break;
			case 'w':
				//call method to draw wild-card
				System.out.println("You have landed on a wild-card square");
				drawCard(numPlayer);
				
				break;
			default:
				break;
			}
			
		}	
		
		//Method for wild cards, also applies the effects on the player
		public static void drawCard(int numPlayer) {
			wildcards drawn = new wildcards("", 'd', 0);
			Collections.shuffle(wildcards);
			drawn = wildcards.get(0);
			System.out.println(drawn.getDisc());
			switch (drawn.getEff()) {
			case 'g':
				//call method to add to or subtract from grade metric
				System.out.println(Integer.toString(drawn.getEffectVal()) + " has been added to grades");
				players[numPlayer].setPlayerGrades(players[numPlayer].getGrades() + drawn.getEffectVal());
				break;
			case 's':
				//call method to add to or subtract from social metric
				System.out.println(Integer.toString(drawn.getEffectVal()) + " has been added to social");
				players[numPlayer].setPlayerGrades(players[numPlayer].getSocial() + drawn.getEffectVal());
				break;
			case 'b':
				System.out.println(Integer.toString(drawn.getEffectVal()) + " has been added to both grades and social");
				players[numPlayer].setPlayerGrades(players[numPlayer].getGrades() + drawn.getEffectVal());
				players[numPlayer].setPlayerGrades(players[numPlayer].getSocial() + drawn.getEffectVal());
				break;
			}
		}
		
		public static void initializeDecisions(){   // Initializes the decisions csv file
    		try {
        	InputStream csvFile = new FileInputStream("csvfiles/decisions.csv");
        	Scanner myReader = new Scanner(new InputStreamReader(csvFile));
        	String data;
        	while (myReader.hasNextLine()) {
            	data = myReader.nextLine();
            	String [] split = data.split(",");
            	decisions.add(new Decisions(split[0],split[1],Integer.parseInt(split[2]),Integer.parseInt(split[3]),split[4],Integer.parseInt(split[5]),Integer.parseInt(split[6])));
            
        	}
        	myReader.close();
    		} catch (FileNotFoundException e) {
        System.out.println("An error occurred.");
    	}
    		// this code is copied from my hackathon app - Htet
	}
		
		public static void shuffleWildcards(){  // shuffles the wildcards
    		try {
        	InputStream csvFile = new FileInputStream("csvfiles/wildcards.csv");
        	Scanner myReader = new Scanner(new InputStreamReader(csvFile));
        	String data;
        	while (myReader.hasNextLine()) {
            	data = myReader.nextLine();
            	String [] split = data.split(",");
            	wildcards.add(new wildcards(split[0],split[1].charAt(0),Integer.parseInt(split[2])));
            
        	}
        	myReader.close();
        	Collections.shuffle(wildcards);
    		} catch (FileNotFoundException e) {
        System.out.println("An error occurred.");
    	}
    		// this code is copied from my hackathon app
	}
		
		public static int choosePlayer() {  // returns which player is currently playing

		
			checkPlayer = ModulusCounter % 2;
			if( checkPlayer  == 0) {
				ModulusCounter ++;
				return 0;
			}
			
			else {
				ModulusCounter ++;
				return 1;
			}
		
		
	}
	public static int seeIfPlayerFinished(int player) {  // checks if a player has finished
		
		if(players[player].getPlayerLocation().getType() == 'e') {
			return choosePlayer();
		}
		
		if(players[player].getPlayerLocation().getType() != 'e') {
			return currentPlayer;
		}
		return currentPlayer;
				
}
	
	public static boolean seeIfBothPlayersFinished() {  // checks if both players have finished
		for (int counter = 0; counter < players.length; counter ++) {
			if (players[counter].getPlayerLocation().getType() != 'e') {
				return false;
			}
		}
		return true;
	}
	
	public static void printPlayersStats() {  // prints the stats for all players
		for (Player i : players) {
			System.out.println("Stats for player " + i.getName() + " (playerID = " + i.getPlayerId() + ") :" );
			System.out.println("Currently on square " + i.getPlayerLocation().getSquareId());
			System.out.println("Grades score: " + i.getGrades());
			System.out.println("Social score: " + i.getSocial());
			System.out.println("");
		}
	}
	
	public static Player seeWhoWon() {  // sees who won, by comparing the total social/grades scores. If there has been a tie, then the winner is randomly chosen.
		int player0Total = 0;
		int player1Total = 0;
		
		player0Total = players[0].getGrades() + players[0].getSocial();
		player1Total = players[1].getGrades() + players[1].getSocial();
		
		if (player0Total > player1Total) {
			System.out.println(players[0].getName() + " Has won the game with a total score of " + player0Total);
			return players[0];
		}
		if (player0Total < player1Total) {
			System.out.println(players[1].getName() + " Has won the game with a total score of " + player1Total);
			return players[1];
		}
		if (player0Total == player1Total) {
			System.out.println("There has been a tie, the winner will now be randomized");
			int min = 1;
			int max = 6;
			int randomInt = (int)(Math.random() * (max - min + 1) + min);
			if (randomInt >= 3) {
				System.out.println(players[1].getName() + " Has won the game!");
			}
			else {
				System.out.println(players[0].getName() + " Has won the game!");
			}
			
		}
		return null;
		
	}
	
	public static void decisionEffects(int eff1, int eff2, Player playerobj) {  // deals with the effects of decisions
		playerobj.setPlayerSocial(playerobj.getSocial() + eff1);
		playerobj.setPlayerGrades(playerobj.getGrades() + eff2);
		System.out.println(Integer.toString(eff1) + " has been added to social");
		System.out.println(Integer.toString(eff2) + " has been added to grades");
	}


	public static boolean checkFileExists() throws ClassNotFoundException  { // checks if the object.txt file exists

		   try {
			   String fileName = "object.txt";
			   FileInputStream fin = new FileInputStream(fileName);
			   ObjectInputStream ois = new ObjectInputStream(fin);
			   try {
		   			ois.readObject();
		   			ois.close();
		   			return true;   //RETURN TRUE WHEN FILE EXISTS AND DATA EXISTS
		   		}catch (EOFException e) {
		   			return false; //RETURN FALSE WHEN FILE EXISTS BUT NO DATA EXISTS
		   		}
		} catch (IOException e) {
			return false;		//RETURN FALSE WHEN FILE DOES NOT EXIST
		}
	}
	
	public static void dataout(ArrayList<Player> people) throws IOException {  // when saving, exports the data of the players into object.txt
		try {
		String fileName = "object.txt";
		FileOutputStream fos = new FileOutputStream(fileName);
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		for(int x = 0; x < people.size();x ++) {
			oos.writeObject(people.get(x));
		}
		oos.close();
		}catch(IOException e){
			
		}
		
	}

	
	public static ArrayList<Player> serializeDataIn(){   // when loading, imports the data from object.txt into the game
		   String fileName= "object.txt";
		   ArrayList<Player> people= new ArrayList<Player>();
		   try {
		   FileInputStream fin = new FileInputStream(fileName);
		   ObjectInputStream ois = new ObjectInputStream(fin);
		   		try {
		   			for(int i = 0; i < 4 ; i++) {
		   			people.add((Player) ois.readObject());
		   			}
		   		}catch (EOFException e) {
			   // End of stream
		   		} 
			ois.close();
			return people;
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		   return null;
		}
	
	public static void inputPlayerNames() {  // takes player input + sets player names based on inputs
		Scanner playernamein = new Scanner(System.in);

		for (int i = 0; i < players.length; i++) {

			System.out.println("Input the name for player " + (i+1));
			String input = playernamein.nextLine();
			players[i].setPlayerName(input);
		}
	}
	
}
