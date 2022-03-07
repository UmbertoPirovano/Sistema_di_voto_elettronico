package gui;

import java.io.IOException;

import dbConnection.PollDAO;
import dbConnection.PollDAOImpl;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import poll.Votazione;
import system.Sessione;
import users.User;

public class BookPollController {

    @FXML
    private Label label_nomeVotazione;

    @FXML
    private Button logoutButton;

    @FXML
    private Text nameSurnameLabel;

    @FXML
    private Button noButton;

    @FXML
    private Text usernameLabel;

    @FXML
    private Button yesButton;

    @FXML
    void confirm(ActionEvent event) {
    	PollDAO p = new PollDAOImpl();
    	p.book(Sessione.getSessione().getUser(), Sessione.getSessione().getVotazione());
    	showPollSelection();
    }

    @FXML
    void deny(ActionEvent event) {
    	showPollSelection();
    }

    @FXML
    void logout(ActionEvent event) {
    	Sessione.getSessione().logoutUser();
    	showLoginWindow();
    }
    
    @FXML
    void initialize() {
    	User u = Sessione.getSessione().getUser();
    	nameSurnameLabel.setText(u.getName() + " " + u.getSurname());
    	usernameLabel.setText(u.getUsername());
    	
    	Votazione v = Sessione.getSessione().getVotazione();
    	label_nomeVotazione.setText(v.getNome());    	
    }
    
    /**
     * Chiude la schermata corrente ed apre la schermata di login.
     */
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
    
    /**
     * Chiude la schermata corrente ed apre la schermata di selezione votazione dedicata agli Elettori.
     */
    void showPollSelection() {
    	try {
			yesButton.getScene().getWindow().hide();
    		Parent root = FXMLLoader.load(getClass().getResource("PollSelection.fxml"));
            Stage stage = new Stage();
        	stage.setTitle("Sistema di voto elettronico - Selezione votazioni");
        	stage.setScene(new Scene(root, 900, 780));
        	stage.setResizable(false);
        	stage.show();
		}catch (IOException e) {
			System.out.println(e.getMessage());
		}
    }

}

