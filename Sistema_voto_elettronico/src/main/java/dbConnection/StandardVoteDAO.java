package dbConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

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
		Objects.requireNonNull(v);
		Objects.requireNonNull(voto);
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
				break;
			default:
				throw new IllegalArgumentException("Unknown type: "+vote.getTipo().toLowerCase());
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
						st.setString(2, "NULL");
						st.setString(3, "NULL");
					}else
						for(Candidato p: ord) {
							CandidatoPersona cPe = (CandidatoPersona) p;
							st = con.prepareStatement("INSERT INTO voti_ordinali_rappresentanti(votazione, rappresentante, posizione) VALUES(?, ?, ?);");
							st.setInt(1, vote.getId());
							st.setInt(2, cPe.getId());
							st.setInt(3, i);
						}
				}
			}else
				if(voto instanceof VotoCategorico) {
					VotoCategorico cat = (VotoCategorico) voto;
					if(vote.getVotoAPartiti()) {
						if(cat.schedaBianca()) {
							st = con.prepareStatement("INSERT INTO voti_categorici_partiti(votazione, partito) VALUES(?, ?);");
							st.setInt(1, vote.getId());
							st.setString(2, "NULL");
						}else {
							CandidatoPartito cPa = cat.getPartito();
							st = con.prepareStatement("INSERT INTO voti_categorici_partiti(votazione, partito) VALUES(?, ?);",Statement.RETURN_GENERATED_KEYS);
							st.setInt(1, vote.getId());
							st.setString(2, cPa.getNome());
							st.executeUpdate();
							int id = st.getGeneratedKeys().getInt(0);
							for(CandidatoPersona cPe: cat) {
								st = con.prepareStatement("INSERT INTO preferenze_voto_categorico(voto_categorico, votazione, rappresentante) VALUES(?, ?, ?);");
								st.setInt(1, id);
								st.setInt(2, vote.getId());
								st.setInt(3, cPe.getId());
							}
						}
					}else {
						if(cat.schedaBianca()) {
							st = con.prepareStatement("INSERT INTO voti_categorici_rappresentanti(votazione, rappresentante) VALUES(?, ?);");
							st.setInt(1, vote.getId());
							st.setString(2, "NULL");
						}else {
							st = con.prepareStatement("INSERT INTO voti_categorici_rappresentanti(votazione, rappresentante) VALUES(?, ?);");
							st.setInt(1, vote.getId());
							st.setInt(2, cat.iterator().next().getId());
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

	
	/**
	 * Restituisce un iteratore dei candidati della votazione passata come parametro.
	 * @param v La votazione standard di cui si vogliono conoscere i candidati.
	 * @return Un iteratore di candidati. Nel caso si tratti di una votazione categorica con preferenze, l'iteratore genera la lista delle
	 * preferenze, ma non il partito a cui appartengono.
	 * @throws NullPointerException Se v e' null.
	 */
	public Iterator<Candidato> candidati(VotazioneStandard v){
		Objects.requireNonNull(v);
		con = getConnection();
		List<Candidato> candidates = new ArrayList<>();
		
		PreparedStatement pS;
		ResultSet rS;
		try {
			if(!v.getVotoAPartiti() || v.getTipo().toLowerCase().equals("preferenziale")) {
				pS = con.prepareStatement("SELECT r.id, r.nome, r.cognome FROM candidati_rappresentanti cr JOIN rappresentanti r ON cr.rappresentante = r.id WHERE cr.votazione = ?;");
				pS.setInt(1, v.getId());
				rS = pS.executeQuery();
				while(rS.next()) {
					candidates.add(new CandidatoPersona(rS.getInt(1), rS.getString(2), rS.getString(3)));
				}
			}else {
				pS = con.prepareStatement("SELECT p.nome FROM candidati_partiti cp WHERE cp.votazione = ?;");
				pS.setInt(0, v.getId());
				rS = pS.executeQuery();
				while(rS.next()) {
					candidates.add(new CandidatoPartito(rS.getString(1)));
				}
			}
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return candidates.iterator();
	}
	
	/**
	 * Restituisce un iteratore delle persone appartenenti al partito passato come parametro che sono candidate per la votazione passata
	 * come parametro.
	 * @param v
	 * @param p
	 * @return Un iteratore di CandidatoPersona.
	 * @throws NullPointerException Se v o p sono null.
	 */
	public Iterator<CandidatoPersona> getPersoneInPartito(VotazioneStandard v, CandidatoPartito p){
		Objects.requireNonNull(v);
		Objects.requireNonNull(p);
		con = getConnection();
		List<CandidatoPersona> persone = new ArrayList<>();
		
		PreparedStatement pS = null;
		try {
			if(!v.getVotoAPartiti()) {						
				pS = con.prepareStatement("SELECT r.id, r.nome, r.cognome FROM candidati_partiti cPa NATURAL JOIN candidati_rappresentanti cRa JOIN rappresentanti r ON cRa.rappresentante = r.id WHERE ? = cPa.votazione AND trim(lower(cPa.partito)) LIKE trim(lower(?)) AND trim(lower(r.partito)) LIKE trim(lower(cPa.partito));");
				pS.setInt(1, v.getId());
				pS.setString(2, p.getNome());
				ResultSet rS1 = pS.executeQuery();
				while(rS1.next()) {
					int id1 = rS1.getInt(1);
					String nome1 = rS1.getString(2);
					String cognome1 = rS1.getString(3);
					persone.add(new CandidatoPersona(id1, nome1, cognome1, p));
				}
			}
			return persone.iterator();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
}
