package poll;

import java.util.Date;

import dbConnection.ReferendumDAO;

public class Referendum extends Votazione {
	
	private boolean quorum;
	
	public Referendum(int id, String nome, String data_inizio, String data_fine, String descrizione) {
		super(id, nome, data_inizio, data_fine, descrizione);
		dbConnection = new ReferendumDAO();
		this.quorum = false;
	}
	
	public Referendum(int id, String nome, String data_inizio, String data_fine, String descrizione, boolean quorum) {
		super(id, nome, data_inizio, data_fine, descrizione);
		dbConnection = new ReferendumDAO();
		this.quorum = quorum;
	}
	
	public Referendum(int id, String nome, Date data_inizio, Date data_fine, String descrizione, boolean quorum) {
		super(id, nome, data_inizio, data_fine, descrizione);
		dbConnection = new ReferendumDAO();
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
