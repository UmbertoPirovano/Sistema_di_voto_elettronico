package gui;


import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import system.Sessione;

/*MVC: questa classe rappresenta la classe View del pattern MVC in quanto si occupa di mostrare i dati
 		all'utente e gestisce le interazioni con l'infrastuttura sottostante.*/

public class GuiApplication extends Application {
	
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
    
	/*
    public void showLoginWindow() {
    	try {
			//logoutButton.getScene().getWindow().hide();
    		Parent root = FXMLLoader.load(getClass().getResource("LoginWindow.fxml"));
            Stage stage = new Stage();
        	stage.setTitle("Sistema di voto elettronico - Login");
        	stage.setScene(new Scene(root, 500, 390));
        	stage.setResizable(false);
        	stage.show();
		}catch (IOException e) {
			System.out.println(e.getMessage());
		}
    }    
    
    public void showPollSelection() {
    	try {
			//submitButton.getScene().getWindow().hide();
    		Parent root = FXMLLoader.load(getClass().getResource("PollSelection.fxml"));
            Stage stage = new Stage();
        	stage.setTitle("Sistema di voto elettronico - Selezione votazioni");
        	stage.setScene(new Scene(root, 900, 780));
        	stage.setResizable(false);
        	stage.show();
		}catch (IOException e) {
			System.out.println(e.getMessage());
		}
    }
    
    public void showBookingWindow() {
    	try {
    		//button_azione.getScene().getWindow().hide();
    		Parent root = FXMLLoader.load(getClass().getResource("bookPoll.fxml"));
    		Stage stage = new Stage();
    		stage.setTitle("Sistema di voto elettronico - Prenotazione");
    		stage.setScene(new Scene(root, 900, 780));
    		stage.setResizable(false);
    		stage.show();
    	}catch (IOException e) {
    		System.out.println(e.getMessage());
    	}
    }
    
    public void showStandardPoll() {
    	try {
			//button_azione.getScene().getWindow().hide();
    		Parent root = FXMLLoader.load(getClass().getResource("votazioneStandard.fxml"));
            Stage stage = new Stage();
        	stage.setTitle("Sistema di voto elettronico - Votazione");
        	stage.setScene(new Scene(root, 900, 780));
        	stage.setResizable(false);
        	stage.show();
		}catch (IOException e) {
			System.out.println(e.getMessage());
		}
    }
    
    public void showConfirmWindow() {
		try {
			//submitButton.getScene().getWindow().hide();
			Parent root = FXMLLoader.load(getClass().getResource("ConfirmVoteWindow.fxml"));
	        Stage stage = new Stage();
	    	stage.setTitle("Sistema di voto elettronico - Conferma voto");
	    	stage.setScene(new Scene(root, 600, 400));
	    	stage.setResizable(false);
	    	stage.show();
		}catch (IOException e) {
			System.out.println(e.getMessage());
		}	    
	}  
	*/  
}