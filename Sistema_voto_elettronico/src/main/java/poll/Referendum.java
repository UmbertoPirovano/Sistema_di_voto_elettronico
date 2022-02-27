package poll;

public class Referendum extends Votazione {
	
	public Referendum(int id, String nome, String data_inizio, String data_fine, String descrizione) {
		super(id, nome, "referendum", data_inizio, data_fine, descrizione);
	}
}
