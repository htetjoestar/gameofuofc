package gameofuofc;
public class Main {

		board boardObject = new board();
		player playerObject = new player(0, "");
		player player1Object = new player(1, "");
		
		
		
		public void main() {
			playerObject.setPlayerLocation(boardObject.getsquare(0));
			
			player1Object.setPlayerLocation(boardObject.getsquare(0));
			
				
			while((playerObject.getPlayerLocation().getType() != 'e' && player1Object.getPlayerLocation().getType() != 'e') ){
			
																
				
				
				
			}
	}
}
