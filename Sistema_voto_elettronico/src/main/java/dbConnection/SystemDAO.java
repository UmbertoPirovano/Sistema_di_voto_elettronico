package dbConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

public interface SystemDAO {
	
	public static Timestamp getDbDateTime() {
		Connection con = DatabaseConnection.getConnection();
		try {
			PreparedStatement st = null;
			st = con.prepareStatement("SELECT NOW() AS datetime;");
			ResultSet res = st.executeQuery();
			while(res.next()) {				
				return Timestamp.from(res.getTimestamp("datetime").toLocalDateTime().toInstant(ZoneOffset.of("+02:00")));
			}
		}catch (SQLException se) {
			se.printStackTrace();
		}
		return null;
	}
	
}
