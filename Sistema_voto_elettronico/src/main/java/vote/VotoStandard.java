package vote;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

import candidates.Candidato;

public class VotoStandard implements Voto, Iterable<Candidato> {
	
	private List<Candidato> preferenze;
	
	public VotoStandard() {
		preferenze = new ArrayList<>();
	}
	
	public void addPreferenza(Candidato c) {
		Objects.requireNonNull(c);
		preferenze.add(c);
	}
	
	public void removePreferenza(Candidato c) {
		Objects.requireNonNull(c);
		preferenze.remove(c);
	}
	
	public List<Candidato> getPreferenze(){
		return preferenze;
	}

	@Override
	public Iterator<Candidato> iterator() {
		return preferenze.iterator();
	}
}
