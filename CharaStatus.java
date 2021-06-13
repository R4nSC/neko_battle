public class CharaStatus {
    private static int count = 1;
    private int id;
    private String _name;
    private Param _level;
    private Param _hp;
    private Param _str;
    private Param _def;
    private Param _spd;
    private Param _luk;
    private Param _exp;

    CharaStatus(String n, Param v, Param h, Param st, Param d, Param l,Param e) {
        this._name  = n;
        this._level = v;
        this._hp    = h;
        this._str   = st;
        this._def   = d;
        this._luk   = l;
        this._exp   = e;
        this.id     = count;
        count++;
    }

    public String getName(){ return this._name; }
    public Param getLevel(){ return this._level; }
    public Param getHp()   { return this._hp; }
    public Param getStr()  { return this._str; }
    public Param getDef()  { return this._def; }
    public Param getLuk()  { return this._luk; }
    public int getId()     { return this.id; }
    public Param getExp()     { return this._exp; }
    public static int getCount() { return count; }

    public void setName(String name) { this._name = name; }
    public void setLevel(Param level){ this._level = level; }
    public void setHp(Param hp)      { this._hp = hp; }
    public void setStr(Param str)    { this._str = str; }
    public void setDef(Param def)    { this._def = def; }
    public void setLuk(Param luk)    { this._luk = luk; }
    public void setExp(Param exp)    { this._exp = exp; }

    public String toString(){
        return "   " + getLevel() + "\n"
            + "   " + getHp().HptoString() + "\n"
            + "   " + getStr() + "\n"
            + "   " + getDef() + "\n"
            + "   " + getLuk() + "\n"
            + "   " + getExp();
    }

    public String EnemyString(){
        return getName() + ":\n"
            + "   " + getLevel() + "\n"
            + "   " + getHp() + "\n"
            + "   " + getStr() + "\n"
            + "   " + getDef() + "\n"
            + "   " + getLuk();
    }

    public String PlayerString(){
        return getName() + ":\n"
            + "   " + getLevel() + "\n"
            + "   " + getHp() + "\n"
            + "   " + getStr() + "\n"
            + "   " + getDef() + "\n"
            + "   " + getLuk() + "\n"
            + "   " + getExp();
    }
}
