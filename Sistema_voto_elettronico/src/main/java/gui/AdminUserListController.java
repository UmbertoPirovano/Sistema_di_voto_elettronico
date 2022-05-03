package gui;

import java.io.IOException;
import java.net.URL;
import java.util.Collections;
import java.util.List;
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
import javafx.scene.control.ButtonBar;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import system.Sessione;
import users.User;

public class AdminUserListController implements Initializable {
	private Sessione sessione;
	
    @FXML
    private Button addUserButton;
    
    @FXML
    private Button backButton;

    @FXML
    private TableColumn<RowUser, String> col_username;
    
    @FXML
    private TableColumn<RowUser, String> col_name;

    @FXML
    private TableColumn<RowUser, String> col_surname;
    
    @FXML
    private TableColumn<RowUser, String> col_type;
    
    @FXML
    private TableColumn<RowUser, String> col_status;

    @FXML
    private TableColumn<RowUser, ButtonBar> col_action;

    @FXML
    private Button logoutButton;

    @FXML
    private Text nameSurnameLabel;

    @FXML
    private TableView<RowUser> userTable;

    @FXML
    private Text usernameLabel;

    @FXML
    void logout(ActionEvent event) {
    	Sessione.getSessione().logoutUser();
    	showLoginWindow();
    }

    @FXML
    void goBack(ActionEvent event) {
    	showAdminMenu();
    }
    
    @FXML
    void mouse(MouseEvent event) {
    	refreshList();
    }

	@FXML
    void showUserEditor(ActionEvent event) {
		try {
    		Parent root = FXMLLoader.load(getClass().getResource("AdminUserEditor.fxml"));
            Stage stage = new Stage();
        	stage.setTitle("Sistema di voto elettronico - User editor");
        	stage.setScene(new Scene(root, 500, 400));
        	stage.setResizable(false);
        	stage.show();
		}catch (IOException e) {
			System.out.println(e.getMessage());
		}
		
		refreshList();
    }

	private void refreshList() {
		userTable.getItems().clear();
		
		UserDAO usersDao = new UserDAOImpl();
    	List<User> users = usersDao.findAll();
    	
    	for(User u : users) {
    		RowUser ru = new RowUser(u);
    		userTable.getItems().add(ru);
    	}		
    	Collections.sort(userTable.getItems());
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		sessione = Sessione.getSessione();
    	nameSurnameLabel.setText(sessione.getUser().getName() + " " + sessione.getUser().getSurname());
    	usernameLabel.setText(sessione.getUser().getUsername());	
    	
    	col_username.setCellValueFactory(new PropertyValueFactory<>("Username"));
    	col_name.setCellValueFactory(new PropertyValueFactory<>("Name"));
    	col_surname.setCellValueFactory(new PropertyValueFactory<>("Surname"));
    	col_type.setCellValueFactory(new PropertyValueFactory<>("Type"));
    	col_action.setCellValueFactory(new PropertyValueFactory<>("ButtonBar"));
    	
    	refreshList();
	}

	private void showLoginWindow() {
		try {
			logoutButton.getScene().getWindow().hide();
    		Parent root = FXMLLoader.load(getClass().getResource("LoginWindow.fxml"));
            Stage stage = new Stage();
        	stage.setTitle("Sistema di voto elettronico - Login");
        	stage.setScene(new Scene(root, 500, 390));
        	stage.setResizable(false);
        	stage.show();
		}catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}
	
	private void showAdminMenu() {
		try {
			logoutButton.getScene().getWindow().hide();
    		Parent root = FXMLLoader.load(getClass().getResource("AdminMenu.fxml"));
            Stage stage = new Stage();
        	stage.setTitle("Sistema di voto elettronico - Menù amministratore");
        	stage.setScene(new Scene(root, 900, 800));
        	stage.setResizable(false);
        	stage.show();
		}catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}
}
