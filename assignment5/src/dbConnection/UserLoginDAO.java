/**
 * DAO interface.
 * Fornisce l'operazione che permette ad un utente di autenticarsi nel sistema.
 */
package dbConnection;

public interface UserLoginDAO {
	public boolean authenticate(String username, String password, String userMode);
}
