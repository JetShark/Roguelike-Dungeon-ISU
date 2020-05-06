import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class SpriteRetrival {

    private static BufferedImage projectileSprites;
    private static BufferedImage skeleton;
    private static final int TILE_SIZE = 32;

    private static BufferedImage loadSprite(String file){

        BufferedImage sprite = null;
        try{
            sprite = ImageIO.read(new File("src/SpriteSheets/" + file + ".png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
            return sprite;
        }
    public static BufferedImage getSprite(int xGrid, int yGrid, int spriteNumber){
        if(spriteNumber == 1) {
            return getProjectileSprites(xGrid, yGrid);
        }
        if(spriteNumber == 2){
            //return getSkeletonSprite(xGrid, yGrid);
        }
        return null;
    }
    private static BufferedImage getProjectileSprites(int xGrid, int yGrid){
        if(projectileSprites == null){
            projectileSprites = loadSprite("Projectile-SpriteSheet");
        }
        return projectileSprites.getSubimage(xGrid * TILE_SIZE, yGrid * TILE_SIZE, TILE_SIZE, TILE_SIZE);
    }
    private static BufferedImage getSkeletonSprite(int xGrid, int yGrid){
        if(skeleton == null){
            skeleton = loadSprite("Skeleton");
        }
        return skeleton.getSubimage(xGrid * TILE_SIZE, yGrid * TILE_SIZE, TILE_SIZE, TILE_SIZE);
    }
}
