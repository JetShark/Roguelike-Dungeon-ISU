import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class WorldDrops {
    private int x,y;
    private int xp = 0,yp = 0;
    private Cowabunga cb;
    private BufferedImage img;
    private WorldDropSpawnPoints wdsp;
    private int gold;
    private int goldTotal;
    private boolean goldAdded = false;

    private int width = 0;
    private int height = 0;
    private int tilesAcross = 0;
    private int tilesDown = 0;
    private static final int tileWidth = 16;
    private static final int tileHeight = 16;
    private static final String mapPath = "Map System/Level 1 Var 1_Entity.csv";
    private int[][] level;
    private Room r;

    public WorldDrops(Cowabunga cb){
        this.cb = cb;
        wdsp = new WorldDropSpawnPoints();
        //itemDrop((int) (3 * Math.random() - 0));
    }
    public void setSpawn(){
        for(int i = 0; i < wdsp.getWidth(); i++){
            for(int j = 0; j < wdsp.getHeight(); j++){
                if(wdsp.getLevel(i,j) == 7) {
                    //System.out.println("level, i , j: " + wdsp.getLevel(i,j) + ", " + i +  ", " + j);
                    //System.out.println("isinroom: " + cb.getMapLevel().getPlayerRoom(cb.getP()).isTileInRoom(i,j));
                    if (cb.getMapLevel().getPlayerRoom(cb.getP()).isTileInRoom(i, j)) {
                        x = i * tileHeight * 4;
                        y = j * tileHeight * 4;
                        //System.out.println("x,y: " + x + ", " + y);
                    }
                }
            }
        }
    }
    public void setItemDrop(int ranNum){
        if(ranNum == 0){
            img = SpriteRetrival.getSprite(0,0,6);
        }
        if(ranNum == 1){
            img = SpriteRetrival.getSprite(0,6,6);
        }
        if(ranNum == 2){
            img = SpriteRetrival.getSprite(1,6,6);
        }
    }
    private void weaponDrop(){

    }
    private void equipmentDrop(){

    }

    public String getGoldTotal(){
        return Integer.toString(goldTotal);
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
    public void paint(Graphics2D g2d){
        r = cb.getMapLevel().getPlayerRoom(cb.getP());
        gold();
        //System.out.println("x,y: " + x + ", " + y);
        g2d.drawImage(img, x, y, null);
    }
    public void move(){

    }
}
