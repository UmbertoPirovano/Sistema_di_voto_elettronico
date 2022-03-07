package poll;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

import candidates.Candidato;
import dbConnection.PollDAOImpl;

public class VotazioneStandard extends Votazione implements Iterable<Candidato> {
	
	private boolean maggioranzaAssoluta;
	private boolean votoAPartiti;
	private List<Candidato> candidati;
	
	private VotazioneStandard(int id, String nome, String tipo, String data_inizio, String data_fine, String descrizione, boolean maggioranzaAssoluta, boolean votoAPartiti) {
		super(id, nome, tipo, data_inizio, data_fine, descrizione);
		this.maggioranzaAssoluta = maggioranzaAssoluta;
		this.votoAPartiti = votoAPartiti;
		//updateCandidati();
	}
	
	public static VotazioneStandard newCategorico(int id, String nome, String data_inizio, String data_fine, String descrizione, boolean maggioranzaAssoluta, boolean votoAPartiti) {
		return new VotazioneStandard(id, nome, "categorico", data_inizio, data_fine, descrizione, maggioranzaAssoluta, votoAPartiti);
	}
	
	public static VotazioneStandard newOrdinale(int id, String nome, String data_inizio, String data_fine, String descrizione, boolean maggioranzaAssoluta, boolean votoAPartiti) {
		return new VotazioneStandard(id, nome, "ordinale", data_inizio, data_fine, descrizione, maggioranzaAssoluta, votoAPartiti);
	}	
	
	/**
	 * Restitusice true se la votazione this adotta come criterio la maggioranza assoluta;
	 * @return
	 */
	public boolean getMaggioranzaAssoluta() {
		return maggioranzaAssoluta;
	}
	
	/**
	 * Restituisce true se la votazione this avviene nei confronti di partiti, false se avviene nei confronti di persone.
	 * @return
	 */
	public boolean getVotoAPartiti() {
		return votoAPartiti;
	}
	
	/**
	 * Restituisce una lista contenente tutti i candidati della votazione this.
	 * Le informazioni vengono recuperate interrogando il db.
	 * @return una lista di oggetti Candidato.
	 */
	public List<Candidato> getCandidati(){
		System.out.println("Recupero i candidati.");
		updateCandidati();
		return candidati; 
    }
	
	/**
	 * Aggiorna il contenuto della lista candidati interrogando il db.
	 */
	private void updateCandidati() {
		System.out.println("Chiedo i candidati al db.");
		candidati = new PollDAOImpl().getCandidati(this);
	}
    
	/**
	 * Restituisce il numero di candidati disponibili per questa votazione.
	 * @return
	 */
    public int countCandidati() {
    	return getCandidati().size();
    }

	@Override
	public Iterator<Candidato> iterator() {
		return candidati.iterator();
	}
}
