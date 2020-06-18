import java.awt.*;
import java.awt.geom.AffineTransform;
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
    private int damage;
    private int projectileType;
    private double imageAngleRad;

    public EnemyProjectile(Cowabunga cb, int x, int y){
        if (cb != null) {
            this.p = cb.getP();
            pX = p.getcX();
            pY = p.getcY();
            ya = (pY - y);
            xa = (pX - x);
            double dX = x - pX;
            double dY = y - pY;
            imageAngleRad = Math.atan2(dY,dX);
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
        this.projectileType = i;
        if(i == 1){
           img = page;
           damage = 2;
        }
        if(i == 2){
            img = arrow;
            damage = 2;
        }
        if(i == 3){
            img = fireBall;
            damage = 2;
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
            int sX = (int) (x + img.getWidth() / 2);
            int sY = (int) (y + img.getHeight() / 2);
            if(projectileType == 2) {
                int cx = img.getWidth() / 2;
                int cy = img.getHeight() / 2;
                AffineTransform at = new AffineTransform();
                at.translate(cx + sX, cy + sY);
                at.rotate(imageAngleRad);
                at.scale(1.5,1.5);
                at.translate(-cx, -cy);
                g2d.drawImage(img, at, null);
            }
            if(projectileType == 1) {
                g2d.drawImage(img, sX, sY, width, height, null);
            }
            if(projectileType == 3){
                //courtWizardAttacks(g2d);
                g2d.drawImage(img, sX, sY, width, height, null);
            }
        }
    }
    public void courtWizardAttacks(Graphics2D g2d){
        int width = img.getWidth();
        int height = img.getHeight();
        int sX = (int) (x + img.getWidth() / 2);
        int sY = (int) (y + img.getHeight() / 2 - 160);
        int xX = sX - 64;
        for(int i = 0; i < 5; i++){
            g2d.drawImage(img, sX, sY + i * 32, width, height, null);
        }
        for(int i = 0; i < 5; i++){
            g2d.drawImage(img, xX + i * 32, sY + 64, width, height, null);
        }

    }
    public int getX(){
        return (int) x + img.getWidth() / 2;
    }
    public int getY(){
        return (int) y + img.getHeight() / 2;
    }
    public int getDamage(){
        return damage;
    }
}
