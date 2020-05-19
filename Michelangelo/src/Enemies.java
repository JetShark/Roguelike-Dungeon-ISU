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
    private int enemyNumber;
    private int tilesAcross = 0;
    private int tilesDown = 0;
    private int spawnPointCount = 0;
    private static final int tileWidth = 16;
    private static final int tileHeight = 16;
    private BufferedImage img = null;
    private int[][] level;

    private BufferedImage[] ratrunning = {SpriteRetrival.getSprite(0, 0, 3), SpriteRetrival.getSprite(3,0,3), SpriteRetrival.getSprite(0,1, 3), SpriteRetrival.getSprite(3,1,3), SpriteRetrival.getSprite(0,2,3), SpriteRetrival.getSprite(1,2,3)};
    private animation ratRunning = new animation(ratrunning, 10);

    private BufferedImage[] flyingBook = {SpriteRetrival.getSprite(4,2,3), SpriteRetrival.getSprite(7, 2,3),SpriteRetrival.getSprite(4,3,3), SpriteRetrival.getSprite(7, 3,3), SpriteRetrival.getSprite(4,4,3), SpriteRetrival.getSprite(7, 4,3), SpriteRetrival.getSprite(4,5,3), SpriteRetrival.getSprite(7, 5,3)};
    private animation flyingBookMoving = new animation(flyingBook, 10);

    private BufferedImage[] gaintSpider = {SpriteRetrival.getSprite(0,6,3), SpriteRetrival.getSprite(3,6,3), SpriteRetrival.getSprite(0,7,3), SpriteRetrival.getSprite(3,7,3), SpriteRetrival.getSprite(0,8,3), SpriteRetrival.getSprite(3,8,3), SpriteRetrival.getSprite(0,9,3), SpriteRetrival.getSprite(3,9,3)};
    private animation gaintSpiderJumping = new animation(gaintSpider, 10);

    private BufferedImage[] ghost = {SpriteRetrival.getSprite(0, 3, 3), SpriteRetrival.getSprite(3,3,3), SpriteRetrival.getSprite(0,4, 3), SpriteRetrival.getSprite(3,4,3), SpriteRetrival.getSprite(0,5,3), SpriteRetrival.getSprite(1,5,3)};
    private animation ghostFloating = new animation(ghost, 10);

    private animation animation = ratRunning;

    public Enemies(Cowabunga cb, String mapPath, int enemyNumber){
        this.cb = cb;
        this.enemyNumber = enemyNumber;
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
    }
    public void move(){
        animation.start();
        animation.update();
        if(enemyNumber == 0){
            animation = ratRunning;
        }
        if(enemyNumber == 1){
            animation = flyingBookMoving;
        }
        if(enemyNumber == 2){
            animation = gaintSpiderJumping;
        }
        if(enemyNumber == 3){
            animation = ghostFloating;
        }
        if(enemyNumber == 4){

        }
        if(enemyNumber == 5){

        }
        if(enemyNumber == 6){

        }
        if(enemyNumber == 7){

        }
        if(enemyNumber == 8){

        }
        if(enemyNumber == 9){

        }
        if(enemyNumber == 9){

        }
        if(enemyNumber == 10){

        }
        if(enemyNumber == 11){

        }
        if(enemyNumber == 12){

        }
        if(enemyNumber == 13){

        }
        if(enemyNumber == 14){

        }
        if(enemyNumber == 15){

        }
        if(enemyNumber == 16){

        }
        if(enemyNumber == 17){

        }
        if(enemyNumber == 18){

        }
        if(enemyNumber == 19){

        }
        if(enemyNumber == 20){

        }
        if(enemyNumber == 21){

        }
        if(enemyNumber == 22){

        }
        if(enemyNumber == 23){

        }
        if(enemyNumber == 24){

        }
        if(enemyNumber == 25){

        }
    }
    public void paint(Graphics2D g2d){
        for(int i = 0; i < width; i++){
            for(int j = 0; j < height; j++){
                if(level[i][j] == 1){
                    this.x = i * tileWidth * 4;
                    this.y = j * tileHeight * 4;
                    g2d.drawImage(animation.getSprite(), x, y,animation.getSprite().getHeight() * 2, animation.getSprite().getWidth() * 2 , null); 
                }
            }
        }
    }
}
