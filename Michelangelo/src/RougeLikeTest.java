import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;
public class RougeLikeTest extends JPanel {
    private RoomTest rt = new RoomTest();
    private Enemytest et = new Enemytest(this);
    private PlayerTest pt = new PlayerTest(this);
    private Bone b = new Bone();
    //private Skeleton s = new Skeleton();
    private WeaponTest wt = new WeaponTest();
    //private PortalGun pg = new PortalGun();


    public RougeLikeTest(){
        addKeyListener(new KeyListener() {
            @Override
            public void keyTyped (KeyEvent e){
            }
            @Override
            public void keyReleased (KeyEvent e){
                //Passes the KeyEvent e to the ball instance
                pt.keyReleased(e);
            }
            @Override
            public void keyPressed (KeyEvent e){
                //Passes the KeyEvent e to the ball instance
                pt.keyPressed(e);
            }
        });
        setFocusable(true);
    }

    public void paint(Graphics g){
        int i = 0, j = 0;
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.fillRect(0,0, 1280,810);
        rt.paint(g2d);
        pt.paint(g2d);
        et.paint(g2d);
        b.paint(g2d);
        //s.paint(g2d);
        wt.paint(g2d);
        //pg.paint(g2d);
    }
    public void move() throws InterruptedException{
        et.move();
        pt.move();
        b.move();
        //s.move();
        //pg.move();
    }

    public static void main(String[] args) throws InterruptedException{
        JFrame frame = new JFrame("Rouge Like Test");
        RougeLikeTest rlt = new RougeLikeTest();
        frame.add(rlt);
        frame.setSize(1265, 808);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        long accumulatedTimeMs = 0;
        while(true){
            long startTime =  System.currentTimeMillis();
            rlt.repaint();
            while(accumulatedTimeMs >= 10){
                rlt.move();
                accumulatedTimeMs -= 10;
            }
            long endTime = System.currentTimeMillis();
            accumulatedTimeMs += endTime - startTime;
        }
        //while(true){
            //rlt.repaint();
            //rlt.move();
            //Thread.sleep(10);
        //}
    }
}
