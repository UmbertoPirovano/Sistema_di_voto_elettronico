import dbConnection.UserDAO;
import dbConnection.UserDAOImpl;
import gui.LoginWindowView;

public class Main {

	public static void main(String[] args) {		
		UserDAO dbDao = new UserDAOImpl();
		dbDao.insertUser(new Elettore(), null)
		LoginWindowView.show();
	}

}
