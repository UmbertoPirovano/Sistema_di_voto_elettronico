import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/*MVC: questa classe rappresenta la classe View del pattern MVC. La classe model e' invece rappresentata temporaneamente dalle tabelle elettore
e amministratore del db*/

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        System.out.println(Main.class.getResource("LoginWindow.fxml"));
    	Parent root = FXMLLoader.load(getClass().getResource("LoginWindow.fxml"));
        
    	primaryStage.setTitle("Sistema di voto elettronico - Login");
        primaryStage.setScene(new Scene(root, 500, 390));
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public static void main(String[] args) {
    	ConnectToDb conn = new ManageUserConnection();
    	launch();
    }

}