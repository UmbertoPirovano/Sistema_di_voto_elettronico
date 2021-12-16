package gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class View extends Application{

	public static void main(String[] args) {
		launch();
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("layout.fxml"));
		primaryStage.setTitle("Sistema di voto elettronico - login");
		primaryStage.setScene(new Scene(root, 529, 334));
		primaryStage.setResizable(false);
		primaryStage.show();
	}

}
