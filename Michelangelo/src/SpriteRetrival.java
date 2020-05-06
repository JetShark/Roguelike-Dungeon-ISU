import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class SpriteRetrival {

    private static BufferedImage projectileSprites;
    private static BufferedImage enemySprites;
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
            //return getEnemySprites(xGrid, yGrid);
        }
        return null;
    }
    private static BufferedImage getProjectileSprites(int xGrid, int yGrid){
        if(projectileSprites == null){
            projectileSprites = loadSprite("Projectile-SpriteSheet");
        }
        return projectileSprites.getSubimage(xGrid * TILE_SIZE, yGrid * TILE_SIZE, TILE_SIZE, TILE_SIZE);
    }
    private static BufferedImage getEnemySprites(int xGrid, int yGrid){
        if(enemySprites == null){
            enemySprites = loadSprite("Skeleton");
        }
        return enemySprites.getSubimage(xGrid * TILE_SIZE, yGrid * TILE_SIZE, TILE_SIZE, TILE_SIZE);
    }
}
