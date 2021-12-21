package dbConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserLoginDAOImpl implements UserLoginDAO {
	Connection con = null;

	private static Connection getConnection() {
		Connection c = null;
		try {
			c = DriverManager.getConnection("jdbc:mysql://localhost/sistema_di_voto?user=Mattia&password=sweng2021");
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return c;
	}
	
	@Override
	public boolean authenticate(String username, String password, String userMode) {
		con = getConnection();
		//password = Encryption.Sha512(password);
		
		PreparedStatement st = null;
		try {
			if(userMode.equals("elettore")) {
				st = con.prepareStatement("SELECT password FROM elettore WHERE elettore.cF = BINARY ?;");
			}else if(userMode.equals("amministratore")) {
				st = con.prepareStatement("SELECT password FROM amministratore WHERE amministratore.username = BINARY ?;");
			}
			
			st.setString(1, username);
					
			ResultSet res = st.executeQuery();
			if(res.next()) {
				String columnValue = res.getString(1);
	        	if(columnValue.equals(password)) 
	        		return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}

}
