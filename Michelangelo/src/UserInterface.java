import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class UserInterface {
    private Boolean Esc = false;
    private int x =510, y = 40;
    private int mx, my;
    private BufferedImage titleScreen;
    private boolean newGame = false;
    public UserInterface(){
        newGame = false;
        try {
            titleScreen = ImageIO.read(new File("Test Screens/Test-Title-Screen.png"));
        } catch (IOException e) {
            System.out.println("No Image");
        }
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
    public void mouseClicked(MouseEvent e){

    }
    public void mousePressed(MouseEvent e){
        mx = e.getX();
        my = e.getY();

        if(mx >= 17 && mx <= 164 && my >= 510 && my <= 556){
            newGame = true;
        }
        if(mx >= 16 && mx <= 156 && my >= 565 && my <= 610){
            System.exit(0);
        }
    }
    public void mouseReleased(MouseEvent e){

    }
    public void mouseEntered(MouseEvent e){

    }
    public void mouseExited(MouseEvent e){

    }

    public boolean getNewGame(){
        return newGame;
    }
    public boolean getEsc(){
        return Esc;
    }

    public void paint(Graphics2D g2d){
        g2d.drawImage(titleScreen, 0,0, null);
        //titleScreen(g2d);
        if(Esc){
            g2d.drawRect(x - 200, y,400,520);
        }
    }


    /*private void titleScreen(Graphics2D g2d){
        g2d.drawImage(titleScreen, 0,0, null);
    }
    private void pauseScreen(){

    }
    private void optionsScreen(){

    }
    private void collectionScreen(){

    }  */
}
