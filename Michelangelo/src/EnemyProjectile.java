import java.awt.*;
import java.awt.image.BufferedImage;

public class EnemyProjectile {
    private BufferedImage page = SpriteRetrival.getSprite(0,0,9);
    private BufferedImage arrow = SpriteRetrival.getSprite(3,0,9);
    private BufferedImage fireBall = SpriteRetrival.getSprite(1,0,9);
    private BufferedImage img;
    private double x;
    private double y;
    private int direction = 0;
    private double xa;
    private double ya;
    private int pX,pY;
    private Player p;
    private Cowabunga cb;
    boolean active = false;
    private boolean clicked = false;
    private boolean collision = false;
    public EnemyProjectile(Cowabunga cb, int x, int y){
        if (cb != null) {
            this.p = cb.getP();
            pX = p.getX();
            pY = p.getY();
            ya = (pY - y);
            xa = (pX - x);
            /*xa = (x - pX);
            ya = (y - pY);*/
            double magnitude = Math.sqrt(xa * xa + ya * ya);
            if (magnitude > 0) {
                xa = xa / magnitude * 1;
                ya = ya / magnitude * 1;
            }
            this.x = x;
            this.y = y;
        }
    }
    public void setSpawn(int x, int y){

    }
    public void setProjectile(int i){
        if(i == 1){
           img = page;
        }
        if(i == 2){
            img = arrow;
        }
        if(i == 3){
            img = fireBall;
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
        } else {
            x = x + xa;
        }
        if (p.getML().checkCollision((int)x, yt)) {
            y += 0;
        } else {
            y = y + ya;
        }
    }
    public void paint(Graphics2D g2d){
        if(img != null) {
            int width = img.getWidth();
            int height = img.getHeight();
            int sX = (int) (x - width / 2);
            int sY = (int) (y - height / 2);
            g2d.drawImage(img, sX, sY, width, height, null);
        }
    }
    public void courtWizardAttacks(Graphics2D g2d){

    }
}
