package dominio;

import java.util.Date;
import java.util.Objects;

/**
 * @author Mattia Garavaglia
 * @author Umberto Pirovano
 * 
 * Un utente di tipo elettore del sistema di voto elettronico.
 * Questo tipo di utente può visualizzare e selezionare le votazioni, effettuare una votazione, esprimere la volontà
 * di votare a distanza, visualizzare il proprio storico delle votazioni ed effettuare il logout.
 * Le istanze di questa classe sono mutabili.
 *
 */

class Elettore {
	private final int UserId;
	private final String name;
	private final String surname;
	private final Date birth_date;
	//private SchedaElettorale
	
	/**
	 * Istanzia un nuovo oggetto Elettore.
	 * @param id Identificatore univoco di this assegnato dal sistema.
	 * @param name Nome dell'elettore.
	 * @param surname Cognome dell'elettore.
	 * @param birth_date Data di nascita dell'elettore.
	 */
	public Elettore(int id, String name, String surname, Date birth_date) {
		this.UserId = id;
		this.name = name;
		this.surname = surname;
		this.birth_date = birth_date;
		//new TesseraElettorale()....
	}
	
	/**
	 * Controlla che this non abbia già espresso la sua preferenza per la votazione v.
	 * @param v Una votazione.
	 * @return true se this non ha ancora espresso una preferenza, false altrimenti.
	 */
	public boolean checkAlreadyVoted(Votazione v) {
		//TODO: implementazione
		return false;
	}
	
	/**
	 * Aggiorna la tessera elettorale aggiungendo la votazione v.
	 * @param v Una votazione del sistema.
	 */
	private void updateTesseraElettorale(Votazione v) {
		//TODO: implementazione
	}
}