package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import dbConnection.UserDAO;
import dbConnection.UserDAOImpl;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import users.Amministratore;
import users.Elettore;
import users.User;

public class AdminUserEditorController implements Initializable {

    @FXML
    private Button confirmButton;

    @FXML
    private TextField fieldName;

    @FXML
    private TextField fieldSurname;

    @FXML
    private TextField fieldUsername;
    
    @FXML
    private PasswordField fieldPassword;

    @FXML
    private ChoiceBox<String> typeChoice;

    @FXML
    void confirm(ActionEvent event) {
    	String username = fieldUsername.getText();
    	String password = fieldPassword.getText();
    	String name = fieldName.getText();
    	String surname = fieldSurname.getText();
    	String type = typeChoice.getValue().toLowerCase();
    	
    	if(username.isBlank() || password.isBlank() || name.isBlank() || surname.isBlank() || type.isBlank()) {
    		return;
    	}
    	
    	User newUser;
    	UserDAO userDao = new UserDAOImpl();
    	if(type.equalsIgnoreCase("Amministratore")){
    		newUser = new Amministratore(0, name, surname, username);
    		userDao.insertUser(newUser, password);
    	}else if(type.equalsIgnoreCase("Elettore")) {
    		newUser = new Elettore(0, name, surname, username);
    		userDao.insertUser(newUser, password);
    	}    	
    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		typeChoice.getItems().addAll("Elettore", "Amministratore");
		typeChoice.setValue("Elettore");
	}

}
