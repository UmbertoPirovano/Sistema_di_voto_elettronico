package dbConnection;

import java.util.List;
import poll.Votazione;

public interface PollDAO {
	
	List<Votazione> getAll();
	
}
