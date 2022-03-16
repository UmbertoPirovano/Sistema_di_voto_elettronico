package poll;


import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

public abstract class Votazione {
	
	private final int id;
	private final String nome;
	private final TipoVotazione tipo;
	private final Date data_inizio;
	private final Date data_fine;
	private final String descrizione;
	
	public Votazione(int id, String nome, String tipo, String data_inizio, String data_fine, String descrizione) {
		this.id = id;
		this.nome = Objects.requireNonNull(nome);
		/*ATTENZIONE ---------------------- LEGGERE--------------------------------------------------------------------
		 * Non avrebbe più senso mettere il controllo del tipo di vooto in VotazioneStandard?
		 * E poi perchè hai messo referendum tra i tipi di voto? Non lo vai già a distinguere considerato che un oggetto
		 * Referendum ha campi di classe diversi rispetto a VotazioneStandard? Io tipo l'avevo pensato per distinguere
		 * voto Ordinale, Categorico e con Preferenze.
		 * P.S. Non sto dicendo che e' sbagliato, volevo solo capire la logica.
		 */
		switch(Objects.requireNonNull(tipo).toLowerCase()) {
			case "referendum":
				this.tipo = TipoVotazione.REFERENDUM;
				break;
			case "ordinale":
				this.tipo = TipoVotazione.ORDINALE;
				break;
			case "categorico":
				this.tipo = TipoVotazione.CATEGORICO;
				break;
			default:
				throw new IllegalArgumentException("Tipo di votazione non supportato");
		}

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
	 * Restitusice il tipo della votazione this.
	 * @return
	 */
	public String getTipo() {
		return tipo.toString();
	}
	
	/**
	 * Restitusice la data in cui la votazione this inizierà.
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public String getData_inizio() {
		return data_inizio.toGMTString();
	}
	
	/**
	 * Restituisce la data in cui la votaione this finirà.
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public String getData_fine() {
		return data_fine.toGMTString();
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
	
	
}
