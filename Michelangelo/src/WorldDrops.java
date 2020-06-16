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
        r = cb.getMapLevel().getPlayerRoom(cb.getP());
        wdsp = new WorldDropSpawnPoints();
        /*try {
            BufferedReader csvReader = new BufferedReader(new FileReader(mapPath));
            try {
                String row;

                while ((row = csvReader.readLine()) != null) {
                    String[] data = row.split(",");
                    height++;
                    if (data.length > width) {
                        width = data.length;
                    }
                }
                csvReader.close();
                level = new int[width][height];
                csvReader = new BufferedReader(new FileReader(mapPath));
                int y = 0;
                while ((row = csvReader.readLine()) != null) {
                    String[] data = row.split(",");
                    for (int x = 0; x < data.length; x++){
                        level[x][y]= Integer.parseInt(data[x]);
                    }
                    y++;
                }
                //System.out.println("Map Width & Height = " + width + ", " + height);
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println(e.toString());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println(e.toString());
        }
        int xp;
        int yp;*/
        for(int i = 0; i < wdsp.getWidth(); i++){
            for(int j = 0; j < wdsp.getHeight(); j++){
                if(wdsp.getLevel(i,j) == 7) {
                    if (r.isTileInRoom(i, j)) {
                        x = wdsp.getX(i);
                        y = wdsp.getY(j);
                    }
                }
            }
        }
        itemDrop((int) (3 * Math.random() - 0));
    }
    private void itemDrop(int ranNum){
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
    public void paint(Graphics2D g2d){
        //System.out.println("x,y: " + x + ", " + y);
        g2d.drawImage(img, x, y, null);
    }
    public void move(){

    }
}
