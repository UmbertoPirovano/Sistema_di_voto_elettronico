package users;

import java.util.Date;
import java.util.Iterator;

/**
 * @author Mattia Garavaglia
 * @author Umberto Pirovano
 * 
 * Un utente di tipo elettore del sistema di voto elettronico.
 * Questo tipo di utente pu� visualizzare e selezionare le votazioni, effettuare una votazione, esprimere la volont�
 * di votare a distanza, visualizzare il proprio storico delle votazioni ed effettuare il logout.
 * Le istanze di questa classe sono mutabili.
 *
 */

class Elettore extends Utente{
	private final Date birth_date;
	
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
	}
	
}