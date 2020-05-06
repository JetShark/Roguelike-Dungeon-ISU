import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class WeaponTest {
    private int x = 800, y = 200;
    private BufferedImage img = null;
    public WeaponTest(){
        try {
            img = ImageIO.read(new File("src/SpriteSheets/Produce.png"));
        } catch (IOException e) {
            System.out.println("No Image");
        }
    }
    public void paint(Graphics2D g2d){
        g2d.drawImage(img, x, y, img.getHeight() * 4, img.getWidth() * 4, null);
    }
}
