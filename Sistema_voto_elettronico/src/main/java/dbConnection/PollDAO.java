package dbConnection;

import java.util.List;

import candidates.Candidato;
import poll.Votazione;
import users.Elettore;
import users.User;

public interface PollDAO {
	
	/**
	 * Restituisce tutte le votazioni presenti nel database.
	 * @return Una lista di votazioni.
	 */
	List<Votazione> getAll();
	
	/**
	 * Controlla se un utente si è prenotato per una votazione.
	 * @param u
	 * @param v
	 * @return true se l'utente u si è già prenotato per v, false altrimenti.
	 */
	boolean checkBooking(User u, Votazione v);
	
	/**
	 * Prenota l'utente u per la votazione v.
	 * @param u
	 * @param v
	 */
	void book(User u, Votazione v);
	
	/**
	 * Crea l'associazione Elettore - Votazione, se essa non e' gia' stata creata in precedenza.
	 * @param e
	 * @param v
	 * @throws NullPointerException Se e o v sono null.
	 * @throws DuplicateVoteException Se l'associazione e - v e' gia'stata creata in precedenza.
	 */
	void vote(Elettore e, Votazione v);
	
	/**
	 * Verifica se l'Elettore passato come parametro ha gia' espresso la sua preferenze per la Votazione indicata.
	 * @param e
	 * @param v
	 * @return true se la preferenza di e su v e' gia' stata espressa, false altrimenti.
	 * @throws NullPointerException Se e o v sono null.
	 */
	boolean checkVoted(Elettore e, Votazione v);
	
	/**
	 * Restituisce una lista di persone candidate per la votazione v.
	 * @param v
	 * @return
	 */
	List<Candidato> getCandidati(Votazione v);
	
	/**
	 * Restituisce una lista di partiti candidati per la votazione v.
	 * @param v
	 * @return
	 */
	List<Candidato> getPartiti(Votazione v);
	
}
