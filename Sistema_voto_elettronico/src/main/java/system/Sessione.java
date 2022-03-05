/**
 * Classe che implementa la sessione per l'applicazione e dunque tiene traccia dell'utente attualmente loggato.
 * La classe rispetta il design pattern singleton.
 */

package system;
import java.util.List;
import java.util.Objects;

import dbConnection.PollDAOImpl;
import dbConnection.UserDAO;
import dbConnection.UserDAOImpl;
import dbConnection.UserLoginDAO;
import dbConnection.UserLoginDAOImpl;
import poll.Candidato;
import poll.Votazione;
import users.User;

public class Sessione {
	
	private static Sessione sessione = null;
	public User utente;
	public Votazione votazione;
	
	private Sessione() {
		super();
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
		votazione = null;
		sessione = null;		
	}
	
	/**
	 * Imposta l'utente u come utente attualmente attivo.
	 * @param u Un utente del sistema.
	 */
	private void setUser(User u) {
		utente = Objects.requireNonNull(u);
	}
	
	/**
	 * Imposta la votazione v come votazione attualmente selezionata.
	 * @param v Una votazione.
	 */
	public void setVotazione(Votazione v) {
		votazione = Objects.requireNonNull(v);
	}
	
	/**
	 * Restituisce la votazione attualmente selezionata.
	 * @return votazione
	 */
	public Votazione getVotazione() {
		return votazione;
	}
	
	/**
     * Verifica che nel DB sia presente un utente con le credenziali passate come parametro.
     * @param username Lo username dell'utente da cercare.
     * @param encryptedPwd La password criptata dell'utente da cercare.
     * @param mode Il tipo di utente da cercare (elettore / amministratore).
     * @return true se e' stato trovato un untente corrispondente alle credenziali inserite, false altrimenti.
     */    
    public boolean executeLogin(String username, String encryptedPwd, String mode) {
    	UserLoginDAO loginDao = new UserLoginDAOImpl();
    	UserDAO userDao = new UserDAOImpl();
    	if (loginDao.authenticate(username, encryptedPwd, mode)) {
    		setUser(userDao.findByUsername(username));
    		return true;
    	}
    	return false;
    }
	
    /**
     * Imposta l'utente attualmente attivo a null.
     */
    public void logoutUser() {
    	utente = null;
    } 
    
    
}
