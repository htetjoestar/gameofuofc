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

public class detailsAlert {

    @FXML
    private Text details;

    @FXML
    private Text playerName;

    @FXML
    private Button resolve;

    @FXML
    private Button showDetails;
    static Stage window = new Stage();
    static String name  = new String();
    static String eff;
    static String val;
    @FXML
    void initialize() {
    	playerName.setText("Landed on a Square");
    	details.setText("");
    }
    public void display(String name, String eff, String val) throws IOException{
    	this.name = name;
    	this.eff = new String (eff);
    	this.val = new String(val);
		Parent root = FXMLLoader.load(Main.class.getResource("details.fxml"));
		window.setScene(new Scene(root));
 		window.showAndWait();
    }
    @FXML
    void resolve(ActionEvent event) {
    	window.close();
    }

    @FXML
    void showDetails(ActionEvent event) {
    	showDetails.setVisible(false);
    	playerName.setText(name);
    	details.setText(val + " has been added to " + eff);
    }
}
