import java.util.Scanner;

import dbConnection.UserDAO;
import dbConnection.UserDAOImpl;
import gui.LoginWindowView;
import users.Amministratore;
import users.Elettore;

public class Main {

	public static void main(String[] args) {
		System.out.println("Consultare il readme.txt per le credenziali di testing.");
		LoginWindowView.show();
	}	
}
