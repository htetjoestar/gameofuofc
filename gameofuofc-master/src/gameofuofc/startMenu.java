package gameofuofc;


import java.util.ArrayList;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
* The class startMenu contains methods that display and handle a start menu for the game. 
* It contains a welcome message, two text boxes for player 1 and player 2, 
* a button to start a new game, and another button to load a previous game.
* The start menu also gives the user the option to play against an AI. 
*/

public class startMenu extends Application {
	ArrayList<Player> playerNames = new ArrayList<>(); 
	   Player player1=new Player(0, null);
	   Player player2=new Player(1, null);
	
	/**
	* The method display() displays all the features, which were organized in GridPane.
	* @param Nothing
	* @return variable of type ArrayList<Player>
	*/
	
	public ArrayList<Player> display() throws Exception
	   {
		   
		   Stage primaryStage = new Stage();
		   Button start = new Button("New Game");
		   Button load=new Button("Load Game");
		   TextField txtPl1; 
		   TextField txtPl2; 
		   Label message; 
		   
		   // Setting up the screen using GridPane 
		   GridPane layout= new GridPane();
		   layout.setVgap(10);
		   layout.setHgap(10);		   
		   layout.add(new Label("Welcome Dino!"),1,0);
		  
		   // Creating input boxes for player names. 
		   txtPl1 = new TextField(""); // Default text of 0
		   txtPl1.setPrefWidth(200);
		   txtPl2 = new TextField("");
		   txtPl2.setPrefWidth(200);
		   
		   // Adding the text box and button onto the screen.
		   layout.add(new Label("Enter Player 1"),1,1);
		   layout.add(new Label("Enter Player 2"),1,2);
		   layout.add(new Label("Leave Player 2 blank to verse PC."),1,3);
		   layout.add(txtPl1,2,1);
		   layout.add(txtPl2, 2, 2);
		   layout.add(start, 1, 4);
		   layout.add(load, 2, 4);
		   
		   message= new Label("");
		   layout.add(message, 1, 4);
		   Scene scene = new Scene(layout, 400, 175);
		   primaryStage.setTitle("Game of University of Calgary");
		   primaryStage.setScene(scene);
		
		   // This method contains the event handler class for start button.
		   start.setOnAction(new EventHandler<ActionEvent>()
		   {		   
			   	/**
				* The class handle(ActionEvent event) is an internal event handler class for 
				* the START button. It checks if any player names were entered, and if the 
				* user wants to play against an AI. 
				* @param variable of type ActionEvent
				* @return Nothing
				*/
			   
			   	@Override
			   	public void handle(ActionEvent event)
			   	{
			   		int aiExists;
			   		String pl1Name=txtPl1.getText();
			   		String pl2Name=txtPl2.getText();
			   		
			   		if(pl1Name.equalsIgnoreCase("") &&
			   			pl2Name.equalsIgnoreCase("")) {
			   			message.setText("Enter at least one player.");
			   		}
			   		else {
			   		    player1.setPlayerName(pl1Name);
			   		    playerNames.add(player1);
			   			
			   			//If player 2 is blank, AI is triggered. 
			   			if(pl2Name.equalsIgnoreCase("")) {
			   				aiExists=1;
			   				player2.setIsAI(true);
			   				primaryStage.close();
			   			}
			   			//If player 2 is not blank, AI is not triggered. 
			   			else {
			   				player1.setPlayerName(pl1Name);
				   			playerNames.add(player1);
				   			player2.setPlayerName(pl2Name);
				   			playerNames.add(player2);
			   				primaryStage.close();
			   			}
			   			
			   		}
			   	}
		   }
		   );
		   
		   // Set the event handler when the LOAD button is clicked
		   load.setOnAction(new EventHandler<ActionEvent>()
		   {		   
			   	/** 
				* This method handle (ActionEvent event) handles events for the LOAD button. 
				* When clicked, it calls for an old list of player names, which will tell 
				* the main class to load a previous game with those players. 
				* @param variable of type ActionEvent
				* @return Nothing
				*/
			   
			   	@Override
			   	public void handle(ActionEvent event)
			   	{
			   		playerNames=Main.serializeDataIn();
			   		primaryStage.close();
			   		}
			   	}
		   );
		   
		   primaryStage.showAndWait();
		   return playerNames;
		
	}
	
	@Override
	public void start(Stage arg0) throws Exception {
		// TODO Auto-generated method stub
		
	}
}
