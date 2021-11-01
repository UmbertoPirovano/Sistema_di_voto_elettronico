package users;

import java.util.Date;
import java.util.Objects;
import java.util.Iterator;

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

class Elettore extends Utente{
	private final Date birth_date;
	//private TesseraElettorale tE;
	
	/**
	 * Istanzia un nuovo oggetto Elettore.
	 * @param id Identificatore univoco di this assegnato dal sistema.
	 * @param name Nome dell'elettore.
	 * @param surname Cognome dell'elettore.
	 * @param birth_date Data di nascita dell'elettore.
	 */
	public Elettore(int id, String name, String surname, Date birth_date) {
		super(id, name, surname);
		this.birth_date = birth_date;
		//te = new TesseraElettorale()
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
	
	/**
	 * Restituisce un iteratore su tutte le votazioni a cui this ha partecipato.
	 * @return Un iteratore di votazioni. 
	 */
	Iterator<Votazione> votes(){
		//TODO: implementazione
	}
	
	/**
	 * Restituisce un iteratore su tutte le votazioni nell'anno specificato a cui ha preso parte this.
	 * @param year Un anno.
	 * @return Un iteratore di votazioni.
	 */
	Iterator<Votazione> votes(int year){
		//TODO: implementazione
	}
	
	/**
	 * Restituisce un iteratore su tutte le votazioni nel mese e nell'anno specificato a cui ha preso parte this. 
	 * @param month Un mese dell'anno.
	 * @param year Un anno.
	 * @return Un iteratore di votazioni.
	 */
	Iterator<Votazione> votes(int month, int year){
		//TODO: implementazione
	}
}