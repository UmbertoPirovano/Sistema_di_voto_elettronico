package dbConnection;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/* DAO: questa classe rappresenta l'interfaccia DAO.
La classe model è rappresentata dal db.*/

public abstract class ConnectToDb {
	protected Connection con;
	
	public ConnectToDb() {
		con = null;
		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost/sistema_di_voto?user=Mattia&password=sweng2021");
		}catch(SQLException ex) {
			ex.printStackTrace();
		}	
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
}
