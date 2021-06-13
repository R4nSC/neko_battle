import javafx.scene.image.Image;

public class MapEnemy {
    private Party EnemyParty;

    private int posX;
    private int posY;

    private int GetExp;
    private boolean KeyHave;

    MapEnemy(int startX,int startY,Party enemy,int exp,boolean key){
        EnemyParty = enemy;
        
        posX = startX;
        posY = startY;

        GetExp = exp;

        KeyHave = key;
    }

    public int getPosX(){
	    return posX;
    }

    public int getPosY(){
	    return posY;
    }

    public void setPosX(int x){
	    posX = x;
    }

    public void setPosY(int y){
	    posY = y;
    }
    
    public Party getEnemyParty(){
	    return EnemyParty;
    }

    public int getExp(){
	    return GetExp;
    }

    public boolean getKeyHave(){
	    return KeyHave;
    }
}
		    
