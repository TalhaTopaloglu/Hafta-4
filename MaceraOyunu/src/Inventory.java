
public class Inventory {
    private Weapon weapon;
    private Armor armor;
    private boolean CaveAward = false;
    private boolean ForestAward = false;
    private boolean RiverAward = false;
    private boolean MineAward = false;


    public Inventory(){
        this.weapon = new Weapon("Yumruk",-1,0,0);
        this.armor = new Armor(-1,"Paçavra",0,0);
    }

    public Weapon getWeapon() {
        return weapon;
    }

    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }

    public Armor getArmor() {
        return armor;
    }

    public void setArmor(Armor armor) {
        this.armor = armor;
    }

    public boolean isCaveAward() {
        return CaveAward;
    }

    public void setCaveAward(boolean caveAward) {
        CaveAward = caveAward;
    }

    public boolean isForestAward() {
        return ForestAward;
    }

    public void setForestAward(boolean forestAward) {
        ForestAward = forestAward;
    }

    public boolean isRiverAward() {
        return RiverAward;
    }

    public void setRiverAward(boolean riverAward) {
        RiverAward = riverAward;
    }

    public boolean isMineAward() {
        return MineAward;
    }

    public void setMineAward(boolean mineAward) {
        MineAward = mineAward;
    }
}
