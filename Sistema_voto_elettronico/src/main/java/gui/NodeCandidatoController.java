package gui;

import java.net.URL;
import java.util.ResourceBundle;

import candidates.Candidato;
import candidates.CandidatoPartito;
import candidates.CandidatoPersona;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import poll.VotazioneStandard;
import system.Sessione;

public class NodeCandidatoController implements Initializable {

    @FXML
    private Label labelAffiliazione;

    @FXML
    private Label labelNome;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		VotazioneStandard v = (VotazioneStandard) Sessione.getSessione().getVotazione();
		Candidato c = v.initNode_next();
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