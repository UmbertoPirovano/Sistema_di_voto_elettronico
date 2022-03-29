package dbConnection;

import poll.Referendum;
import poll.Votazione;
import poll.VotazioneStandard;
import vote.Voto;
import vote.VotoReferendum;
import vote.VotoOrdinale;

public class ReferendumDAO implements VoteDAO{

	@Override
	public boolean vota(Votazione v, Voto voto) {
		if(!(v instanceof Referendum) || !(voto instanceof VotoReferendum))
			throw new IllegalArgumentException();
		return false;
	}

}
