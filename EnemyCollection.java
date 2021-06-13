import java.util.ArrayList;

public class EnemyCollection {
    private ArrayList<Party> EnemyList;

    EnemyCollection(){
		EnemyList = new ArrayList<Party>();
		addEnemy(new Party("mouse",
				new Param("LEVEL",1,100),
				new Param("HP",10,50),
				new Param("STR",8,255),
				new Param("DEF",7,255),
				new Param("LUK",8,255),
				new Param("EXP",0,99999)));
		addEnemy(new Party("snake",
				new Param("LEVEL",2,100),
				new Param("HP",16,50),
				new Param("STR",13,255),
				new Param("DEF",8,255),
				new Param("LUK",10,255),
				new Param("EXP",3,99999)));
		addEnemy(new Party("fox",
				new Param("LEVEL",3,100),
				new Param("HP",20,50),
				new Param("STR",16,255),
				new Param("DEF",14,255),
				new Param("LUK",10,255),
				new Param("EXP",7,99999)));
		addEnemy(new Party("hawk",
				new Param("LEVEL",4,100),
				new Param("HP",25,50),
				new Param("STR",25,255),
				new Param("DEF",10,255),
				new Param("LUK",20,255),
				new Param("EXP",13,99999)));
		addEnemy(new Party("bear",
				new Param("LEVEL",5,100),
				new Param("HP",50,50),
				new Param("STR",30,255),
				new Param("DEF",25,255),
				new Param("LUK",15,255),
				new Param("EXP",20,99999)));
		addEnemy(new Party("lion",
				new Param("LEVEL",7,100),
				new Param("HP",100,100),
				new Param("STR",40,255),
				new Param("DEF",26,255),
				new Param("LUK",30,255),
				new Param("EXP",43,99999)));
		addEnemy(new Party("human",
				new Param("LEVEL",10,100),
				new Param("HP",50,50),
				new Param("STR",50,255),
				new Param("DEF",50,255),
				new Param("LUK",50,255),
				new Param("EXP",100,99999)));
    }

    public void addEnemy(Party a){
		EnemyList.add(a);
    }
    public Party getNewEnemy(int num) {
        Party cloneEnemy = EnemyList.get(num);
        CharaStatus[] ceStatus = cloneEnemy.getPartyStatus();
        Party newEnemy = new Party (ceStatus[0].getName(),
                                    new Param(ceStatus[0].getLevel().getName(),
                                              ceStatus[0].getLevel().getCurMax(),
                                              ceStatus[0].getLevel().getMax()),
                                    new Param(ceStatus[0].getHp().getName(),
                                              ceStatus[0].getHp().getCurMax(),
					      					  ceStatus[0].getHp().getMax()),
                                    new Param(ceStatus[0].getStr().getName(),
                                              ceStatus[0].getStr().getCurMax(),
                                              ceStatus[0].getStr().getMax()),
                                    new Param(ceStatus[0].getDef().getName(),
                                              ceStatus[0].getDef().getCurMax(),
                                              ceStatus[0].getDef().getMax()),
                                    new Param(ceStatus[0].getLuk().getName(),
                                              ceStatus[0].getLuk().getCurMax(),
                                              ceStatus[0].getLuk().getMax()),
                                    new Param(ceStatus[0].getExp().getName(),
                                              ceStatus[0].getExp().getCurMax(),
                                              ceStatus[0].getExp().getMax()));
	
        return newEnemy;
    }
}
