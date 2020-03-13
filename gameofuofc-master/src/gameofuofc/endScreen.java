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

public class endScreen {

    @FXML
    private Text textbox;

    @FXML
    private Button revealbtn;
    static String winner;
    Stage window = new Stage();
    void showEnd(String awinner) throws IOException {
    	winner = awinner;
		Parent root = FXMLLoader.load(Main.class.getResource("WinScreen.fxml"));
		window.setScene(new Scene(root));
		window.showAndWait();
    }
    @FXML
    void reveal(ActionEvent event) {
    	textbox.setText(winner + " has won!");
    }

}
