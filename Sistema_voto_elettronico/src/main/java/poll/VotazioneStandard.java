package poll;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Map.Entry;

import candidates.Candidato;
import dbConnection.PollDAOImpl;
import dbConnection.StandardVoteDAO;

public class VotazioneStandard extends Votazione implements Iterable<Candidato> {
	
	private boolean maggioranzaAssoluta;
	private boolean votoAPartiti;
	private TipoVotazione tipo;
	private Map<Candidato, String> candidati;		//la String è l'id del Node con cui viene rappresentata v
	
	
	private int init_index;
	
	private VotazioneStandard(int id, String nome, String data_inizio, String data_fine, String descrizione, TipoVotazione tipo, boolean maggioranzaAssoluta, boolean votoAPartiti) {
		super(id, nome, data_inizio, data_fine, descrizione);
		dbConnection = new StandardVoteDAO();
		Objects.requireNonNull(tipo);
		switch(tipo) {
			case PREFERENZIALE:
				this.tipo = TipoVotazione.PREFERENZIALE;
				break;
			case ORDINALE:
				this.tipo = TipoVotazione.ORDINALE;
				break;
			case CATEGORICO:
				this.tipo = TipoVotazione.CATEGORICO;
		}
		this.maggioranzaAssoluta = maggioranzaAssoluta;
		this.votoAPartiti = votoAPartiti;
		this.candidati = new HashMap<>();
		updateCandidati();
		init_index = 0;
	}
	
	public static VotazioneStandard newCategorico(int id, String nome, String data_inizio, String data_fine, String descrizione, boolean maggioranzaAssoluta, boolean votoAPartiti) {
		return new VotazioneStandard(id, nome, data_inizio, data_fine, descrizione, TipoVotazione.CATEGORICO, maggioranzaAssoluta, votoAPartiti);
	}
	
	public static VotazioneStandard newOrdinale(int id, String nome, String data_inizio, String data_fine, String descrizione, boolean maggioranzaAssoluta, boolean votoAPartiti) {
		return new VotazioneStandard(id, nome, data_inizio, data_fine, descrizione, TipoVotazione.ORDINALE, maggioranzaAssoluta, votoAPartiti);
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
		List<Candidato> tmp = new ArrayList<>(candidati.keySet());
		tmp.sort(null);
		return tmp;
    }
	
	/**
	 * Aggiorna il contenuto della lista candidati interrogando il db.
	 */
	public void updateCandidati() {
		candidati.clear();
		List<Candidato> tmp = new PollDAOImpl().getCandidati(this);
		for(Candidato c : tmp) {
			candidati.put(c, null);
		}
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
		return getCandidati().iterator();
	}
	
	/**
	 * Iteratore custom necessario ad inizializzare gli oggetti Node usati per rappresentare
	 * gli oggetti Candidato nella gui.
	 * @return Il candidato next().
	 */
	public Candidato initNode_next() {
		if(init_index == countCandidati()) {
			init_index = 0;
		}
		return getCandidati().get(init_index++);
	}
	
	/**
	 * Associa ad un Candidato già presente nella mappa un id di tipo String che si riferisce
	 * al Nodo utilizzato per rappresentare c nella gui.
	 * @param c Un candidato.
	 * @param node_id L'id del nodo che rappresenta c nella gui.
	 */
	public void assocNodeCandidate(Candidato c, String node_id) {
		if(candidati.containsKey(c))
			candidati.put(c, node_id);
	}
	
	/*
	public void vota(List<String> scelte) {
		for(int i=0 ; i < scelte.size() ; i++) {
			for(Entry<Candidato, String> e : candidati.entrySet()) {
				if(e.getValue() == scelte.get(i)) System.out.println(e.getKey().getNome());
			}
		}
	}
	*/
	
	/**
	 * Fornito un array di id di Node che rappresentano oggetti Candidato, restituisce una lista di Candidati
	 * associati a quegli id.
	 * @param ids Lista di String cioè id di nodi.
	 * @return Una lista di oggetti Candidato.
	 */
	public List<Candidato> getCandidatiFromNode(List<String> ids){
		Objects.requireNonNull(ids);
		List<Candidato> c = new ArrayList<>();
		for(Entry<Candidato, String> e : candidati.entrySet()) {
			if(ids.contains(e.getValue())) c.add(e.getKey());
		}
		return c;
	}

	@Override
	public void vota() {
		// TODO Auto-generated method stub
		
	}
	
	public String getTipo() {
		return tipo.toString();
	}
}
