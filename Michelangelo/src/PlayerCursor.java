import java.awt.*;
import java.awt.event.*;
import javax.imageio.ImageIO;
import javax.swing.*;
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
    private boolean cliked = false;
    private boolean projetile = false;
    private int speed = 0;

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
    }
    public void mousePressed(MouseEvent e) {
        if(e.getButton() == MouseEvent.BUTTON1){
            cliked = true;
        }
    }
    public void mouseReleased(MouseEvent e) {
        if(e.getButton() == MouseEvent.BUTTON1){
            cliked = false;
        }
    }
    public void move(){
        if(cliked){
            projetile = true;
            p.setProjetile(projetile);
            speed = 2;
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

}
