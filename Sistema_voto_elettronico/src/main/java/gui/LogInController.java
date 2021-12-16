package gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;

public class LogInController {

	@FXML
	BorderPane container;
	
    @FXML
    private MenuItem adminItem;

    @FXML
    private MenuItem electorItem;

    @FXML
    private Text error;

    @FXML
    private Button loginButton;

    @FXML
    private PasswordField password;

    @FXML
    private TextField userName;

    @FXML
    private MenuButton listTypes;
    
    private String userType;
    
    public LogInController(){
    	error = new Text();
    	userType = "";
    }

    @FXML
    void setToAdmin(ActionEvent event) {
    	userType = "amministratore";
    	listTypes.setText(adminItem.getText());
    }

    @FXML
    void setToElctor(ActionEvent event) {
    	userType = "elettore";
    	listTypes.setText(electorItem.getText());
    }

    @FXML
    void logIn(MouseEvent event) {
    	error.setText("");
    	error.setText("Benvenuto "+userName.getText());
    }
    
    @FXML
    void removeAllFocus(MouseEvent event) {
    	container.requestFocus();
    }

}
