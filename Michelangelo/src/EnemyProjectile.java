import java.awt.*;
import java.awt.image.BufferedImage;

public class EnemyProjectile {
    private BufferedImage img;
    private double x = 10;
    private double y = 10;
    private int direction = 0;
    private double xa = 1;
    private double ya = 1;
    private int mX = 1;
    private int mY = 1;
    private Player p;
    private Cowabunga cb;
    boolean active = false;
    private boolean clicked = false;
    private boolean collision = false;
    public EnemyProjectile(){
        if (p != null) {
            this.p = p;
            this.cb = p.getCb();
            int pX = p.getX();
            int pY = p.getY();
            //System.out.println("xa:"+xa+" ya:"+ya+" px:"+pX+" pY:"+pY+" mX:"+mX+" camX:"+p.getCamX()+" camY:"+p.getCamY());
            ya = (mY + p.getCamY() - pY);
            xa = (mX + p.getCamX() - pX);
            double magnitude = Math.sqrt(xa * xa + ya * ya);
            if (magnitude > 0) {
                xa = xa / magnitude * 3;
                ya = ya / magnitude * 3;
            }
            x = pX;
            y = pY;
        }
    }
    public void setProjectile(int i){
        if(i == 1){

        }
        if(i == 2){

        }
        if(i == 3){

        }
        if(i == 4){

        }
    }
    public void paint(Graphics2D g2d){
        int width = img.getWidth() * 1;
        int height = img.getHeight() * 1;
        int sX=(int)(x - width/2);
        int sY=(int)(y - height/2);
        p.passXY(sX,sY);
        if(p.getAlive()) {
            g2d.drawImage(img, sX, sY, width, height, null);
        }
    }
    public void move(){
        int xt = (int)x;
        int yt = (int)y;
        //if (speedx != 0 || speedy != 0) {
        xt += (int) xa;
        yt += (int) ya;
        if (p.getML().checkCollision(xt, (int)y)) {
            x += 0;
            collision = true;
        } else {
            x = x + xa;
        }
        if (p.getML().checkCollision((int)x, yt)) {
            y += 0;
            collision = true;
        } else {
            y = y + ya;
        }
    }
}
