package vote;
/**
 * Le istanze di questa classe rappresentano voti di un referendum. Gli oggetti di questo tipo sono immutabili.
 *
 */

public class VotoReferendum implements Voto {
	//La scelta effettuata in this.
	private boolean voto;
	//Indica l'aver espresso la scelta o meno.
	private boolean settato;
	
	public VotoReferendum(boolean v) {
		voto = v;
		settato = true;
	}
	
	public VotoReferendum() {
		settato = false;
	}
	
	public boolean getVoto() {
		if(!settato)
			throw new VoidCardException();
		return voto;
	}

	@Override
	public boolean schedaBianca() {
		return settato;
	}
}
