package gameofuofc;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Main {

		public board boardObject = new board();
		player playerObject = new player(0, "");
		player player1Object = new player(1, "");
		player[] players = {playerObject, player1Object};
		public int currentPlayer = 0;
		static ArrayList<wildcards> wildcards = new ArrayList<wildcards>();
		
		public void main(String[] args) {

			playerObject.setPlayerLocation(boardObject.getSquare(0));
			
			player1Object.setPlayerLocation(boardObject.getSquare(0));
			
			shuffleWildcards();
		
			while(seeIfBothPlayersFinished() ==  false) {
			currentPlayer = choosePlayer();
			currentPlayer = seeIfPlayerFinished(currentPlayer);
			movePlayer(currentPlayer, spin());
			
			
			}
			// seeWhoWon();
			// This is where we total the grades and the social bars we add them together and see which player has the higher score
			// return who won and congratulate them												
				
					}
				
			
		
		public int spin() {
			int min = 1;
			int max = 6;
			int randomInt = (int)(Math.random() * (max - min + 1) + min);
			return randomInt;		
			// Source for random integer generator: https://www.educative.io/edpresso/how-to-generate-random-numbers-in-java 
		}
		
		public void movePlayer(int numPlayer, int numMove) {
			int moveLoc;    // location to move

			moveLoc = players[numPlayer].getPlayerLocation().getSquareId() + numMove; // location to move = current location + distance to move
			players[numPlayer].setPlayerLocation(boardObject.getSquare(moveLoc));
			switch (players[numPlayer].getPlayerLocation().getType()) {
			case 'g':
				//call method to add to or subtract from grade metric
				players[numPlayer].setPlayerGrades(players[numPlayer].getGrades() + boardObject.getSquare(moveLoc).getEffectVal());
				break;
			case 's':
				//call method to add to or subtract from social metric
				players[numPlayer].setPlayerSocial(players[numPlayer].getSocial() + boardObject.getSquare(moveLoc).getEffectVal());
				break;
			case 'w':
				//call method to draw wild-card
				drawCard(numPlayer);
				
				break;
			default:
				break;
			}
			
		}	
		
		
		public void drawCard(int numPlayer) {
			wildcards drawn = new wildcards("", 'd', 0);
			Collections.shuffle(wildcards);
			drawn = wildcards.get(0);
			switch (players[numPlayer].getPlayerLocation().getType()) {
			case 'g':
				//call method to add to or subtract from grade metric
				players[numPlayer].setPlayerGrades(players[numPlayer].getGrades() + drawn.getEffectVal());
				break;
			case 's':
				//call method to add to or subtract from social metric
				players[numPlayer].setPlayerGrades(players[numPlayer].getSocial() + drawn.getEffectVal());
				break;
			case 'b':
				players[numPlayer].setPlayerGrades(players[numPlayer].getGrades() + drawn.getEffectVal());
				players[numPlayer].setPlayerGrades(players[numPlayer].getSocial() + drawn.getEffectVal());
				break;
			}
		}
		
		
		public static void shuffleWildcards(){
    		try {
        	InputStream csvFile = new FileInputStream("wildcards.csv");
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
	}
		
		public int choosePlayer() {
		int ModulusCounter = 2;
		int checkPlayer = 0;
		
		
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
	public int seeIfPlayerFinished(int player) {
		
		if(players[player].getPlayerLocation().getType() == 'e') {
			return choosePlayer();
		}
		
		if(players[player].getPlayerLocation().getType() != 'e') {
			return currentPlayer;
		}
		return currentPlayer;
				
}
	
	public boolean seeIfBothPlayersFinished() {
		for (int counter = 0; counter < players.length; counter ++) {
			if (players[counter].getPlayerLocation().getType() != 'e') {
				return false;
			}
		}
		return true;
	}
	}
