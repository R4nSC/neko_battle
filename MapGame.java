import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;

public class MapGame extends Application {
    static Stage stage;
    static Stage battle;
    static Stage menu;
    static Stage gameclear;
    
   @Override
    public void start(Stage primaryStage) throws Exception {
        stage = primaryStage;
        primaryStage.setTitle("MAP GAME");
        Pane myPane_top = (Pane)FXMLLoader.load(getClass().getResource("MapGame.fxml"));
        Scene myScene = new Scene(myPane_top,693,592);
        primaryStage.setScene(myScene);
        primaryStage.show();
        
        //battle.setX(stage.getX() + 100);
        //battle.setY(stage.getY() + 100);
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}
