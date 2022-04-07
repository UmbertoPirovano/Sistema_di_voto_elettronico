package dbConnection;
/**
 * Questa eccezione viene sollevata quando un elettore tenta di votare ad una votazione per cui ha gia' espresso la preferenza.
 */
public class DuplicateVoteException extends RuntimeException {
	public DuplicateVoteException() {
		super();
	}
}
