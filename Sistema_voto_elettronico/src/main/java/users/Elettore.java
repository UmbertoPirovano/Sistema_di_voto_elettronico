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

public class Elettore extends User{
	/**
	 * Istanzia un nuovo oggetto Elettore.
	 * @param name Nome dell'elettore.
	 * @param surname Cognome dell'elettore.
	 * @param username Lo username dell'elettore.
	 */
	public Elettore(int id, String name, String surname, String username) {
		super(id, name, surname, username);
	}
	
}