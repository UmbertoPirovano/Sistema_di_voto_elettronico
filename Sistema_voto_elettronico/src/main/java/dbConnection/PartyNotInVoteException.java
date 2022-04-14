package dbConnection;

/**
 * Questa eccezione viene sollevata quando si tenta di aggiungere ad una votazione categorica con preferenze un candidato il cui partito
 * non prende parte a quella votazione.
 */
public class PartyNotInVoteException extends RuntimeException {
	public PartyNotInVoteException() {
		super();
	}
	
	public PartyNotInVoteException(String msg) {
		super(msg);
	}
}
