import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

//import java.awt.event.MouseEvent;

public class WeaponProjectile {

    //Attributes
    private BufferedImage[] youMonster = {SpriteRetrival.getSprite(1,18,1)};
    private Animation idleYouMonster = new Animation(youMonster,10);
    private double x = 10;
    private double y = 10;
    private int direction = 0;
    private double xa = 1;
    private double ya = 1;
    private Animation animation = idleYouMonster;
    private int width = animation.getSprite().getWidth() * 1;
    private int height = animation.getSprite().getHeight() * 1;
    private int damage = 0;
    private int mX = 1;
    private int mY = 1;
    private Player p;
    private Cowabunga cb;
    boolean active = false;
    private boolean clicked = false;
    private boolean collision = false;



    //private Animation animation;

    //Constructor

    public WeaponProjectile(Player p, double mX, double mY, int projectileDamage) {
        // FIXME: 2020-05-26 suck an egg
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
            this.damage = projectileDamage;
        }
    }

    //Accessors

    public boolean getCollision(){
        return collision;
    }
    public double getX() {
        return x - width / 2;
    }

    public double getY(){
        return y - height/2;
    }

    public int getDamage(){
        return damage;
    }


    //Mutators

    //Methods

    public void mouseMoved(MouseEvent e){
        //customCursor = Toolkit.getDefaultToolkit().createCustomCursor(img, new Point(16,16), "customCursor");
        mX = e.getX();
        mY = e.getY();
    }

    public void MouseDragged(MouseEvent e){

    }
    public void mousePressed(MouseEvent e) {
        if(e.getButton() == MouseEvent.BUTTON1){
            clicked = true;
        }
    }

    public void mouseReleased(MouseEvent e) {
        if(e.getButton() == MouseEvent.BUTTON1){
            clicked = false;
        }
    }
    public void move() {
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
        //}
        /*x = x + xa;
        y = y + ya;*/
    }
    public void paint(Graphics2D g2d){
        //animation = idleYouMonster;
        animation.start();
        animation.update();
        int sX=(int)(x - width/2);
        int sY=(int)(y - height/2);
        p.passXY(sX,sY);
        if(p.getAlive()) {
            g2d.drawImage(animation.getSprite(), sX, sY, width, height, null);
        }
    }
}