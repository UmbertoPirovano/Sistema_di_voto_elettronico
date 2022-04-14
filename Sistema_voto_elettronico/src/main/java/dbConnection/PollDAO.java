package dbConnection;

import java.util.List;

import candidates.Candidato;
import candidates.CandidatoPartito;
import candidates.CandidatoPersona;
import poll.Votazione;
import poll.VotazioneStandard;
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
	
	/**
	 * Restituisce una lista contenente tutte le votazioni terminate.
	 * @return Una lista contenente tutte le votazioni terminate.
	 */
	List<Votazione> votazioniTerminate();
	
	/**
	 * Restituisce una lista contenente tutte le votazioni terminate nell'anno indicato come parametro.
	 * @param anno Un anno.
	 * @return Una lista contenente tutte le votazioni terminate in anno.
	 * @throws IllegalArgumentException se anno e' minore di 0 o maggiore dell'anno corrente.
	 */
	List<Votazione> votazioniTerminate(int anno);
	
	/**
	 * Restituisce una lista contenente tutte le votazioni terminate nel mese dell'anno indicati come parametro.
	 * @param anno Un anno.
	 * @param Un mese.
	 * @return Una lista contenente tutte le votazioni terminate in mese/anno.
	 * @throws IllegalArgumentException se anno e' minore di 0 o maggiore dell'anno corrente, oppure se mese non e' compreso tra 0 e 12.
	 */
	List<Votazione> votazioniTerminate(int anno, int mese);
	
	/**
	 * Aggiunge al database la votazione passata come parametro, se essa non e' gia' presente.
	 * @param v La votazione da aggiungere al database.
	 * @throws NullPointerException Se v e' null.
	 */
	void creaVotazione(Votazione v);
	
	/**
	 * Rimuove dal database la votazione passata come parametro, se la contiene.
	 * @param v La votazione da rimuovere.
	 * @throws NullPointerException Se v e' null.
	 */
	void rimuoviVotazione(Votazione v);
	
	/**
	 * Aggiorna, nel database, la votazione passata come parametro.
	 * @param v La votazione da aggiornare.
	 * @throws NullPointerException Se v e' null.
	 */
	void aggiornaVotazione(Votazione v);
	
	/**
	 * Restituisce una lista delle votazioni in corso.
	 * @return Una lista delle votazioni in corso.
	 */
	List<Votazione> votazioniInCorso();
	
	/**
	 * Aggiunge il partito passato come parametro alla votazione indicata.
	 * @param v Un partito.
	 * @param p Una votazione standard.
	 * @throws NullPointerException Se v o p sono null.
	 */
	void addPartitoToVotazione(VotazioneStandard v, CandidatoPartito p);
	
	/**
	 * Aggiunge il candidato passato come parametro alla votazione indicata. In caso di votazione categorica con preferenze, il candidato
	 * verrà aggiunto solo se il partito a cui appartiene partecipa alla votazione.
	 * @param p Il candidato da aggiungere.
	 * @param v La votazione a cui aggiungere p.
	 * @throws NullPointerException Se p o v sono null.
	 * @throws PartyNotInVoteException Se il partito di p non partecipa a v.
	 */
	void addCandidatoToVotazione(CandidatoPersona p, VotazioneStandard v);
	
	/**
	 * Rimuove dalla votazione indicata il partito passato come parametro.
	 * @param p Il partito da rimuovere.
	 * @param v La votazione da cui rimuove p.
	 * @throws NullPointerException Se p o v sono null.
	 */
	void removePartitoFromVotazione(CandidatoPartito p, VotazioneStandard v);
	
	/**
	 * Rimuove dalla votazione indicata il candidato passato come parametro.
	 * @param p Il candidato da rimuovere.
	 * @param v La votazione da cui rimuove p.
	 * @throws NullPointerException Se p o v sono null.
	 */
	void removeCandidatoFromVotazione(CandidatoPersona p, VotazioneStandard v);
	
	
}
