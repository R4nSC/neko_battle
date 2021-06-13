public class Party {
    private CharaStatus[] partyStatus;
    private int CharaCount;

    Party(String name,Param le,Param hp,Param st,Param de,Param lu,Param ex){
        partyStatus = new CharaStatus[6];
        partyStatus[0] = new CharaStatus(name,le,hp,st,de,lu,ex);
        CharaCount = 1;
    }

    public CharaStatus[] getPartyStatus(){
	    return partyStatus;
    }

    public int getCharaCount(){
	    return CharaCount;
    }

    public void setPartyStatus(CharaStatus[] ps){
	    partyStatus = ps;
    }

    public void setCharaCount(int cc){
	    CharaCount = cc;
    }
    
    public void addChara(CharaStatus adStatus) {
        partyStatus[CharaCount] = adStatus;
        CharaCount++;
    }

    public void removeChara(int num) {
        for(int i=num;i<CharaCount;i++){
            partyStatus[i] = partyStatus[i+1];
        }
        CharaCount--;
    }
}
