package gui;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import dbConnection.Encryption;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import system.Sessione;

/*MVC: questa classe e' il Controller nel pattern MVC in qanto riceve i comandi utente ricevuti attraverso il view e 
 		reagisce ad essi attraverso operazioni che possono interessare il model. */

public class LoginWindowController {
	
	@FXML
	private Pane container;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ChoiceBox<String> modeSelection;

    @FXML
    private PasswordField pwField;

    @FXML
    private Button submitButton;

    @FXML
    private TextField userField;
    
    @FXML
    private Label statusLabel;
    
    @FXML
    private Button settingsButton;
    
    @FXML
    void showSettings(ActionEvent event) {
    	try {
    		Parent root = FXMLLoader.load(getClass().getResource("SettingsWindow.fxml"));
            Stage stage = new Stage();
        	stage.setTitle("Sistema di voto elettronico - Settings");
        	stage.setScene(new Scene(root, 600, 400));
        	stage.setResizable(false);
        	stage.show();
		}catch (IOException e) {
			System.out.println(e.getMessage());
		}
    }
    
    /**
     * Quando viene premuto il tasto invio esegue il login con i dati inseriti in userField e pwField.
     * @param ke Il tasto premuto.
     */
    @FXML
    public void enterPressed(KeyEvent ke) {
    	if(ke.getCode().equals(KeyCode.ENTER)) {
    		submitButton.fire();
    	}    		
    }
    
    /**
     * Quando viene premuto il bottone submitButton tenta di eseguire il login al sistema. In caso di fallimento stampa un messaggio sulla
     * form. Altrimenti reindirizza ad un'altra finestra (temporaneamente stampa un messaggio di benvenuto).
     * @param event L'evento di click del bottone submitButton.
     */
    @FXML
    void submit(ActionEvent event) {
    	String username = userField.getText();
    	String password = pwField.getText();
    	String mode = modeSelection.getValue().toLowerCase();
    	
    	boolean login = Sessione.getSessione().executeLogin(username, Encryption.Sha512(password), mode);
    	
    	
    	if(login) {    	
    		statusLabel.setTextFill(Color.color(0, 1, 0));
    		statusLabel.setText("Benvenuto " + username);
    		if(mode.equalsIgnoreCase("elettore"))
    			showPollSelection();
    		else if(mode.equalsIgnoreCase("amministratore"))
    			showAdminMenu();
    	}else {
    		statusLabel.setTextFill(Color.color(1, 0, 0));
    		statusLabel.setText("Verificare le credenziali e il tipo di utente.");
    	}
    	
    }
    
    /**
     * Inizializza questo Controller.
     */
    @FXML
    void initialize() {
        assert modeSelection != null : "fx:id=\"modeSelection\" was not injected: check your FXML file 'LoginWindow.fxml'.";
        assert pwField != null : "fx:id=\"pwField\" was not injected: check your FXML file 'LoginWindow.fxml'.";
        assert submitButton != null : "fx:id=\"submitButton\" was not injected: check your FXML file 'LoginWindow.fxml'.";
        assert userField != null : "fx:id=\"userField\" was not injected: check your FXML file 'LoginWindow.fxml'.";
        
        modeSelection.getItems().addAll("Elettore", "Amministratore");
        modeSelection.setValue("Elettore");
        
        statusLabel.setAlignment(Pos.CENTER);
        
        //premere invio nella casella della password equivale a premere il tasto di login.
        pwField.setOnKeyPressed(new EventHandler<KeyEvent>(){
        	@Override
        	public void handle(KeyEvent ke) {
        		if(ke.getCode().equals(KeyCode.ENTER)) {
            		submitButton.fire();
            	}
        	}
        });
    }
    
    /**
     * Quando viene clickato container viene tolto il focus dai possibili elementi selezionati precedentemente.
     * @param event Indica il punto in cui avviene il click del mouse.
     */
    @FXML
    void setFocus(MouseEvent event) {
    	container.requestFocus();
    }
    
    /**
     * Chiude la schermata di login ed apre la schermata di selezione votazione dedicata agli Elettori.
     */
    void showPollSelection() {
    	try {
			submitButton.getScene().getWindow().hide();
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
    
    /**
     * Chiude la finestra di login ed apre il men� amministratore.
     */
    void showAdminMenu() {
    	try {
			submitButton.getScene().getWindow().hide();
    		Parent root = FXMLLoader.load(getClass().getResource("AdminMenu.fxml"));
            Stage stage = new Stage();
        	stage.setTitle("Sistema di voto elettronico - Men� amministratore");
        	stage.setScene(new Scene(root, 900, 800));
        	stage.setResizable(false);
        	stage.show();
		}catch (IOException e) {
			System.out.println(e.getMessage());
		}
    }

}

