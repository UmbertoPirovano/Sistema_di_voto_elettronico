package vote;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import candidates.Candidato;
import candidates.CandidatoPartito;
import candidates.CandidatoPersona;

/**
 * Questa classe rappresenta istanze di Voti categorici, sia con preferenze sia senza. Gli oggetti di questo tipo sono mutabili.
 */

public class VotoCategorico extends VotoStandard {
	//Il partito di cui si e' espressa la preferenza
	private CandidatoPartito partito;
	//Una collezione che contiene i rappresentanti scelti all'interno del partito selezionato
	private Set<CandidatoPersona> preferenze;
	
	/**
	 * Istanzia this ad una scheda bianca.
	 */
	public VotoCategorico() {
		partito = null;
		preferenze = new HashSet<>();
	}
	
	/**
	 * Istanzia this con i parametri passati come argomento.
	 * @param partito Il partito scelto.
	 * @param preferenze Una collezione dei rappresentati selezionati all'interno del partito.
	 * @throws NullPointerException Se partito o preferenze sono null o se preferenze contiene un valore null.
	 * @throws IllegalArgumentException Se uno dei rappresentanti in preferenze non appartiene a partito.
	 */
	public VotoCategorico(CandidatoPartito partito, Collection<CandidatoPersona> preferenze) {
		Objects.requireNonNull(partito);
		Objects.requireNonNull(preferenze);
		preferenze.forEach(p -> Objects.requireNonNull(p));
		preferenze.forEach(p -> {if(!partito.contains(p)) throw new IllegalArgumentException("La persona "+ p +" non appartiene "
				+ "al partito "+ partito);});
		List<CandidatoPersona> persone = new ArrayList<>();
		for(CandidatoPersona p: partito) {
			persone.add(p);
		}
		this.partito = new CandidatoPartito(new String(partito.getNome()), persone);
		
		this.preferenze = new HashSet<>(preferenze);
	}
	
	/**
	 * Valuta se il Partito passato come parametro e' quello selezionato in this.
	 * @param p Il Partito da confrontare con quello selezionato.
	 * @return true se p e' uguale a partito, false altrimenti.
	 * @throws NullPointerException Se p e' null.
	 */
	public boolean confrontaPartito(CandidatoPartito p) {
		Objects.requireNonNull(p);
		return partito.equals(p);
	}
	
	/**
	 * Valuta se tra le preferenze di this e' presente quella passata come parametro.
	 * @param p Il rappresentante da cercare tra le preferenze di this.
	 * @return true se p si trova tra le preferenze, false altrimenti.
	 * @throws NullPointerException Se p e' null.
	 */
	public boolean containsPreferenza(CandidatoPersona p) {
		Objects.requireNonNull(p);
		return preferenze.contains(p);
	}
	
	/**
	 * Restituisce true se il partito non e' stato selezionato, false altrimenti.
	 */
	@Override
	public boolean schedaBianca() {
		return partito == null;
	}
	
	/**
	 * Aggiunge una preferenza a questo voto, settando il partito se esso non e' ancora stato scelto o aggiungendo
	 * una persona appartenente al partito a cui si riferisce this (se il partito e' gia' stato selezionato).
	 */
	@Override
	public void addPreferenza(Candidato c) {
		Objects.requireNonNull(c);
		if(c instanceof CandidatoPartito) {
			if(schedaBianca()) {
				CandidatoPartito p = (CandidatoPartito) c;
				List<CandidatoPersona> persone = new ArrayList<>();
				for(CandidatoPersona per: p) {
					persone.add(per);
				}
				partito = new CandidatoPartito(new String(p.getNome()), persone);
			}
		}else {
			if(!schedaBianca()) {
				CandidatoPersona p = (CandidatoPersona) c;
				if(partito.contains(p))
					preferenze.add(p);
			}
		}
		
	}
	
	
	/**
	 * Rimuove il Candidato passato come parametro, se presente tra quelli selezionati. Se il Candidato e' un rappresentante
	 * presente in preferenze, esso viene semplicemente rimosso da preferenze. Se si tratta del partito selezionato, il partito viene
	 * rimosso, cosi' come i rappresentanti in preferenze.
	 */
	@Override
	public void removePreferenza(Candidato c) {
		Objects.requireNonNull(c);
		if(c instanceof CandidatoPersona)
			preferenze.remove(c);
		else {
			CandidatoPartito other = (CandidatoPartito) c;
			if(other.equals(partito)) {
				partito = null;
				preferenze = new HashSet<>();
			}
		}
	}

}
