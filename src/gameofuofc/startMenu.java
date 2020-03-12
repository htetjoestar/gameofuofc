package gameofuofc;


import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class startMenu extends Application {
	
	public String [] display() throws Exception
	   {
		   Stage primaryStage = new Stage();
		   Button start = new Button("Start");
		   TextField txtPl1; 
		   TextField txtPl2; 
		   Label message; 
		   String [] playerNames = {null,null}; 
//		   Setting up the screen using GridPane 
		   GridPane layout= new GridPane();
		   layout.setVgap(10);
		   layout.setHgap(10);		   
		   layout.add(new Label("Welcome Dino!"),1,0);
		  
//		   Creating input boxes for player names. 
		   txtPl1 = new TextField(""); // Default text of 0
		   txtPl1.setPrefWidth(200);
		   txtPl2 = new TextField("");
		   txtPl2.setPrefWidth(200);
		   
//		   Adding the text box and button onto the screen.
		   layout.add(new Label("Enter Name for Player 1"),1,1);
		   layout.add(new Label("Enter Name for Player 2"),1,2);
		   layout.add(txtPl1,2,1);
		   layout.add(txtPl2, 2, 2);
		   layout.add(start, 1, 3);
		   
		   message= new Label("");
		   layout.add(message, 1, 4);
		   Scene scene = new Scene(layout, 400, 175);
		   primaryStage.setTitle("Game of University of Calgary");
		   primaryStage.setScene(scene);
		   
		   
		   // Set the event handler when the button is clicked
		   start.setOnAction(new EventHandler<ActionEvent>()
		   {		   
			   	@Override
			   	public void handle(ActionEvent event)
			   	{
			   		String player1Name; 
			   		String player2Name;
			   		int aiExists;
			   		String pl1Name=txtPl1.getText();
			   		String pl2Name=txtPl2.getText();
			   		
			   		if(pl1Name.equalsIgnoreCase("") &&
			   			pl2Name.equalsIgnoreCase("")) {
			   			message.setText("Do not leave player names blank.");
			   		}
			   		else {
			   			player1Name=pl1Name;
			   			playerNames[0] = player1Name;
			   			//If only one player entered, AI is triggered. 
			   			if(pl2Name.equalsIgnoreCase("")) {
			   				player2Name="1";
			   				aiExists=Integer.parseInt(player2Name);
			   				primaryStage.close();
			   			}
			   			//Two players entered, AI is not triggered. 
			   			else {
			   				player2Name=pl2Name; 
			   				playerNames[1] = player2Name;
			   				primaryStage.close();
			   			}
			   		}
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
