package dbConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import candidates.Candidato;
import candidates.CandidatoPartito;
import candidates.CandidatoPersona;
import poll.Referendum;
import poll.TipoVotazione;
import poll.Votazione;
import poll.VotazioneStandard;
import system.Sessione;
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
					votazioni.add(VotazioneStandard.newOrdinale(res.getInt("id"), res.getString("nome"), res.getString("data_inizio"), res.getString("data_fine"), res.getString("descrizione"), false, false));
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
			st = con.prepareStatement("SELECT R.id, R.nome, R.cognome, R.partito FROM candidati_rappresentanti AS C JOIN votazioni AS V ON V.id=C.votazione JOIN rappresentanti AS R ON R.id=C.rappresentante WHERE V.id= ?;");
			st.setInt(1, v.getId());
			ResultSet res = st.executeQuery();
			while(res.next()) {		
				Candidato c = new CandidatoPersona(Integer.parseInt(res.getString("id")), res.getString("nome"), res.getString("cognome"), new CandidatoPartito(res.getString("partito")));
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

	@Override
	public void vote(Elettore e, Votazione v) {
		con = getConnection();
		if(checkVoted(e,v))
			throw new DuplicateVoteException();
		try {
			PreparedStatement st = con.prepareStatement("INSERT INTO ha_votato(votazione, persona) VALUES(?, ?);");
			st.setInt(1, v.getId());
			st.setInt(2, e.getId());
			st.executeUpdate();
		}catch(SQLException se) {
			se.printStackTrace();
		}
	}

	@Override
	public boolean checkVoted(Elettore e, Votazione v) {
		con = getConnection();
		
		try {
			PreparedStatement st = con.prepareStatement("SELECT count(*) FROM ha_votato hv WHERE ? = hv.votazione AND ? = hv.persona;");
			st.setInt(1, v.getId());
			st.setInt(2, e.getId());
			ResultSet rS = st.executeQuery();
			if(rS.getInt("count(*)") == 1)
				return true;
		}catch(SQLException se) {
			se.printStackTrace();
		}
		
		return false;
	}

	@Override
	public List<Votazione> votazioniTerminate() {
		con = getConnection();
		Timestamp ts = new Timestamp(System.currentTimeMillis());
		List<Votazione> votazioni = new ArrayList<>();
		try {
			PreparedStatement st = con.prepareStatement("SELECT * FROM votazioni v WHERE v.data_fine < ?;");
			st.setTimestamp(1, ts);
			ResultSet rS = st.executeQuery();
			while(rS.next()) {
				int id = rS.getInt(1);
				String nome = rS.getString(2);
				String inizio = rS.getTimestamp(3).toString();
				String fine = rS.getTimestamp(4).toString();
				String tipo = rS.getString(5);
				String descrizione = rS.getString(6);
				if(tipo.toLowerCase().trim().equals("ordinale") || tipo.toLowerCase().trim().equals("categorico") || tipo.toLowerCase().trim().equals("preferenziale")) {
					boolean maggioranzaAssoluta = rS.getBoolean(7);
					boolean votoAPartiti = rS.getBoolean(8);
					
					switch(tipo.toLowerCase().trim()) {
						case "ordinale":
							votazioni.add(new VotazioneStandard(id, nome, inizio, fine, descrizione, TipoVotazione.ORDINALE, maggioranzaAssoluta, votoAPartiti));
							break;
						case "categorico":
							votazioni.add(new VotazioneStandard(id, nome, inizio, fine, descrizione, TipoVotazione.CATEGORICO, maggioranzaAssoluta, votoAPartiti));
							break;
						case "preferenziale":
							votazioni.add(new VotazioneStandard(id, nome, inizio, fine, descrizione, TipoVotazione.PREFERENZIALE, maggioranzaAssoluta, votoAPartiti));
					}
				}else
					if(tipo.toLowerCase().trim().equals("referendum")) {
						boolean quorum = rS.getBoolean(9); 
						votazioni.add(new Referendum(id, nome, inizio, fine, descrizione, quorum));
					}else
						throw new IllegalArgumentException("Type not found: "+tipo.toLowerCase().trim());
				
			}
		}catch(SQLException se) {
			se.printStackTrace();
		}
		return votazioni;
	}

	@Override
	public List<Votazione> votazioniTerminate(int anno) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Votazione> votazioniTerminate(int anno, int mese) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void creaVotazione(Votazione v) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void rimuoviVotazione(Votazione v) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void aggiornaVotazione(Votazione v) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Votazione> votazioniInCorso() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addPartitoToVotazione(VotazioneStandard v, CandidatoPartito p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addCandidatoToVotazione(CandidatoPersona p, VotazioneStandard v) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removePartitoFromVotazione(CandidatoPartito p, VotazioneStandard v) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeCandidatoFromVotazione(CandidatoPersona p, VotazioneStandard v) {
		// TODO Auto-generated method stub
		
	}

}
