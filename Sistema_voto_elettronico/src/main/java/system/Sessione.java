/**
 * Classe che implementa la sessione per l'applicazione e dunque tiene traccia dell'utente attualmente loggato.
 * La classe rispetta il design pattern singleton.
 */

package system;
import java.util.Objects;

import dbConnection.UserDAO;
import dbConnection.UserDAOImpl;
import dbConnection.UserLoginDAO;
import dbConnection.UserLoginDAOImpl;
import gui.GuiApplication;
import poll.Votazione;
import users.User;
import vote.Voto;

public class Sessione {
	
	/**
	 * Attributi
	 * L'attributo sessione contiene l'oggetto this.
	 * utente è l'utente attualmente abilitato e che sta interagendo con il sistema.
	 * votazione è la votazione attualmente selezionata sulla quale si stanno effettuando operazioni.
	 * voto è l'oggetto voto che si è creato in seguito ad una votazione effettuata dall'utente
	 * prenotazione è un attributo booleano che è true se il sistema di prenotazione delle votazioni è attivo. Di default è false.
	 */
	private static Sessione sessione = null;
	private User utente;
	private Votazione votazione;
	private Voto voto;
	private static boolean prenotazione = false;
	
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
	 * Restituisce l'utente attivo in questa sessione.
	 * @return
	 */
	public User getUser() {
		return utente;
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
	
	public void setVoto(Voto v) {
		this.voto = v;
	}
	
	public Voto getVoto() {
		return voto;
	}
	
	/**
	 * Restituisce true se il sistema di prenotazione delle votazioni è abilitato, false altrimenti.
	 * @return
	 */
	public boolean getSettingsPrenotazione() {
		return prenotazione;
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
