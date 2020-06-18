import java.awt.*;
import java.awt.image.BufferedImage;

public class Bosses {
    private Cowabunga cb;
    private EnemySpawnPoints eps;
    private int x,y;

    private BufferedImage[] neromancerIdle = {SpriteRetrival.getSprite(0,0, 7), SpriteRetrival.getSprite(3,0,7), SpriteRetrival.getSprite(0,1,7), SpriteRetrival.getSprite(3,1,7)};
    private Animation necroIdle = new Animation(neromancerIdle, 50);
    private BufferedImage[] neromancerWalk = {SpriteRetrival.getSprite(0,2, 7), SpriteRetrival.getSprite(3,2,7), SpriteRetrival.getSprite(0,3,7), SpriteRetrival.getSprite(3,3,7)};
    private Animation necroWalking = new Animation(neromancerWalk, 50);


    private Animation bossAnimation = necroIdle;
    public Bosses(Cowabunga cb){
        this.cb = cb;
        eps = new EnemySpawnPoints("Map System/Level 1 Var 1_Entity.csv");
        for(int i = 0; i < eps.getWidth(); i++ ){
            for(int j = 0; j < eps.getHeight(); j++){
                if(eps.getLevel(i,j) == 2){
                    x = i * 64;
                    y = j * 64;
                }
            }
        }
    }
    public void move(){

    }
    public void necromancerMovement(){

    }
    public void paint(Graphics2D g2d){
        bossAnimation = necroIdle;
        bossAnimation.start();
        bossAnimation.update();
        if(cb.getPlayerRoom() != null){
            //System.out.println("midhor, vormid: " + cb.getPlayerRoom().getMidHorizontalTile() + ", " + cb.getPlayerRoom().getMidVerticalTile());
            //g2d.drawImage(bossAnimation.getSprite(), cb.getPlayerRoom().getMidHorizontalTile()*64, cb.getPlayerRoom().getMidVerticalTile()*64, bossAnimation.getSprite().getWidth() * 3, bossAnimation.getSprite().getHeight() * 3,null);
            g2d.drawImage(bossAnimation.getSprite(), x, y, bossAnimation.getSprite().getWidth() * 3, bossAnimation.getSprite().getHeight() * 3,null);
        }

        //g2d.setColor(Color.PINK);
        //g2d.fillRect(1000, 1000,200,200);
    }
}
