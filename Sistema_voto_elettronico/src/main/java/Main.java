import java.util.Scanner;

import dbConnection.UserDAO;
import dbConnection.UserDAOImpl;
import gui.GuiApplication;
import system.Sessione;
import users.Amministratore;
import users.Elettore;

public class Main {

	public static void main(String[] args) {
		/*
		Elettore u = new Elettore("Umberto", "Pirovano", "umberto");
		Elettore m = new Elettore("Mattia", "Garavaglia", "mattia");
		Amministratore a = new Amministratore("Admin", "Admin", "admin");
		UserDAO db = new UserDAOImpl();
		db.insertUser(u, "1234");
		db.insertUser(m, "1234");
		db.insertUser(a, "admin");
		*/
		//System.out.println("Consultare il readme.txt per le credenziali di testing.");
		Sessione s = Sessione.getSessione();
		GuiApplication.show();
		s.destroy();
	}
}
