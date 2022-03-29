package vote;

import candidates.Candidato;

public abstract class VotoStandard implements Voto{
	public abstract void addPreferenza(Candidato c);
	public abstract void removePreferenza(Candidato c);
}
