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
public abstract class User {
	private final String name;
	private final String surname;
	private final String username;
	
	/**
	 * Crea un nuovo oggetto utente.
	 * @param id Identificatore univoco di this assegnato dal sistema.
	 * @param name Nome dell'utente.
	 * @param surname Cognome dell'utente.
	 */
	public User(String name, String surname, String username){
		this.name = name;
		this.surname = surname;
		this.username = username;
	}
	
	public String getName() {
		return name;
	}
	
	public String getSurname() {
		return surname;
	}
	
	public String getUsername() {
		return username;
	}
}
