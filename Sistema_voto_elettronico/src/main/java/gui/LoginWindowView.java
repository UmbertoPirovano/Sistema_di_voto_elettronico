package gui;

import dbConnection.UserDAO;
import dbConnection.UserDAOImpl;
import dbConnection.UserLoginDAO;
import dbConnection.UserLoginDAOImpl;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import system.Sessione;

/*MVC: questa classe rappresenta la classe View del pattern MVC in quanto si occupa di mostrare i dati
 		all'utente e gestisce le interazioni con l'infrastuttura sottostante.*/

public class LoginWindowView extends Application {
	
    @Override
    public void start(Stage primaryStage) throws Exception{
        //System.out.println(LoginWindowView.class.getResource("/main/resources/gui/LoginWindow.fxml"));
    	Parent root = FXMLLoader.load(getClass().getResource("LoginWindow.fxml"));
        
    	primaryStage.setTitle("Sistema di voto elettronico - Login");
        primaryStage.setScene(new Scene(root, 500, 390));
        primaryStage.setResizable(false);
        primaryStage.show();
    }
    
    /**
     * Lancia il metodo start.
     */
    public static void show() {
    	launch();
    }
    
    /**
     * Verifica che nel DB sia presente un utente con le credenziali passate come parametro.
     * @param username Lo username dell'utente da cercare.
     * @param encryptedPwd La password criptata dell'utente da cercare.
     * @param mode Il tipo di utente da cercare (elettore / amministratore).
     * @return true se e' stato trovato un untente corrispondente alle credenziali inserite, false altrimenti.
     */
    public static boolean executeLogin(String username, String encryptedPwd, String mode) {
    	UserLoginDAO loginDao = new UserLoginDAOImpl();
    	UserDAO userDao = new UserDAOImpl();
    	if (loginDao.authenticate(username, encryptedPwd, mode)) {
    		Sessione.getSessione().loginUser(userDao.findByUsername(username));
    		return true;
    	}
    	return false;
    }
    
    public static void executeLogout() {
    	Sessione.getSessione().logoutUser();
    }

}