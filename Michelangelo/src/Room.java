import java.awt.*;

public class Room {
    //Attributes
    private int leftMostTile;
    private int topMostTile;
    private int rightMostTile;
    private int bottomMostTile;
    private int doorCount;
    private int[] doorX = new int[3];
    private int[] doorY = new int[3];
    private int[] doorTile = new int[3];
    private final int TILE_SIZE = 64; //tile size of the tiles on the spread sheet.
    private boolean firstCleared = false;


//Constructor

    public Room(int x0, int y0, int x1, int y1) {
        leftMostTile = x0;
        topMostTile = y0;
        rightMostTile = x1;
        bottomMostTile = y1;
        doorCount = 0;

        System.out.println("x0:"+x0+"y0:"+y0+"x1:"+x1+"y1:"+y1);
    }

//Accessors
    public boolean isTileInRoom(int x, int y) {
        if (x < leftMostTile || x > rightMostTile || y < topMostTile || y > bottomMostTile) {
            return false;
        }
        return true;
    }

    public boolean isPlayerInRoom(int x, int y) {
        if (x < leftMostTile*TILE_SIZE || x >= (rightMostTile+1)*TILE_SIZE-16 || y < topMostTile*TILE_SIZE || y  >= (bottomMostTile+1)*TILE_SIZE-32) {
            return false;
        }
        return true;
    }

    public boolean getFirstCleared() {
        return firstCleared;
    }
    public int getMidHorizontalTile() {
        return (int)(((rightMostTile+leftMostTile)/2)*1);
    }

    public int getMidVerticalTile() {
        return (int)(((bottomMostTile+topMostTile)/2)*1);
    }

//Mutators
    public void addDoor(int tileX, int tileY, int tile) {
        doorX[doorCount] = tileX;
        doorY[doorCount] = tileY;
        doorTile[doorCount] = tile;
        doorCount ++;
        System.out.println("Added door at:"+tileX+", "+tileY+", "+doorCount);
    }

    public void openDoors(MapLevel levelIn) {
        int i;
        for (i = 0; i<doorCount; i++) {
            levelIn.openDoor(doorX[i], doorY[i]);
        }
    }

    public void closeDoors(MapLevel levelIn) {
        int i;
        for (i = 0; i<doorCount; i++) {
            levelIn.closeDoor(doorX[i], doorY[i], doorTile[i]);
        }
    }

    public void setFirstCleared() {
        if (!firstCleared) {
            firstCleared = true;
        }
    }
//Methods

    public void paint(Graphics2D g) {
        //g.setColor(new Color(0,0,255,128));
        //g.fillRect(leftMostTile*TILE_SIZE,topMostTile*TILE_SIZE, (rightMostTile-leftMostTile)*TILE_SIZE,(bottomMostTile-topMostTile)*TILE_SIZE);

        g.fillRect(leftMostTile*TILE_SIZE,topMostTile*TILE_SIZE, (rightMostTile-leftMostTile+1)*TILE_SIZE,(bottomMostTile-topMostTile+1)*TILE_SIZE);
        g.setColor(Color.cyan);
        g.drawString(("x0:"+leftMostTile+"y0:"+topMostTile+"x1:"+rightMostTile+"y1:"+bottomMostTile+"doors:"+doorCount), leftMostTile*TILE_SIZE,topMostTile*TILE_SIZE);
        g.drawString(("x0:"+leftMostTile+"y0:"+topMostTile+"x1:"+rightMostTile+"y1:"+bottomMostTile+"doors:"+doorCount), rightMostTile*TILE_SIZE,topMostTile*TILE_SIZE);
        g.drawString(("x0:"+leftMostTile+"y0:"+topMostTile+"x1:"+rightMostTile+"y1:"+bottomMostTile+"doors:"+doorCount), leftMostTile*TILE_SIZE,bottomMostTile *TILE_SIZE);
        g.drawString(("x0:"+leftMostTile+"y0:"+topMostTile+"x1:"+rightMostTile+"y1:"+bottomMostTile+"doors:"+doorCount), rightMostTile*TILE_SIZE,bottomMostTile *TILE_SIZE);

    }

}
