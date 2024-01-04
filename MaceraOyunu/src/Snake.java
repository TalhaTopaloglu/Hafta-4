import java.util.Random;

public class Snake extends Obstacle {

    public Snake(int snakeDamage) {
        super(4, "Yılan",snakeDamage,12,0);

        setSnakeDamage(snakeDamage);
    }
        Random r = new Random();
        int hitSnakeChace = r.nextInt(4)+3;

    // Yılana set metotuyla rastgele bir damage bilgisi ekliyoruz.
    public void setSnakeDamage(int snakeDamage) {
        if(hitSnakeChace == 3){
            snakeDamage = hitSnakeChace;
        }
        if(hitSnakeChace == 4){
            snakeDamage = hitSnakeChace;
        }
        if(hitSnakeChace == 5){
            snakeDamage = hitSnakeChace;
        }
        if(hitSnakeChace == 6){
            snakeDamage = hitSnakeChace;
        }
        super.setDamage(snakeDamage);
    }

}
