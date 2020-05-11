import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.util.concurrent.TimeUnit;

public class Player {
    private int x = 80, y = 80, xA, yA;
    private int speed = 3;
    private boolean right = false, left = false;
    private Boolean up = false, down = false;
    private boolean dodgeRoll = false;
    private boolean projetile = false;
    private int newmX, newmY;
    private PlayerCursor pc;
    private Cowabunga cb;

    /*private Point mouse = MouseInfo.getPointerInfo().getLocation(); //not needed here can be removed. but leaving it for now.
    private Point mouseLocation;
     */
    private int mX, mY;


    private BufferedImage[] walking = {SpriteRetrival.getSprite(0,0,2), SpriteRetrival.getCharacterSpriteSheet(2,0), SpriteRetrival.getCharacterSpriteSheet(0,1), SpriteRetrival.getCharacterSpriteSheet(1,1)};
    private animation Walking = new animation(walking,10);
    private animation animation = Walking;
    private int direction = 1;


    private int VIEWPORT_SIZE_X = 1020, VIEWPORT_SIZE_Y = 640; //Camera Set up and things.
    private int offsetMaxX = 5120 - VIEWPORT_SIZE_X;
    private int offsetMaxY = 2048 - VIEWPORT_SIZE_Y;
    private int offsetMinX = 0;
    private int offsetMinY = 0;
    private int camX = this.x - VIEWPORT_SIZE_X/2;
    private int camY = this.y - VIEWPORT_SIZE_Y/2;

    public Player(Cowabunga cb){
        this.cb = cb;
    }

    public void keyPressed(KeyEvent e){
        if (e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_W){
            animation.start();
            up = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_A){
            animation.start();
            direction = -1;
            left = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_D){
            direction = 1;
            animation.start();
            right = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_DOWN || e.getKeyCode() == KeyEvent.VK_S){
            animation.start();
            down = true;
        }
        if(e.getKeyCode() == KeyEvent.VK_C){
            dodgeRoll = true;
        }
    }
    public void keyReleased(KeyEvent e){
        if (e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_A){
            animation.stop();
            left = false;
            //speed = 0;
        }
        if (e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_D){
            animation.stop();
            right = false;
            //speed = 0;
        }
        if (e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_W){
            animation.stop();
            up = false;
            //speed = 0;
        }
        if (e.getKeyCode() == KeyEvent.VK_DOWN || e.getKeyCode() == KeyEvent.VK_S){
            animation.stop();
            down = false;
            //speed = 0;
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
        if (right && !dodgeRoll){
            x = x + speed;
        }
        if (left && !dodgeRoll){
            x = x - speed;
        }
        if (down && !dodgeRoll){
            y = y + speed;
        }
        if (up && !dodgeRoll) {
            y = y - speed;
        }
        if(dodgeRoll){
            y = mY - 35;
            x = mX - 45;
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
            g2d.drawImage(animation.getSprite(), x, y, animation.getSprite().getHeight() * 3, animation.getSprite().getWidth() * 3 , null);
        }

        if(direction == -1){
            g2d.drawImage(animation.getSprite(), x + (animation.getSprite().getWidth() * 2) , y, -animation.getSprite().getHeight() * 3, animation.getSprite().getWidth() * 3 , null);
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
