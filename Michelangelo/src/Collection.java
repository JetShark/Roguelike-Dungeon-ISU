import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
public class Collection {
    private BufferedImage collectionScreen;
    private UserInterface ui;
    private Player p;
    private Weapons w;
    private boolean esc = true;
    private LoreClass lc = new LoreClass();
    public Collection(UserInterface ui){
        this.ui = ui;
        p = ui.getP();
        w = new Weapons(p);
        lc.setLore("src/ItemInformation/Weapons-Lore.txt");
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
        int x = 0;
        int y = 0;
        lc.setItemLore("1");
        lc.lore();
        Font currentFont = g2d.getFont();
        Font smallTextFont = currentFont.deriveFont(currentFont.getSize() * 0.5f);
        Font newFont = currentFont.deriveFont(currentFont.getSize() * 2f);
        int strWidth = g2d.getFontMetrics(newFont).stringWidth(lc.getName());
        //System.out.println("name: " + lc.getName());
        if(esc) {
            g2d.drawImage(collectionScreen, x, y, null);
            g2d.setColor(Color.WHITE);
            g2d.setFont(newFont);
            g2d.drawString(lc.getName(), x + 500, y + 300);


        }

    }
}
