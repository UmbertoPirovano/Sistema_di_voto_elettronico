package dbConnection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

//DAO: questa classe rappresenta una classe concreta DAO che implementa la connessione di gestione utenti con il db.

public class ManageUserConnection extends ConnectToDb {
	
	public ManageUserConnection() {
		super();
	}
	
	public void addUser(String username, String password, String userMode) {
		password = Sha512(password);
		
		PreparedStatement st = null;
		try {
			if(userMode.equals("elettore")) {
				st = con.prepareStatement("INSERT INTO elettore(cF,password) VALUES (?,?);");
			}else if(userMode.equals("amministratore")) {
				st = con.prepareStatement("INSERT INTO amministratore(username,password) VALUES (?,?);");
			}else {
				throw new IllegalArgumentException("Undefined user mode: "+ userMode);
			}
			st.setString(1, username);
			st.setString(2, password);
			st.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
