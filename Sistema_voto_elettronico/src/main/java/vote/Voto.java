package vote;

public interface Voto {
	
	@Override
	public String toString();
	
	/**
	 * Valuta se e' stata espressa una preferenza su this.
	 * @return true se la preferenza e' stata espressa, false altrimenti.
	 */
	public boolean schedaBianca();
}
