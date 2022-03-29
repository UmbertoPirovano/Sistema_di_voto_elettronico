package dbConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import poll.Votazione;
import poll.VotazioneStandard;
import vote.Voto;
import vote.VotoOrdinale;

public class StandardVoteDAO implements VoteDAO{
	Connection con = null;

	private static Connection getConnection() {
		Connection c = null;
		try {
			c = DriverManager.getConnection("jdbc:mysql://localhost/sistema_di_voto?user=root&password=");
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return c;
	}

	@Override
	public boolean vota(Votazione v, Voto voto) {
		if(!(v instanceof VotazioneStandard) || !(voto instanceof VotoOrdinale))
			throw new IllegalArgumentException();
		con = getConnection();
		return false;
	}

}
