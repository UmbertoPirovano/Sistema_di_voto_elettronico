package dbConnection;

import poll.Votazione;
import vote.Voto;

public interface VoteDAO {
	boolean vota(Votazione v, Voto voto);
}
