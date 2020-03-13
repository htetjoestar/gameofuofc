package gameofuofc;

import java.io.IOException;

import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;



public class DecisionAlert {
	
    @FXML
    private Button btn;
    
    @FXML
    private Text desc;
    @FXML
    private Button showBtn;
    
    @FXML
    private Text op1text;

    @FXML
    private Button op1btn;

    @FXML
    private Text op2;

    static private Decisions tobe;
    static private int val;
    static Stage window = new Stage();
    @FXML
    void initialize() {
    	desc.setText("You have landed on the Decision. Click ->");
    }
	public int display(Decisions theDe) throws IOException {
			tobe = new Decisions(theDe);
	    	
			Parent root = FXMLLoader.load(Main.class.getResource("decisionAlert.fxml"));
			window.setScene(new Scene(root));
     		window.showAndWait();
     		return val;
	}

    @FXML
    void Show(ActionEvent event) {
    	
    	desc.setText(tobe.getDesc());
    	op2.setText(tobe.getOp2());
    	op1text.setText(tobe.getOp1());
    	op1text.setVisible(true);
    	op2.setVisible(true);
    	op1btn.setVisible(true);
    	showBtn.setVisible(false);
    	btn.setVisible(true);
    }
    @FXML
    void closeWindow(ActionEvent event) {
    	val = 2;
    	window.close();
    }
    @FXML
    void op1effs(ActionEvent event) {
    	val = 1;
    	window.close();
    }
}
	
