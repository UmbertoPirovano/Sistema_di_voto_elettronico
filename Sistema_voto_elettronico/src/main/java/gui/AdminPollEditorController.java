package gui;

import java.net.URL;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

import dbConnection.PollDAO;
import dbConnection.PollDAOImpl;
import dbConnection.SystemDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import poll.Referendum;
import poll.Votazione;

public class AdminPollEditorController implements Initializable {

    @FXML
    private Button categoricoAddButton;

    @FXML
    private TextArea categoricoCandidateListField;

    @FXML
    private TextArea categoricoDescriptionField;

    @FXML
    private DatePicker categoricoEndDateField;

    @FXML
    private Label categoricoEndDateLabel;

    @FXML
    private ChoiceBox<Integer> categoricoEndHhChoice;

    @FXML
    private ChoiceBox<Integer> categoricoEndMmChoice;

    @FXML
    private TextField categoricoNameField;

    @FXML
    private Label categoricoNameLabel;

    @FXML
    private DatePicker categoricoStartDateField;

    @FXML
    private Label categoricoStartDateLabel;

    @FXML
    private ChoiceBox<Integer> categoricoStartHhChoice;

    @FXML
    private ChoiceBox<Integer> categoricoStartMmChoice;

    @FXML
    private Label categoricoStatusLabel;

    @FXML
    private ChoiceBox<String> categoricoTypeChoice;

    @FXML
    private Button ordinaleAddButton;

    @FXML
    private TextArea ordinaleCandidateListField;

    @FXML
    private TextArea ordinaleDescriptionField;

    @FXML
    private DatePicker ordinaleEndDateField;

    @FXML
    private Label ordinaleEndDateLabel;

    @FXML
    private ChoiceBox<Integer> ordinaleEndHhChoice;

    @FXML
    private ChoiceBox<Integer> ordinaleEndMmChoice;

    @FXML
    private TextField ordinaleNameField;

    @FXML
    private Label ordinaleNameLabel;

    @FXML
    private DatePicker ordinaleStartDateField;

    @FXML
    private Label ordinaleStartDateLabel;

    @FXML
    private ChoiceBox<Integer> ordinaleStartHhChoice;

    @FXML
    private ChoiceBox<Integer> ordinaleStartMmChoice;

    @FXML
    private Label ordinaleStatusLabel;

    @FXML
    private ChoiceBox<String> ordinaleTypeChoice;

    @FXML
    private Button referendumAddButton;

    @FXML
    private TextArea referendumDescriptionField;

    @FXML
    private DatePicker referendumEndDateField;

    @FXML
    private Label referendumEndDateLabel;

    @FXML
    private ChoiceBox<Integer> referendumEndHhChoice;

    @FXML
    private ChoiceBox<Integer> referendumEndMmChoice;

    @FXML
    private TextField referendumNameField;

    @FXML
    private Label referendumNameLabel;

    @FXML
    private DatePicker referendumStartDateField;

    @FXML
    private Label referendumStartDateLabel;

    @FXML
    private ChoiceBox<Integer> referendumStartHhChoice;

    @FXML
    private ChoiceBox<Integer> referendumStartMmChoice;

    @FXML
    private Label referendumStatusLabel;

    @FXML
    private ChoiceBox<String> referendumTypeChoice;

    @FXML
    void addReferendum(ActionEvent event) {
    	clearAllLabel();
    	Timestamp start = buildDate(referendumStartDateField, referendumStartHhChoice, referendumStartMmChoice);
    	Timestamp end = buildDate(referendumEndDateField, referendumEndHhChoice, referendumEndMmChoice);
    	if(!validateDate(start, end)) return;
    	
    	Votazione v = null;
    	if(referendumTypeChoice.getValue().equalsIgnoreCase("Con quorum"))
    		v = new Referendum(0, referendumNameField.getText(), start, end, referendumDescriptionField.getText(), true);
    	else if(referendumTypeChoice.getValue().equalsIgnoreCase("Senza quorum"))
    		v = new Referendum(0, referendumNameField.getText(), start, end, referendumDescriptionField.getText(), false);
    	
    	PollDAO pollDao = new PollDAOImpl();
    	pollDao.creaVotazione(v);
    }
    
    @FXML
    void addOrdinale(ActionEvent event) {
    	clearAllLabel();
    	Timestamp start = buildDate(ordinaleStartDateField, ordinaleStartHhChoice, ordinaleStartMmChoice);
    	Timestamp end = buildDate(ordinaleEndDateField, ordinaleEndHhChoice, ordinaleEndMmChoice);
    	if(!validateDate(start, end)) return;
    	
    	Votazione v = null;
    	
    	PollDAO pollDao = new PollDAOImpl();
    	pollDao.creaVotazione(v);
    }
    
