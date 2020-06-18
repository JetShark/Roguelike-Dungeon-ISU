import java.awt.*;
import java.awt.image.BufferedImage;

public class Bomb {
    private Cowabunga cb;
    private WorldDrops wd;
    private Player p;
    private int x,y;
    private boolean bombPlaced;
    private BufferedImage img;
    private int detonation = 150;
    private boolean blownUp, blowUp;
    public Bomb(Cowabunga cb){
        this.cb = cb;
        bombPlaced = false;
        img = SpriteRetrival.getSprite(0,6,6);
    }
    public void placeBomb(){
        if(wd.getBombsTotal() >= 1){
            this.x = p.getX();
            this.y = p.getY();
            bombPlaced = true;
            wd.minusBombs(1);
            blowUp = true;
            //detonation = 100;
        }
    }
    public void blowUp(){
        if(detonation == 0){
            blownUp = true;
            detonation = 150;
        }
        detonation--;
    }
    public void paint(Graphics2D g2d){
        if(bombPlaced && !blownUp){
            //System.out.println("x,y: " + x + "," + y);
            g2d.drawImage(img, x,y, null);
        }
    }
    public void move(){
        wd = cb.getWd();
        p = cb.getP();
        if(blowUp) {
            blowUp();
        }
    }
}
