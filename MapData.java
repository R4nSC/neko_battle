import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.image.Image;

/* 生成されたマップのクラス */
public class MapData {
    public static final int TYPE_WALL   = 1;
    public static final int TYPE_NONE   = 0;
    public static final int TYPE_KEY = 2;
    public static final int TYPE_BOSS = 3;
    public static final int TYPE_GOAL = 4;
    public static final int TYPE_OTHERS = 5;
    public Image[] mapImage;
    private int[] map;
    private int[] map2image;
    private int width;
    private int height;
    private MapEnemy[] enemy;
    private EnemyCollection enemyList;
    private int Enemycount;
    private int[] EnemyGetExp = {3,5,7,10,14,20,30};

    /* 0. 初期化(コンストラクタ) */
    MapData(int x, int y){
		mapImage = new Image[6];
		mapImage[TYPE_NONE] = new Image("img/SPACE.png");
		mapImage[TYPE_WALL] = new Image("img/WALL.png");
		mapImage[TYPE_KEY] = new Image("img/b-nekod2.png");
		mapImage[TYPE_BOSS] = new Image("img/boss.png");
		mapImage[TYPE_GOAL] = new Image("img/goal.png");
		mapImage[5] = new Image("img/key.png");
		width  = x;
		height = y;
		map = new int[y*x];
		fillMap(MapData.TYPE_WALL);
		digMap(1, 3);
		Enemycount = 3;
		enemy = new MapEnemy[Enemycount];
		enemyList = new EnemyCollection();
		KeySet();
		setGoal(19,13);
		printMap();
    }

    /* 1. 縦の長さのゲッタ */
    public int getHeight(){
		return height;
    }

    /* 2. 横の長さのゲッタ */
    public int getWidth(){
		return width;
    }

    public int getEnemyCount(){
		return Enemycount;
    }

    public MapEnemy[] getEnemy(){
		return enemy;
    }

    /* 3. マス目の番号を求める(x座標,y座標より判定) */
    public int toIndex(int x, int y){
		return x + y * width;
    }

    /* 4. マップの今いるマスの状態を判定 */
    public int getMap(int x, int y) {
		/* 4.1 マップの範囲外だったら例外処理 */
		if (x < 0 || width <= x || y < 0 || height <= y) {
			return -1;
		}
		return map[toIndex(x,y)];
    }

    /* 5. マップの今いるマスに適した画像を入手 */
    public Image getImage(int x, int y) {
		return mapImage[getMap(x,y)];
    }

    /* 6. あるマスにマップの状態を格納する */
    public void setMap(int x, int y, int type){
		if (x < 1 || width <= x-1 || y < 1 || height <= y-1) {
			return;
		}
		map[toIndex(x,y)] = type;
    }

    /* 7. マップのすべてのマスを任意の１つの状態にする */
    public void fillMap(int type){
		for (int y=0; y<height; y++){
			for (int x=0; x<width; x++){
				map[toIndex(x,y)] = type;
			}
		}
    }

    /* 8. マップの道を作成する */
    public void digMap(int x, int y){
		setMap(x, y, MapData.TYPE_NONE);
		int[][] dl = {{0,1},{0,-1},{-1,0},{1,0}};
		int[] tmp;

		for (int i=0; i<dl.length; i++) {
			int r = (int)(Math.random()*dl.length);
			tmp = dl[i];
			dl[i] = dl[r];
			dl[r] = tmp;
		}

		for (int i=0; i<dl.length; i++){
			int dx = dl[i][0];
			int dy = dl[i][1];
			if (getMap(x+dx*2, y+dy*2) == MapData.TYPE_WALL){
				setMap(x+dx, y+dy, MapData.TYPE_NONE);
				digMap(x+dx*2, y+dy*2);
			}
		}
    }

