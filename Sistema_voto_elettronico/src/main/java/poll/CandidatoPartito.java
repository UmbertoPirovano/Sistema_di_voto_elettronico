package poll;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CandidatoPartito implements Candidato {
	
	private String nome;
	private List<CandidatoPersona> candidati;
	
	public CandidatoPartito(String nome) {
		this.nome = Objects.requireNonNull(nome);
		candidati = new ArrayList<>();
	}
	
	public CandidatoPartito(String nome, List<CandidatoPersona> candidati) {
		this.nome = Objects.requireNonNull(nome);
		this.candidati = new ArrayList<>(candidati);
	}
	
	@Override
	public String getNome() {
		return nome;
	}
	
	/**
	 * Restituisce la lista di candidati.
	 * @return
	 */
	public List<CandidatoPersona> getCandidati(){
		return candidati;
	}
	
	/**
	 * Sostituisce la lista di candidati con quella fornita in argomento.
	 * @param candidati
	 */
	public void setCandidati(List<CandidatoPersona> candidati) {
		this.candidati = Objects.requireNonNull(candidati);
	}
	
	/**
	 * Aggiunge il candidato p alla lista.
	 * @param p
	 */
	public void addCandidato(CandidatoPersona p) {
		Objects.requireNonNull(p);
		p.setAffiliazione(this);
		candidati.add(p);
	}
	
	/**
	 * Aggiunge alla lista un nuovo candidato di cui viene fornito nome e cognome.
	 * @param nome
	 * @param cognome
	 */
	public void addCandidato(String nome, String cognome) {
		Objects.requireNonNull(nome);
		Objects.requireNonNull(cognome);
		candidati.add(new CandidatoPersona(nome, cognome, this));
	}
	
	/**
	 * Rimuove il candidato p dalla lista.
	 * @param p
	 */
	public void removeCandidato(CandidatoPersona p) {
		Objects.requireNonNull(p);
		candidati.remove(p);
	}
	
	/**
	 * Rimuove dalla lista il candidato che corrisponde al nome ed al cognome forniti come argomento.
	 * @param nome
	 * @param cognome
	 */
	public void removeCandidato(String nome, String cognome) {
		Objects.requireNonNull(nome);
		Objects.requireNonNull(cognome);
		for(CandidatoPersona p : candidati) {
			if(p.getNome().equals(nome + " " + cognome)) candidati.remove(p);
		}		
	}

}
