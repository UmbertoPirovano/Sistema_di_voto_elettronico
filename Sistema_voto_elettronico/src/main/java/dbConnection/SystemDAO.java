package dbConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

public interface SystemDAO {
	
	public static LocalDateTime getDbDateTime() {
		Connection con = DatabaseConnection.getConnection();
		try {
			PreparedStatement st = null;
			st = con.prepareStatement("SELECT NOW() AS datetime;");
			ResultSet res = st.executeQuery();
			while(res.next()) {				
				return res.getTimestamp("datetime").toLocalDateTime();
			}
		}catch (SQLException se) {
			se.printStackTrace();
		}
		return null;
	}
	
}
