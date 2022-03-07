package candidates;

import java.util.Objects;

public class CandidatoPersona implements Candidato {
	
	private String nome;
	private String cognome;
	private CandidatoPartito affiliazione;
	
	
	public CandidatoPersona(String nome, String cognome) {
		this.nome = Objects.requireNonNull(nome);
		this.cognome = Objects.requireNonNull(cognome);
		this.affiliazione = null;
	}
	
	public CandidatoPersona(String nome, String cognome, CandidatoPartito affiliazione) {
		this.nome = Objects.requireNonNull(nome);
		this.cognome = Objects.requireNonNull(cognome);
		this.affiliazione = affiliazione;
	}
	
	@Override
	public String getNome() {
		return nome + " " + cognome;
	}
	
	/**
	 * Se il candidato è affiliato ad un gruppo o partito restituisce il nome di quest'ultimo, "indipendente" altrimenti.
	 * @return String contenente il nome del gruppo/partito.
	 */
	public String getAffiliazione() {
		if (affiliazione == null) return "indipendente";
		return affiliazione.getNome();
	}
	
	/**
	 * Imposta l'affiliazione del candidato this a p.
	 * @param p
	 */
	public void setAffiliazione(CandidatoPartito p) {
		affiliazione = Objects.requireNonNull(p);
	}	

}
