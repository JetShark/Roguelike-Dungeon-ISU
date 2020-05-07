import java.awt.*;
import java.awt.event.*;
import java.util.concurrent.TimeUnit;

public class Player {
    private int x = 80, y = 80;
    private int speed = 3;
    private boolean right = false, left = false;
    private Boolean up = false, down = false;
    private Cowabunga cb;
    private int VIEWPORT_SIZE_X = 1020, VIEWPORT_SIZE_Y = 640;
    private int offsetMaxX = 2400 - VIEWPORT_SIZE_X;
    private int offsetMaxY = 1440 - VIEWPORT_SIZE_Y;
    private int offsetMinX = 0;
    private int offsetMinY = 0;
    private int camX = this.x - VIEWPORT_SIZE_X/2;
    private int camY = this.y - VIEWPORT_SIZE_Y/2;

    public Player(Cowabunga cb){
        this.cb = cb;
    }

    public void keyPressed(KeyEvent e){
        if (e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_W){
            up = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_A){
            left = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_D){
            right = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_DOWN || e.getKeyCode() == KeyEvent.VK_S){
            down = true;
        }
    }
    public void keyReleased(KeyEvent e){
        if (e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_A){
            left = false;
            //speed = 0;
        }
        if (e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_D){
            right = false;
            //speed = 0;
        }
        if (e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_W){
            up = false;
            //speed = 0;
        }
        if (e.getKeyCode() == KeyEvent.VK_DOWN || e.getKeyCode() == KeyEvent.VK_S){
            down = false;
            //speed = 0;
        }
    }
    public void move(){
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
        if (right){
            x = x + speed;
        }
        if (left ){
            x = x - speed;
        }
        if (down ){
            y = y + speed;
        }
        if (up ){
            y = y - speed;
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
        g2d.setColor(Color.RED);
        g2d.fillRect(x,y, 32,32);
    }
    public int getCamX(){
        return this.camX;
    }
    public int getCamY(){
        return this.camY;
    }
}
