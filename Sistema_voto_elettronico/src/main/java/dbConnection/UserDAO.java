/**
 * DAO interface.
 * Fornisce le operazioni eseguibili sugli utenti registrati nel database.
 */
package dbConnection;

import java.util.List;
import users.User;

public interface UserDAO {
	/**
	 * Restituisce una lista contenente tutti gli utenti del sistema (elettori e
	 * amministratori).
	 * 
	 * @return la lista di tutti gli utenti del sistema.
	 */
	List<User> findAll();

	/**
	 * Restituisce una lista contenete gli utenti del sistema (elettori e
	 * amministratori) con l'id passato come parametro.
	 * 
	 * @param id Un intero rappresentante un id.
	 * @return La lista degli utenti il cui id corrisponde con quello passato come
	 *         parametro.
	 */
	List<User> findById(int id);

	/**
	 * Restituisce una lista di utenti del sistema (elettori e amministratori) il
	 * cui nome e cognome corrisponde con quelli passati come parametro.
	 * 
	 * @param name    Un nome.
	 * @param surname Un cognome.
	 * @return La lista degli utenti il cui nome e cognome corrispondono con quelli
	 *         passati come parametro.
	 */
	List<User> findByName(String name, String surname);

	/**
	 * Inserisce nel DB degli utenti del sistema lo User e la password (che viene
	 * criptata nel metodo) nel DB, se non e' gia' presente un altro utente con lo
	 * stesso username.
	 * 
	 * @param user     Lo User da inserire nel DB.
	 * @param password La password di user.
	 * @return true se l'inserimento va a buon fine, false altrimenti.
	 */
	boolean insertUser(User user, String password);

	/**
	 * Aggiorna nel DB degli utenti del sistema i campi corrispondenti allo User
	 * passato come parametro, se e' presente.
	 * 
	 * @param user Lo User da aggiornare.
	 * @return true se l'aggiornamento va a buon fine, false altrimenti.
	 */
	boolean updateUser(User user);

	/**
	 * Rimuove lo User passato come parametro dal DB degli utenti del sistema, se e'
	 * presente.
	 * 
	 * @param user
	 * @return true se la cancellazione va a buon fine, false altrimenti.
	 */
	boolean deleteUser(User user);
}
