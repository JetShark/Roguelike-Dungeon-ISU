import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
public class Collision {
    private Cowabunga ba;
    private Player p;
    private int width = 0;
    private int height = 0;
    private static final int tileWidth = 16;
    private static final int tileHeight = 16;
    private BufferedImage img = null;
    private int[][] collision;
    private int topLeftY, topLeftX, topRightY, topRightX, bottomLeftY, bottomLeftX, bottomRightY, bottomRightX;

    public Collision(String mapPath){
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
                collision = new int[width][height];
                csvReader = new BufferedReader(new FileReader(mapPath));
                int y = 0;
                while ((row = csvReader.readLine()) != null) {
                    String[] data = row.split(",");
                    for (int x = 0; x < data.length; x++){
                        collision[x][y]= Integer.parseInt(data[x]);
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
        generateHitboxs();
    }
    public void generateHitboxs(){
        for(int i = 0; i < width; i++){
            for(int j = 0; j < height; j++){
                if(collision[i][j] == 140){
                    int x = i * tileWidth * 4;
                    int y = j * tileHeight * 4;
                    topLeftX = x;
                    topLeftY = y;
                    topRightX = topLeftX + tileWidth * 4;
                    bottomLeftY = topLeftY + tileHeight * 4;
                    System.out.println("tlx, tly, trx, bly = " + topLeftX + ", " + topLeftY + ", " + topRightX + ", " + bottomLeftY);
                }
            }
        }
    }
    public boolean canMove(int xt, int yt){
        System.out.println("xt, xy = " + xt + ", " + yt);
        System.out.println("topLeftX = " + topLeftX);
        if(xt == topLeftX){
            return false;
        }
        return true;
    }
}
