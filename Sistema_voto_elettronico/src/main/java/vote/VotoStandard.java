package vote;

import candidates.Candidato;

public abstract class VotoStandard implements Voto{
	/**
	 * Aggiunge il Candidato a this.
	 * @param c Il Candidato da aggiungere.
	 * @throws NullPointerException Se c e' null.
	 */
	public abstract void addPreferenza(Candidato c);
	
	/**
	 * Rimuove il Candidato da this, se presente.
	 * @param c Il Candidato da rimuovere.
	 * @throws NullPointerException Se c e' null.
	 */
	public abstract void removePreferenza(Candidato c);
}
