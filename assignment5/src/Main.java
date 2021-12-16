

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        System.out.println(Main.class.getResource("LoginWindow.fxml"));
    	Parent root = FXMLLoader.load(getClass().getResource("LoginWindow.fxml"));
        
    	primaryStage.setTitle("Login - Sistema di voto elettronico");
        primaryStage.setScene(new Scene(root, 500, 300));
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}