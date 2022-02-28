package gui;

import java.io.IOException;
import java.util.Objects;

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
		
		button_info.setOnAction(event -> showMessageWindow(getDescrizione()));
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
	
	public void showMessageWindow(String s) {
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
	
}