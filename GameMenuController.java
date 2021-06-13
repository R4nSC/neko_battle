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

public class GameMenuController implements Initializable {
    public Label CharaLabel_1;
    public Label CharaLabel_2;
    public Label CharaLabel_3;
    public Label CharaLabel_4;
    public Label CharaLabel_5;
    public Label CharaLabel_6;
    public Label[] CharaLabels;

    public Label CharaName_1;
    public Label CharaName_2;
    public Label CharaName_3;
    public Label CharaName_4;
    public Label CharaName_5;
    public Label CharaName_6;
    public Label[] CharaNames;

    public VBox Chara1;
    public VBox Chara2;
    public VBox Chara3;
    public VBox Chara4;
    public VBox Chara5;
    public VBox Chara6;
    public VBox[] Charas;

    @Override
    public void initialize(URL url,ResourceBundle rb){
		CharaLabels = new Label[6];
		CharaLabels[0] = CharaLabel_1;
		CharaLabels[1] = CharaLabel_2;
		CharaLabels[2] = CharaLabel_3;
		CharaLabels[3] = CharaLabel_4;
		CharaLabels[4] = CharaLabel_5;
		CharaLabels[5] = CharaLabel_6;

		CharaNames = new Label[6];
		CharaNames[0] = CharaName_1;
		CharaNames[1] = CharaName_2;
		CharaNames[2] = CharaName_3;
		CharaNames[3] = CharaName_4;
		CharaNames[4] = CharaName_5;
		CharaNames[5] = CharaName_6;

		Charas = new VBox[6];
		Charas[0] = Chara1;
		Charas[1] = Chara2;
		Charas[2] = Chara3;
		Charas[3] = Chara4;
		Charas[4] = Chara5;
		Charas[5] = Chara6;
		
		outputChara();
    }

    public void outputChara(){
		for(int i=0;i<6;i++){
			Charas[i].setVisible(false);
		}
		CharaStatus[] party = MapGameController.chara.getPlayerParty().getPartyStatus();
		int CharaCount = MapGameController.chara.getPlayerParty().getCharaCount();
		for(int i=0;i<CharaCount;i++){
			CharaNames[i].setText(party[i].getName());
			CharaLabels[i].setText(party[i].toString());
			Charas[i].setVisible(true);
		}
    }

    public void ExitAction(ActionEvent event){
		MapGame.menu.close();
    }
}
