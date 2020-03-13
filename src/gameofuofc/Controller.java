package gameofuofc;



import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

public class Controller extends Main{
    @FXML
    private Label TurnLabel;
    @FXML
    static private ImageView boardpic;

    @FXML
    private Button RollButton;
    
    @FXML
    private Text previousTurnP;

    @FXML
    private Text previousTurnE;
    
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
    
    @FXML
    private Button saveBtn;

    @FXML
    private Text savedtxt;


	public Board boardObject = new Board();
	static int ModulusCounter = 2;
	static int checkPlayer = 0;
	static int decisionMade = 0;
	ArrayList<Player> temp = new ArrayList<Player>();
	@FXML
	void initialize()  {
		initializeDecisions();
		shuffleWildcards();
		try {
			temp = new startMenu().display();
			players[0] = (temp.get(0));
			players[1] = (temp.get(1));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		savedtxt.setVisible(false);
		player1Name.setText(players[0].getName());
		player2Name.setText(players[1].getName());
		TurnLabel.setText(players[currentPlayer].getName() + "'s turn");
		previousTurnP.setText("");
		previousTurnE.setText("");
		updateStats();
		currentPlayer = choosePlayer();
		currentPlayer = seeIfPlayerFinished(currentPlayer);
	}
	@FXML
	void Roll(ActionEvent event) {
		if(super.seeIfBothPlayersFinished() ==  false) {
			this.movePlayerS(currentPlayer, super.spin());
			updateStats();
			currentPlayer = choosePlayer();
			currentPlayer = seeIfPlayerFinished(currentPlayer);
			TurnLabel.setText(players[currentPlayer].getName() + "'s turn");
			if(players[currentPlayer].getIsAI() == true) {
				this.movePlayerS(currentPlayer, super.spin());
				updateStats();
				currentPlayer = choosePlayer();
				currentPlayer = seeIfPlayerFinished(currentPlayer);
				TurnLabel.setText(players[currentPlayer].getName() + "'s turn");
			}
		}else if (super.seeIfBothPlayersFinished() ==  true){
		try {
			new endScreen().showEnd(seeWhoWon().getName());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
		savedtxt.setVisible(false);	
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
					if(players[numPlayer].getIsAI() == false) {
					decisionMade = new DecisionAlert().display(decisions.get(boardObject.getSquare(index).getEffectVal()));
					}else {
						decisionMade = computerRandom();
					}
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
				previousTurnP.setText("Previous turn:" + players[numPlayer].getName());
				previousTurnE.setText(Integer.toString(boardObject.getSquare(moveLoc).getEffectVal()) + " Has been added to grades");
			break;
		case 's':
			//call method to add to or subtract from social metric
			players[numPlayer].setPlayerSocial(players[numPlayer].getSocial() + boardObject.getSquare(moveLoc).getEffectVal());
			previousTurnP.setText("Previous turn:" + players[numPlayer].getName());
			previousTurnE.setText(Integer.toString(boardObject.getSquare(moveLoc).getEffectVal()) + " Has been added to grades");
			break;
		case 'w':
			//call method to draw wild-card
			drawCard(numPlayer);
			previousTurnP.setText("Previous turn:" + players[numPlayer].getName());
			previousTurnE.setText("Landed on a wildCard");
			break;
		default:
			break;
		}
}
	public static void drawCard(int numPlayer) {
		wildcards drawn = new wildcards("", 'd', 0);
		Collections.shuffle(wildcards);
		drawn = wildcards.get(0);
		switch (drawn.getEff()) {
		case 'g':
			//call method to add to or subtract from grade metric
			System.out.println(Integer.toString(drawn.getEffectVal()) + " has been added to grades");
			players[numPlayer].setPlayerGrades(players[numPlayer].getGrades() + drawn.getEffectVal());
			if(players[numPlayer].getIsAI() == false) {
			try {
				new wildcardHandler().display(drawn.getDisc(), "grades score", Integer.toString(drawn.getEffectVal()));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}}
			break;
		case 's':
			//call method to add to or subtract from social metric
			System.out.println(Integer.toString(drawn.getEffectVal()) + " has been added to social");
			players[numPlayer].setPlayerGrades(players[numPlayer].getSocial() + drawn.getEffectVal());
			if(players[numPlayer].getIsAI() == false) {
			try {
				new wildcardHandler().display(drawn.getDisc(), "social score", Integer.toString(drawn.getEffectVal()));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}}
			break;
		case 'b':
			System.out.println(Integer.toString(drawn.getEffectVal()) + " has been added to both grades and social");
			players[numPlayer].setPlayerGrades(players[numPlayer].getGrades() + drawn.getEffectVal());
			players[numPlayer].setPlayerGrades(players[numPlayer].getSocial() + drawn.getEffectVal());
			if(players[numPlayer].getIsAI() == false) {
			try {
				new wildcardHandler().display(drawn.getDisc(), "grades and social score", Integer.toString(drawn.getEffectVal()));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		}}
	}
	public static Player seeWhoWon() {  // sees who won, by comparing the total social/grades scores. If there has been a tie, then the winner is randomly chosen.
		int player0Total = 0;
		int player1Total = 0;
		
		player0Total = players[0].getGrades() + players[0].getSocial();
		player1Total = players[1].getGrades() + players[1].getSocial();
		
		if (player0Total > player1Total) {
			return players[0];
		}
		if (player0Total < player1Total) {
			return players[1];
		}
		if (player0Total == player1Total) {
			int min = 1;
			int max = 6;
			int randomInt = (int)(Math.random() * (max - min + 1) + min);
			if (randomInt >= 3) {
				return players[1];
			}
			else {
				return players[0];
			}
			
		}
		return null;
		
	}

    @FXML
    void save(ActionEvent event) throws IOException {
    	ArrayList<Player> temp = new ArrayList<Player>();
    	temp.add(players[0]);
    	temp.add(players[1]);
    	dataout(temp);
    	savedtxt.setVisible(true);
    }
	}
	
