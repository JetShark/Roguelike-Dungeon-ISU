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
    private static BufferedImage hudSprites;
    private static BufferedImage bossesSprites;

    private static java.util.Map<Integer, BufferedImage> cachedImage = new java.util.HashMap<Integer, BufferedImage>();

    private static final int TILE_SIZE = 32; //tile size of the tiles on the spread sheet.
    private static final int TILES_ACROSS = 100; //tile size of the tiles on the spread sheet.
    private static final int TILES_DOWN = 100; //tile size of the tiles on the spread sheet.

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
            //return getEnemsySprites(xGrid, yGrid);
            return getEnemySprites(xGrid, yGrid);
        }
        if(spriteNumber == 4){
            return getWeaponSpriteSheet(xGrid, yGrid);
        }
        if(spriteNumber == 5){
            return getCharacterSpriteSheetTest(xGrid, yGrid);
        }
        if(spriteNumber == 6){
            return getHUDSpriteSheet(xGrid, yGrid);
        }
        if(spriteNumber == 7){
            return getBossesSpriteSheet(xGrid, yGrid);
        }
        return null;
    }
    private static BufferedImage getProjectileSprites(int xGrid, int yGrid){
        if(projectileSprites == null){
            projectileSprites = loadSprite("Projectile-SpriteSheet"); // sets projectileSprites to the spritesheet if it does not have on yet
        }

        int key=xGrid+yGrid*TILES_ACROSS+1*TILES_ACROSS*TILES_DOWN;
        BufferedImage subimage;
        if (cachedImage.containsKey(key)) {
            subimage = cachedImage.get(key);
        } else {
            subimage = projectileSprites.getSubimage(xGrid * TILE_SIZE, yGrid * TILE_SIZE, TILE_SIZE, TILE_SIZE); // returns the sub images based on the x,y values given.
            cachedImage.put(key, subimage);
        }

        return subimage;
    }
    private static BufferedImage getEnemySprites(int xGrid, int yGrid){
        if(enemySprites == null){
            enemySprites = loadSprite("Ennemy-SpriteSheet");
        }

        int key=xGrid+yGrid*TILES_ACROSS+2*TILES_ACROSS*TILES_DOWN;
        BufferedImage subimage;
        if (cachedImage.containsKey(key)) {
            subimage = cachedImage.get(key);
        } else {
            subimage = enemySprites.getSubimage(xGrid * TILE_SIZE, yGrid * TILE_SIZE, TILE_SIZE, TILE_SIZE); // returns the sub images based on the x,y values given.
            cachedImage.put(key, subimage);
        }

        return subimage;

    }
    private static BufferedImage getCharacterSpriteSheet(int xGrid, int yGrid){
        int TILE_SIZE_X = 24;
        int TILE_SIZE_Y = 34;
        if(characterSprites == null){
            characterSprites = loadSprite("Character-SpriteSheet");
        }
        int key=xGrid+yGrid*TILES_ACROSS+3*TILES_ACROSS*TILES_DOWN;
        BufferedImage subimage;
        if (cachedImage.containsKey(key)) {
            subimage = cachedImage.get(key);
        } else {
            subimage = characterSprites.getSubimage(xGrid * TILE_SIZE_X, yGrid * TILE_SIZE_Y, TILE_SIZE_X, TILE_SIZE_Y); // returns the sub images based on the x,y values given.
            cachedImage.put(key, subimage);
        }

        return subimage;
    }
    private static BufferedImage getCharacterSpriteSheetTest(int xGrid, int yGrid){
        if(characterSpritesTest == null){
            characterSpritesTest = loadSprite("Test-Character-SpriteSheet");
        }
        int key=xGrid+yGrid*TILES_ACROSS+4*TILES_ACROSS*TILES_DOWN;
        BufferedImage subimage;
        if (cachedImage.containsKey(key)) {
            subimage = cachedImage.get(key);
        } else {
            subimage = characterSpritesTest.getSubimage(xGrid * TILE_SIZE, yGrid * TILE_SIZE, TILE_SIZE, TILE_SIZE); // returns the sub images based on the x,y values given.
            cachedImage.put(key, subimage);
        }

        return subimage;
    }
    private static BufferedImage getWeaponSpriteSheet(int xGrid, int yGrid){
        if(weaponSprites == null){
            weaponSprites = loadSprite("Weapon-SpriteSheet");
        }

        int key=xGrid+yGrid*TILES_ACROSS+5*TILES_ACROSS*TILES_DOWN;
        BufferedImage subimage;
        if (cachedImage.containsKey(key)) {
            subimage = cachedImage.get(key);
        } else {
            subimage = weaponSprites.getSubimage(xGrid * TILE_SIZE, yGrid * TILE_SIZE, TILE_SIZE, TILE_SIZE); // returns the sub images based on the x,y values given.
            cachedImage.put(key, subimage);
        }

        return subimage;
    }
    private static BufferedImage getHUDSpriteSheet(int xGrid, int yGrid){
        if(hudSprites == null){
            hudSprites = loadSprite("HUD-SpriteSheet");
        }

        int key=xGrid+yGrid*TILES_ACROSS+6*TILES_ACROSS*TILES_DOWN;
        BufferedImage subimage;
        if (cachedImage.containsKey(key)) {
            subimage = cachedImage.get(key);
        } else {
            subimage = hudSprites.getSubimage(xGrid * TILE_SIZE, yGrid * TILE_SIZE, TILE_SIZE, TILE_SIZE); // returns the sub images based on the x,y values given.
            cachedImage.put(key, subimage);
        }

        return subimage;
    }
    private static BufferedImage getBossesSpriteSheet(int xGrid, int yGrid){
        int TILE_SIZE_X = 64;
        int TILE_SIZE_Y = 64;
        if(bossesSprites == null){
            bossesSprites = loadSprite("Bosses-SpriteSheet");
        }

        int key=xGrid+yGrid*TILES_ACROSS+7*TILES_ACROSS*TILES_DOWN;//May need to be a seven not a six
        BufferedImage subimage;
        if (cachedImage.containsKey(key)) {
            subimage = cachedImage.get(key);
        } else {
            subimage = bossesSprites.getSubimage(xGrid * TILE_SIZE_X, yGrid * TILE_SIZE_Y, TILE_SIZE_X, TILE_SIZE_Y); // returns the sub images based on the x,y values given.
            cachedImage.put(key, subimage);
        }

        return subimage;
    }
}
