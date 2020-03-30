package gameofuofc;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
* The class endScreen contains methods that display the end of the game ends 
* and give the user the option to reveal winner. 
*/

public class endScreen {

    @FXML
    private Text textbox;

    @FXML
    private Button revealbtn;
    static String winner;
    Stage window = new Stage();
	
    /** The method showEnd(String awinner) launches the display that the game has ended. 
    * It contains a button that would reveal the winner. It takes winner of type String, 
    * throws IOException, and returns nothing.
    */
	
    void showEnd(String awinner) throws IOException {
    	winner = awinner;
		Parent root = FXMLLoader.load(Main.class.getResource("WinScreen.fxml"));
		window.setScene(new Scene(root));
		window.showAndWait();
    }
	
    // This method handles the event at which the button has been clicked and reveals 
    // the winner. 
    @FXML
    void reveal(ActionEvent event) {
    	textbox.setText(winner + " has won!");
    }

}
