import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

// FIXME: 2020-05-29 Possibly should extend worlddrops?
public class ShopItemsSpawn {
    private Cowabunga cb;
    private int width = 0;
    private int height = 0;
    private int tilesAcross = 0;
    private int tilesDown = 0;
    private int[] xt = new int[6];
    private int[] yt = new int[6];
    private String[] instanceName = new String[5];
    private int x,y;
    private int spawnPointCount = 0;
    private static final int tileWidth = 16;
    private static final int tileHeight = 16;
    private BufferedImage img = null;
    private int[][] level;

    public ShopItemsSpawn(String mapPath){
        try {
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
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                if (level[i][j] == 4) {
                    this.x = i * tileWidth * 4;
                    this.y = j * tileHeight * 4;
                    xt[spawnPointCount] = i * tileWidth * 4;
                    yt[spawnPointCount] = j * tileHeight * 4;
                    System.out.println("x, y: " + x + ", " + y + ", " + spawnPointCount);
                    System.out.println("xt, yt: " + xt[spawnPointCount] + ", " + yt[spawnPointCount]);
                    if(spawnPointCount == 1) {
                        instanceName[spawnPointCount] = "gearSpawn";
                    }
                    System.out.println("iName: " + instanceName[spawnPointCount]);
                    spawnPointCount += 1;
                }
                if (level[i][j] == 5) {
                    this.x = i * tileWidth * 4;
                    this.y = j * tileHeight * 4;
                    xt[spawnPointCount] = i * tileWidth * 4;
                    yt[spawnPointCount] = j * tileHeight * 4;
                    System.out.println("x, y: " + x + ", " + y + ", " + spawnPointCount);
                    System.out.println("xt, yt: " + xt[spawnPointCount] + ", " + yt[spawnPointCount]);
                    if(spawnPointCount == 3) {
                        instanceName[spawnPointCount] = "weaponSpawn";
                    }
                    System.out.println("iName: " + instanceName[spawnPointCount]);
                    spawnPointCount += 1;
                }
                if (level[i][j] == 3) {
                    this.x = i * tileWidth * 4;
                    this.y = j * tileHeight * 4;
                    xt[spawnPointCount] = i * tileWidth * 4;
                    yt[spawnPointCount] = j * tileHeight * 4;
                    System.out.println("x, y: " + x + ", " + y + ", " + spawnPointCount);
                    System.out.println("xt, yt: " + xt[spawnPointCount] + ", " + yt[spawnPointCount]);
                    if (spawnPointCount == 0) {
                        instanceName[spawnPointCount] = "heartsSpawn";
                    }
                    if (spawnPointCount == 2) {
                        instanceName[spawnPointCount] = "keysSpawn";
                    }
                    if (spawnPointCount == 4) {
                        instanceName[spawnPointCount] = "bombsSpawn";
                    }
                    System.out.println("iName: " + instanceName[spawnPointCount]);
                    System.out.println("i , j: " + i + ", " + j);
                    spawnPointCount += 1;
                }
            }
        }
    }
    public int getX(int i) {
        return xt[i];
    }
    public int getY(int i){
        return yt[i];
    }
    public String getInstanceName(int i){
        return instanceName[i];
    }

}

