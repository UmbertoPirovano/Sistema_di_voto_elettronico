package gui;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import poll.Candidato;
import poll.CandidatoPartito;
import poll.CandidatoPersona;
import system.Sessione;

public class NodeCandidatoController implements Initializable {

    @FXML
    private Label labelAffiliazione;

    @FXML
    private Label labelNome;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		Candidato c = Sessione.getSessione().getVotazione().listCandidato();
		if(c instanceof CandidatoPersona) {
			CandidatoPersona p = (CandidatoPersona) c;
			labelNome.setText(p.getNome());
			labelAffiliazione.setText(p.getAffiliazione());
		}else if(c instanceof CandidatoPartito) {
			CandidatoPartito p = (CandidatoPartito) c;
			labelNome.setText(p.getNome());
			labelAffiliazione.setText("partito");
		}		
	}

}