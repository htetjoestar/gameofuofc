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

/**
* The class DecisionAlert contains methods that notify the user when they've landed 
* on a decision spot, which asks them a question and gives them choices. Each choice
* affects their social and grades bars differently. 
*/

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

    /** 
    * The method initialize() notifies the user when they have landed on a decision spot 
    * and gives them options. 
    * @param None
    * @returns Nothing
    */
    @FXML
    void initialize() {
    	desc.setText("You have landed on the Decision. Click ->");
    }
	/**
	* The display(Decisions theDe) is an internal class that launches the decisions screen. 
	* @param variable of type Decisions 
	* @throws IOException 
	* @return integer, which represents an option 
	*/
	
	public int display(Decisions theDe) throws IOException {
			tobe = new Decisions(theDe);
	    	
			Parent root = FXMLLoader.load(Main.class.getResource("decisionAlert.fxml"));
			window.setScene(new Scene(root));
     		window.showAndWait();
     		return val;
	}
	
    /** 
    * The method Show(Action event) handles the event at which a button has been clicked 
    * to reveal the question and options. 
    * @param variable of type Action 
    * @return Nothing 
    */
		
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
	
    /**
    * The method closeWindow(ActionEvent event) handles the event at which the user 
    * selected option 2. 
    * @param variable of type ActionEvent 
    * @return Nothing 
    */
	
    @FXML
    void closeWindow(ActionEvent event) {
    	val = 2;
    	window.close();
    }
	
    /**
    * The method op1effs(ActionEvent event) handles the event at which the user 
    * selected option 1. 
    * @param variable of type ActionEvent 
    * @return Nothing 
    */
	
    @FXML
    void op1effs(ActionEvent event) {
    	val = 1;
    	window.close();
    }
}
	
