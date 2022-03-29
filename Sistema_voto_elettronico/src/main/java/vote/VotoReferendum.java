package vote;

public class VotoReferendum implements Voto {
	
	private boolean voto;
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
