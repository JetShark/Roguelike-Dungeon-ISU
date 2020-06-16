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
        findRooms();
        System.out.println(roomList.size());
    }
    //Accessors
    public MapLayer getLevel(){
        return level[1];
    }

    //Mutators

    public void openDoor(int doorX, int doorY) {
        level[2].setLevelTile(doorX,doorY,-1);
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
        int i=0;

        for (int y = 0; y < level.length - 1; y++) {
            if(y == 0){
                level[y].fillEmptySpaces(g);
            }
            level[y].paint(g);
        }

        for (Room room:roomList) {
            g.setColor(new Color(255*((i/4)&1),255*(((i>>1)&1)),255*(i&1),128));
            room.paint(g);
            i++;
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
        int x;
        int y;
        for (y = 1; y < level[2].getHeight()-1; y++) {
            for (x = 1; x < level[2].getWidth()-1; x++) {
                if (level[2].getLevel(x, y) != -1) {
                    findRoomsNearDoor(x, y);

                }
            }
        }
    }
//Layer[2] is doors, layer[1] is door
    private void findRoomsNearDoor(int x, int y) {
        System.out.println("findroomsneardoor"+x+", "+y);
        if (level[1].getLevel(x,y-1) == -1) {
            findRoomDirection(x,y,0,-1);
        }
        if (level[1].getLevel(x,y+1) == -1) {
            findRoomDirection(x,y,0,+1);
        }
        if (level[1].getLevel(x-1,y) == -1) {
            findRoomDirection(x,y,-1,0);
        }
        if (level[1].getLevel(x+1,y) == -1) {
            findRoomDirection(x,y,+1, 0);
        }
    }

    private void findRoomDirection(int x, int y, int deltaX, int deltaY) {
        int width = 0;
        int height = 0;
        for (Room room:roomList) {
            if (room.isTileInRoom(x+deltaX, y+deltaY)) {
                return;
            }
        }
        int distance = 0;
        int negaDistance = 0;
        distance = findWall(x,y,deltaX,deltaY) -1;
        System.out.println("this is first find wall"+x+", "+y+", "+deltaX+", "+deltaY+", "+distance);
        width = distance * deltaX;
        height = distance * deltaY;
        distance = findWall(x + deltaX, y + deltaY, + deltaY, -deltaX); //rotate 90 degrees to find the other axis
        negaDistance = findWall(x + deltaX, y + deltaY, -deltaY, +deltaX); //rotate -90 degrees
        //distance = 1;
        //negaDistance = 1;

        width += (distance+negaDistance-1)*deltaY;
        height += (distance+negaDistance-1)*deltaX;


        int x0 = Math.min(x-distance*deltaY, x-distance*deltaY+width);
        int y0 = Math.min(y-distance*deltaX, y-distance*deltaX+height);
        int x1 = Math.max(x-distance*deltaY, x-distance*deltaY+width);
        int y1 = Math.max(y-distance*deltaX, y-distance*deltaX+height);
        if (deltaX==0 && deltaY==1) {
            x0 = Math.min(x-negaDistance*deltaY, x-negaDistance*deltaY+width);
            y0 = Math.min(y-negaDistance*deltaX, y-negaDistance*deltaX+height);
            x1 = Math.max(x-negaDistance*deltaY, x-negaDistance*deltaY+width);
            y1 = Math.max(y-negaDistance*deltaX, y-negaDistance*deltaX+height);
        }
        if(deltaX == 1 || deltaY ==1) {
            x0++;
            y0++;
        } else {
            x1--;
            y1--;
        }
        Room room = new Room(x0, y0, x1, y1);
        roomList.push(room);
        addDoorsToRoom(room, x0, y0, x1, y1);
    }

    private void addDoorsToRoom(Room room, int x0, int y0, int x1, int y1) {
        int x;
        int y;

        for (x=x0; x<=x1; x++) {
            if (level[2].getLevel(x, y0-1) != -1) {
              room.addDoor(x, y0-1, level[2].getLevel(x, y0-1));
            }
            if (level[2].getLevel(x, y1-0) != -1) {
              room.addDoor(x, y1, level[2].getLevel(x, y1-0));
            }
        }
        for (y=y0; y<=y1; y++) {
            if (level[2].getLevel(x0-1, y) != -1) {
              room.addDoor(x0-1, y, level[2].getLevel(x0-1, y));
            }
            if (level[2].getLevel(x1+0, y) != -1) {
              room.addDoor(x1, y, level[2].getLevel(x1+0, y));
            }
        }
    }

    private int findWall(int x, int y, int deltaX, int deltaY) {
        int distance = 0;
        do {
            x = x+deltaX;
            y = y+deltaY;
            distance++;
        } while (level[1].getLevel(x, y) == -1);
        return distance;
    }

    public Room getPlayerRoom(Player player) {
        for (Room room:roomList) {
            if (room.isPlayerInRoom(player.getX(), player.getY())) {
                return room;
            }
        }
        return null;
    }

    public void closeDoor(int doorX, int doorY, int doorTile) {
        level[2].setLevelTile(doorX,doorY,doorTile);
    }
    public void closeAllDoors() {
        for (Room room:roomList) {
            room.closeDoors(this);
        }
    }
}
