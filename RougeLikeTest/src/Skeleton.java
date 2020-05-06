import java.awt.*;
import java.awt.image.BufferedImage;
public class Skeleton {
    public String spriteName = "Skeleton";
    //private BufferedImage[] idle = {SpriteRetrival.getSprite(0,0,2), SpriteRetrival.getSprite(0,2,2), SpriteRetrival.getSprite(1,0,2), SpriteRetrival.getSprite(1,2,2), SpriteRetrival.getSprite(2,0,2), SpriteRetrival.getSprite(2,1,2) };
    //private  BufferedImage[] idle1 = {SpriteRetrivalTest.getSprite(0,0, spriteName), SpriteRetrivalTest.getSprite(0,1, spriteName), SpriteRetrivalTest.getSprite(0,2, spriteName), SpriteRetrivalTest.getSprite(1,0, spriteName), SpriteRetrivalTest.getSprite(1,1, spriteName), SpriteRetrivalTest.getSprite(1,2, spriteName), SpriteRetrivalTest.getSprite(2,0, spriteName), SpriteRetrivalTest.getSprite(2,1, spriteName), SpriteRetrivalTest.getSprite(0,0, spriteName), SpriteRetrivalTest.getSprite(0,1, spriteName)};
    //private animation Idle = new animation(idle, 10);
    //private animation Idle1 = new animation(idle1, 10);
    //private animation animation = Idle1;
    private int x = 500, y = 500;
    public Skeleton(){

    }
    public void paint(Graphics2D g2d){
        //g2d.drawImage(animation.getSprite(),x,y, animation.getSprite().getHeight() * 6, animation.getSprite().getWidth() * 6, null);
    }
    public void move(){
        //animation.start();
        //animation = Idle;
        //animation = Idle1;
        //animation.update();
    }
}

