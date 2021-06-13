import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.VBox;
import javafx.scene.layout.Pane;

public class GameBattleController implements Initializable {
    public CharaStatus[] PlayerParty;
    public int PlayerCount;

    public CharaStatus[] EnemyParty;
    public int EnemyCount;

    public int NowPlayerTane;
    public int[] PlayerState;
    public MapEnemy NowEnemy;
    public int JoinFlag;
    public int GameOverFlag;
    public int[] NextLevelExp = {0,3,7,13,20,30,43,60,75,100};
    
    public Label player1;
    public Label player2;
    public Label player3;
    public Label player4;
    public Label player5;
    public Label player6;
    public Label[] players;
    
    public Label enemy1;
    public Label enemy2;
    public Label enemy3;
    public Label enemy4;
    public Label enemy5;
    public Label enemy6;
    public Label[] enemys;
    
    public Label BattleStatus;
    public Label PlayerNextAction;
    
    public Button AttackButton;
    public Button DefenseButton;
    public Button ChargeButton;
    public Button JoinButton;
    public Button EnemyButton;
    public Button OKButton;
    public Button[] PlayerButton;
    
    public VBox BattleLog;

    @Override
    public void initialize(URL url,ResourceBundle rb){
		System.out.println("AA");
		PlayerParty = MapGameController.chara.getPlayerParty().getPartyStatus();
		PlayerCount = MapGameController.chara.getPlayerParty().getCharaCount();
		int px = MapGameController.chara.getPosX();
		int py = MapGameController.chara.getPosY();
		int enemycount = MapGameController.mapData.getEnemyCount();
		MapEnemy[] en = MapGameController.mapData.getEnemy();
		for(int i=0;i<enemycount;i++){
			if(en[i].getPosX() == px && en[i].getPosY() == py){
				System.out.println(i + ":(" + px + " " + py + ")");
				NowEnemy = en[i];
				EnemyParty = en[i].getEnemyParty().getPartyStatus();
				EnemyCount = en[i].getEnemyParty().getCharaCount();
			}
		}
		System.out.println(EnemyParty[0].EnemyString());
		NowPlayerTane = 0;
		JoinFlag = 0;
		GameOverFlag = 0;
		NowEnemy.setPosX(0);
		NowEnemy.setPosY(0);
		PlayerState = new int[6];
		for(int i=0;i<6;i++){
			PlayerState[i] = 0;
		}
			
		players = new Label[6];
		players[0] = player1;
		players[1] = player2;
		players[2] = player3;
		players[3] = player4;
		players[4] = player5;
		players[5] = player6;
		
		enemys = new Label[6];
		enemys[0] = enemy1;
		enemys[1] = enemy2;
		enemys[2] = enemy3;
		enemys[3] = enemy4;
		enemys[4] = enemy5;
		enemys[5] = enemy6;
		
		PlayerButton = new Button[4];
		PlayerButton[0] = AttackButton;
		PlayerButton[1] = DefenseButton;
		PlayerButton[2] = ChargeButton;
		PlayerButton[3] = JoinButton;
		EnemyButton.setVisible(false);
		OKButton.setVisible(false);
		
		BattleStatus.setText("Player 考え中...");
		outputStatus();
    }

    public void outputStatus(){
		for(int i=0;i<PlayerCount;i++){
			players[i].setText(PlayerParty[i].PlayerString());
		}
		for(int i=0;i<EnemyCount;i++){
			enemys[i].setText(EnemyParty[i].EnemyString());
		}
		PlayerNextAction.setText(PlayerParty[NowPlayerTane].getName() + "のターン...");
    }

    public void AttackAction(ActionEvent event){
		int str = PlayerParty[NowPlayerTane].getStr().getCur();
		int enemyNum = 0;
		int hp = EnemyParty[enemyNum].getHp().getCur();
		int def = EnemyParty[enemyNum].getDef().getCur();
		int damage = Math.max(1,(int)(6*Math.random()+(str-def-3)));
		if(PlayerState[NowPlayerTane] == 2){
			damage = (int)(damage*2.5);
			PlayerState[NowPlayerTane] = 0;
		}
		int nhp = hp - damage;
		EnemyParty[enemyNum].getHp().setCur(nhp);
		BattleStatus.setText(PlayerParty[NowPlayerTane].getName() + "の攻撃！ " + EnemyParty[enemyNum].getName() + "に" + damage + "ダメージ");
		AddBattleLog(PlayerParty[NowPlayerTane].getName() + "の攻撃！ " + EnemyParty[enemyNum].getName() + "に" + damage + "ダメージ");

		TaneChange();
		outputStatus();
		int HpZeroCount = 0;
		for(int i=0;i<EnemyCount;i++){
			if(EnemyParty[i].getHp().getCur() <= 0){
				HpZeroCount++;
			}
		}
		if(HpZeroCount == EnemyCount){
			BattleStatus.setText("Playerの勝利！！！");
			for(int i=0;i<PlayerCount;i++){
				Param nexp = PlayerParty[i].getExp();
				nexp.setCur(nexp.getCur()+NowEnemy.getExp());
				PlayerParty[i].setExp(nexp);
				Param plevel = PlayerParty[i].getLevel();
				while(NextLevelExp[plevel.getCur()] <= PlayerParty[i].getExp().getCur()){
					plevel.setCur(plevel.getCur()+1);
					AddBattleLog(PlayerParty[i].getName() + "のレベルが上がった");
					StatusUp(i);
				}
			}
			outputStatus();
			PlayerNextAction.setText(NowEnemy.getExp() + "の経験値を得た");
			ButtonChange(false,false,true);
		}
    }

