public class ToolStore extends NormalLoc {
    public ToolStore(Player player) {
        super(player, "Mağaza");
    }

    @Override
    public boolean onLocation() {
        System.out.println("---------- Mağazaya Hoşgeldiniz ----------");
        boolean showMenu = true;
        while (showMenu) {
            System.out.println("1- Silahlar ");
            System.out.println("2- Zırhlar ");
            System.out.println("3 -Çıkış Yap ");
            System.out.print("Seçiminiz : ");
            int selectCase = Location.input.nextInt();
            while (selectCase < 0 || selectCase > 3) {
                System.out.print("Geçersiz değer tekrar giriniz : ");
                selectCase = input.nextInt();

            }
            switch (selectCase) {
                case 1:
                    printWeapon();
                    buyWeapon();
                    break;
                case 2:
                    printArmor();
                    buyArmor();
                    break;
                case 3:
                    System.out.println("Görüşmek Üzere");
                    showMenu = false;
                    break;
            }
        }
        return true;
    }

    public void printWeapon() {
        System.out.println("-----Silahlar-----");
        System.out.println();
        for (Weapon w : Weapon.weapons()) {
            System.out.println(w.getId() + " - " + w.getName() + " <Para : " + w.getPrice() + " , Hasar : " + w.getDamage() + " >");
        }
        System.out.println("0 - Çıkış Yap");
    }

    public void buyWeapon() {
        System.out.println("Bir silah seçiniz : ");
        int selectWeapon = input.nextInt();
        while (selectWeapon < 0 || selectWeapon > Weapon.weapons().length) {
            System.out.println("Geçersiz değer");
            selectWeapon = input.nextInt();
        }
        Weapon selectedWeapon = Weapon.getWeaponObjById(selectWeapon);
        if (selectedWeapon != null) {
            if (selectedWeapon.getPrice() > this.getPlayer().getMoney()) {
                System.out.println("Yeterli Para Bulunmamaktadır.");
            } else {
                // Satın almanın olduğu alan.
                System.out.println(selectedWeapon.getName() + " silahını satın aldınız !");
                int balance = this.getPlayer().getMoney() - selectedWeapon.getPrice();
                this.getPlayer().setMoney(balance);
                System.out.println("Kalan Bakiye : " + this.getPlayer().getMoney());
//                System.out.println("Önceki slahınız : " + getPlayer().getInventory().getWeapon().getName());
                this.getPlayer().getInventory().setWeapon(selectedWeapon);
//                System.out.println("Güncel silahınız : " + getPlayer().getInventory().getWeapon().getName());
            }
        }
    }

    public void printArmor() {
        System.out.println("-----Zırhlar-----");
        System.out.println();
        for (Armor a : Armor.armors()) {
            System.out.println(a.getId() + " - " + a.getName() + " <Para : " + a.getPrice() + " , Zırh : " + a.getBlock() + " >");
        }
        System.out.println("0 - Çıkış Yap");
    }

    public void buyArmor() {
        System.out.println("Bir zırh seçiniz : ");
        int selectArmor = input.nextInt();
        while (selectArmor < 0 || selectArmor > Armor.armors().length) {
            System.out.print("Geçersiz değer, tekrar değer giriniz.");
            selectArmor = input.nextInt();
        }
        if (selectArmor != 0) {
            Armor selectedArmor = Armor.getArmorObjById(selectArmor);
            if (selectedArmor != null) {
                if (selectedArmor.getPrice() > this.getPlayer().getMoney()) {
                    System.out.println("Yeterli Para Bulunmamaktadır.");
                } else {
                    // Satın almanın olduğu alan.
                    System.out.println(selectedArmor.getName() + " zırhını satın aldınız !");
                    int balance = this.getPlayer().getMoney() - selectedArmor.getPrice();
                    this.getPlayer().setMoney(balance);
                    System.out.println("Kalan Bakiye : " + this.getPlayer().getMoney());
//                System.out.println("Önceki zırhınız : " + getPlayer().getInventory().getWeapon().getName());
                    this.getPlayer().getInventory().setArmor(selectedArmor);
//                System.out.println("Güncel zırhınız : " + getPlayer().getInventory().getWeapon().getName());
                }
            }
        }
    }

}

