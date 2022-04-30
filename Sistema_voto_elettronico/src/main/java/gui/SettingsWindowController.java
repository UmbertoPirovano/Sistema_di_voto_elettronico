package gui;

import java.net.URL;
import java.util.ResourceBundle;

import dbConnection.DatabaseConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class SettingsWindowController implements Initializable {

    @FXML
    private Button buttonConfirm;

    @FXML
    private TextField fieldDb;

    @FXML
    private PasswordField fieldPwd;

    @FXML
    private TextField fieldUsr;
    
    @FXML
    void confirmConnectionSettings(ActionEvent event) {
    	String db = fieldDb.getText();
    	String user = fieldUsr.getText();
    	String pwd = fieldPwd.getText();
    	DatabaseConnection.setParams(db, user, pwd);
    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		fieldDb.setText(DatabaseConnection.getDatabase());
		fieldUsr.setText(DatabaseConnection.getUser());
	}

}
