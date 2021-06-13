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
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;
import javafx.scene.layout.HBox;
    
public class MapGameController implements Initializable {
    public static MapData mapData;
    public static MoveChara chara;
    public GridPane mapGrid;
    public ImageView[] mapImageView;
    public static int StageRank;
    public boolean key;
    public Label floorLabel;
    public ImageView kiv;
    public HBox KeyImage;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
		StageRank = 0;
		Image ki = new Image("img/key.png");
		kiv = new ImageView();
		kiv.setImage(ki);
		KeyImage.getChildren().add(kiv);
		NewMapPrint();
		mapPrint(chara, mapData);
    }

    public void NewMapPrint(){
		mapData = new MapData(21,15);
		if(StageRank != 0){
			Party pp = chara.getPlayerParty();
			chara = new MoveChara(1,1,mapData);
			chara.setPlayerParty(pp);
		} else {
			chara = new MoveChara(1,1,mapData);
		}
		StageRank++;
		if(StageRank == 6) {
			GameClear();
		}
		floorLabel.setText(StageRank + "階");
		KeyImage.setVisible(false);
		mapImageView = new ImageView[mapData.getHeight()*mapData.getWidth()];
		for(int y=0; y<mapData.getHeight(); y++){
			for(int x=0; x<mapData.getWidth(); x++){
				int index = y*mapData.getWidth() + x;
				mapImageView[index] =new ImageView();
				mapGrid.add(mapImageView[index], x, y);
			}
		}
		key = false;
    }

    public void GameClear(){
		try{
			MapGame.gameclear = new Stage();
			MapGame.gameclear.initOwner(MapGame.stage);
			MapGame.gameclear.setTitle("MENU");
			Pane myPane_four = (Pane)FXMLLoader.load(getClass().getResource("GameClear.fxml"));
			Scene myScene4 = new Scene(myPane_four,693,592);
			MapGame.gameclear.setScene(myScene4);
			MapGame.gameclear.show();
		} catch(Exception e){
		}
    }
    
    public void mapPrint(MoveChara c, MapData m){
		for(int y=0; y<mapData.getHeight(); y++){
			for(int x=0; x<mapData.getWidth(); x++){
				int index = y*mapData.getWidth() + x;
				if (x==c.getPosX() && y==c.getPosY()){
					mapImageView[index].setImage(c.getImage());
				} else {
					mapImageView[index].setImage(m.getImage(x,y));
				}
			}
		}	
    }
    
    public void MenuButtonAction(ActionEvent event) {
		try{
			MapGame.menu = new Stage();
			MapGame.menu.initOwner(MapGame.stage);
			MapGame.menu.setTitle("MENU");
			Pane myPane_trd = (Pane)FXMLLoader.load(getClass().getResource("GameMenu.fxml"));
			Scene myScene3 = new Scene(myPane_trd,693,592);
			MapGame.menu.setScene(myScene3);
			MapGame.menu.show();
		} catch(Exception e){
			System.err.println(e);
		}
    }

    public void keyAction(KeyEvent event){
		KeyCode key = event.getCode();
		if (key == KeyCode.W){
			upButtonAction();
		}else if (key == KeyCode.S){
			downButtonAction();
		}else if (key == KeyCode.D){
			rightButtonAction();
		}else if (key == KeyCode.A){
			leftButtonAction();
		}
    }

    public void upButtonAction(){
		//System.out.println("UP");
		chara.setCharaDir(MoveChara.TYPE_UP);
		chara.move(0, -1);
		if(mapData.keyget(chara.getPosX(),chara.getPosY()) == true){
			key = true;
			KeyImage.setVisible(true);
		}
		if(mapData.goolDecision(chara.getPosX(),chara.getPosY()) == true && key == true){
			NewMapPrint();
		}
		mapPrint(chara, mapData);
    }
    public void upButtonAction(ActionEvent event) {
		upButtonAction();
    }
    
    public void downButtonAction(){
		//System.out.println("DOWN");
		chara.setCharaDir(MoveChara.TYPE_DOWN);
		chara.move(0, 1);
		if(mapData.keyget(chara.getPosX(),chara.getPosY()) == true){
			key = true;
			KeyImage.setVisible(true);
		}
		if(mapData.goolDecision(chara.getPosX(),chara.getPosY()) == true && key == true){
			NewMapPrint();
		}
		mapPrint(chara, mapData);
    }
    public void downButtonAction(ActionEvent event) {
		downButtonAction();
    }
    
    public void rightButtonAction(){
		//System.out.println("RIGHT");
		chara.setCharaDir(MoveChara.TYPE_RIGHT);
		chara.move( 1, 0);
		if(mapData.keyget(chara.getPosX(),chara.getPosY()) == true){
			key = true;
			KeyImage.setVisible(true);
		}
		if(mapData.goolDecision(chara.getPosX(),chara.getPosY()) == true && key == true){
			NewMapPrint();
		}
		mapPrint(chara, mapData);
    }
    public void rightButtonAction(ActionEvent event) {
		rightButtonAction();
    }
    
    public void leftButtonAction(){
		//System.out.println("LEFT");
		chara.setCharaDir(MoveChara.TYPE_LEFT);
		chara.move( -1, 0);
		if(mapData.keyget(chara.getPosX(),chara.getPosY()) == true){
			key = true;
			KeyImage.setVisible(true);
		}
		if(mapData.goolDecision(chara.getPosX(),chara.getPosY()) == true && key == true){
			NewMapPrint();
		}
		mapPrint(chara, mapData);
    }
    public void leftButtonAction(ActionEvent event) {
		leftButtonAction();
    }
}
