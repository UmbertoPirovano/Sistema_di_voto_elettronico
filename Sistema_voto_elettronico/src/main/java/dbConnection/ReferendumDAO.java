package dbConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import poll.Referendum;
import poll.Votazione;
import poll.VotazioneStandard;
import vote.Voto;
import vote.VotoReferendum;
import vote.VotoOrdinale;

public class ReferendumDAO implements VoteDAO{
	
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
		if(!(v instanceof Referendum) || !(voto instanceof VotoReferendum))
			throw new IllegalArgumentException();
		con = getConnection();

		try {
			PreparedStatement st = con.prepareStatement("INSERT INTO voti_referendum(votazione, scelta) VALUES(?, ?);");
			st.setInt(1, v.getId());
			if(voto.schedaBianca()) {
				st.setString(2, "NULL");
			}else {
				st.setString(2, Boolean.toString(((VotoReferendum) voto).getVoto()));
			}
			st.executeUpdate();
			return true;
		}catch(SQLException se) {
			se.printStackTrace();
		}
		
		return false;
	}

}
