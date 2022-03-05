package poll;


import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import dbConnection.PollDAOImpl;

public abstract class Votazione {
	
	private final int id;
	private final String nome;
	private final String tipo;
	private final Date data_inizio;
	private final Date data_fine;
	private final String descrizione;
	
	private int index_candidate;
	
	public Votazione(int id, String nome, String tipo, String data_inizio, String data_fine, String descrizione) {
		this.id = id;
		this.nome = Objects.requireNonNull(nome);
		this.tipo = Objects.requireNonNull(tipo);
		this.data_inizio = stringToDate(data_inizio);
		this.data_fine = stringToDate(data_fine);
		this.descrizione = Objects.requireNonNull(descrizione);
		
		assert repOk();
	}
	
	public int getId() {
		return id;
	}
	
	public String getNome() {
		return nome;
	}
	
	public String getTipo() {
		return tipo;
	}
	
	public String getData_inizio() {
		return data_inizio.toGMTString();
	}
	
	public String getData_fine() {
		return data_fine.toGMTString();
	}
	
	public String getDescrizione() {
		return descrizione;
	}
	
	private boolean repOk() {
		return (id > 0) && data_inizio.before(data_fine);
	}
	
	private Date stringToDate(String date) {
		try {
			Objects.requireNonNull(date);
			DateFormat df = new SimpleDateFormat("yy-MM-dd HH:mm:ss");
			return df.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public List<Candidato> getCandidati(){
    	return new PollDAOImpl().getCandidati(this); 
    }
    
    public Candidato listCandidato() {
    	List<Candidato> candidati = getCandidati();
    	if(index_candidate == candidati.size()) {
    		index_candidate = 0;
    	}
    	return candidati.get(index_candidate++);
    }
    
    public int countCandidati() {
    	return getCandidati().size();
    }
}
