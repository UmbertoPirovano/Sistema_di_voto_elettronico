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
import poll.Votazione;
import system.Sessione;

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
		this.tipo = new SimpleStringProperty(v.getTipo());
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
		Sessione.getSessione().setVotazione(v);
		if(p.checkBooking(Sessione.getSessione().utente, Sessione.getSessione().getVotazione())) {
			try {
				button_azione.getScene().getWindow().hide();
	    		Parent root = FXMLLoader.load(getClass().getResource("votazioneOrdinale.fxml"));
	            Stage stage = new Stage();
	        	stage.setTitle("Sistema di voto elettronico - Votazione");
	        	stage.setScene(new Scene(root, 900, 780));
	        	stage.setResizable(false);
	        	stage.show();
			}catch (IOException e) {
				System.out.println(e.getMessage());
			}
		}else{
			try {
				button_azione.getScene().getWindow().hide();
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
	}
	
}
