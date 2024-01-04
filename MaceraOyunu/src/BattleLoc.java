import java.util.Random;

public abstract class BattleLoc extends Location {

    private Obstacle obstacle;
    private String award;
    private int maxObstacle;

    public BattleLoc(Player player, String name, Obstacle obstacle, String award, int maxObstacle) {
        super(player, name);
        this.obstacle = obstacle;
        this.award = award;
        this.maxObstacle = maxObstacle;
    }

    @Override
    public boolean onLocation() {

        int obsNumber = this.randomObstacleNumber();
        System.out.println("Şuan buradasınız : " + this.getName());
        System.out.println("Dikkatli ol burada " + obsNumber + " tane " + this.getObstacle().getName() + " yaşıyor");
        System.out.print("<S>avaş veya <K>aç : ");
        String selectCase = input.nextLine();
        selectCase = selectCase.toUpperCase();
        if (selectCase.equals("S") && combat(obsNumber)) {
            System.out.println(this.getName() + " tüm düşmanları yendiniz.");
            isAwardInInventory();
            return true;
        }
        if (this.getPlayer().getHealth() <= 0) {
            System.out.println("Öldünüz.");
            return false;
        }

        return true;
    }

    public void isAwardInInventory() {
        if (this.getName().equals(getName())) {

            if (this.getName().equals("Mağara")) {
                getPlayer().getInventory().setCaveAward(true);
                System.out.println("-- --- --- -- -- --- --- ---- -->  " + getPlayer().getInventory().isCaveAward());
                System.out.println("--------------Envantere eklenen-------------- : " + this.getAward());
            }
            if (this.getName().equals("Orman")) {
                getPlayer().getInventory().setForestAward(true);
                System.out.println("-- --- --- -- -- --- --- ---- -->  " + getPlayer().getInventory().isForestAward());
                System.out.println("--------------Envantere eklenen-------------- : " + this.getAward());
            }
            if (this.getName().equals("Nehir")) {
                getPlayer().getInventory().setRiverAward(true);
                System.out.println("-- --- --- -- -- --- --- ---- -->  " + getPlayer().getInventory().isRiverAward());
                System.out.println("--------------Envantere eklenen-------------- : " + this.getAward());
            }
            if(this.getName().equals("Maden")){
                getPlayer().getInventory().setMineAward(true);
                mineAvard();
            }
        }
    }

        // ödüllerin şansa göre gelme durumu
    public void mineAvard(){
        Random r = new Random();
        int awardChance = r.nextInt(100)+1;
        if(awardChance <= 15){
            weaponAwardChance();
        }
        else if(awardChance <= 30){
            armorAwardChance();
        }
        else if(awardChance <=55) {
            moneyAwardChance();
        }else{
            System.out.println("Maalesef Bir Şey Kazanamadınız");
        }
    }
        // Zırhlara özel metot
    public void armorAwardChance(){
        Random r = new Random();
        int awardChance = r.nextInt(100)+1;
        if(awardChance <= 20){
            getPlayer().getInventory().setArmor(Armor.getArmorObjById(3));
            System.out.println("Ağır Zırh Kazandınız");
        }
        else if(awardChance <=50){
            getPlayer().getInventory().setArmor(Armor.getArmorObjById(2));
            System.out.println("Orta Zırh kazandınız");
        }
        else{
            getPlayer().getInventory().setArmor(Armor.getArmorObjById(1));
            System.out.println("Hafif Zırh Kazandınız");
        }
    }
    // Silahlara özel metot
    public void weaponAwardChance(){
        Random r = new Random();
        int awardChance = r.nextInt(100)+1;
        if(awardChance <= 20){
            getPlayer().getInventory().setWeapon(Weapon.getWeaponObjById(3));
            System.out.println("Tüfek Kazandınız");
        }
        else if(awardChance <=50){
            getPlayer().getInventory().setWeapon(Weapon.getWeaponObjById(2));
            System.out.println("Kılıç Kazanadınız");
        }
        else{
            getPlayer().getInventory().setWeapon(Weapon.getWeaponObjById(1));
            System.out.println("Tabanca Kazandınız");
        }
    }
    // para kazanmaya özel metot
    public void moneyAwardChance(){
        Random z = new Random();
        int moneyChance = z.nextInt(100)+1;

        if(moneyChance <= 20){
            getPlayer().setMoney(getPlayer().getMoney() + 10);
            System.out.println("10 para kazandınız.");
        }

        else if(moneyChance <=50){
            getPlayer().setMoney(getPlayer().getMoney() + 5);
            System.out.println("5 para kazandınız.");

        }
        else{
            getPlayer().setMoney(getPlayer().getMoney() + 1);
            System.out.println("1 para kazandınız.");

        }
    }

    // Savaş metodu sadeleştirildi.
    public boolean combat(int obsNumber) {
        Random r = new Random();
        int firstHitChanceNumber = r.nextInt(2);
        System.out.println("ilk vuruş yapma olasılığınız %50");

        if (firstHitChanceNumber == 0) {
            return playerIsFirst(obsNumber);
        } else {
            return obstacleIsFirst(obsNumber);
        }
    }

