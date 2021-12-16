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
class Amministratore extends Utente{
	
	Amministratore(int id, String name, String surname){
		super(id, name, surname);
	}
	
	/**
	 * Restituisce una nuova votazione creata tramite i parametri specificati.
	 * @param name Il nome della votazione.
	 * @param start La data di inizio della votazione.
	 * @param end La data di fine della votazione.
	 * @param t Il tipo di votazione.
	 * @return La votazione creata.
	 */
	/*
	Votazione createVote(String name, Date start, Date end, VoteType t) {
		//TODO: implementazione
	}
	*/
	
	/**
	 * Aggiunge alla blacklist specificata l'elettore indicato.
	 * @param b La blacklist del sistema.
	 * @param e Un elettore.
	 */
	/*
	void addToBlackList(BlackList b, Elettore e) {
		
	}
	
	@Override
	Iterator<Votazione> votes(){
		//TODO: implementazione
	}
	
	@Override
	Iterator<Votazione> votes(int year){
		//TODO: implementazione
	}
	
	@Override
	Iterator<Votazione> votes(int month, int year){
		//TODO: implementazione
	}
	*/
}
