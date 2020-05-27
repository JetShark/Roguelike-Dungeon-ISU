import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

//import java.awt.event.MouseEvent;

public class WeaponProjectile {

    //Attributes
    private BufferedImage[] youMonster = {SpriteRetrival.getSprite(1,18,1)};
    private animation idleYouMonster = new animation(youMonster,10);
    private double x = 10;
    private double y = 10;
    private int direction = 0;
    private double xa = 1;
    private double ya = 1;
    private int mX = 1;
    private int mY = 1;
    private Player p;
    boolean active = false;
    private boolean clicked = false;



    private animation animation;

    //Constructor

    public WeaponProjectile(Player p, double mX, double mY) {
        // FIXME: 2020-05-26 suck an egg
        System.out.println("here1 "+xa);

        if (p != null) {
            this.p = p;
            int pX = p.getX();
            int pY = p.getY();
            xa = (mX + p.getCamX() - pX);
            System.out.println("xa:"+xa+"ya:"+ya+"px:"+pX+"pY:"+pY+"mX:"+mX+"camX:"+p.getCamX());
            //xa = (mX);
            ya = (mY + p.getCamY() - pY);
            double magnitude = Math.sqrt(xa * xa + ya * ya);
            if (magnitude > 0) {
                xa = xa / magnitude * 3;
                ya = ya / magnitude * 3;
            }
            x = pX + p.getCamX();
            y = pY + p.getCamY();
        }
    }

    //Accessors

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
        x = (int)(x + xa);
        y = (int)(y + ya);
    }
    public void paint(Graphics2D g2d){
        animation = idleYouMonster;
        animation.start();
        animation.update();
        int sX=(int)x;
        int sY=(int)y;
        if (p != null) {
            sX = sX - p.getCamX();
            sY = sY - p.getCamY();
        }
        g2d.drawImage(animation.getSprite(), sX, sY,animation.getSprite().getHeight() * 1, animation.getSprite().getWidth() * 1, null);
    }
}