    @FXML
    void addCategorico(ActionEvent event) {
    	clearAllLabel();
    	Timestamp start = buildDate(categoricoStartDateField, categoricoStartHhChoice, categoricoStartMmChoice);
    	Timestamp end = buildDate(categoricoEndDateField, categoricoEndHhChoice, categoricoEndMmChoice);
    	if(!validateDate(start, end)) return;
    	
    	Votazione v = null;
    	
    	PollDAO pollDao = new PollDAOImpl();
    	pollDao.creaVotazione(v);
    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		initializeTimePicker(referendumStartHhChoice, referendumStartMmChoice);
		initializeTimePicker(ordinaleStartHhChoice, ordinaleStartMmChoice);
		initializeTimePicker(categoricoStartHhChoice, categoricoStartMmChoice);
		initializeTimePicker(referendumEndHhChoice, referendumEndMmChoice);
		initializeTimePicker(ordinaleEndHhChoice, ordinaleEndMmChoice);
		initializeTimePicker(categoricoEndHhChoice, categoricoEndMmChoice);
		
		referendumTypeChoice.getItems().addAll("Senza quorum", "Con quorum");
		referendumTypeChoice.setValue("Senza quorum");
		ordinaleTypeChoice.getItems().addAll("Votazione a candidati", "Votazione a partiti");
		ordinaleTypeChoice.setValue("Votazione a candidati");
		categoricoTypeChoice.getItems().addAll("a candidati senza preferenze", "a partiti senza preferenze", "con preferenze");
		categoricoTypeChoice.setValue("a candidati senza preferenze");
	}
	
	/**
	 * Inizializza i valori messi a disposizione dai TimePicker affinchè fungano da selezione di un orario.
	 * @param boxHh Il ChoiceBox che deve servire a selezionare l'ora
	 * @param boxMm Il ChoiceBox che deve servire a selezionare il minuto
	 */
	private void initializeTimePicker(ChoiceBox<Integer> boxHh, ChoiceBox<Integer> boxMm) {
		List<Integer> ore = new ArrayList<>();
		for(int i=0 ; i <= 23 ; i++) {
			ore.add(i);
		}
		List<Integer> minuti = new ArrayList<>();
		for(int i=0 ; i <= 59 ; i++) {
			minuti.add(i);
		}
		boxHh.getItems().addAll(ore);
		boxMm.getItems().addAll(minuti);
		boxHh.setValue(0);
		boxMm.setValue(0);
	}
	
	/**
	 * Imposta la stringa msg come valore della label.
	 * @param label
	 * @param msg
	 */
	private void displayError(Label label, String msg) {
		Objects.requireNonNull(label);
		Objects.requireNonNull(msg);
		label.setTextFill(Color.color(1, 0, 0));
		label.setText(msg);
	}
	
	private void clearAllLabel() {
		referendumStatusLabel.setText("");
		referendumNameLabel.setText("");
		referendumStartDateLabel.setText("");
		referendumEndDateLabel.setText("");
		
		ordinaleStatusLabel.setText("");
		ordinaleNameLabel.setText("");
		ordinaleStartDateLabel.setText("");
		ordinaleEndDateLabel.setText("");
		
		categoricoStatusLabel.setText("");
		categoricoNameLabel.setText("");
		categoricoStartDateLabel.setText("");
		categoricoEndDateLabel.setText("");
	}
	
	/**
	 * Costruisce un oggetto Date contenente data e ora a partire dal valore di un DatePicker e due ChoiceBox di Integer
	 * tramite i quali si selezionano rispettivamente ore e minuti.
	 * @param datePicker L'oggetto DatePicker con il quale si seleziona la data
	 * @param boxHh L'oggetto ChoiceBox con il quale si selezionano le ore
	 * @param boxMm L'oggetto ChoiceBox con il quale si selezionano i minuti
	 * @return Un oggetto Date che rappresenta la coppia data-ora
	 */
	private Timestamp buildDate(DatePicker datePicker, ChoiceBox<Integer> boxHh, ChoiceBox<Integer> boxMm) {		
		LocalDateTime date = datePicker.getValue().atStartOfDay();
    	date = date.withHour(boxHh.getValue());
    	date = date.withMinute(boxMm.getValue());
    	Timestamp ts = Timestamp.from(date.toInstant(ZoneOffset.of("+02:00")));
    	return ts;
	}
	
	/**
	 * Controlla che le date passate in argomento siano valide come rispettivamente data iniziale e finale per una votazione.
	 * @param d1 la data che si intende usare come inizio
	 * @param d2 la data che si intende usare come fine
	 * @return true se d1 è una data che precede d2 ma segue la data corrente, false altrimenti
	 */
	private boolean validateDate(Date d1, Date d2) {
		Timestamp now = SystemDAO.getDbDateTime();
		System.out.println(now);
		System.out.println(d1);
		System.out.println(d2);
		if(d1.after(now) && d1.before(d2)) return true;
		else if(d2.before(d1)) {
			displayError(referendumEndDateLabel, "Data non valida");
			displayError(ordinaleEndDateLabel, "Data non valida");
			displayError(categoricoEndDateLabel, "Data non valida");
			return false;
		}else if(d1.before(now)) {
			displayError(referendumStartDateLabel, "Data non valida");
			displayError(ordinaleStartDateLabel, "Data non valida");
			displayError(categoricoStartDateLabel, "Data non valida");
			return false;
		}
		displayError(referendumStatusLabel, "Controlla i campi");
		displayError(ordinaleStatusLabel, "Controlla i campi");
		displayError(categoricoStatusLabel, "Controlla i campi");
		return false;
	}
}
