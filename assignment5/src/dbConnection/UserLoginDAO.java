package dbConnection;

public interface UserLoginDAO {
	public boolean authenticate(String username, String password, String userMode);
}
