package vote;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

import candidates.Candidato;

public class VotoOrdinale extends VotoStandard implements Iterable<Candidato>{
	
	private List<Candidato> preferenze;
	
	/**
	 * Istaniza un oggetto VotoStandard vuoto.
	 */
	public VotoOrdinale() {
		preferenze = new ArrayList<>();
	}
	
	/**
	 * Istanzia un oggetto VotoStandard a partire dal parametro 'lista' che diventa
	 * la lista di preferenze contenute nel voto.
	 * @param lista - Una lista di oggetti Candidato
	 * @throws NullPointerException se lista è null
	 */
	public VotoOrdinale(List<Candidato> lista) {
		Objects.requireNonNull(lista);
		preferenze = new ArrayList<>(lista);
	}
	
	@Override
	public void addPreferenza(Candidato c) {
		Objects.requireNonNull(c);
		preferenze.add(c);
	}
	
	@Override
	public void removePreferenza(Candidato c) {
		Objects.requireNonNull(c);
		preferenze.remove(c);
	}
	
	@Override
	public boolean schedaBianca() {
		if(preferenze.size() == 0) return true;
		return false;
	}
	
	@Override
	public Iterator<Candidato> iterator() {
		return preferenze.iterator();
	}
	
	@Override
	public String toString() {
		if(schedaBianca()) return "Scheda bianca";
		String s = "";
		for(Candidato c : preferenze) {
			s += c + ", ";
		}
		s = s.substring(0, s.length()-2);
		return s;
	}
}
