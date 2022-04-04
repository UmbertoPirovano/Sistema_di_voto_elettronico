package dbConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import poll.TipoVotazione;
import poll.Votazione;
import poll.VotazioneStandard;
import vote.Voto;
import vote.VotoCategorico;
import vote.VotoOrdinale;
import vote.VotoReferendum;
import vote.VotoStandard;
import candidates.CandidatoPartito;
import candidates.CandidatoPersona;
import candidates.Candidato;

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
		if(!(v instanceof VotazioneStandard) || !(voto instanceof VotoOrdinale) || !(voto instanceof VotoStandard))
			throw new IllegalArgumentException();
		
		VotazioneStandard vote = (VotazioneStandard) v;
		
		switch(vote.getTipo().toLowerCase()) {
			case "ordinale":
				if(!(voto instanceof VotoOrdinale))
					throw new IllegalArgumentException();
				break;
			case "categorico":
				if(!(voto instanceof VotoCategorico))
					throw new IllegalArgumentException();
				break;
			case "preferenziale":
				if(!(voto instanceof VotoCategorico))
					throw new IllegalArgumentException();
		}
		con = getConnection();
		
		try {
			PreparedStatement st = null;
			
			if(voto instanceof VotoOrdinale) {
				int i = 0;
				VotoOrdinale ord = (VotoOrdinale) voto;
				if(vote.getVotoAPartiti()) {
					if(ord.schedaBianca()) {
						st = con.prepareStatement("INSERT INTO voti_ordinali_partiti(partito, votazione, posizione) VALUES(?, ?, ?);");
						st.setString(1, "NULL");
						st.setInt(2, vote.getId());
						st.setString(3, "NULL");
					}else
						for(Candidato p: ord) {
							CandidatoPartito cPa = (CandidatoPartito) p;
							st = con.prepareStatement("INSERT INTO voti_ordinali_partiti(partito, votazione, posizione) VALUES(?, ?, ?);");
							st.setString(1, cPa.getNome());
							st.setInt(2, vote.getId());
							st.setInt(3, i);
						}
				}
				else {
					if(ord.schedaBianca()) {
						st = con.prepareStatement("INSERT INTO voti_ordinali_rappresentanti(votazione, rappresentante, posizione) VALUES(?, ?, ?);");
						st.setInt(1, vote.getId());
						st.setString(1, "NULL");
						st.setString(3, "NULL");
					}else
						for(Candidato p: ord) {
							CandidatoPersona cPe = (CandidatoPersona) p;
							st = con.prepareStatement("INSERT INTO voti_ordinali_rappresentanti(votazione, rappresentante, posizione) VALUES(?, ?, ?);");
							st.setInt(1, vote.getId());
							st.setString(2, cPe.getNome());
							st.setInt(3, i);
						}
				}
			}
			st.executeUpdate();
			return true;
		}catch(SQLException se) {
			se.printStackTrace();
		}
		
		return false;
	}

}
