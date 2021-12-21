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
	 * @param id Identificatore univoco di this assegnato dal sistema.
	 * @param name Nome dell'elettore.
	 * @param surname Cognome dell'elettore.
	 */
	public Elettore(String name, String surname, String username) {
		super(name, surname, username);
	}
	
}