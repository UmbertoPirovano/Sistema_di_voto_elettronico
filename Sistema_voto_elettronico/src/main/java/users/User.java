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
	private int id;
	private final String name;
	private final String surname;
	private final String username;
	
	/**
	 * Costruttore ereditato da tutti le classi che estendono User.
	 * @param name Nome dell'utente.
	 * @param surname Cognome dell'utente.
	 * @param username Lo username dell'utente.
	 */
	public User(int id, String name, String surname, String username){
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.username = username;
	}
	
	/**
	 * Restituisce l'id di questo User.
	 * @return L'id di questo User.
	 */
	public int getId() {
		return id;
	}
	
	/**
	 * Restituisce il nome dello user.
	 * @return Il nome dello user.
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Restituisce il cognome dello user.
	 * @return Il cognome dello user.
	 */
	public String getSurname() {
		return surname;
	}
	
	/**
	 * Restituisce lo username dello user.
	 * @return Lo username dello user.
	 */
	public String getUsername() {
		return username;
	}
}
