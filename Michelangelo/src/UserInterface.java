import java.awt.*;
import java.awt.event.*;

public class UserInterface {
    private Boolean Esc = false;
    private int x =510, y = 40;
    public UserInterface(){

    }
    public void keyPressed(KeyEvent e){
        if(e.getKeyCode() == KeyEvent.VK_ESCAPE){
            Esc = true;
        }
    }
    public void keyReleased(KeyEvent e){
        if(e.getKeyCode() == KeyEvent.VK_ESCAPE){
            Esc = false;
        }
    }
    public void paint(Graphics2D g2d){
        if(Esc){
            g2d.drawRect(x - 200, y,400,520);
        }
    }
}
