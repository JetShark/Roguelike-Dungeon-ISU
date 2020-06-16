import java.awt.*;
import java.awt.image.BufferedImage;

public class WorldDrops {
    private int x,y;
    private Cowabunga cb;
    private BufferedImage img;
    public WorldDrops(int x, int y, Cowabunga cb){
        this.cb = cb;
        this.x = x;
        this.y = y;
        itemDrop((int) (3 * Math.random() - 0));
    }
    private void itemDrop(int ranNum){
        if(ranNum == 0){
            img = SpriteRetrival.getSprite(0,0,6);
        }
        if(ranNum == 1){
            img = SpriteRetrival.getSprite(0,6,6);
        }
        if(ranNum == 2){
            img = SpriteRetrival.getSprite(1,6,6);
        }
    }
    public void paint(Graphics2D g2d){
        g2d.drawImage(img,x,y,null);
    }
    public void move(){

    }
}
