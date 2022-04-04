package vote;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import candidates.Candidato;
import candidates.CandidatoPartito;
import candidates.CandidatoPersona;

/**
 * Questa classe rappresenta istanze di Voti categorici, sia con preferenze sia senza. Gli oggetti di questo tipo sono mutabili.
 */

public class VotoCategorico extends VotoStandard implements Iterable<CandidatoPersona>{
	//Il partito di cui si e' espressa la preferenza
	private CandidatoPartito partito;
	//Una collezione che contiene i rappresentanti scelti all'interno del partito selezionato
	private Set<CandidatoPersona> preferenze;
	private boolean votoAPartiti;
	/**
	 * Istanzia this ad una scheda bianca.
	 */
	public VotoCategorico(boolean votoAPartiti) {
		partito = null;
		preferenze = new HashSet<>();
		this.votoAPartiti = votoAPartiti;
	}
	
	/**
	 * Restituisce il partito su cui e' stata espressa una preferenza in this, se e' possbile esprimere preferenze a partiti.
	 * @return Il partito su cui e' stata espressa la preferenza.
	 * @throws IllegalArgumentException Se this non e' un voto categorico con preferenze o se l'unica preferenza esprimibile deve essere 
	 * su una persona.
	 */
	public CandidatoPartito getPartito() {
		if(!votoAPartiti)
			throw new IllegalArgumentException("Called getPartito on a Vote with votoAPartiti = false");
		return partito;
	}
	
	@Override
	public Iterator<CandidatoPersona> iterator(){
		return preferenze.iterator();
	}
	
	/**
	 * Valuta se il Partito passato come parametro e' quello selezionato in this.
	 * @param p Il Partito da confrontare con quello selezionato.
	 * @return true se p e' uguale a partito, false altrimenti.
	 * @throws NullPointerException Se p e' null.
	 * @throws IllegalArgumentException Se this non e' un voto categorico con preferenze o se l'unica preferenza esprimibile deve essere 
	 * su una persona.
	 */
	public boolean confrontaPartito(CandidatoPartito p) {
		if(!votoAPartiti)
			throw new IllegalArgumentException("Called confrontaPartito on a Vote with votoAPartiti = false");
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
		if(votoAPartiti)
			return partito == null;
		return preferenze.size() == 0;
	}
	
	/**
	 * Aggiunge una preferenza a questo voto, settando il partito se esso non e' ancora stato scelto o aggiungendo
	 * una persona appartenente al partito a cui si riferisce this (se il partito e' gia' stato selezionato).
	 */
	@Override
	public void addPreferenza(Candidato c) {
		Objects.requireNonNull(c);
		if(c instanceof CandidatoPartito) {
			if(votoAPartiti)
				if(schedaBianca()) {
					CandidatoPartito p = (CandidatoPartito) c;
					List<CandidatoPersona> persone = new ArrayList<>();
					for(CandidatoPersona per: p) {
						persone.add(per);
					}
					partito = new CandidatoPartito(new String(p.getNome()), persone);
				}
			else
				throw new IllegalArgumentException("Called addPartito on a Vote with votoAPartiti = false");
		}else {
			if(votoAPartiti) {
				if(!schedaBianca()) {
					CandidatoPersona p = (CandidatoPersona) c;
					if(partito.contains(p))
						preferenze.add(p);
				}
			}else
				preferenze.add((CandidatoPersona) c);
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
			if(votoAPartiti) {
				CandidatoPartito other = (CandidatoPartito) c;
				if(other.equals(partito)) {
					partito = null;
					preferenze = new HashSet<>();
				}
			}
		}
	}

}
