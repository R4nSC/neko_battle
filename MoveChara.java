import javafx.scene.image.Image;

/* プレイヤーキャラのクラス */
public class MoveChara {
    public static final int TYPE_DOWN  = 0;
    public static final int TYPE_LEFT  = 1;
    public static final int TYPE_RIGHT = 2;
    public static final int TYPE_UP    = 3;

    private Party PlayerParty;
    
    private int posX;
    private int posY;

    private MapData mapData;
    
    private Image[] charaImage;
    private int count   = 0;
    private int diffx   = 1;
    private int charaDir;

    /* 0. 初期化(コンストラクタ) */
    MoveChara(int startX, int startY, MapData mapData){
		this.mapData = mapData;
		
		PlayerParty = new Party("cat",
					new Param("LEVEL",1,100),
					new Param("HP",15,50),
					new Param("STR",10,255),
					new Param("DEF",10,255),
					new Param("LUK",10,255),
					new Param("EXP",0,99999));
		
		charaImage = new Image[12];
		charaImage[0 * 3 + 0] = new Image("img/nekod1.png");
		charaImage[0 * 3 + 1] = new Image("img/nekod2.png");
		charaImage[0 * 3 + 2] = new Image("img/nekod3.png");
		charaImage[1 * 3 + 0] = new Image("img/nekol1.png");
		charaImage[1 * 3 + 1] = new Image("img/nekol2.png");
		charaImage[1 * 3 + 2] = new Image("img/nekol3.png");
		charaImage[2 * 3 + 0] = new Image("img/nekor1.png");
		charaImage[2 * 3 + 1] = new Image("img/nekor2.png");
		charaImage[2 * 3 + 2] = new Image("img/nekor3.png");
		charaImage[3 * 3 + 0] = new Image("img/nekou1.png");
		charaImage[3 * 3 + 1] = new Image("img/nekou2.png");
		charaImage[3 * 3 + 2] = new Image("img/nekou3.png");

		posX = startX;
		posY = startY;

		charaDir = TYPE_DOWN;
    }

    /* 1. 使用する画像のIndexを計算 */
    public int getIndex(){
		return charaDir * 3 + count;
    }

    /* 2. 使用する画像を決めるcountを調整する */
    public void changeCount(){
		count = count + diffx;
		if (count > 2) {
			count = 1;
			diffx = -1;
		} else if (count < 0){
			count = 1;
			diffx = 1;
		}
    }

    /* 3. posXを返すゲッタ */
    public int getPosX(){
		return posX;
    }

    /* 4. posYを返すゲッタ */
    public int getPosY(){
		return posY;
    }

    public Party getPlayerParty(){
		return PlayerParty;
    }

    public void setPlayerParty(Party pp){
		PlayerParty = pp;
    }

    /* 5. プレイヤーが向いている方向の変更 */
    public void setCharaDir(int cd){
	charaDir = cd;
    }

    /* 6. プレイヤーが動ける場所なのか判定 */
    public boolean canMove(int dx, int dy){
		if (mapData.getMap(posX+dx, posY+dy) == MapData.TYPE_WALL){
			return false;
		} else if (mapData.getMap(posX+dx, posY+dy) == MapData.TYPE_NONE){
			return true;
		} else if (mapData.getMap(posX+dx, posY+dy) == MapData.TYPE_KEY){
			return true;
		} else if (mapData.getMap(posX+dx, posY+dy) == MapData.TYPE_BOSS){
			return true;
		} else if (mapData.getMap(posX+dx, posY+dy) == MapData.TYPE_GOAL){
			return true;
		}
		return false;
    }

    /* 7. プレイヤーを動かす */
    public boolean move(int dx, int dy){
		if (canMove(dx,dy)){
			posX += dx;
			posY += dy;
			return true;
		}else {
			return false;
		}
    }

    /* 8. 次に表示するプレイヤーの画像を渡す */
    public Image getImage(){
		changeCount();
		return charaImage[getIndex()];
    }
}

