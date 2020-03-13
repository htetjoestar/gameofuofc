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

public class wildcardHandler {

    @FXML
    private Button drawCard;

    @FXML
    private Button resolve;

    @FXML
    private Text Detes;
    @FXML
    private Text effText;
    
    static Stage window = new Stage();
    static String displayText;
    static String effects;
    static String value;
    @FXML
    void initialize() {
    	effText.setText("");
    	resolve.setVisible(false);
    }
    public void display(String adisplaytext, String aeffects, String avalue) throws IOException{
		Parent root = FXMLLoader.load(Main.class.getResource("wildcardAlert.fxml"));
		displayText = adisplaytext;
		effects = aeffects;
		value = avalue;
		window.setScene(new Scene(root));
 		window.showAndWait();
    }
    
    @FXML
    void closewindow(ActionEvent event) {
    	window.close();
    }

    @FXML
    void drawtheCard(ActionEvent event) {
    	resolve.setVisible(true);
    	drawCard.setVisible(false);
    	Detes.setText(displayText);
    	effText.setText(value + " has been added to " + effects);
    }
    }
