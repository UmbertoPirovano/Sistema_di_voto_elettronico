package gui;

import dbConnection.UserLoginDAO;
import dbConnection.UserLoginDAOImpl;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/*MVC: questa classe rappresenta la classe View del pattern MVC in quanto si occupa di visualizzare i dati
 		all'utente e gestisce le interazioni con l'infrastuttura sottostante.*/

public class LoginWindowView extends Application {
	
    @Override
    public void start(Stage primaryStage) throws Exception{
        System.out.println(LoginWindowView.class.getResource("/gui/LoginWindow.fxml"));
    	Parent root = FXMLLoader.load(getClass().getResource("/gui/LoginWindow.fxml"));
        
    	primaryStage.setTitle("Sistema di voto elettronico - Login");
        primaryStage.setScene(new Scene(root, 500, 390));
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public static void show() {
    	launch();
    }
    
    public static boolean executeLogin(String username, String encryptedPwd, String mode) {
    	UserLoginDAO conn = new UserLoginDAOImpl();
    	boolean result =  conn.authenticate(username, encryptedPwd, mode);
    	return result;
    }

}