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
import javafx.stage.Stage;
import system.Sessione;
import vote.Voto;

public class ConfirmVoteWindowController implements Initializable {

    @FXML
    private Button confirmButton;

    @FXML
    private Button denyButton;

    @FXML
    private Label selectionLabel;

    @FXML
    void confirmVote(ActionEvent event) {
    	Sessione.getSessione().getVotazione().vota();
    	showPollSelection();
    }

    @FXML
    void denyVote(ActionEvent event) {
    	Sessione.getSessione().setVoto(null);
    	showPollWindow();
    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		Voto v = Sessione.getSessione().getVoto();
		selectionLabel.setText(v.toString());		
	}
	
	/**
	 * Mostra la schermata dove è possibile esprimere il voto per la votazione selezionata.
	 */
	private void showPollWindow() {
		try {
			denyButton.getScene().getWindow().hide();
    		Parent root = FXMLLoader.load(getClass().getResource("votazioneStandard.fxml"));
            Stage stage = new Stage();
        	stage.setTitle("Sistema di voto elettronico - Votazione");
        	stage.setScene(new Scene(root, 900, 780));
        	stage.setResizable(false);
        	stage.show();
		}catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}
	
	/**
     * Chiude la schermata di login ed apre la schermata di selezione votazione dedicata agli Elettori.
     */
    private void showPollSelection() {
    	try {
			confirmButton.getScene().getWindow().hide();
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
