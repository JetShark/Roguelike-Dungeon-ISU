import java.awt.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class MapLevel {

    //Attributes
    private MapLayer[] level = new MapLayer[7];
    private String mapPath;
    private java.util.LinkedList<Room> roomList = new java.util.LinkedList<Room>();
    //private Collision[] c = new Collision[5];
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

    public void openDoor(int x, int y) {
        // FIXME: 2020-06-13 in progress
    }

    //Methods

    public boolean checkCollision(int x, int y){
        boolean any_collision = false;
        for(int t = 0; t < level.length - 1; t++ ) {
            if(t == 1 || t == 2 || t == 4) {
                for (int i = 0; i < level[t].getWidth(); i++) {
                    for (int j = 0; j < level[t].getHeight(); j++) {
                        int id = level[t].getLevel(i, j); //Finds the id of the tiles
                        if (id != -1) { //check if the tile is not -1
                            int xt = i * level[t].getTileWidth() * 4; // creates the x and y values for the tile
                            int yt = j * level[t].getTileHeight() * 4;
                            boolean x_overlaps = (x < xt + (level[t].getTileWidth() * 4)) && (x + 32 > xt); //checks if the x values overlap. right, left
                            boolean y_overlaps = (y < yt + (level[t].getTileHeight() * 4)) && (y + 32 > yt); //checks if the y values overlap. bottom, top
                            boolean Collision = x_overlaps && y_overlaps;
                            if (Collision) { //checks if there is collision
                                any_collision = true;
                            }
                        }
                    }
                }
                if (any_collision) {
                    return true; //returns that tshe player is colliding
                }
            }
        }
        return false;
    }

    public void paint(Graphics2D g) {
        for (int y = 0; y < level.length - 1; y++) {
            if(y == 0){
                level[y].fillEmptySpaces(g);
            }
            level[y].paint(g);
        }

        //level[1].paint(g);
    }

    private void findRooms() {
        //this should start with finding all the doors
        //then check to the left, and the right for walls, and if it finds none,
        //than it will check to see if that room has already been logged,
        // if it is a new room, it will log it, then read tiles sequentially upwards,
        // and downwards until it finds a wall, then from there read left and right until it finds a wall,
        // then back up, and down,
    }
}
