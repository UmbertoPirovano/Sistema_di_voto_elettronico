package vote;

public class VotoReferendum implements Voto {
	
	private final boolean voto;
	
	public VotoReferendum(boolean v) {
		voto = v;
	}
	
	public boolean getVoto() {
		return voto;
	}
}
