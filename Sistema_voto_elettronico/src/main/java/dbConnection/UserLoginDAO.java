/**
 * DAO interface.
 * Fornisce l'operazione che permette ad un utente di autenticarsi nel sistema.
 */
package dbConnection;

public interface UserLoginDAO {
	/**
	 * Esegue l'autenticazione al sistema con le credenziali passate come parametro.
	 * 
	 * @param username     Uno username.
	 * @param encryptedPwd Una password criptata con SHA512.
	 * @param userMode     Il tipo di utente da autenticare (elettore /
	 *                     amministratore).
	 * @return true se l'autenticazione e' andata a buon fine, false altrimenti.
	 */
	public boolean authenticate(String username, String encryptedPwd, String userMode);
}
