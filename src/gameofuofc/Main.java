package gameofuofc;
public class Main {

		public board boardObject = new board();
		player playerObject = new player(0, "");
		player player1Object = new player(1, "");
		player[] players = {playerObject, player1Object};
		
		public void main(String[] args) {

			playerObject.setPlayerLocation(boardObject.getSquare(0));
			
			player1Object.setPlayerLocation(boardObject.getSquare(0));
			
				
			while((playerObject.getPlayerLocation().getType() != 'e' && player1Object.getPlayerLocation().getType() != 'e') ){
			
																
				
					}
				
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
				break;
			default:
				break;
			}
			
		}
}
