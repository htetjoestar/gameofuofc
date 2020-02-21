package gameofuofc;
public class Main {

		board boardObject = new board();
		player playerObject = new player(0, null);
		player player1Object = new player(1, null);
		squares FinalSquare = new square();
		FinalSquare = ("Final Square", false, 'e', 23);
		
	
		playerObject.setPlayerLocation(boardObject.getsquare(0));
		
		player1Object.setPlayerLocation(boardObject.getsquare(0));
		
		
		
		public void main() {
			while((playerObject.getType() && player1Object.getType()) != FinalSquare){
			
				choosePlayer().spin().moveLocation;
				
				
				
				
				
				
			}
	}
}
