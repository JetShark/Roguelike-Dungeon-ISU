import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
public class SpriteRetrivalTest {
    private static BufferedImage boneProjectile;
    private static BufferedImage spriteSheet;
    private static final int TILE_SIZE = 32;

    private static BufferedImage loadSprite(String file){

        BufferedImage sprite = null;
        try{
            sprite = ImageIO.read(new File("src/SpriteSheets" + file + ".png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sprite;
    }
    public static BufferedImage getSprite(int xGrid, int yGrid, String spriteName){
        if(spriteSheet == null){
            spriteSheet = loadSprite(spriteName);
        }
        return spriteSheet.getSubimage(xGrid * TILE_SIZE, yGrid * TILE_SIZE, TILE_SIZE, TILE_SIZE);
    }
}
