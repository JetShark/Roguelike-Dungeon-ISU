import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class SpriteRetrival {

    private static BufferedImage projectileSprites; //creates the img.
    private static BufferedImage enemySprites;
    private static BufferedImage characterSprites;
    private static BufferedImage characterSpritesTest;
    private static BufferedImage weaponSprites;
    private static final int TILE_SIZE = 32; //tile size of the tiles on the spread sheet.

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
        //Takes the x and y quartents that are given and pass them on to the right method based on what number they are acssesing.
        if(spriteNumber == 1) {
            return getProjectileSprites(xGrid, yGrid);
        }
        if(spriteNumber == 2){
            //return getEnemySprites(xGrid, yGrid);
            return getCharacterSpriteSheet(xGrid, yGrid);
        }
        if(spriteNumber == 3){
            //return getEnemySprites(xGrid, yGrid);
            return getEnemySprites(xGrid, yGrid);
        }
        if(spriteNumber == 4){
            return getWeaponSpriteSheet(xGrid, yGrid);
        }
        if(spriteNumber == 5){
            return getCharacterSpriteSheetTest(xGrid, yGrid);
        }
        return null;
    }
    private static BufferedImage getProjectileSprites(int xGrid, int yGrid){
        if(projectileSprites == null){
            projectileSprites = loadSprite("Projectile-SpriteSheet"); // sets projectileSprites to the spritesheet if it does not have on yet
        }
        return projectileSprites.getSubimage(xGrid * TILE_SIZE, yGrid * TILE_SIZE, TILE_SIZE, TILE_SIZE); // returns the sub images based on the x,y values given.
    }
    private static BufferedImage getEnemySprites(int xGrid, int yGrid){
        if(enemySprites == null){
            enemySprites = loadSprite("Ennemy-SpriteSheet");
        }
        return enemySprites.getSubimage(xGrid * TILE_SIZE, yGrid * TILE_SIZE, TILE_SIZE, TILE_SIZE);
    }
    public static BufferedImage getCharacterSpriteSheet(int xGrid, int yGrid){
        int TILE_SIZE_X = 24;
        int TILE_SIZE_Y = 34;
        if(characterSprites == null){
            characterSprites = loadSprite("Character-SpriteSheet");
        }
        return characterSprites.getSubimage(xGrid * TILE_SIZE_X, yGrid * TILE_SIZE_Y, TILE_SIZE_X, TILE_SIZE_Y);
    }
    public static BufferedImage getCharacterSpriteSheetTest(int xGrid, int yGrid){
        if(characterSpritesTest == null){
            characterSpritesTest = loadSprite("Test-Character-SpriteSheet");
        }
        return characterSpritesTest.getSubimage(xGrid * TILE_SIZE, yGrid * TILE_SIZE, TILE_SIZE, TILE_SIZE);
    }
    public static BufferedImage getWeaponSpriteSheet(int xGrid, int yGrid){
        if(weaponSprites == null){
            weaponSprites = loadSprite("Weapon-SpriteSheet");
        }
        return weaponSprites.getSubimage(xGrid * TILE_SIZE, yGrid * TILE_SIZE, TILE_SIZE, TILE_SIZE);
    }
}
