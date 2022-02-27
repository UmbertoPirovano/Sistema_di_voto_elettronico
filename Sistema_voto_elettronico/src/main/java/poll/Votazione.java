package poll;

import java.util.Objects;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.Button;

public abstract class Votazione {
	
	private final SimpleIntegerProperty id;
	private final SimpleStringProperty nome;
	private final SimpleStringProperty tipo;
	private final SimpleStringProperty data_inizio;
	private final SimpleStringProperty data_fine;
	private final SimpleStringProperty descrizione;
	private Button button_info;
	private Button button_azione;
	
	public Votazione(int id, String nome, String tipo, String data_inizio, String data_fine, String descrizione) {
		this.id = new SimpleIntegerProperty(id);
		this.nome = new SimpleStringProperty(nome);
		this.tipo = new SimpleStringProperty(tipo);
		this.data_inizio = new SimpleStringProperty(data_inizio);
		this.data_fine = new SimpleStringProperty(data_fine);
		this.descrizione = new SimpleStringProperty(descrizione);
		this.button_info = new Button("Info");
		this.button_azione = new Button("Prenota o vota");
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
}
