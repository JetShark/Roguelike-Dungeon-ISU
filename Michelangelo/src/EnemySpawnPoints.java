import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;

public class EnemySpawnPoints {
    private Cowabunga cb;
    private int width = 0;
    private int height = 0;
    private int tilesAcross = 0;
    private int tilesDown = 0;
    private int[] xt = new int[100];
    private int[] yt = new int[100];
    private int x,y;
    private int spawnPointCount = 0;
    private static final int tileWidth = 16;
    private static final int tileHeight = 16;
    private BufferedImage img = null;
    private int[][] level;

    public EnemySpawnPoints(String mapPath){
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
                System.out.println("Map Width & Height = " + width + ", " + height);
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
                 if (level[i][j] == 1) {
                     this.x = i * tileWidth * 4;
                     this.y = j * tileHeight * 4;
                     xt[spawnPointCount] = i * tileWidth * 4;
                     yt[spawnPointCount] = j * tileHeight * 4;
                     //System.out.println("x, y: " + x + ", " + y + ", " + spawnPointCount);
                     //System.out.println("xt, yt: " + xt[spawnPointCount] + ", " + yt[spawnPointCount]);
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
    public int getLevel(int i, int j){
        return level[i][j];
    }
    public int getWidth(){
        return width;
    }
    public int getHeight(){
        return height;
    }
}

