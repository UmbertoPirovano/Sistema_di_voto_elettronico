package gui;

import java.io.IOException;
import java.net.URL;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;

import dbConnection.PollDAO;
import dbConnection.PollDAOImpl;
import dbConnection.UserDAO;
import dbConnection.UserDAOImpl;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import poll.Votazione;
import system.Sessione;
import users.User;

public class AdminPollListController implements Initializable {
	private Sessione sessione;
	
    @FXML
    private Button addUserButton;

    @FXML
    private Button backButton;

    @FXML
    private TableColumn<RowVotazione, Integer> col_id;

    @FXML
    private TableColumn<RowVotazione, String> col_name;
    
    @FXML
    private TableColumn<RowVotazione, String> col_type;

    @FXML
    private TableColumn<RowVotazione, String> col_startDate;

    @FXML
    private TableColumn<RowVotazione, String> col_endDate;
    
    @FXML
    private TableColumn<RowVotazione, String> col_action;
    
    @FXML
    private TableColumn<RowVotazione, String> col_status;

    @FXML
    private Button logoutButton;

    @FXML
    private Text nameSurnameLabel;

    @FXML
    private Text usernameLabel;

    @FXML
    private TableView<RowVotazione> votazioniTable;

    @FXML
    void goBack(ActionEvent event) {
    	showAdminMenu();
    }

    @FXML
    void logout(ActionEvent event) {
    	Sessione.getSessione().logoutUser();
    	showLoginWindow();
    }
    
    @FXML
    void mouse(MouseEvent event) {
    	refreshList();
    }

    @FXML
    void showPollEditor(ActionEvent event) {
    	try {
    		Parent root = FXMLLoader.load(getClass().getResource("AdminPollEditor.fxml"));
            Stage stage = new Stage();
        	stage.setTitle("Sistema di voto elettronico - Editor votazioni");
        	stage.setScene(new Scene(root, 700, 800));
        	stage.setResizable(false);
        	stage.show();
		}catch (IOException e) {
			System.out.println(e.getMessage());
		}
    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		sessione = Sessione.getSessione();
    	nameSurnameLabel.setText(sessione.getUser().getName() + " " + sessione.getUser().getSurname());
    	usernameLabel.setText(sessione.getUser().getUsername());
    	
    	col_id.setCellValueFactory(new PropertyValueFactory<>("Id"));
    	col_name.setCellValueFactory(new PropertyValueFactory<>("Nome"));
    	col_type.setCellValueFactory(new PropertyValueFactory<>("Tipo"));
    	col_startDate.setCellValueFactory(new PropertyValueFactory<>("Data_inizio"));
    	col_endDate.setCellValueFactory(new PropertyValueFactory<>("Data_fine"));
    	col_status.setCellValueFactory(new PropertyValueFactory<>("Stato"));
    	col_action.setCellValueFactory(new PropertyValueFactory<>("buttonBar"));
    	
    	refreshList();
	}
	
	private void refreshList() {
		votazioniTable.getItems().clear();
		
		PollDAO polls = new PollDAOImpl();
    	List<Votazione> votazioni = polls.getAll();
    	
    	for(Votazione v : votazioni) {
    		RowVotazione rv = new RowVotazione(v);
    		votazioniTable.getItems().add(rv);
    	}
    	Collections.sort(votazioniTable.getItems());
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
