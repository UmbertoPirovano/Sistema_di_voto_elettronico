
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

public class LogInConnection extends ConnectToDb {
	
	public LogInConnection() {
		super();	
	}
	
	public boolean checkCredentials(String username, String password, String userMode) {
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
	        	if(columnValue.equals(MD5(password))) 
	        		return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}
	
	/*
	public static void main(String[] args) {
		ConnectToDb c = new ConnectToDb();
		System.out.println(c.checkCredentials("topolino", "1234", "Elettore"));
	}
	*/
	/*
	public static void main(String[] args) {
		

		try {
			Statement s = con.createStatement();
			ResultSet res = s.executeQuery("SELECT * FROM test");
			ResultSetMetaData rsmd = res.getMetaData();
			System.out.println("querying SELECT * FROM XXX");
			int columnsNumber = rsmd.getColumnCount();
			while (res.next()) {
				for (int i = 1; i <= columnsNumber; i++) {
					if (i > 1) System.out.print(",  ");
			        	String columnValue = res.getString(i);
			        	System.out.print(columnValue + " " + rsmd.getColumnName(i));
			       	}
			       System.out.println("");
			   	}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	*/
}
