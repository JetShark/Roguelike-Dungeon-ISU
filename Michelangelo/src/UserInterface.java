import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.Buffer;

public class UserInterface {
    private Boolean Esc = false;
    private int on = 0;
    private int x =510, y = 40;
    private int mx, my;
    private BufferedImage titleScreen;
    private BufferedImage pauseMenu;
    private boolean newGame = false;
    private boolean TitleScreen;
    public UserInterface(){
        TitleScreen = true;
        newGame = false;
        try {
            titleScreen = ImageIO.read(new File("Test Screens/Test-Title-Screen.png"));
            pauseMenu = ImageIO.read(new File("Test Screens/Test-Pause-Menu.png"));
        } catch (IOException e) {
            System.out.println("No Image");
        }
    }
    public void keyPressed(KeyEvent e){
        if(e.getKeyCode() == KeyEvent.VK_ESCAPE){
            if(on == 0){
                Esc = true;
            }
            if(on == 1){
                Esc = false;
            }
        }
    }
    public void keyReleased(KeyEvent e){
        if(e.getKeyCode() == KeyEvent.VK_ESCAPE){
            on = 1;
            if(!Esc){
                on = 0;
            }
        }
    }
    public void mouseClicked(MouseEvent e){

    }
    public void mousePressed(MouseEvent e){
        mx = e.getX();
        my = e.getY();
        if(mx >= 17 && mx <= 164 && my >= 510 && my <= 556){
            newGame = true;
            TitleScreen = false;
        }
        if(mx >= 16 && mx <= 156 && my >= 565 && my <= 610){
            System.exit(0);
        }
        if(Esc){
            if(mx >= 391 && mx <= 630 && my >= 103 && my <= 166){
                Esc = false;
            }
            if(mx >= 391 && mx <= 630 && my >= 215 && my <= 278){

            }
            if(mx >= 391 && mx <= 630 && my >= 327 && my <= 390){
                TitleScreen = true;
                newGame = false;
                Esc = false;
            }
            if(mx >= 391 && mx <= 630 && my >= 439 && my <= 502){
                System.exit(0);
            }
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
        if(!newGame || TitleScreen){
            titleScreen(g2d);
        }
        pauseScreen(g2d);
    }

    private void titleScreen(Graphics2D g2d){
        g2d.drawImage(titleScreen, 0,0, null);
    }
    private void pauseScreen(Graphics2D g2d){
        if(Esc){
            g2d.drawImage(pauseMenu,334,44,null);
            g2d.setColor(Color.BLACK);
            g2d.fillRect(0,0,334,640);
            g2d.fillRect(0,0, 1020, 44);
            g2d.fillRect(686, 0, 334, 640);
            g2d.fillRect(0, 571,1020, 44);
        }
    }
    private void optionsScreen(){

    }
    private void collectionScreen(){

    }
}
