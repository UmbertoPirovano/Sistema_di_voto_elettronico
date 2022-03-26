package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import system.Sessione;

public class ReferendumWindowController implements Initializable {

    @FXML
    private Label labelVotazione;

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
    void logout(ActionEvent event) {
    	Sessione.getSessione().logoutUser();
    	showLoginWindow();
    }

    @FXML
    void selectNo(ActionEvent event) {

    }

    @FXML
    void selectYes(ActionEvent event) {

    }
    
    @Override
	public void initialize(URL location, ResourceBundle resources) {
		labelVotazione.setText(Sessione.getSessione().getVotazione().getNome().toUpperCase());
		nameSurnameLabel.setText(Sessione.getSessione().getUser().getName() + " " + Sessione.getSessione().getUser().getSurname());
		usernameLabel.setText(Sessione.getSessione().getUser().getUsername());		
	}
    
    /**
	 * Chiude la schermata corrente ed apre qella di login.
	 */
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
	
	/**
	 * Apre la schermata di riepilogo dove è possibile confermare il proprio voto.
	 */
	private void showConfirmWindow() {
		try {
			yesButton.getScene().getWindow().hide();
			Parent root = FXMLLoader.load(getClass().getResource("ConfirmVoteWindow.fxml"));
	        Stage stage = new Stage();
	    	stage.setTitle("Sistema di voto elettronico - Conferma voto");
	    	stage.setScene(new Scene(root, 600, 400));
	    	stage.setResizable(false);
	    	stage.show();
		}catch (IOException e) {
			System.out.println(e.getMessage());
		}	    
	}

}
