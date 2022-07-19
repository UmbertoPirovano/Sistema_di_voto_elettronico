package poll;


import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

import dbConnection.StandardVoteDAO;
import dbConnection.VoteDAO;
import system.Sessione;

public abstract class Votazione {
	
	private int id;
	private final String nome;
	private final Timestamp data_inizio;
	private final Timestamp data_fine;
	private final String descrizione;
	VoteDAO dbConnection;
	
	//IL CONTROLLO SULLA DATA DI INIZIO DEVE ESSERE EFFETTUATO IN FASE DI CREAZIONE DELL'OGGETTO VOTAZIONE
	//AGGIUNGERE UN CONTROLLO IN FASE DI CREAZIONE DELLA VOTAZIONE: LA DATA DI INIZIO DEVE ESSERE SUCCESSIVA A QUELLA ATTUALE
	
	public Votazione(String nome, String data_inizio, String data_fine, String descrizione) {
		this.nome = Objects.requireNonNull(nome);
		this.data_inizio = Timestamp.valueOf(data_inizio);
		this.data_fine = Timestamp.valueOf(data_fine);
		this.descrizione = Objects.requireNonNull(descrizione);
		
		assert repOk();
	}
	
	public Votazione(int id, String nome, String data_inizio, String data_fine, String descrizione) {
		this.id = id;
		this.nome = Objects.requireNonNull(nome);
		this.data_inizio = Timestamp.valueOf(data_inizio);
		this.data_fine = Timestamp.valueOf(data_fine);
		this.descrizione = Objects.requireNonNull(descrizione);
		
		assert repOk();
	}
	
	public Votazione(int id, String nome, Timestamp data_inizio, Timestamp data_fine, String descrizione) {
		this.id = id;
		this.nome = Objects.requireNonNull(nome);
		this.data_inizio = Objects.requireNonNull(data_inizio);
		this.data_fine = Objects.requireNonNull(data_fine);
		this.descrizione = Objects.requireNonNull(descrizione);
		
		assert repOk();
	}
	
	/**
	 * Restituisce l'id della votazione this.
	 * @return
	 */
	public int getId() {
		return id;
	}
	
	/**
	 * Restituisce il nome della votazione this.
	 * @return
	 */
	public String getNome() {
		return nome;
	}
	
	/**
	 * Restitusice la data in cui la votazione this inizierà.
	 * @return
	 */
	public Timestamp getDataInizio() {
		return data_inizio;
	}
	
	public String getDataInizioFormatted() {
		DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy, hh:mm:ss");
		String day = formatter.format(getDataInizio());
		return day;
	}
	
	/**
	 * Restituisce la data in cui la votaione this finirà.
	 * @return
	 */
	public Timestamp getDataFine() {
		return data_fine;
	}
	
	public String getDataFineFormatted() {
		DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy, hh:mm:ss");
		String day = formatter.format(getDataFine());
		return day;
	}
	
	/**
	 * Restituisce la descrizione della votazione this.
	 * @return
	 */
	public String getDescrizione() {
		return descrizione;
	}
	
	private boolean repOk() {
		return (id > 0) && data_inizio.before(data_fine);
	}
	
	/**
	 * Permette di esprime la propria preferenza nella votazione corrente.
	 */
	public void vota() {
		dbConnection.vota(this, Sessione.getSessione().getVoto());
	}
	
	public String getStato() {
		Timestamp now = new Timestamp(System.currentTimeMillis());
		if(now.before(data_inizio)) return "In preparazione";
		else if(now.after(data_inizio) && now.before(data_fine)) return "In corso";
		else return "Terminata";
	}	
}
