/**
 * Classe che implementa la sessione per l'applicazione e dunque tiene traccia dell'utente attualmente loggato.
 * La classe rispetta il design pattern singleton.
 */

package system;
import java.util.Objects;

import users.User;

public class Sessione {
	
	private static Sessione sessione = null;
	public User utente;
	
	private Sessione() {
		
	}
	
	/**
	 * Se una sessione è già istanziata restituisce this, altrimenti ne istanzia una nuova e la restituisce.
	 * @return this
	 */
	public static Sessione getSessione() {
		if(sessione == null) sessione = new Sessione();
		return sessione;
	}
	
	/**
	 * Distrugge l'istanza della sessione.
	 */
	public void destroy() {
		utente = null;
		sessione = null;		
	}
	
	/**
	 * Imposta l'utente u come utente attualmente attivo.
	 * @param u Un utente del sistema.
	 */
	public void loginUser(User u) {
		utente = Objects.requireNonNull(u);
	}
	
	/**
	 * Imposta l'utente attualmente attivo a null.
	 */
	public void logoutUser() {
		utente = null;
	}	
	
}
