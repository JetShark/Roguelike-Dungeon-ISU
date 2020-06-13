import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
public class Collection {
    private BufferedImage collectionScreen;
    private UserInterface ui;
    private boolean esc = true;
    public Collection(UserInterface ui){
        this.ui = ui;
        try {
            collectionScreen = ImageIO.read(new File("Test Screens/Collection-Screen.png"));
        } catch (IOException e) {
            System.out.println("No Image");
        }
    }
    public void keyPressed(KeyEvent e){
        if(e.getKeyCode() == KeyEvent.VK_ESCAPE){
            esc = false;
            ui.setTitleScreen(true);
            ui.setCollectionScreen(false);
            //ui.setEsc(esc);
        }
    }
    public void keyReleased(KeyEvent e){
        if(e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            //esc = false;
        }
    }
    public void mousePressed(MouseEvent e){

    }
    public void mouseReleased(MouseEvent e){

    }
    public void paint(Graphics2D g2d){
        if(esc) {
            g2d.drawImage(collectionScreen, 0, 0, null);
        }
    }
}
