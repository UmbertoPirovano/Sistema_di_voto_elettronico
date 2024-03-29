package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;


import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import poll.VotazioneStandard;
import system.Sessione;
import vote.Voto;
import vote.VotoOrdinale;

public class VotazioneStandardController implements Initializable {

    @FXML
    private Button logoutButton;

    @FXML
    private Text nameSurnameLabel;

    @FXML
    private Text usernameLabel;
    
    @FXML
    private Label labelVotazione;
    
    @FXML
    private ListView<Node> candidateList;
    
    @FXML
    private ListView<Node> selectedList;
    
    @FXML
    private Button submitButton;

    @FXML
    void logout(ActionEvent event) {
    	Sessione.getSessione().logoutUser();
    	showLoginWindow();
    }
    
    @FXML
    void confirmVote(ActionEvent event) {
    	VotazioneStandard p = (VotazioneStandard) Sessione.getSessione().getVotazione();
    	List<String> ids = new ArrayList<>();

    	for(Node n : selectedList.getItems()) {
    		ids.add(n.getId());
    	}
    	Voto v = new VotoOrdinale(p.getVotoAPartiti(),p.getCandidatiFromNode(ids));
    	Sessione.getSessione().setVoto(v);
    	showConfirmWindow();
    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		labelVotazione.setText(Sessione.getSessione().getVotazione().getNome().toUpperCase());
		nameSurnameLabel.setText(Sessione.getSessione().getUser().getName() + " " + Sessione.getSessione().getUser().getSurname());
		usernameLabel.setText(Sessione.getSessione().getUser().getUsername());	
		
		loadCandidates();		
	}    
	
	/**
	 * Renderizza ed inserisce nella lista candidateList una visualizzazione dei candidati
	 * disponibili per la votazione.
	 */
	private void loadCandidates() {		
		VotazioneStandard v = (VotazioneStandard) Sessione.getSessione().getVotazione();
		v.updateCandidati();
		for(int i=0; i < v.countCandidati(); i++) {
			try {
				
				final Node n = FXMLLoader.load(getClass().getResource("NodeCandidato.fxml"));
				
				n.setOnMouseClicked(new EventHandler<MouseEvent>() {
					@Override
					public void handle(MouseEvent event) {
						if(candidateList.getItems().contains(n)) {
							selectedList.getItems().add(n);
							candidateList.getItems().remove(n);
						}else if(selectedList.getItems().contains(n)) {
							selectedList.getItems().remove(n);
							candidateList.getItems().add(n);
						}
					}					
				});
				n.setId("" + i);
				v.assocNodeCandidate(v.getCandidati().get(i), n.getId());
				candidateList.getItems().add(n);
			}catch(IOException e){
				e.printStackTrace();
			}
		}		
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
	 * Apre la schermata di riepilogo dove � possibile confermare il proprio voto.
	 */
	private void showConfirmWindow() {
		try {
			submitButton.getScene().getWindow().hide();
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
