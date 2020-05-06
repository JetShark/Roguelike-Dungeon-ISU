import java.awt.*;
import java.awt.image.BufferedImage;
public class PortalGun {
    private String spriteName = "You. Monster. Firing Animation";
    private BufferedImage[] firing = {SpriteRetrivalTest.getSprite(0,0, spriteName), SpriteRetrivalTest.getSprite(0,3, spriteName),SpriteRetrivalTest.getSprite(1,0, spriteName), SpriteRetrivalTest.getSprite(1,3, spriteName), SpriteRetrivalTest.getSprite(2,0, spriteName), SpriteRetrivalTest.getSprite(2,3, spriteName), SpriteRetrivalTest.getSprite(3,0, spriteName), SpriteRetrivalTest.getSprite(3,3, spriteName)};
    private animation Firing = new animation(firing, 10);
    private animation animation = Firing;
    private int x = 100, y= 300;
    public PortalGun(){

    }
    public void move(){
        animation.start();
        animation = Firing;
        animation.update();
    }
    public void paint(Graphics2D g2d){
        g2d.drawImage(animation.getSprite(), x,y,animation.getSprite().getHeight() * 4, animation.getSprite().getWidth() * 4, null);
    }
}
