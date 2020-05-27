import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
public class PlayerCursor {
    private int mX,mY;
    private int x, y;
    private Cowabunga cb;
    private Player p;
    private BufferedImage img = null;
    public static Cursor customCursor;
    private boolean clicked = false;
    private boolean projetile = false;
    private int speed = 0;
    private int nextProjectile = 0;
    private double shotCooldown = 0;
    private double shotCooldownInit = 17.5;
    private double magazineCooldown = 0;
    private double magazineCooldownInit = 100;
    private double magazineShots = 0;
    private double magazineShotsInit = 7;
    //private double shotCooldownInit = currentWeapon.getShotDelay();
    //private double magazineCooldownInit = currentWeapon.getShotDelay();
    //private double magazineShotsInit = currentWeapon.getShotDelay();

    private WeaponProjectile[] projectileList = new WeaponProjectile[16];


    public PlayerCursor(){
        try {
            img = ImageIO.read(new File("src/SpriteSheets/Custom-Cursor-Black.png"));
        } catch (IOException e) {
            System.out.println("No Image");
        }
    }
    public void MouseDragged(MouseEvent e){

    }
    public void MouseMoved(MouseEvent e){
        customCursor = Toolkit.getDefaultToolkit().createCustomCursor(img, new Point(16,16), "customCursor");
        mX = e.getX();
        mY = e.getY();
    }
    public void paint(Graphics2D g2d){
        //g2d.drawArc(x + mX,y + mY, 30,30,0,360);
        for (WeaponProjectile wp:projectileList) {
            if (wp != null) {
                wp.paint(g2d);
            }
        }
    }
    public void mousePressed(MouseEvent e) {
        if(e.getButton() == MouseEvent.BUTTON1){
            clicked = true;
        }
    }
    public void mouseReleased(MouseEvent e) {
        if(e.getButton() == MouseEvent.BUTTON1) {
            clicked = false;
        /*
        int i;
        for (i=0; i < projectileList.length; i++){
            if (projectileList[i] == null) {
                projectileList[i] = new WeaponProjectile(p, mX, mY);
                break;
            }
        }
        if (i == projectileList.length) {
            projectileList[0] = new WeaponProjectile(p, mX, mY);
        }
        */
            if (shotCooldown <= 0 && magazineCooldown <=0) {
                projectileList[nextProjectile++] = new WeaponProjectile(p, mX, mY);
                if (nextProjectile >= projectileList.length) nextProjectile = 0;

                shotCooldown = shotCooldownInit;
                if (magazineShots >= magazineShotsInit) {
                    magazineCooldown = magazineCooldownInit;
                }
                magazineShots = magazineShots + 1;
            }
        }
    }
    public void move(){
        if(clicked){
            projetile = true;
            p.setProjetile(projetile);
            speed = 2;
        }
        for (WeaponProjectile wp:projectileList) {
            if (wp != null) {
                wp.move();
            }
        }
        if (shotCooldown > 0) {
            shotCooldown = shotCooldown - 1;
        }
        if (magazineCooldown > 0) {
            magazineCooldown = magazineCooldown - 1;
        }
        if (magazineCooldown <= 0) {
            magazineShots = 0;
        }

    }
    public Cursor getCustomCursor(){
        return customCursor;
    }
    public int getmX(){
        return mX;
    }
    public int getmY(){
        return mY;
    }

    public void setPlayer(Player p) {
        this.p = p;
    }

}
