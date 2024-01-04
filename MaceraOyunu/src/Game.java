import java.util.Scanner;

public class Game {
    private Scanner input = new Scanner(System.in);
    public void start(){
        System.out.println("Mecara Oyununa Hoşgeldiniz !");
        // System.out.print("Lütfen Bir isim Giriniz : ");
        // String playerName = input.nextLine();
        Player player = new Player("playerName");
        System.out.println("Sayın " + player.getName() + " bu karanlıık ve sisli adaya hoşgeldiniz.");
        System.out.println("Bu karanlık adada haytta kalmak tamamen senin elinde.");
        System.out.println("Lütfen bir karakter seçiniz.");
        player.selectChar();

            Location location = null;
        while(true){
            player.printInfo();
            System.out.println("\n #################   BÖLGELER    ####################");
            System.out.println("1 - Güvenli Ev ---> Düşman Yok");
            System.out.println("2 - Dükkan ---> Silay veya Zırh satın alabilirsiniz.");
            System.out.println("3 - Mağara ---> Mağaraya --> Ödül : <Yemek>, dikkatli ol zombi çıkabilir !");
            System.out.println("4 - Orman ---> Ormana --> Ödül : <Odun>, dikkatli ol vampir çıkabilir !");
            System.out.println("5 - Nehir ---> Nehire --> Ödül : <Su>, dikkatli ol ayı çıkabilir !");
            System.out.println("6 - Maden ---> Madene --> Ödül : <İtem>, dikkatli ol yılan çıkabilir !");

            System.out.println("\n #################   MENU    ####################");
            System.out.println("0 - Çıkış Yap \n");
            System.out.println("9 - Ana Menü \n");
            System.out.print("Lüfen Gitmek İstediğiniz Bölgeyi Seçin : ");
            int selectLoc = input.nextInt();

            switch (selectLoc){
                case 0:
                    location = null;
                    break;
                case 1:
                    location = new SafeHouse(player);
                    if(player.getInventory().isCaveAward() && player.getInventory().isForestAward() && player.getInventory().isRiverAward()){
                        System.out.println("Tebrikler Kazandınız");
                        location = null;
                    }
                    break;
                case 2:
                    location = new ToolStore(player);
                    break;
                case 3:
                    if(!player.getInventory().isCaveAward()){
                    location = new Cave(player);
                    }
                    else {
                        System.out.println("Tekrar değer giriniz zeten o bölge de düşman yok ve ödülü kazanılmış.");
                        continue;
                    }
                    break;
                case 4:
                    if(!player.getInventory().isForestAward()){
                        location = new Forest(player);
                    }
                    else {
                        System.out.println("Tekrar değer giriniz zeten o bölge de düşman yok ve ödülü kazanılmış.");
                        continue;
                    }
                    break;
                case 5:
                    if(!player.getInventory().isRiverAward()){
                        location = new River(player);
                    }
                    else {
                        System.out.println("Tekrar değer giriniz zeten o bölge de düşman yok ve ödülü kazanılmış.");
                        continue;
                    }
                    break;
                case 6:
                    location = new Mine(player);
                    break;
                default:
                    System.out.println("Lütfen geçerli bir değer giriniz");
                    break;
            }
            if(location == null){
                System.out.println("Bu karanlık ve sisli adaya veda ettin :)");
                break;
            }
            if(!location.onLocation()){
                System.out.println("GAME OVER!!");
                break;
            }
        }

    }
}
