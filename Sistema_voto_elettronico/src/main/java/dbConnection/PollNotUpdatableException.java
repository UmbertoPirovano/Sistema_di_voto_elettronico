package dbConnection;
/**
 * Eccezione sollevata quando si tenta di modificare o eliminare una votazione impropriamente (ad esempio se si vuole cancellare/modificare
 *  un elezione terminata o in corso).
 */
public class PollNotUpdatableException extends RuntimeException {
	public PollNotUpdatableException() {
		super();
	}
	
	public PollNotUpdatableException(String msg) {
		super(msg);
	}
}
