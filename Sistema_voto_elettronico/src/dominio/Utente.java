package dominio;

import java.util.Date;

/**
 * @author Mattia Garavaglia
 * @author Umberto Pirovano
 * 
 * Un utente generico del sistema di voto elettronico.
 * Questo tipo di utente può visualizzare e selezionare le votazioni, effettuare una votazione, esprimere la volontà
 * di votare a distanza, visualizzare il proprio storico delle votazioni ed effettuare il logout.
 * Le istanze di questa classe sono mutabili.
 *
 */
abstract class Utente {
	private final int userId;
	private final String name;
	private final String surname;
	
	/**
	 * Crea un nuovo oggetto utente.
	 * @param id Identificatore univoco di this assegnato dal sistema.
	 * @param name Nome dell'utente.
	 * @param surname Cognome dell'utente.
	 */
	Utente(int id, String name, String surname){
		userId = id;
		this.name = name;
		this.surname = surname;
	}
	
	/**
	 * Restituisce un iteratore su tutte le votazioni.
	 * @return Un iteratore di votazioni. 
	 */
	abstract Iterator<Votazione> votes();
	
	/**
	 * Restituisce un iteratore su tutte le votazioni nell'anno specificato.
	 * @param year Un anno.
	 * @return Un iteratore di votazioni.
	 */
	abstract Iterator<Votazione> votes(int year);
	
	/**
	 * Restituisce un iteratore su tutte le votazioni nel mese e nell'anno specificato. 
	 * @param month Un mese dell'anno.
	 * @param year Un anno.
	 * @return Un iteratore di votazioni.
	 */
	abstract Iterator<Votazione> votes(int month, int year);
}
