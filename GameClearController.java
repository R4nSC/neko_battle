import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.VBox;

public class GameClearController implements Initializable {
    @Override
    public void initialize(URL url,ResourceBundle rb){
    }

    public void OKAction(ActionEvent event){
        System.exit(1);
    }

}
