package gameofuofc;



import java.io.IOException;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class Controller extends Main{
    @FXML
    private Label TurnLabel;

    @FXML
    private Button RollButton;

    @FXML
    private Label player1Name;
    @FXML
    private Label player2Name;
    @FXML
    private Label p1square;

    @FXML
    private Label p1grades;

    @FXML
    private Label p1social;

    @FXML
    private Label p2square;

    @FXML
    private Label p2grades;

    @FXML
    private Label p2social;


	public Board boardObject = new Board();
	static int ModulusCounter = 2;
	static int checkPlayer = 0;
	static int decisionMade = 0;
	String [] temp = {null, null};
	@FXML
	void initialize()  {
		initializeDecisions();
		shuffleWildcards();
		try {
			temp = new startMenu().display();
			players[0].setPlayerName(temp[0]);
			players[1].setPlayerName(temp[1]);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		player1Name.setText(playerObject.getName());
		player2Name.setText(player1Object.getName());
		TurnLabel.setText(players[currentPlayer].getName() + "'s turn");

		updateStats();
	}
	@FXML
	void Roll(ActionEvent event) {
		if(super.seeIfBothPlayersFinished() ==  false) {
			this.movePlayerS(currentPlayer, super.spin());
			updateStats();
			currentPlayer = choosePlayer();
			currentPlayer = seeIfPlayerFinished(currentPlayer);
			TurnLabel.setText(players[currentPlayer].getName() + "'s turn");
	}
		
	}
	
	public void updateStats(){
		p1square.setText("Location: " + Integer.toString(players[0].getPlayerLocation().getSquareId()));
		p1grades.setText("Grades: " + Integer.toString(players[0].getGrades()));
		p1social.setText("Social: " + Integer.toString(players[0].getSocial()));
		p2square.setText("Location: " + Integer.toString(players[1].getPlayerLocation().getSquareId()));
		p2grades.setText("Grades: " + Integer.toString(players[1].getGrades()));
		p2social.setText("Social: " + Integer.toString(players[1].getSocial()));
	}
	
	public void movePlayerS(int numPlayer, int numMove) {
		int moveLoc;    // location to move
		
		moveLoc = players[numPlayer].getPlayerLocation().getSquareId() + numMove; // location to move = current location + distance to move
		for(int index = players[numPlayer].getPlayerLocation().getSquareId(); index < moveLoc;index++) {
			if (boardObject.getSquare(index).getType() == 'd') {
				try {
					decisionMade = new DecisionAlert().display(decisions.get(boardObject.getSquare(index).getEffectVal()));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			//	decisionMade = decisions.get(boardObject.getSquare(index).getEffectVal()).makeDecision();
				if (decisionMade == 1) {
					decisionEffects(decisions.get(boardObject.getSquare(index).getEffectVal()).getEffg1(), decisions.get(boardObject.getSquare(index).getEffectVal()).getEffs1(), players[numPlayer]);
				}
				if (decisionMade == 2) {
					decisionEffects(decisions.get(boardObject.getSquare(index).getEffectVal()).getEffg2(), decisions.get(boardObject.getSquare(index).getEffectVal()).getEffs2(), players[numPlayer]);
				}
				
			} 
			if (boardObject.getSquare(index).getType() == 'e') {
				System.out.println(players[numPlayer].getName() + " has reached the finish point");
				players[numPlayer].setPlayerLocation(boardObject.getSquare(index));
				return;
			}
		}
		players[numPlayer].setPlayerLocation(boardObject.getSquare(moveLoc));
		switch (players[numPlayer].getPlayerLocation().getType()) {
		case 'g':
			//call method to add to or subtract from grade metric
			players[numPlayer].setPlayerGrades(players[numPlayer].getGrades() + boardObject.getSquare(moveLoc).getEffectVal());
			try {
				new detailsAlert().display(players[numPlayer].getName(), "Grades", Integer.toString(boardObject.getSquare(moveLoc).getEffectVal()));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case 's':
			//call method to add to or subtract from social metric
			players[numPlayer].setPlayerSocial(players[numPlayer].getSocial() + boardObject.getSquare(moveLoc).getEffectVal());
			try {
				new detailsAlert().display(players[numPlayer].getName(), "Social", Integer.toString(boardObject.getSquare(moveLoc).getEffectVal()));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case 'w':
			//call method to draw wild-card
			drawCard(numPlayer);
			
			break;
		default:
			break;
		}
}}
	
