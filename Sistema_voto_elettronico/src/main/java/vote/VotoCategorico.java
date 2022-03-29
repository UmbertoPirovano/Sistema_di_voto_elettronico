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

public class VotoCategorico extends VotoStandard{
	private CandidatoPartito partito;
	private Set<CandidatoPersona> preferenze;
	
	public VotoCategorico() {
		partito = null;
		preferenze = new HashSet<>();
	}
	
	public VotoCategorico(final CandidatoPartito partito, final Collection<CandidatoPersona> preferenze) {
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

	@Override
	public void removePreferenza(Candidato c) {
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
