package gui;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import dbConnection.PollDAO;
import dbConnection.PollDAOImpl;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import poll.Votazione;
import system.Sessione;

public class PollSelectionController {
	private Sessione sessione;
	


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
    private TableColumn<RowVotazione, String> col_description;
    
    @FXML
    private TableColumn<RowVotazione, String> col_action;
	
    @FXML
    private Button logoutButton;

    @FXML
    private Text nameSurnameLabel;

    @FXML
    private Text usernameLabel;

    @FXML
    private TableView<RowVotazione> votazioniTable;

    @FXML
    void logout(ActionEvent event) {
    	Sessione.getSessione().logoutUser();
    	showLoginWindow();
    }

    @FXML
    void nextPage(ActionEvent event) {

    }
    
    @FXML
    void initialize() {
    	sessione = Sessione.getSessione();
    	nameSurnameLabel.setText(sessione.getUser().getName() + " " + sessione.getUser().getSurname());
    	usernameLabel.setText(sessione.getUser().getUsername());
    	
    	col_id.setCellValueFactory(new PropertyValueFactory<>("Id"));
    	col_name.setCellValueFactory(new PropertyValueFactory<>("Nome"));
    	col_type.setCellValueFactory(new PropertyValueFactory<>("Tipo"));
    	col_startDate.setCellValueFactory(new PropertyValueFactory<>("Data_inizio"));
    	col_endDate.setCellValueFactory(new PropertyValueFactory<>("Data_fine"));
    	
    	col_description.setCellValueFactory(new PropertyValueFactory<>("button_info"));
    	col_action.setCellValueFactory(new PropertyValueFactory<>("button_azione"));
    	
    	PollDAO polls = new PollDAOImpl();
    	List<Votazione> votazioni = polls.getAll();
    	for(Votazione v : votazioni) {
    		RowVotazione rv = new RowVotazione(v);
    		votazioniTable.getItems().add(rv);
    	}    	
    }
    
    void showLoginWindow() {
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

}
