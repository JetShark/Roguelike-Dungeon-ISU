import java.awt.*;
import java.awt.image.BufferedImage;


public class Bone {
    private BufferedImage[] bone5 = {SpriteRetrival.getSprite(0,13, 1), SpriteRetrival.getSprite(3,13, 1), SpriteRetrival.getSprite(0,14, 1),  SpriteRetrival.getSprite(3,14, 1), SpriteRetrival.getSprite(0,15, 1), SpriteRetrival.getSprite(3,15, 1), SpriteRetrival.getSprite(0,16, 1), SpriteRetrival.getSprite(3,16, 1)};
    private Animation spin = new Animation(bone5, 10);
    private Animation animation = spin;
    private int x = 100, y = 100;

    public Bone(){

    }
    public void paint(Graphics2D g2d){
        g2d.drawImage(animation.getSprite(), x, y, animation.getSprite().getHeight() * 3, animation.getSprite().getWidth() * 3 , null);
    }
    public void move(){
        animation.start();
        animation = spin;
        animation.update();
    }

}
