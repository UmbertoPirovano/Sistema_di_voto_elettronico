package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectToDb {

	public static void main(String[] args) {
		Connection con = null;
		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost/sistema_di_voto?user=Mattia&password=sweng2021");
		}catch(SQLException ex) {
			ex.printStackTrace();
		}

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

}
