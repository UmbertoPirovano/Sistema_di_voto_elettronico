package dbConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;

public interface SystemDAO {
	
	public static Date getDbDateTime() {
		Connection con = DatabaseConnection.getConnection();
		try {
			PreparedStatement st = null;
			st = con.prepareStatement("SELECT NOW() AS datetime;");
			ResultSet res = st.executeQuery();
			while(res.next()) {				
				return Date.from(res.getTimestamp("datetime").toLocalDateTime().toInstant(ZoneOffset.UTC));
			}
		}catch (SQLException se) {
			se.printStackTrace();
		}
		return null;
	}
	
}
