package poll;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class VotazioneOrdinale extends Votazione {
	
	private List<Candidato> candidati;
	
	public VotazioneOrdinale(int id, String nome, String data_inizio, String data_fine, String descrizione) {
		super(id, nome, "Votazione ordinale", data_inizio, data_fine, descrizione);
	}
}
