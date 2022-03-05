package dbConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import poll.Candidato;
import poll.CandidatoPersona;
import poll.CandidatoPartito;
import poll.Referendum;
import poll.Votazione;
import poll.VotazioneOrdinale;
import system.Sessione;
import users.Amministratore;
import users.Elettore;
import users.User;

public class PollDAOImpl implements PollDAO {	
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
	public List<Votazione> getAll() {
		List<Votazione> votazioni = new ArrayList<>();
		con = getConnection();
		try {
			PreparedStatement st = null;
			st = con.prepareStatement("SELECT * FROM votazioni;");
			ResultSet res = st.executeQuery();
			while(res.next()) {
				switch(res.getString("tipo")) {
				case "referendum":
					votazioni.add(new Referendum(res.getInt("id"), res.getString("nome"), res.getString("data_inizio"),
									res.getString("data_fine"), res.getString("descrizione")));
					break;
				case "votazione ordinale":
					votazioni.add(new VotazioneOrdinale(res.getInt("id"), res.getString("nome"), res.getString("data_inizio"),
							res.getString("data_fine"), res.getString("descrizione")));
					break;
				}
			}
		}catch (SQLException se) {
			se.printStackTrace();
		}
		return votazioni;
	}

	@Override
	public boolean checkBooking(User u, Votazione v) {
		Objects.requireNonNull(u);
		Objects.requireNonNull(v);
		con = getConnection();
		try {
			PreparedStatement st = con.prepareStatement("SELECT * FROM prenotazioni WHERE votazione= ? AND elettore= ?;");
			st.setInt(1, v.getId());
			st.setString(2, u.getUsername());
			ResultSet res = st.executeQuery();
			if(res.next()) return true;
		}catch(SQLException se) {
			se.printStackTrace();
		}
		return false;
	}

	@Override
	public void book(User u, Votazione v) {
		con = getConnection();
		try {
			PreparedStatement st = con.prepareStatement("INSERT INTO prenotazioni(votazione, elettore) VALUES( ?, ?);");
			st.setInt(1, v.getId());
			st.setString(2, u.getUsername());
			st.executeUpdate();
		}catch(SQLException se) {
			se.printStackTrace();
		}		
	}

	@Override
	public List<Candidato> getCandidati(Votazione v) {
		con = getConnection();
		List<Candidato> candidati = new ArrayList<>();
		try {
			PreparedStatement st = null;
			st = con.prepareStatement("SELECT R.nome, R.cognome, R.partito FROM candidati_rappresentanti AS C JOIN votazioni AS V ON V.id=C.votazione JOIN rappresentanti AS R ON R.id=C.rappresentante WHERE V.id= ?;");
			st.setInt(1, Sessione.getSessione().getVotazione().getId());
			ResultSet res = st.executeQuery();
			while(res.next()) {				
				Candidato c = new CandidatoPersona(res.getString("nome"), res.getString("cognome"), new CandidatoPartito(res.getString("partito")));
				candidati.add(c);
			}
		}catch (SQLException se) {
			se.printStackTrace();
		}
		return candidati;
	}

	@Override
	public List<Candidato> getPartiti(Votazione v) {
		con = getConnection();
		List<Candidato> candidati = new ArrayList<>();
		try {
			PreparedStatement st = null;
			st = con.prepareStatement("SELECT C.partito FROM votazioni AS V JOIN candidati_partiti AS C ON C.votazione=V.id WHERE V.id= ?;");
			st.setInt(1, Sessione.getSessione().getVotazione().getId());
			ResultSet res = st.executeQuery();
			while(res.next()) {				
				Candidato c = new CandidatoPartito(res.getString("partito"));
				candidati.add(c);
			}
		}catch (SQLException se) {
			se.printStackTrace();
		}
		return candidati;
	}

}
