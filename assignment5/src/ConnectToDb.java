
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

public class ConnectToDb {
	private Connection con;
	
	public ConnectToDb() {
		con = null;
		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost/sistema_di_voto?user=Mattia&password=sweng2021");
		}catch(SQLException ex) {
			ex.printStackTrace();
		}	
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
			//System.out.println(st);
					
			ResultSet res = st.executeQuery();
			ResultSetMetaData rsmd = res.getMetaData();
			if(res.next()) {
				String columnValue = res.getString(1);
	        	if(columnValue.equals(MD5(password))) return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}
	
	public static String MD5(String s) {
	      MessageDigest m = null;
		try {
			m = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	      m.update(s.getBytes(),0,s.length());     
	      return new BigInteger(1,m.digest()).toString(16); 
	   }
	
	public void addUser(String username, String password, String userMode) {
		password = MD5(password);
		
		PreparedStatement st = null;
		try {
			if(userMode.equals("elettore")) {
				st = con.prepareStatement("INSERT INTO elettore(cF,password) VALUES (?,?);");
			}else if(userMode.equals("amministratore")) {
				st = con.prepareStatement("INSERT INTO amministratore(username,password) VALUES (?,?);");
			}
			st.setString(1, username);
			st.setString(2, password);
			st.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
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
