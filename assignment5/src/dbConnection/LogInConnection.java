package dbConnection;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

/*MVC: questa classe rappresenta la classe Model del pattern MVC in quanto contiene i metodi d'accesso ai dati del database
 		necessari nella LoginWindow.*/
//DAO: questa classe rappresenta una classe concreta DAO.

public class LogInConnection extends ConnectToDb {
	
	public LogInConnection() {
		super();	
	}
	
	public boolean checkCredentials(String username, String encryptedPwd, String userMode) {
		PreparedStatement st = null;
		try {
			if(userMode.equals("elettore")) {
				st = con.prepareStatement("SELECT password FROM elettore WHERE elettore.cF = BINARY ?;");
			}else if(userMode.equals("amministratore")) {
				st = con.prepareStatement("SELECT password FROM amministratore WHERE amministratore.username = BINARY ?;");
			}
			
			st.setString(1, username);
					
			ResultSet res = st.executeQuery();
			ResultSetMetaData rsmd = res.getMetaData();
			if(res.next()) {
				String columnValue = res.getString(1);
	        	if(columnValue.equals(encryptedPwd)) 
	        		return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}
}