    public void StatusUp(int num){
		String str = "";
		int np = PlayerParty[num].getHp().getCurMax()+(int)(5*Math.random());
		str += "HP+" + (np-PlayerParty[num].getHp().getCurMax()) + ",";
		PlayerParty[num].setHp(new Param("HP",np,np));
		np = PlayerParty[num].getStr().getCurMax()+(int)(3*Math.random());
		str += "STR+" + (np-PlayerParty[num].getStr().getCurMax()) + ",";
		PlayerParty[num].setStr(new Param("STR",np,np));
		np = PlayerParty[num].getDef().getCurMax()+(int)(3*Math.random());
		str += "DEF+" + (np-PlayerParty[num].getDef().getCurMax()) + ",";	
		PlayerParty[num].setDef(new Param("DEF",np,np));
		np = PlayerParty[num].getLuk().getCurMax()+(int)(3*Math.random());
		str += "LUK+" + (np-PlayerParty[num].getLuk().getCurMax());
		PlayerParty[num].setLuk(new Param("LUK",np,np));
		AddBattleLog(str);
    }

    public void DefenseAction(ActionEvent event){
		PlayerState[NowPlayerTane] = 1;
		AddBattleLog(PlayerParty[NowPlayerTane].getName() + "が守りの態勢に入った");
		TaneChange();
		outputStatus();
    }

    public void ChargeAction(ActionEvent event){
		PlayerState[NowPlayerTane] = 2;
		AddBattleLog(PlayerParty[NowPlayerTane].getName() + "は力を溜めている");
		TaneChange();
		outputStatus();
    }

    public void JoinAction(ActionEvent event){
		if(PlayerCount == 6){
			BattleStatus.setText("これ以上は仲間にできない！！！");    
		} else {
			int Pluk = PlayerParty[NowPlayerTane].getLuk().getCur();
			int enemyNum = 0;
			int Eluk = EnemyParty[enemyNum].getLuk().getCur();
			int Ehp = EnemyParty[enemyNum].getHp().getCur();
			int EhpMax = EnemyParty[enemyNum].getHp().getCurMax();
			int point = 10*(Ehp/EhpMax)-(int)(5*Math.random())-(Pluk-Eluk);
			if(point <= 0){
				Party pp = MapGameController.chara.getPlayerParty();
				pp.addChara(EnemyParty[enemyNum]);
				AddBattleLog(EnemyParty[enemyNum].getName() + "が仲間になった");
				BattleStatus.setText(EnemyParty[enemyNum].getName() + "が仲間になった");
				ButtonChange(false,false,true);
			} else {
				AddBattleLog(EnemyParty[enemyNum].getName() + "を仲間にするのに失敗した");
				BattleStatus.setText(EnemyParty[enemyNum].getName() + "を仲間にするのに失敗した");
				TaneChange();
				JoinFlag = 1;
			}
		}
    }
    
    public void EnemyAction(ActionEvent event){
		int str = EnemyParty[0].getStr().getCur();
		int enemyNum = (int)(PlayerCount*Math.random());
		int hp = PlayerParty[enemyNum].getHp().getCur();
		int def = PlayerParty[enemyNum].getDef().getCur();
		int damage = Math.max(1,(int)(6*Math.random()+(str-def-3)));
		if(PlayerState[enemyNum] == 1){
			damage = (int)(damage/2.5);
			PlayerState[enemyNum] = 0;
		}
		int nhp = hp - damage;
		PlayerParty[enemyNum].getHp().setCur(nhp);
		BattleStatus.setText(EnemyParty[0].getName() + "の攻撃！ " + PlayerParty[enemyNum].getName() + "に" + damage + "ダメージ");
		AddBattleLog(EnemyParty[0].getName() + "の攻撃！ " + PlayerParty[enemyNum].getName() + "に" + damage + "ダメージ");
		DefenseUnlock();
		outputStatus();
		if(nhp <= 0){
			if(enemyNum == 0){
				PlayerNextAction.setText(PlayerParty[enemyNum].getName() + "が倒された！ Game Over...");
				GameOverFlag = 1;
				ButtonChange(false,false,true);
			} else {
				AddBattleLog(PlayerParty[enemyNum].getName() + "が倒された！");
				players[PlayerCount-1].setVisible(false);
				PlayerCount--;
				MapGameController.chara.getPlayerParty().removeChara(enemyNum);
				PlayerParty = MapGameController.chara.getPlayerParty().getPartyStatus();
				ButtonChange(true,false,false);
				outputStatus();
			}
		} else {
			ButtonChange(true,false,false);
		}
    }

    public void OKAction(ActionEvent event){
		if(GameOverFlag == 1){
			System.exit(1);
		} else {
			MapGame.battle.close();
		}
    }

    public void TaneChange(){
		NowPlayerTane++;
		if(NowPlayerTane == PlayerCount){
			NowPlayerTane = 0;
			ButtonChange(false,true,false);
			PlayerNextAction.setText("Enemyの攻撃が来る...");
		}
    }

    public void DefenseUnlock(){
		for(int i=0;i<PlayerCount;i++){
			if(PlayerState[i] == 1){
			PlayerState[i] = 0;
			}
		}
    }
    
    public void ButtonChange(boolean player,boolean enemy,boolean OK){
		for(int i=0;i<4;i++){
			PlayerButton[i].setVisible(player);
		}
		if(JoinFlag == 1) JoinButton.setVisible(false);
		EnemyButton.setVisible(enemy);
		OKButton.setVisible(OK);
    }

    public void AddBattleLog(String str){
		Label AddLog = new Label();
		AddLog.setText(str);
		BattleLog.getChildren().add(AddLog);
    }
}
