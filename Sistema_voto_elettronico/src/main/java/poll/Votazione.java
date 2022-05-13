package poll;


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
	private final Date data_inizio;
	private final Date data_fine;
	private final String descrizione;
	VoteDAO dbConnection;
	
	//IL CONTROLLO SULLA DATA DI INIZIO DEVE ESSERE EFFETTUATO IN FASE DI CREAZIONE DELL'OGGETTO VOTAZIONE
	//AGGIUNGERE UN CONTROLLO IN FASE DI CREAZIONE DELLA VOTAZIONE: LA DATA DI INIZIO DEVE ESSERE SUCCESSIVA A QUELLA ATTUALE
	
	public Votazione(String nome, String data_inizio, String data_fine, String descrizione) {
		this.nome = Objects.requireNonNull(nome);
		this.data_inizio = stringToDate(data_inizio);
		this.data_fine = stringToDate(data_fine);
		this.descrizione = Objects.requireNonNull(descrizione);
		
		assert repOk();
	}
	
	public Votazione(int id, String nome, String data_inizio, String data_fine, String descrizione) {
		this.id = id;
		this.nome = Objects.requireNonNull(nome);
		this.data_inizio = stringToDate(data_inizio);
		this.data_fine = stringToDate(data_fine);
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
	@SuppressWarnings("deprecation")
	public Date getDataInizio() {
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
	@SuppressWarnings("deprecation")
	public Date getDataFine() {
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
	
	/**
	 * Restitusice la conversione in tipo Date di una Stringa contenente una data nel formato "yy-MM-dd HH:mm:ss".
	 * @param date la stringa contenente la data.
	 * @return l'oggetto Date rappresentante la data.
	 */
	private Date stringToDate(String date) {
		try {
			Objects.requireNonNull(date);
			DateFormat df = new SimpleDateFormat("yy-MM-dd HH:mm:ss");
			return df.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public String getStato() {
		Date now = new Date(System.currentTimeMillis());
		if(now.before(data_inizio)) return "In preparazione";
		else if(now.after(data_inizio) && now.before(data_fine)) return "In corso";
		else return "Terminata";
	}	
}
