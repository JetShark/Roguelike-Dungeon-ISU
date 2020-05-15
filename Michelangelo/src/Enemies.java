import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.*;

public class Enemies {
    private int x, y;
    private int i;

    private Cowabunga cb;
    private int width = 0;
    private int height = 0;
    private int tilesAcross = 0;
    private int tilesDown = 0;
    private static final int tileWidth = 16;
    private static final int tileHeight = 16;
    private BufferedImage img = null;
    private int[][] level;

    private BufferedImage[] ratrunning = {SpriteRetrival.getSprite(0, 0, 3), SpriteRetrival.getSprite(3,0,3), SpriteRetrival.getSprite(0,1, 3), SpriteRetrival.getSprite(3,1,3), SpriteRetrival.getSprite(0,2,3), SpriteRetrival.getSprite(1,2,3)};
    private animation ratRunning = new animation(ratrunning, 10);
    private animation animation = ratRunning;

    public Enemies(Cowabunga cb, String mapPath){
        this.cb = cb;
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
        for(int i = 0; i < width; i++){
            for(int j = 0; j < height; j++){
                if(level[i][j] == 1){
                    this.x = i * tileWidth * 4;
                    this.y = j * tileHeight * 4;
                }
            }
        }
    }
    public void move(){
        animation.start();
        animation = ratRunning;
        animation.update();
    }
    public void paint(Graphics2D g2d){
        g2d.drawImage(animation.getSprite(), x, y,animation.getSprite().getHeight() * 2, animation.getSprite().getWidth() * 2 , null);
    }
}
