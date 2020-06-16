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
    private ToolTip tt;
    private ShopItemsSpawn sis;
    private int spawnPointCount = 0;
    private static final int tileWidth = 16;
    private static final int tileHeight = 16;
    private BufferedImage img;
    private int[][] level;
    private int hitboxX, hitboxY, hitboxXT, hitboxYT;

//Constructor
    public ShopItems(int xInput, int yInput, String instanceName, Player pInput, Cowabunga cb){
        x = xInput;
        y = yInput;
        p = pInput;
        this.cb = cb;
        sis = cb.getSis();
        itemType = instanceName;
        System.out.println("itemType: " + itemType);
        if (itemType.equals("heartsSpawn")) {
            img = SpriteRetrival.getSprite(0,0,6);
        }
        if (itemType.equals("keysSpawn")) {
            img = SpriteRetrival.getSprite(3,6,6);
        }
        if (itemType.equals("bombsSpawn")) {
            img = SpriteRetrival.getSprite(0,6,6);
        }
        if(itemType.equals("gearSpawn")){
            img = SpriteRetrival.getSprite(0,0,4);
        }
        if(itemType.equals("weaponSpawn")){
            img = SpriteRetrival.getSprite(0,1,4);
        }
    }

//Accessors

//Mutators

//Methods
    public void currentItem(){
        tt = cb.getTt();
        hitboxX = x - 20;
        hitboxY = y - 10;
        hitboxXT = x + 84;
        hitboxYT = y + 74;
        boolean x_overlaps;
        boolean y_overlaps;
        x_overlaps = (p.getHitboxX() < hitboxXT) && (p.getHitboxXT() > hitboxX);
        y_overlaps = (p.getHitboxY() < hitboxYT) && (p.getHitboxYT() > hitboxY);
        boolean collision = x_overlaps && y_overlaps;
        if(collision){
            //System.out.println("collison" + collision);
            tt.setX(x);
            tt.setY(y);
            if (itemType.equals("heartsSpawn")) {
                tt.setItem(true, "heart");
            }
            if (itemType.equals("keysSpawn")) {
                tt.setItem(true, "key");
            }
            if (itemType.equals("bombsSpawn")) {
                tt.setItem(true, "bomb");
            }
            if(itemType.equals("gearSpawn")){
                tt.setItem(false, "false");
            }
            if(itemType.equals("weaponSpawn")){
                tt.setItem(false, "false");
            }
        }
    }
    public void paint(Graphics2D g2d){

        //Animation.start();
        //Animation.update();

        /*if (p != null) {
            sX = sX - p.getCamX();
            sY = sY - p.getCamY();
        }*/
        int width = (int)(img.getWidth() * 1);
        int height = (int)(img.getHeight() * 1);

        /*
        if (itemType.equals("keysSpawn")) {
            width = (int) (img.getWidth() * 1.25);
            height = (int) (img.getHeight() * 1.25);
        }
        */

        int sX=(int)(x + (width/2));
        int sY=(int)(y + (height/3));
        g2d.drawImage(img, sX, sY, width, height, null);
        currentItem();
    }
}
