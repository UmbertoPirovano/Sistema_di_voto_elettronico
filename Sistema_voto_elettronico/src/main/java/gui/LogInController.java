package gui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

public class LogInController {

    @FXML
    private Text error;

    @FXML
    private Button loginButton;

    @FXML
    private PasswordField password;

    @FXML
    private TextField userName;
    
    public LogInController(){
    	error = new Text();
    }

    @FXML
    void logIn(MouseEvent event) {
    	error.setText("");
    	error.setText("Benvenuto "+userName.getText());
    }

}