    public void KeySet(){
        int key = 0;
        for(int i=0;i<=21;i++){
			for(int j=5;j<=15;j++){
				if(getMap(i, j) == MapData.TYPE_NONE &&
					getMap(i-1, j) == MapData.TYPE_WALL &&
					getMap(i+1, j) == MapData.TYPE_WALL &&
					getMap(i, j+1) == MapData.TYPE_WALL && key != Enemycount){
					int floorRank = MapGameController.StageRank;
					if(key == 0){
						setMap(i,j,MapData.TYPE_BOSS);
						Party enemyParty = enemyList.getNewEnemy(floorRank+1);
						enemy[key] = new MapEnemy(i,j,enemyParty,EnemyGetExp[floorRank+1],true);
					} else {
						setMap(i, j, MapData.TYPE_KEY);
						Party enemyParty = enemyList.getNewEnemy(floorRank);
						enemy[key] = new MapEnemy(i,j,enemyParty,EnemyGetExp[floorRank],false);
					}
					key++;
				}
				else if(getMap(i, j) == MapData.TYPE_NONE &&
					getMap(i-1, j) == MapData.TYPE_WALL &&
					getMap(i, j-1) == MapData.TYPE_WALL &&
					getMap(i, j+1) == MapData.TYPE_WALL && key != Enemycount){
					int floorRank = MapGameController.StageRank;
					if(key == 0){
						setMap(i,j,MapData.TYPE_BOSS);
						Party enemyParty = enemyList.getNewEnemy(floorRank+1);
						enemy[key] = new MapEnemy(i,j,enemyParty,EnemyGetExp[floorRank+1],true);
					} else {
						setMap(i, j, MapData.TYPE_KEY);
						Party enemyParty = enemyList.getNewEnemy(floorRank);
						enemy[key] = new MapEnemy(i,j,enemyParty,EnemyGetExp[floorRank],false);
					}
					key++;
				}
				else if(getMap(i, j) == MapData.TYPE_NONE &&
					getMap(i, j-1) == MapData.TYPE_WALL &&
					getMap(i+1, j) == MapData.TYPE_WALL &&
					getMap(i, j+1) == MapData.TYPE_WALL && key != Enemycount){
					int floorRank = MapGameController.StageRank;
					if(key == 0){
						setMap(i,j,MapData.TYPE_BOSS);
						Party enemyParty = enemyList.getNewEnemy(floorRank+1);
						enemy[key] = new MapEnemy(i,j,enemyParty,EnemyGetExp[floorRank+1],true);
					} else {
						setMap(i, j, MapData.TYPE_KEY);
						Party enemyParty = enemyList.getNewEnemy(floorRank);
						enemy[key] = new MapEnemy(i,j,enemyParty,EnemyGetExp[floorRank],false);
					}
					key++;
				}
				if(getMap(i, j) == MapData.TYPE_NONE &&
					getMap(i-1, j) == MapData.TYPE_WALL &&
					getMap(i+1, j) == MapData.TYPE_WALL &&
					getMap(i, j-1) == MapData.TYPE_WALL && key != Enemycount){
					int floorRank = MapGameController.StageRank;
					if(key == 0){
						setMap(i,j,MapData.TYPE_BOSS);
						Party enemyParty = enemyList.getNewEnemy(floorRank+1);
						enemy[key] = new MapEnemy(i,j,enemyParty,EnemyGetExp[floorRank+1],true);
					} else {
						setMap(i, j, MapData.TYPE_KEY);
						Party enemyParty = enemyList.getNewEnemy(floorRank);
						enemy[key] = new MapEnemy(i,j,enemyParty,EnemyGetExp[floorRank],false);
					}
					key++;
				}
			}
        }
    }
    
    /* 9. マップの状態を出力する */
    public void printMap(){
		for (int y=0; y<height; y++){
			for (int x=0; x<width; x++){
				if (getMap(x,y) == MapData.TYPE_KEY){
					System.out.print("kk");
				} else if (getMap(x,y) == MapData.TYPE_WALL){
					System.out.print("++");
				}else{
					System.out.print("  ");
				}
			}
			System.out.print("\n");
		}
    }
    
    public boolean keyget(int nowx,int nowy){
		for(int i=0;i<Enemycount;i++){
			if(nowx == enemy[i].getPosX() && nowy == enemy[i].getPosY()){
				try{
					MapGame.battle = new Stage();
					MapGame.battle.initOwner(MapGame.stage);
					MapGame.battle.setTitle("BATTLE");
					Pane myPane_sec = (Pane)FXMLLoader.load(getClass().getResource("GameBattle.fxml"));
					Scene myScene2 = new Scene(myPane_sec,693,592);
					MapGame.battle.setScene(myScene2);
					MapGame.battle.show();
				} catch(Exception e){
					System.err.println(e);
				}
				setMap(nowx, nowy, MapData.TYPE_NONE);
				if(enemy[i].getKeyHave() == true){
					//System.out.print("KEY GET\n");
					return true;
				} else {
					return false;
				}
			}
		}
		return false;
    }

    public void setGoal(int x,int y){
		setMap(x, y, MapData.TYPE_GOAL);
    }
    
    /* 10. ゴール判定 ゴール座標を(19,13)として処理 */
    public boolean goolDecision(int nowx,int nowy){
	/* 10.1 ゴールだった場合 */
		if(nowx == 19 && nowy == 13){
			return true;
		} else { 
			return false;
		}
    }
}
