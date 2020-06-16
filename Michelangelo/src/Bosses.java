import java.awt.*;
import java.awt.image.BufferedImage;

public class Bosses {
    private Cowabunga cb;
    private EnemySpawnPoints eps;
    private int x,y;

    private BufferedImage[] neromancerIdle = {SpriteRetrival.getSprite(0,0, 7), SpriteRetrival.getSprite(3,0,7), SpriteRetrival.getSprite(0,1,7), SpriteRetrival.getSprite(3,1,7)};
    private Animation necroIdle = new Animation(neromancerIdle, 10);

    private Animation bossAnimation;
    public Bosses(Cowabunga cb){
        this.cb = cb;
        eps = new EnemySpawnPoints("Map System/Level 1 Var 1_Entity.csv");
        for(int i = 0; i < eps.getWidth(); i++ ){
            for(int j = 0; j < eps.getHeight(); j++){
                if(eps.getLevel(i,j) == 2){
                    x = i * 16 * 4;
                    y = i * 16 * 4;
                    System.out.println("i,j: " + i + ", " + j);
                }
            }
        }
    }
    public void move(){

    }
    public void paint(Graphics2D g2d){
        bossAnimation = necroIdle;
        bossAnimation.start();
        bossAnimation.update();
        System.out.println("x,y: " + x + ", " + y);
        g2d.drawImage(bossAnimation.getSprite(), x,y, bossAnimation.getSprite().getHeight() * 5, bossAnimation.getSprite().getWidth() * 5,null);
        g2d.drawRect(x,y,10,10);
    }
}
