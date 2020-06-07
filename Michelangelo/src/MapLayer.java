import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;

public class MapLayer {
    //Attributes

    private Cowabunga ba;
    private int width = 0;
    private int height = 0;
    private int tilesAcross = 0;
    private int tilesDown = 0;
    private static final int tileWidth = 16;
    private static final int tileHeight = 16;
    private BufferedImage img = null;
    private int[][] level;
    private java.util.Map<Integer, BufferedImage> cachedImage = new java.util.HashMap<Integer, BufferedImage>();



//Constructor

    public MapLayer(String mapPath, String tilePath) {
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
                img = ImageIO.read(new File(tilePath));
                tilesAcross = img.getWidth() / tileWidth;
                tilesDown = img.getHeight() / tileHeight;
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println(e.toString());
            }
            this.ba = ba;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println(e.toString());
        }
    }
//Accessors
    public int getLevel(int x, int y) {
        return level[x][y];
    }
    public int getWidth(){
        return width;
    }
    public int getHeight(){
        return height;
    }
//Mutators

//Methods
    public void paint(Graphics2D g) {

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int i = (level[x][y])%tilesAcross;
                int j = (level[x][y])/tilesAcross;
                if(level[x][y] == -1){
                } else {

                    BufferedImage subimage;
                    if (cachedImage.containsKey(level[x][y])) {
                        subimage = cachedImage.get(level[x][y]);
                    } else {
                        subimage = img.getSubimage(i * tileWidth, j * tileHeight, tileWidth, tileHeight);
                        cachedImage.put(level[x][y], subimage);
                    }
                    g.drawImage(subimage, x*tileWidth*4, y*tileHeight*4, tileWidth*4, tileHeight*4, null);
                }
                //g.drawImage(img.getSubimage(i*tileWidth, j*tileHeight, tileWidth,tileHeight), x*tileWidth*4, y*tileHeight*4, tileWidth*4, tileHeight*4, null);
            }
        }
    }
    public void fillEmptySpaces(Graphics2D g){
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int i = (level[x][y])%tilesAcross;
                int j = (level[x][y])/tilesAcross;
                if(level[x][y] == -1){
                    g.setColor(Color.BLACK);
                    g.fillRect(x*tileWidth*4,y*tileHeight*4,tileWidth*4, tileHeight*4);
                }
            }
        }
    }
}
