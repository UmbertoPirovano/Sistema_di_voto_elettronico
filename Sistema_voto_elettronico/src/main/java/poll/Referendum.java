package poll;

public class Referendum extends Votazione {
	
	private boolean quorum;
	
	public Referendum(int id, String nome, String data_inizio, String data_fine, String descrizione) {
		super(id, nome, "referendum", data_inizio, data_fine, descrizione);
		this.quorum = false;
	}
	
	public Referendum(int id, String nome, String data_inizio, String data_fine, String descrizione, boolean quorum) {
		super(id, nome, "referendum", data_inizio, data_fine, descrizione);
		this.quorum = quorum;
	}
	
	/**
	 * Restituisce true se this rappresenta un referendum con quorum, false altrimenti.
	 * @return true se su questo referendum è attivo il quorum.
	 */
	public boolean getQuorum() {
		return quorum;
	}
	
}
