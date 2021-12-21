package users;

import java.util.Date;
import java.util.Iterator;

/**
 * @author Mattia Garavaglia
 * @author Umberto Pirovano
 * 
 * Un utente di tipo amministratore del sistema di voto elettronico.
 * Questo tipo di utente pu� creare una nuova votazione, modificare le votazioni create (ma non ancora in corso), aggiungere
 * e rimuovere elettori da una blacklist, visualizzare una lista di votazioni (con stato) ed effettuare logout.
 * Le istanze di questa classe sono mutabili.
 *
 */
public class Amministratore extends User{
	
	public Amministratore(String name, String surname, String username){
		super(name, surname, username);
	}
	
}
