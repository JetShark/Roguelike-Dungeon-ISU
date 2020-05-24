import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Player {
    private int x, y, xA, yA;
    private int oldX, oldY;
    private int speedx = 0, speedy = 0;
    private boolean right = false, left = false;
    private Boolean up = false, down = false;
    private boolean dodgeRoll = false;
    private boolean projetile = false;
    private int newmX, newmY;
    private PlayerCursor pc;
    private Cowabunga cb;
    private MapLevel ml;
    private MapLayer mp;
    private Collision c = new Collision("Map System/Level 1 Var 1_Wall.csv");
    private Collision d = new Collision("Map System/Level 1 Var 1_Collision_Required_Decoration.csv");

    private int width = 0;
    private int height = 0;
    private int tilesAcross = 0;
    private int tilesDown = 0;
    private static final int tileWidth = 16;
    private static final int tileHeight = 16;
    private BufferedImage img = null;
    private int[][] level;
    /*private Point mouse = MouseInfo.getPointerInfo().getLocation(); //not needed here can be removed. but leaving it for now.
    private Point mouseLocation;
     */
    private int mX, mY;

    private BufferedImage[] walking = {SpriteRetrival.getSprite(0,0,2), SpriteRetrival.getCharacterSpriteSheet(2,0), SpriteRetrival.getCharacterSpriteSheet(0,1), SpriteRetrival.getCharacterSpriteSheet(1,1)};
    private animation Walking = new animation(walking,10);
    private animation animation = Walking;
    private int direction = 1;

    private int WORLD_WIDTH, WORLD_HEIGHT;
    private int VIEWPORT_SIZE_X = 1020, VIEWPORT_SIZE_Y = 640; //Camera Set up and things.
    private int offsetMaxX;
    private int offsetMaxY;
    private int offsetMinX;
    private int offsetMinY;
    private int camX;
    private int camY;

    public Player(Cowabunga cb, String mapPath){
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
                if(level[i][j] == 0){
                    this.x = i * tileWidth * 4;
                    this.y = j * tileHeight * 4;
                }
            }
        }
        System.out.println("x and y = " + x + ", " + y);
        WORLD_HEIGHT = height * 4 * tileHeight;
        WORLD_WIDTH = width * 4 * tileWidth;
        offsetMaxX = WORLD_WIDTH - VIEWPORT_SIZE_X;
        offsetMaxY = WORLD_HEIGHT - VIEWPORT_SIZE_Y;
        offsetMinX = 0;
        offsetMinY = 0;
        camX = this.x - VIEWPORT_SIZE_X/2;
        camY = this.y - VIEWPORT_SIZE_Y/2;
    }

    public void keyPressed(KeyEvent e){
        if (e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_W){
            animation.start();
            up = true;
            speedy = -3;

        }
        if (e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_A){
            animation.start();
            direction = -1;
            left = true;
            speedx = -3;
        }
        if (e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_D){
            direction = 1;
            animation.start();
            right = true;
            speedx = 3;
        }
        if (e.getKeyCode() == KeyEvent.VK_DOWN || e.getKeyCode() == KeyEvent.VK_S){
            animation.start();
            down = true;
            speedy = 3;
        }
        if(e.getKeyCode() == KeyEvent.VK_C){
            dodgeRoll = true;
        }
    }
    public void keyReleased(KeyEvent e){
        if (e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_A){
            animation.stop();
            left = false;
            speedx = 0;
        }
        if (e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_D){
            animation.stop();
            right = false;
            speedx = 0;
        }
        if (e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_W){
            animation.stop();
            up = false;
            speedy = 0;
        }
        if (e.getKeyCode() == KeyEvent.VK_DOWN || e.getKeyCode() == KeyEvent.VK_S){
            animation.stop();
            down = false;
            speedy = 0;
        }
        if(e.getKeyCode() == KeyEvent.VK_C){
            dodgeRoll = false;
        }
    }
    public void mouseDragged(MouseEvent e){

    }
    public void mouseMoved(MouseEvent e){
        mX = e.getX();
        mY = e.getY();
    }

    public void move(){
        animation.update();
        int xt = x, yt = y;
        /*if (right && this.x + 32 < cb.getWidth() - 48){
            x = x + speed;
        }
        if (left && this.x > cb.getWidth() - 1200){
            x = x - speed;
        }
        if (down && this.y + 32 < cb.getHeight() - 48){
            y = y + speed;
        }
        if (up && this.y > cb.getHeight() - 720){
            y = y - speed;
        }
         */
        //c.generateHitboxs(x/tileWidth/4,y/tileHeight/4);
        /*if(c.checkCollision(x,y)){
            x = x - 10;
        }
        if(c.checkCollision(x,y)){
           //x = x + 10;
        }
        if(c.checkCollision(x,y)){
           y = y + 10;
        }
        if(c.checkCollision(x,y)){
           //y = y - 10;
        }*/
        if (right && !dodgeRoll){
            speedx = 3;
        }
        if (left && !dodgeRoll){
            speedx = -3;
        }
        if (down && !dodgeRoll){
            speedy = 3;
        }
        if (up && !dodgeRoll) {
           speedy = -3;
        }
        if(dodgeRoll) {
            y = mY - 35;
            x = mX - 45;
        }
        if(speedx != 0 || speedy != 0){
            xt += speedx;
            yt += speedy;
            if(c.checkCollision(xt, y) || c.checkCollision(xt, y)){
                speedx = 0;
            } else {
                x += speedx;
            }
            if(c.checkCollision(x, yt) || d.checkCollision(x, yt)){
                speedy = 0;
            } else {
                y += speedy;
            }
            //System.out.println("x,y = " + speedx + ", " + speedy);
        }
        //x = x + speed;
        //x = x + speed;
    }
    public void paint(Graphics2D g2d){
        camX = this.x - VIEWPORT_SIZE_X/2;
        camY = this.y - VIEWPORT_SIZE_Y/2;
        if (camX > offsetMaxX) {
            camX = offsetMaxX;
        } else if (camX < offsetMinX) {
            camX = offsetMinX;
        }
        if (camY > offsetMaxY) {
            camY = offsetMaxY;
        }else if (camY < offsetMinY) {
            camY = offsetMinY;
        }
        if(direction == 1){
            g2d.drawImage(animation.getSprite(), x, y, animation.getSprite().getHeight() * 2, animation.getSprite().getWidth() * 2 , null);
        }

        if(direction == -1){
            g2d.drawImage(animation.getSprite(), x + (animation.getSprite().getWidth() * 2) , y, -animation.getSprite().getHeight() * 2, animation.getSprite().getWidth() * 2 , null);
        }
        g2d.setColor(Color.RED);
        //g2d.fillRect(x,y, 32,32);
        if(projetile){
            g2d.drawOval(xA,yA, 20,10);
        }
        //g2d.drawArc(mX - 10,mY -10, 20, 20, 0, 360);
    }
    public int getCamX(){
        return this.camX;
    }
    public int getCamY(){
        return this.camY;
    }
    public void setProjetile(boolean projetile){
        this.projetile = projetile;
    }
}
