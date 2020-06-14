public class Room {
    //Attributes
    private int leftMostTile;
    private int topMostTile;
    private int rightMostTile;
    private int bottomMostTile;
    private int doorCount;
    private int[] doorX = new int[3];
    private int[] doorY = new int[3];
    private final int TILE_SIZE = 64; //tile size of the tiles on the spread sheet.


//Constructor

    public Room(int x0, int y0, int x1, int y1) {
        leftMostTile = x0;
        topMostTile = y0;
        rightMostTile = x1;
        bottomMostTile = y1;
        doorCount = 0;
    }

//Accessors

//Mutators
    public void addDoor(int tileX, int tileY) {
        doorX[doorCount] = tileX;
        doorY[doorCount] = tileY;
        doorCount ++;
    }
//Methods
    public void openDoors(MapLevel levelIn) {
        int i;
        for (i = 0; i<doorCount; i++) {
            levelIn.openDoor(doorX[i], doorY[i]);
        }
    }

    public boolean isTileInRoom(int x, int y) {
        if (x < leftMostTile || x > rightMostTile || y < topMostTile || y > bottomMostTile) {
            return false;
        }
        return true;
    }

    public boolean isPlayerInRoom(int x, int y) {
        if (x < leftMostTile*TILE_SIZE || x > rightMostTile*TILE_SIZE || y < topMostTile*TILE_SIZE || y > bottomMostTile*TILE_SIZE) {
            return false;
        }
        return true;
    }

}
