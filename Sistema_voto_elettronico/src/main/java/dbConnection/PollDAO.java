package dbConnection;

import java.util.List;

import poll.Candidato;
import poll.Votazione;
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
