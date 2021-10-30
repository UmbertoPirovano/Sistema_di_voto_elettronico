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
	private final int UserId;
	private final String name;
	private final String surname;
	private final Date birth_date;
	
	/**
	 * Crea un nuovo oggetto utente.
	 * @param id Identificatore univoco di this assegnato dal sistema.
	 * @param name Nome dell'utente.
	 * @param surname Cognome dell'utente.
	 * @param birth_date Data di nascita dell'utente.
	 */
	Utente(int id, String name, String surname, Date birth_date){
		
	}
	
	
	
}
