/**
 * Le istanze di questa classe rappresentano una riga della tabella della schermata "PollSelection.fxml".
 * Conserva un riferimento all'oggetto Votazione dal quale viene creato, rende gli attributi di questo rappresentabili in forma tabellare
 * ed aggiunge i due tasti "info" e "prenota o vota" necessari rispettivamente a visualizzare i dettagli della votazione o a prenotarsi/votare
 * per essa.
 */
package gui;

import java.io.IOException;
import java.util.Objects;

import dbConnection.PollDAO;
import dbConnection.PollDAOImpl;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import poll.Referendum;
import poll.Votazione;
import poll.VotazioneStandard;
import system.Sessione;
import users.Elettore;

public class RowVotazione {
	private final Votazione v;
	private final SimpleIntegerProperty id;
	private final SimpleStringProperty nome;
	private final SimpleStringProperty tipo;
	private final SimpleStringProperty data_inizio;
	private final SimpleStringProperty data_fine;
	private final SimpleStringProperty descrizione;
	private Button button_info;
	private Button button_azione;
	
	public RowVotazione(Votazione v) {
		this.v = Objects.requireNonNull(v);
		this.id = new SimpleIntegerProperty(v.getId());
		this.nome = new SimpleStringProperty(v.getNome());
		if(v instanceof Referendum)
			this.tipo = new SimpleStringProperty("REFERENDUM");
		else
			this.tipo = new SimpleStringProperty(((VotazioneStandard) v).getTipo());
		this.data_inizio = new SimpleStringProperty(v.getData_inizio());
		this.data_fine = new SimpleStringProperty(v.getData_fine());
		this.descrizione = new SimpleStringProperty(v.getDescrizione());
		this.button_info = new Button("Info");
		this.button_azione = new Button("Prenota o vota");
		
		button_info.setOnAction(event -> showMessageWindow());
		button_azione.setOnAction(event -> handleAzione());
	}
	
	public int getId() {
		return id.get();
	}
	
	public String getNome() {
		return nome.get();
	}
	
	public String getTipo() {
		return tipo.get();
	}
	
	public String getData_inizio() {
		return data_inizio.get();
	}
	
	public String getData_fine() {
		return data_fine.get();
	}
	
	public String getDescrizione() {
		return descrizione.get();
	}
	
	public Button getButton_info() {
		return button_info;
	}
	
	public Button getButton_azione() {
		return button_azione;
	}
	
	/**
	 * Alla pressione del bottone "info" viene aperta una schermata che mostra i dettagli di questa votazione.
	 */
	private void showMessageWindow() {
		try {
    		Sessione.getSessione().setVotazione(v);
			
			Parent root = FXMLLoader.load(getClass().getResource("PollDescription.fxml"));
            Stage stage = new Stage();
        	stage.setTitle("Sistema di voto elettronico - Descrizione");
        	stage.setScene(new Scene(root, 600, 400));
        	stage.setResizable(false);
        	stage.show();
		}catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}
	
	/**
	 * Alla pressione del bottone "Prenota o vota" se l'utente non è ancora prenotato per questa votazione apre la schermata di prenotazione,
	 * altrimenti apre la schermata di votazione. La schermata corrente viene chiusa.
	 */
	private void handleAzione() {
		PollDAO p = new PollDAOImpl();
		Sessione.getSessione().setVotazione(v);		//!!!Qui impostiamo la votazione attiva in sessione.
		Elettore e = (Elettore) Sessione.getSessione().getUser();
		
		button_azione.getScene().getWindow().hide();
		if(Sessione.getSessione().getSettingsPrenotazione()) {
			//caso in cui il sistema di prenotazione è abilitato
			if(p.checkBooking(Sessione.getSessione().getUser(), v) && !p.checkVoted(e, v)) {
				if(v instanceof Referendum) {
					showReferendum();
				} else if(v instanceof VotazioneStandard) {
					showStandardPoll();				
				}
			}else if(!p.checkVoted(e, v)){
				showBookingWindow();
			}
		}else{
			//caso in cui il sistema di prenotazione è disabilitato
			if(!p.checkVoted(e, v)) {
				if(v instanceof Referendum) {
					showReferendum();
				} else if(v instanceof VotazioneStandard) {
					showStandardPoll();				
				}
			}
		}
	}
	
	
	/**
	 * Apre la schermata di prenotazione della votazione selezionata.
	 */
	private void showBookingWindow() {
    	try {
    		Parent root = FXMLLoader.load(getClass().getResource("bookPoll.fxml"));
    		Stage stage = new Stage();
    		stage.setTitle("Sistema di voto elettronico - Prenotazione");
    		stage.setScene(new Scene(root, 900, 780));
    		stage.setResizable(false);
    		stage.show();
    	}catch (IOException e) {
    		System.out.println(e.getMessage());
    	}
    }
	
	/**
	 * Apre la schermata per effettuare la votazione di una votazione standard.
	 */
	private void showStandardPoll() {
		try {
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
	 * Apre la schermata per effettuare la votazione di una votazione standard.
	 */
	private void showReferendum() {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("Referendum.fxml"));
			Stage stage = new Stage();
			stage.setTitle("Sistema di voto elettronico - Votazione");
			stage.setScene(new Scene(root, 900, 780));
			stage.setResizable(false);
			stage.show();
		}catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}	
}
