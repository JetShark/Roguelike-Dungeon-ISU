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

    private boolean projetile = false;
    private int newmX, newmY;
    private PlayerCursor pc;
    private Cowabunga cb;
    private MapLevel ml;
    private Bomb b;

    private boolean dodgeRoll = false;
    private int xRollDistance = 0;
    private double xDistanceRolled = 0;
    private int yRollDistance = 0;
    private double yDistanceRolled = 0;
    private double xRollSpeed = 0;
    private double yRollSpeed = 0;
    private int xMaxDistance = 30;
    private int yMaxDistance = 30;
    private double rpX = 100;
    private double rpY = 100;



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
    private MouseEvent et;

    private int health;
    private int numOfHearts;
    private int damage;
    private int damageCount = 0;
    private int hitInvincibility = 100;
    private boolean invulnerable = false;
    private boolean hit = false;
    private boolean alive;

    private int hitboxX, hitboxY, hitboxXT, hitboxYT;
    private double passX;
    private double passY;
    private BufferedImage[] walking = {SpriteRetrival.getSprite(0,0,5), SpriteRetrival.getSprite(2,0, 5), SpriteRetrival.getSprite(0,1,5), SpriteRetrival.getSprite(1,1, 5)};
    private Animation Walking = new Animation(walking,25);
    private Animation animation = Walking;
    private int direction = 1;

    private BufferedImage[] idleFront = {SpriteRetrival.getSprite(0,0, 2)};
    private Animation IdleFront = new Animation(idleFront, 25);

    private BufferedImage[] idleBack = {SpriteRetrival.getSprite(1,0, 2)};
    private Animation IdleBack = new Animation(idleBack, 25);

    private BufferedImage[] idleSide = {SpriteRetrival.getSprite(2,0, 2)};
    private Animation IdleSide = new Animation(idleSide, 25);

    private BufferedImage[] walkingSide = {SpriteRetrival.getSprite(4,0, 2), SpriteRetrival.getSprite(7,0,2)};
    private Animation WalkingSide = new Animation(walkingSide, 25);

    private BufferedImage[] walkingFront = {SpriteRetrival.getSprite(0,1, 2), SpriteRetrival.getSprite(1,1,2), SpriteRetrival.getSprite(2,1,2), SpriteRetrival.getSprite(3,1,2)};
    private Animation WalkingFront = new Animation(walkingFront, 15);

    private BufferedImage[] walkingBack = {SpriteRetrival.getSprite(4,1, 2), SpriteRetrival.getSprite(7,1,2)};
    private Animation WalkingBack = new Animation(walkingBack, 25);

    private Animation lastMovmentButton;
    private Animation lastIdleButton;
    private boolean moving = false;

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
        ml = cb.getMapLevel();
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
        lastIdleButton = IdleFront;

        health = 12;
        numOfHearts = 3;
        alive = true;
    }

    public void resetPlayer(){
        animation = IdleFront;
        health = 12;
        numOfHearts = 3;
        alive = true;
    }

    public void keyPressed(KeyEvent e){
        if (e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_W){
            up = true;
            speedy = -3;
            lastIdleButton = IdleBack;
            lastMovmentButton = WalkingBack;
            //Animation = IdleBack;
        }
        if (e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_A){
            direction = -1;
            left = true;
            speedx = -3;
            lastIdleButton = IdleSide;
            lastMovmentButton = WalkingSide;
            //Animation = WalkingSide;
        }
        if (e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_D){
            direction = 1;
            right = true;
            speedx = 3;
            lastIdleButton = IdleSide;
            lastMovmentButton = WalkingSide;
            //Animation = WalkingSide;
        }
        if (e.getKeyCode() == KeyEvent.VK_DOWN || e.getKeyCode() == KeyEvent.VK_S){
            down = true;
            speedy = 3;
            lastIdleButton = IdleFront;
            lastMovmentButton = WalkingFront;
            //Animation = WalkingFront;
        }
        if(e.getKeyCode() == KeyEvent.VK_C){
            //dodgeRoll = true;
        }
    }
    public void keyReleased(KeyEvent e){
        if (e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_A){
            //Animation = IdleSide;
            if(!right && !up && !down && left){
                moving = false;
            }
            left = false;
            speedx = 0;
        }
        if (e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_D){
            //Animation = IdleSide;
            if(right && !up && !down && !left){
                moving = false;
            }
            right = false;
            speedx = 0;
        }
        if (e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_W){
            //Animation = IdleBack;
            if(!right && up && !down && !left){
                moving = false;
            }
            up = false;
            speedy = 0;
        }
        if (e.getKeyCode() == KeyEvent.VK_DOWN || e.getKeyCode() == KeyEvent.VK_S){
            //Animation = IdleFront;
            if(!right && !up && down && !left){
                moving = false;
            }
            down = false;
            speedy = 0;
        }
        if(e.getKeyCode() == KeyEvent.VK_C){
            //dodgeRoll = false;
            dodgeRoll();
            dodgeRoll = true;
        }
        if(e.getKeyCode() == KeyEvent.VK_F){
            b.placeBomb();
        }
    }
    public void mouseDragged(MouseEvent e){

    }
    public void mouseMoved(MouseEvent e){
        mX = e.getX() + camX;
        mY = e.getY() + camY;
        this.et = e;
    }
    public void mousePressed(MouseEvent e){};
    public void mouseReleased(MouseEvent e){};
    public void move(){
        playerHealth();
        b = cb.getBomb();
        if(alive) {
            int xt = x, yt = y;
            if (right && !dodgeRoll) {
                speedx = 3;
                moving = true;
            }
            if (left && !dodgeRoll) {
                speedx = -3;
                moving = true;
            }
            if (down && !dodgeRoll) {
                speedy = 3;
                moving = true;
            }
            if (up && !dodgeRoll) {
                speedy = -3;
                moving = true;
            }
        /*
        if(dodgeRoll) {
            y = mY - 35;
            x = mX - 45;
        }
        */
            if (speedx != 0 || speedy != 0) {
                xt += speedx;
                yt += speedy;
                if (ml.checkCollision(xt, y)) {
                    speedx = 0;
                } else {
                    x += speedx;
                }
                if (ml.checkCollision(x, yt)) {
                    speedy = 0;
                } else {
                    y += speedy;
                }
                hitboxX = x + 8;
                hitboxY = y + 5;
                hitboxXT = x + animation.getSprite().getWidth() - 8;
                hitboxYT = y + animation.getSprite().getHeight() - 7;
                //System.out.println("x,y = " + speedx + ", " + speedy);

            }
            while (dodgeRoll) { // FIXME: 2020-05-27 not formated correctly, should possibly be changed to do something in move instead
                if (xRollDistance > xDistanceRolled) {
                    x = (int) (x + xRollSpeed);
                    xDistanceRolled = xDistanceRolled + xRollSpeed;
                }
                if (yRollDistance > yDistanceRolled) {
                    y = (int) (y + yRollSpeed);
                    yDistanceRolled = yDistanceRolled + yRollSpeed;
                }

                if (xDistanceRolled >= xRollDistance && yDistanceRolled >= yRollDistance) {
                    dodgeRoll = false;
                }
            }
        }
        //x = x + speed;
        //x = x + speed;
    }
    public void dodgeRoll() {
        mX = et.getX() + getCamX();
        mY = et.getY() + getCamX();
        rpX = x + getCamX();
        rpY = y + getCamY();
        /*Old Function Here
        y = mY - 35;
        x = mX - 45 ;
        */

        int playerMouseXDiff = (int)(x - mX);
        int playerMouseYDiff = (int)(y - mY);

        xDistanceRolled = 0;
        yDistanceRolled = 0;

        if (playerMouseXDiff < xMaxDistance) {
            xRollDistance = playerMouseXDiff;
        } else {
            xRollDistance = xMaxDistance;
        }
        if (playerMouseXDiff > -xMaxDistance) {
            xRollDistance = playerMouseXDiff;
        } else {
            xRollDistance = -xMaxDistance;
        }

        if (playerMouseYDiff < yMaxDistance && playerMouseXDiff > -yMaxDistance) {
            yRollDistance = playerMouseYDiff;
        } else {
            yRollDistance = yMaxDistance;
        }
        xRollSpeed = 0.005 * xRollDistance;
        yRollSpeed = 0.005 * yRollDistance;
        /*
        while (dodgeRoll) { // FIXME: 2020-05-27 not formatted correctly, should possibly be changed to do something in move instead
            if (xRollDistance > xDistanceRolled) {
                x = x + 1;
            }
            if (yRollDistance > yDistanceRolled) {
                y = y + 1;
            }

            if (xDistanceRolled >= xRollDistance && yDistanceRolled >= yRollDistance) {
                dodgeRoll = false;
            }
        }
        */
    }
    public void damage(int damage){
        this.damage = damage;
        if(!invulnerable){
            health = health - damage;
            System.out.println("health: " + health);
            if(damage == 1) {
                damageCount = damageCount + 1;
            }
            if(damage == 2) {
                damageCount = damageCount + 2;
            }
            if(damage == 3) {
                damageCount = damageCount + 3;
            }
            if(damage == 4) {
               numOfHearts = numOfHearts - 1;
            }
            hit = true;
            if(damageCount >= 4){
                numOfHearts = numOfHearts - 1;
                System.out.println("numOfHearts: " + numOfHearts);
                damageCount = 0;
            }
        }
        /*if(hit){
            invulnerable = true;
            if(hitInvincibility == 0){
                invulnerable = false;
                hitInvincibility = 100;
                hit = false;
            }
        }*/
        //System.out.println("numOfHearts: " + numOfHearts);
    }
    private void playerHealth(){
        /*if(!invulnerable){
            health = health - damage;
            hit = true;
        }*/
        if(hit){
            hitInvincibility--;
            invulnerable = true;
            if(hitInvincibility == 0){
                invulnerable = false;
                hitInvincibility = 100;
                hit = false;
                damage = 0;
            }
        }
        if(health <= 0){
            alive = false;
        }
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
        //System.out.println("health: " + health);
    }

    public void setPlayerHealth(int health){
        this.health = this.health + health;
        System.out.println("health: " + this.health);
    }
    public int getPlayerHealth(){
        return health;
    }
    public void setNumOfHearts(int numOfHearts){
        this.numOfHearts = numOfHearts;
    }
    public int getNumOfHearts(){
        return numOfHearts;
    }

    public void paint(Graphics2D g2d){
        //System.out.println("health: " + health);
        if(alive) {
            animation = IdleFront;
            if(!moving){
                animation = (lastIdleButton);
            }
            if(moving){
                animation = lastMovmentButton;
            }
            if(animation != null) {
                animation.start();
                if (direction == 1) {
                    g2d.drawImage(animation.getSprite(), x, y, animation.getSprite().getHeight(), animation.getSprite().getWidth() * 2, null);
                }

                if (direction == -1) {
                    g2d.drawImage(animation.getSprite(), x + (animation.getSprite().getWidth()), y, -animation.getSprite().getHeight(), animation.getSprite().getWidth() * 2, null);
                }
                animation.update();
            }
            g2d.setColor(Color.RED);
            //g2d.fillRect(x,y, 32,32);
            if (projetile) {
                g2d.drawOval(xA, yA, 20, 10);
            }
        }
        //g2d.drawArc(mX - 10,mY -10, 20, 20, 0, 360);
    }
    public int getCamX(){
        return this.camX;
    }
    public int getCamY(){
        return this.camY;
    }
    public int getX(){
        return x;
    }
    public int getY(){
        return y;
    }
    public int getHitboxX() {
        return hitboxX;
    }
    public int getHitboxY(){
        return hitboxY;
    }
    public int getHitboxXT() {
        return hitboxXT;
    }
    public int getHitboxYT() {
        return hitboxYT;
    }
    public boolean getAlive(){
        return alive;
    }
    public MapLevel getML(){
        return ml;
    }
    public Cowabunga getCb(){return cb;}
    public void passXY(double x, double y){
        passX = x;
        passY = y;
    }
    public int passX(){
        return (int) passX;
    }
    public int passY(){
        return (int) passY;
    }
    public void setProjetile(boolean projetile){
        this.projetile = projetile;
    }
}
