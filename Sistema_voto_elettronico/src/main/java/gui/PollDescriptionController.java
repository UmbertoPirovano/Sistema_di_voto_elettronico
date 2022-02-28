package gui;

import java.util.Objects;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import poll.Votazione;
import system.Sessione;

public class PollDescriptionController {

	 @FXML
    private Label label_dataFine;

    @FXML
    private Label label_dataInizio;

    @FXML
    private Label label_nome;

    @FXML
    private Label label_tipo;
	
	@FXML
    private TextArea msg_field;
    
    public void setMessage(String msg) {
    	Objects.requireNonNull(msg);
    	msg_field.setText(msg);
    }
    
    @FXML
    void initialize() {
    	Votazione v = Sessione.getSessione().getVotazione();
    	label_nome.setText(v.getNome());
    	label_tipo.setText(v.getTipo());
    	label_dataInizio.setText(v.getData_inizio());
    	label_dataFine.setText(v.getData_fine());
    	msg_field.setText(stringPadding(v.getDescrizione()));
    }
    
    private String stringPadding(String s) {
    	String nuova = new String();
    	int n = 0;
    	for(char c : s.toCharArray()) {
    		nuova += c;
    		n++;
    		if(c == ' ' && n%75 <= 7) {
    			nuova += "\n";
    		}
    	}    	
    	return nuova;
    }

}

