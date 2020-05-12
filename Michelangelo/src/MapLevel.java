import java.awt.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class MapLevel {

    //Attributes
    private MapLayer[] level = new MapLayer[6];
    private String mapPath;
    private Collision[] c = new Collision[4];
    //level = new MapLayer[6];

    /*
    [1]
    floor=maps/my_floor.csv,sprite/sheets/floor.png
    wall=maps/my_wall.csv,sprite/sheets/wall.png
    door=maps/my_door.csv,sprite/sheets/door0.png,sprite/sheets/door1.png
    decor=...
    item=...

     */
    //Constructor
    public MapLevel(String levelPath, String levelName)
    {
        //level = new MapLayer[6];

        try {
            BufferedReader levelReader = new BufferedReader(new FileReader(levelPath));
            try {
                String row;
                int i=0;

                while ((row = levelReader.readLine()) != null) {
                    if (row.equals("["+levelName+"]")) {
                        while ((row = levelReader.readLine()) != null) {
                            String[] layer = row.split("=");
                            if (layer.length<2) {break;}
                            String[] attribute = layer[1].split(",");
                            level[i] = new MapLayer(attribute[0], attribute[1]);
                            if(i <= 2){
                                //c[i] = new Collision(attribute[0]);
                            }
                            i++;
                        }
                        break;
                    }
                }
                /*
                System.out.println("Map Width & Height = " + width + ", " + height);
                img = ImageIO.read(new File(tilePath));
                tilesAcross = img.getWidth() / tileWidth;
                tilesDown = img.getHeight() / tileHeight;
                */
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println(e.toString());
            }
            //this.ba = ba;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println(e.toString());
        }
    }
    //Accessors
    public MapLayer getLevel(){
        return level[1];
    }

    //Mutators

    //Methods

    public void paint(Graphics2D g) {
        for (int y = 0; y < level.length - 1; y++) {
            level[y].paint(g);
        }

        //level[1].paint(g);
    }
}
