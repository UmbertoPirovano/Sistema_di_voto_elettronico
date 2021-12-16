import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.control.Label;

public class LoginWindowController {

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
    //Premere il tasto invio equivale a schiacciare il bottone per effettuare il login.
    public void enterPressed(KeyEvent ke) {
    	if(ke.getCode().equals(KeyCode.ENTER)) {
    		submitButton.fire();
    	}    		
    }
    
    @FXML
    void submit(ActionEvent event) {
    	String username = userField.getText();
    	String password = pwField.getText();
    	String mode = modeSelection.getValue();
    	
    	//da implementare il controllo con il DB
    	if(username.equals("Topolino") && password.equals("ciao")) {    	
    		statusLabel.setTextFill(Color.color(0, 1, 0));
    		statusLabel.setText("Benvenuto " + username);
    	}else {
    		statusLabel.setTextFill(Color.color(1, 0, 0));
    		statusLabel.setText("Username o password non corretti.");
    	}
    	
    }
    
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

}

