import dbConnection.UserDAO;
import dbConnection.UserDAOImpl;
import gui.LoginWindowView;
import users.Elettore;

public class Main {

	public static void main(String[] args) {		
		UserDAO dbDao = new UserDAOImpl();
		dbDao.insertUser(new Elettore("Mattia","Garavaglia","mattia"), "1234");
		LoginWindowView.show();
	}

}
