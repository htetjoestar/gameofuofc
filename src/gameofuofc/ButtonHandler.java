package gameofuofc;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class ButtonHandler {

    @FXML
    private Button button1;

    @FXML
    private Label There;

    @FXML
    void handle(ActionEvent event) {
    	There.setText("niggaa");
    }

}
