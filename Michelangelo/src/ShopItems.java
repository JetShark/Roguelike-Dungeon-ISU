import java.awt.*;
import java.awt.image.BufferedImage;

// FIXME: 2020-05-29 Possibly should extend worlddrops?
public class ShopItems {
    //Attributes

    /*
    private JPanelExample ba;
    private int x = 0;
    private int y = 520;
    private int xa = 5;
    private int ya = 0;
    private int dir = 1;
    private BufferedImage img = null;
    public boolean beamed = false
    */

    private Cowabunga cb;
    private int width = 0;
    private int height = 0;
    private int tilesAcross = 0;
    private int tilesDown = 0;
    private int[] xt = new int[100];
    private int[] yt = new int[100];
    private int x,y;
    private String itemType;
    private Player p;
    private int spawnPointCount = 0;
    private static final int tileWidth = 16;
    private static final int tileHeight = 16;
    private BufferedImage img;
    private int[][] level;

//Constructor
    public ShopItems(int xInput, int yInput, String instanceName, Player pInput){
        x = xInput;
        y = yInput;
        p = pInput;
        itemType = instanceName;
        if (itemType == "heartSpawn") {
            img = SpriteRetrival.getSprite(0,0,6);
        }
        if (itemType == "keysSpawn") {
            img = SpriteRetrival.getSprite(1,6,6);
        }
        if (itemType == "bombsSpawn") {
            img = SpriteRetrival.getSprite(0,6,6);
        }
    }

//Accessors

//Mutators

//Methods
    public void paint(Graphics2D g2d){

        //animation.start();
        //animation.update();
        int sX=(int)x;
        int sY=(int)y;
        if (p != null) {
            sX = sX - p.getCamX();
            sY = sY - p.getCamY();
        }

        g2d.drawImage(img, sX, sY,img.getHeight() * 1, img.getWidth() * 1, null);
    }
}
