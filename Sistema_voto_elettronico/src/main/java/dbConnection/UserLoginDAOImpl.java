/**
 * DAO concrete class.
 * Implementa i metodi della relativa interfaccia.
 */
package dbConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserLoginDAOImpl implements UserLoginDAO {
	Connection con = null;

	/**
	 * Restituisce una connessione al DB contenente gli utenti.
	 * 
	 * @return Connessione al DB contenente gli utenti.
	 */
	private static Connection getConnection() {
		return DatabaseConnection.getConnection();
	}

	@Override
	public boolean authenticate(String username, String encryptedPwd, String userMode) {
		con = getConnection();

		PreparedStatement st = null;
		try {
			if (userMode.equals("elettore")) {
				st = con.prepareStatement("SELECT password FROM elettore WHERE elettore.cF = BINARY ?;");
			} else if (userMode.equals("amministratore")) {
				st = con.prepareStatement(
						"SELECT password FROM amministratore WHERE amministratore.username = BINARY ?;");
			}

			st.setString(1, username);

			ResultSet res = st.executeQuery();
			if (res.next()) {
				String columnValue = res.getString(1);
				if (columnValue.equals(encryptedPwd))
					return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;
	}

}