    // Oyuncunun ilk vurduğu metot
    public boolean playerIsFirst(int obsNumber) {
        System.out.println("******************");
        System.out.println("İlk Oyuncu Vurdu.");
        System.out.println("******************");

        for (int i = 1; i <= obsNumber; i++) {
            this.getObstacle().setHealth(this.getObstacle().getOrginalHealth());
            playerStats();
            obstacleStats(i);

            while (this.getPlayer().getHealth() > 0 && this.getObstacle().getHealth() > 0) {
                // buraya da olabilir
                System.out.print("<V>ur veya <K>aç : ");
                String selectCombat = input.nextLine().toUpperCase();
                if (selectCombat.equals("V")) {
                    System.out.println("Siz Vurdunuz !");
                    this.getObstacle().setHealth(this.getObstacle().getHealth() - this.getPlayer().getTotalDamage()); // Canavarın Canı
                    afterHit();
                    if (this.getObstacle().getHealth() > 0) {
                        System.out.println();
                        System.out.println("Canavar Size Vurdu");
                        int obstacleDamage = this.getObstacle().getDamage() - this.getPlayer().getInventory().getArmor().getBlock();
                        if (obstacleDamage <= 0) {
                            obstacleDamage = 0;
                            System.out.println("Engellendi");
                        }
                        this.getPlayer().setHealth(this.getPlayer().getHealth() - obstacleDamage); // Oyuncunun Canı

                        afterHit();
                    }
                } else {
                    return false;
                }
            }
            if (this.getObstacle().getHealth() < this.getPlayer().getHealth()) {
                System.out.println("Düşmanı Yendiniz");
                System.out.println(this.getObstacle().getAward() + " para kazandınız.");
                this.getPlayer().setMoney(this.getPlayer().getMoney() + this.getObstacle().getAward());
                System.out.println("Güncel paranız : " + this.getPlayer().getMoney());
            } else {
                return false;
            }
        }
        return true;
    }

    // Canavarın  ilk vurduğu metot
    public boolean obstacleIsFirst(int obsNumber) {
        System.out.println("******************");
        System.out.println("İlk Canavar Vurdu.");
        System.out.println("******************");

        for (int i = 1; i <= obsNumber; i++) {
            this.getObstacle().setHealth(this.getObstacle().getOrginalHealth());
            playerStats();
            obstacleStats(i);

            while (this.getPlayer().getHealth() > 0 && this.getObstacle().getHealth() > 0) {
                if (this.getObstacle().getHealth() > 0) {
                    System.out.println();
                    System.out.println("Canavar Size Vurdu");
                    int obstacleDamage = this.getObstacle().getDamage() - this.getPlayer().getInventory().getArmor().getBlock();
                    if (obstacleDamage <= 0) {
                        obstacleDamage = 0;
                        System.out.println("Engellendi");
                    }
                    this.getPlayer().setHealth(this.getPlayer().getHealth() - obstacleDamage); // Oyuncunun Canı
                    afterHit();
                }
                if (getPlayer().getHealth() <= 0) {
                    return false;
                }
                System.out.print("<V>ur veya <K>aç : ");
                String selectCombat = input.nextLine().toUpperCase();
                if (selectCombat.equals("V")) {
                    System.out.println("Siz Vurdunuz !");
                    this.getObstacle().setHealth(this.getObstacle().getHealth() - this.getPlayer().getTotalDamage()); // Canavarın Canı
                    afterHit();
                } else {
                    return false;
                }
            }
            if (this.getObstacle().getHealth() < this.getPlayer().getHealth()) {
                System.out.println("Düşmanı Yendiniz");
                System.out.println(this.getObstacle().getAward() + " para kazandınız.");
                this.getPlayer().setMoney(this.getPlayer().getMoney() + this.getObstacle().getAward());
                System.out.println("Güncel paranız : " + this.getPlayer().getMoney());
            } else {
                return false;
            }
        }
        return true;
    }

    public void afterHit() {
        System.out.println("Canınız : " + this.getPlayer().getHealth());
        System.out.println(this.getObstacle().getName() + " Canı : " + this.getObstacle().getHealth());
        System.out.println();
    }

    public void playerStats() {
        System.out.println("Oyuncu değerleri");
        System.out.println("---------------------");
        System.out.println("Sağlık : " + this.getPlayer().getHealth());
        System.out.println("Silah : " + this.getPlayer().getInventory().getWeapon().getName());
        System.out.println("Hasar : " + this.getPlayer().getTotalDamage());
        System.out.println("Zırh : " + this.getPlayer().getInventory().getArmor().getName());
        System.out.println("Blokalma : " + this.getPlayer().getInventory().getArmor().getBlock());
        System.out.println("Para : " + this.getPlayer().getMoney());
        System.out.println();

    }

    public void obstacleStats(int i) {
        System.out.println(i + ". " + this.getObstacle().getName() + " Değerleri");
        System.out.println("---------------------");
        System.out.println("Sağlık : " + this.getObstacle().getHealth());
        System.out.println("Hasar : " + this.getObstacle().getDamage());
        System.out.println("Ödülü : " + this.getObstacle().getAward());
        System.out.println();
    }

    public int randomObstacleNumber() {
        Random r = new Random();
        return r.nextInt(this.getMaxObstacle()) + 1;
    }

    public Obstacle getObstacle() {
        return obstacle;
    }

    public void setObstacle(Obstacle obstacle) {
        this.obstacle = obstacle;
    }

    public String getAward() {
        return award;
    }

    public void setAward(String award) {
        this.award = award;
    }

    public int getMaxObstacle() {
        return maxObstacle;
    }

    public void setMaxObstacle(int maxObstacle) {
        this.maxObstacle = maxObstacle;
    }


}
