import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class UserInterface {
    private Boolean Esc = false;
    private Player p;
    private Collection c;
    private int on = 0;
    private int x =510, y = 40;
    private int mx, my;
    private BufferedImage titleScreen;
    private BufferedImage pauseMenu;
    private boolean newGame = false;
    private boolean TitleScreen;
    private boolean collectionScreen = false;

    public UserInterface(Player p){
        TitleScreen = true;
        newGame = false;
        this.p = p;
        c = new Collection(this);
        try {
            titleScreen = ImageIO.read(new File("Test Screens/Title-Screen.png"));
            pauseMenu = ImageIO.read(new File("Test Screens/Test-Pause-Menu.png"));
        } catch (IOException e) {
            System.out.println("No Image");
        }
    }
    public void keyPressed(KeyEvent e){
        if(e.getKeyCode() == KeyEvent.VK_ESCAPE){
            if(newGame) {
                if (on == 0) {
                    Esc = true;
                }
                if (on == 1) {
                    Esc = false;
                }
            }
        }
    }
    public void keyReleased(KeyEvent e){
        if(e.getKeyCode() == KeyEvent.VK_ESCAPE){
            if(newGame) {
                on = 1;
                if (!Esc) {
                    on = 0;
                }
            }
        }
    }
    public void mouseClicked(MouseEvent e){

    }
    public void mousePressed(MouseEvent e){

    }
    public void mouseReleased(MouseEvent e){
        mx = e.getX();
        my = e.getY();
        if(!newGame && !collectionScreen) {
            if (mx >= 432 && mx <= 591 && my >= 224 && my <= 256) {
                newGame = true;
                TitleScreen = false;
            }
            if (mx >= 416 && mx <= 608 && my >= 336 && my <= 368) {
                System.exit(0);
            }
            if (mx >= 432 && mx <= 591 && my >= 262 && my <= 296) {

            }
            if (mx >= 400 && mx <= 620 && my >= 300 && my <= 329) {
               collectionScreen = true;
            }
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
        if(!p.getAlive()){
            if(mx >= p.getCamX() + 560 && mx <= p.getCamX() + 660 && my >= p.getCamY() + 430 && my <= p.getCamY() + 460){
                TitleScreen = true;
                newGame = false;
            }
            if(mx >= p.getCamX() + 450 && mx <= p.getCamX() + 550 && my >= p.getCamY() + 430 && my <= p.getCamY() + 460){
                //newGame = true;
                p.resetPlayer();
            }
        }
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
    public void setCollectionScreen(boolean collectionScreen){
        this.collectionScreen = collectionScreen;
    }
    public void setTitleScreen(boolean titleScreen){
        this.TitleScreen = titleScreen;
    }
    public Player getP(){
        return p;
    }

    public void paint(Graphics2D g2d){
        if(!newGame || TitleScreen){
            titleScreen(g2d);
        }
        pauseScreen(g2d);
        deathScreen(g2d);
        collectionScreen(g2d);
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
    private void collectionScreen(Graphics2D g2d){
        if(collectionScreen) {
            c.paint(g2d);
        }
    }
    private void deathScreen(Graphics2D g2d){
        float thickness = 4;
        Stroke oldStroke = g2d.getStroke();
        x = p.getCamX();
        y = p.getCamY();
        if(!p.getAlive()){
            g2d.setColor(Color.BLACK);
            g2d.setStroke(new BasicStroke(thickness));
            g2d.drawRect(x + 255, y + 160, 510, 320);
            g2d.setStroke(oldStroke);

            g2d.setColor(new Color(0,0,225,150));
            g2d.fillRect(x + 256, y + 162, 507, 318);

            g2d.setColor(new Color(0,0,225,200));
            g2d.fillRect(x + 281, y + 251, 123, 207);
            g2d.fillRect(x + 486, y + 251, 147, 47);
            g2d.fillRect(x + 486, y + 301, 147, 47);
            g2d.fillRect(x + 486, y + 361, 147, 47);
            //g2d.fillRect(x + 451, y + 431, 97, 27);
            g2d.fillRect(x + 561, y + 431, 97, 27);

            g2d.setColor(Color.BLACK);
            g2d.setStroke(new BasicStroke(thickness));
            g2d.drawRect(x + 280, y + 250, 125, 210);
            g2d.drawRect(x + 485, y + 250, 150, 50);
            g2d.drawRect(x + 485, y + 300, 150, 50);
            g2d.drawRect(x + 485, y + 360, 150, 50);
            //g2d.drawRect(x + 450, y + 430, 100, 30);
            g2d.drawRect(x + 560, y + 430, 100, 30);
            g2d.setStroke(oldStroke);

            g2d.setColor(Color.WHITE);
            Font currentFont = g2d.getFont();
            Font smallTextFont = currentFont.deriveFont(currentFont.getSize() * 0.5f);
            Font newFont = currentFont.deriveFont(currentFont.getSize() * 2f);
            g2d.setFont(newFont);
            g2d.drawString("You've Died", x + 382, y + 210);
            g2d.setFont(smallTextFont);
            //g2d.drawString("Quick Restart", x + 463, y + 449);
            g2d.drawString("Main Menu", x + 580, y + 449);
        }
    }
}
