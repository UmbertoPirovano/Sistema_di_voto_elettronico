package poll;

public class VotazioneOrdinale extends Votazione {
	
	public VotazioneOrdinale(int id, String nome, String data_inizio, String data_fine, String descrizione) {
		super(id, nome, "Votazione ordinale", data_inizio, data_fine, descrizione);
	}
}
