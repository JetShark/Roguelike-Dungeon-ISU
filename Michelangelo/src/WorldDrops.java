import java.awt.*;
import java.awt.image.BufferedImage;

public class WorldDrops {
    private int x[] = new int[30];
    private int y[] = new int[30];
    private int xp = 0,yp = 0;
    private Cowabunga cb;
    private BufferedImage[] img = new BufferedImage[30];
    private WorldDropSpawnPoints wdsp;
    private Equipment equipment;
    private int gold;
    private int goldTotal;
    private boolean goldAdded = false;
    private int keys;
    private int keysTotal;
    private boolean keysAdded = false;
    private int bombs;
    private int bombsTotal;
    private boolean bombsAdded = false;
    private int ranNum;
    private int ranKeyOrBomb;
    private boolean pickedUp;
    private boolean[] pickedUpList = new boolean[30];
    int ranHeart;

    private int width = 0;
    private int height = 0;
    private int tilesAcross = 0;
    private int tilesDown = 0;
    private static final int tileWidth = 16;
    private static final int tileHeight = 16;
    private static final String mapPath = "Map System/Level 1 Var 1_Entity.csv";
    private int[][] level;
    private Room r;
    private int worldDropCount = 0;

    public WorldDrops(Cowabunga cb){
        this.cb = cb;
        wdsp = new WorldDropSpawnPoints();
        pickedUp = false;
        //itemDrop((int) (3 * Math.random() - 0));
    }
    public void setSpawn(){
        for(int i = 0; i < wdsp.getWidth(); i++){
            for(int j = 0; j < wdsp.getHeight(); j++){
                if(wdsp.getLevel(i,j) == 7) {
                    //System.out.println("level, i , j: " + wdsp.getLevel(i,j) + ", " + i +  ", " + j);
                    //System.out.println("isinroom: " + cb.getMapLevel().getPlayerRoom(cb.getP()).isTileInRoom(i,j));
                    if (cb.getMapLevel().getPlayerRoom(cb.getP()).isTileInRoom(i, j)) {
                        x[worldDropCount] = i * tileWidth * 4;
                        y[worldDropCount] = j * tileHeight * 4;
                        System.out.println("x, y, wdc: " + x[worldDropCount] + ", " + y[worldDropCount] + ", " + worldDropCount);
                        worldDropCount += 1;
                        //System.out.println("x,y: " + x + ", " + y);
                    }
                }
            }
        }
        System.out.println("x, y, wdc: " + x[0] + ", " + x[0] + ", " + worldDropCount);
    }
    public void setItemDrop(/*int ranNum*/){
        ranNum = (int) (7 * Math.random() - 0);
        System.out.println("ranNum: " + ranNum);
        if(ranNum < 3){
            ranHeart = (int) (4 * Math.random() - 0);
            if(ranHeart == 0){
                if(worldDropCount >= 1){
                    img[worldDropCount - 1] = SpriteRetrival.getSprite(0,0,6);
                    pickedUp = false;
                }
            }
            if(ranHeart == 1){
                if(worldDropCount >= 1){
                    img[worldDropCount - 1] = SpriteRetrival.getSprite(3,1,6);
                    pickedUp = false;
                }
            }
            if(ranHeart == 2){
                if(worldDropCount >= 1){
                    img[worldDropCount - 1] = SpriteRetrival.getSprite(6,2,6);
                    pickedUp = false;
                }
            }
            if(ranHeart == 3){
                if(worldDropCount >= 1){
                    img[worldDropCount - 1] = SpriteRetrival.getSprite(1,4,6);
                    pickedUp = false;
                }
            }
        }
        if(ranNum >= 3 && ranNum < 5){
            ranKeyOrBomb = (int) (6 * Math.random() - 0);
            if(ranKeyOrBomb <= 3) {
                if (worldDropCount >= 1) {
                    img[worldDropCount - 1] = SpriteRetrival.getSprite(0, 6, 6);
                    pickedUp = false;

                }
            }
            if(ranKeyOrBomb > 3){
                if(worldDropCount >= 1) {
                    img[worldDropCount - 1] = SpriteRetrival.getSprite(1, 6, 6);
                    pickedUp = false;
                }
            }
        }
        if(ranNum >= 5){
            int ranEquip = (int) (26 * Math.random() - 0);
            if(worldDropCount >= 1) {
                img[worldDropCount - 1] = SpriteRetrival.getSprite(4,6,6);
                pickedUp = false;
                for(int i = 0; i < worldDropCount; i++) {
                    equipment.setEquipment(ranEquip);
                    equipment.setX(x[i]);
                    equipment.setY(y[i]);
                }
            }
        }
    }
    public String getGoldTotal(){
        return Integer.toString(goldTotal);
    }
    public String getKeyTotal(){
        return Integer.toString(keysTotal);
    }
    public int getKeysTotal(){
        return keysTotal;
    }
    public void setKeys(int keys){
        this.keys = keys;
        if(!keysAdded){
            keysAdded = true;
        }
    }
    public void keys(){
        if(!pickedUp) {
            if (cb.getCollision().worldDropCollision() && ranNum >= 3 && ranNum < 5) {
                if(ranKeyOrBomb > 3) {
                    if (!keysAdded) {
                        setKeys(1);
                        keysTotal = keysTotal + keys;
                        pickedUp = true;
                    }
                }
            }
            if (!cb.getCollision().worldDropCollision()) {
                keysAdded = false;
            }
        }
    }
    public int getBombsTotal(){
        return bombsTotal;
    }
    public String getBombTotal(){
        return Integer.toString(bombsTotal);
    }
    public void setBombs(int bombs){
        this.bombs = bombs;
        if(!bombsAdded){
            bombsAdded = true;
        }
    }
    public void minusBombs(int minus){
        if(bombsTotal > 0) {
            bombsTotal = bombsTotal - minus;
        }
    }
    public void bombs(){
        //System.out.println("wdc: " + cb.getCollision().worldDropCollision());
        if(!pickedUp) {
            if (cb.getCollision().worldDropCollision() && ranNum >= 3 && ranNum < 5) {
                if(ranKeyOrBomb <= 3) {
                    if (!bombsAdded) {
                        setBombs(1);
                        bombsTotal = bombsTotal + bombs;
                        pickedUp = true;
                    }
                }
            }
            if (!cb.getCollision().worldDropCollision()) {
                bombsAdded = false;
            }
        }
    }
    public void setGold(int gold){
        this.gold = gold;
        if(!goldAdded){
            goldAdded = true;
        }
    }
    public void gold(){
        goldTotal = goldTotal + gold;
        if(goldAdded) {
            gold = 0;
        }
        //goldTotal = goldTotal + gold;
    }
    public void pickUpHealth(){
        if(!pickedUp) {
            if (cb.getCollision().worldDropCollision() && ranNum < 3 && cb.getP().getPlayerHealth() < 12) {
                if (ranHeart == 0) {
                    cb.getP().setPlayerHealth(4);
                    pickedUp = true;
                }
                if (ranHeart == 1) {
                    cb.getP().setPlayerHealth(3);
                    pickedUp = true;
                }
                if (ranHeart == 2) {
                    cb.getP().setPlayerHealth(2);
                    pickedUp = true;
                }
                if (ranHeart == 3) {
                    cb.getP().setPlayerHealth(1);
                    pickedUp = true;
                }
            }
        }
    }
    public void paint(Graphics2D g2d){
        r = cb.getMapLevel().getPlayerRoom(cb.getP());
        equipment = cb.getEquipment();
        gold();
        bombs();
        keys();
        pickUpHealth();
        //System.out.println("x,y: " + x[worldDropCount] + ", " + y[worldDropCount]);
        //System.out.println("x, y, wdc: " + x[0] + ", " + x[0] + ", " + worldDropCount);
        for (int i = 0; i < worldDropCount; i++) {
            if(!pickedUp) {
                g2d.drawImage(img[i], x[i], y[i], null);
                //equipment.setX(x[i]);
                //equipment.setY(y[i]);
                equipment.paint(g2d);
            }
        }
    }
    public void move(){

    }
    public int getX(){
        if(worldDropCount >= 1) {
            return x[worldDropCount - 1];
        }
        return 0;
    }
    public int getY(){
        if(worldDropCount >= 1) {
            return y[worldDropCount - 1];
        }
        return 0;
    }

}
