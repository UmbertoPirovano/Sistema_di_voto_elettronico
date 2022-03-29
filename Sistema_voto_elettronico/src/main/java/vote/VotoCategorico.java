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
	
	@Override
	public boolean schedaBianca() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void addPreferenza(Candidato c) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removePreferenza(Candidato c) {
		// TODO Auto-generated method stub
		
	}

}
