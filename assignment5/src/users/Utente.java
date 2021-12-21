package users;

import java.util.Date;
import java.util.Iterator;

/**
 * @author Mattia Garavaglia
 * @author Umberto Pirovano
 * 
 * Un utente generico del sistema di voto elettronico.
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
	
	int getUserId() {
		return userId;
	}
}
