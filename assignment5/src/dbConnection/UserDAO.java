package dbConnection;

import java.util.List;
import users.User;

public interface UserDAO {
	List<User> findAll();
    List<User> findById(int id);
    List<User> findByName(String name, String surname);
    boolean insertUser(User user, String password);
    boolean updateUser(User user);
    boolean deleteUser(User user);    
}
