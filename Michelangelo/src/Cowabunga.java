import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Cowabunga extends JPanel{
    private Enemies et = new Enemies(this);
    private Player p = new Player(this);
    private Bone b = new Bone();
    private Weapons wt = new Weapons();
    private PlayerCursor pc = new PlayerCursor();
    private UserInterface ui = new UserInterface();
    //@Override

    MapLayer currentMap;

    public Cowabunga() throws Exception {
        currentMap = new MapLayer("Map System/Test Map_Map.csv", "Map System/DawnLike/Objects/floor.png");
        addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                super.mouseMoved(e);
                setCursor(PlayerCursor.customCursor);
                //p.MouseMoved(e);
                pc.MouseMoved(e);
                p.mouseMoved(e);
            }
            @Override
            public void mouseDragged(MouseEvent e) {
                super.mouseDragged(e);
            }
        });
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
            }
            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
            }
        });
        //This adds the KeyListener to the BallWorld. It’s in this section that
        //you HAVE to have all three key listener methods whether they are used
        //or not. We place the KeyListener in the JPanel class because as we
        //mentioned before it is the director of all the action, so it receives
        //the keyboard information and then passes it along to the appropriate
        //classes.
        addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {
                //Passes the KeyEvent e to the ball instance
                //list[0].keyReleased(e);
                p.keyReleased(e);
                ui.keyReleased(e);
            }

            @Override
            public void keyPressed(KeyEvent e) {
                //Passes the KeyEvent e to the ball instance
                //list[0].keyPressed(e);
                p.keyPressed(e);
                ui.keyPressed(e);
            }
        }); //NOTE THE SEMI-COLON!!!!

        //Making sure our JPanel has the focus and therefor it is the
        //instance that will receive the keyboard input
        setFocusable(true);
    }


    private void move() throws InterruptedException { //Have the ball move
        // FIXME: 2020-05-05 need to have the player move function
        et.move();
        p.move();
        b.move();
    }

    public void paint(Graphics g) {
        super.paint(g);
        g.translate(-p.getCamX(), -p.getCamY());
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.RED);
        g2d.setBackground(Color.black);
        currentMap.paint(g2d);
        p.paint(g2d);
        et.paint(g2d);
        b.paint(g2d);
        wt.paint(g2d);
        pc.paint(g2d);
        ui.paint(g2d);
    }


    public static void main(String[] args) throws Exception {
        JFrame frame = new JFrame("Michelangelo");
        //Add our JPanel to the frame
        Cowabunga cb = new Cowabunga();
        frame.add(cb);
        frame.setSize(1020 ,640);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        long accumulatedTimeMs = 0;
        while(true){
            long startTime =  System.currentTimeMillis();
            cb.repaint();
            while(accumulatedTimeMs >= 10){
                cb.move();
                accumulatedTimeMs -= 10;
            }
            long endTime = System.currentTimeMillis();
            accumulatedTimeMs += endTime - startTime;
        }

        /*while (true)
        {
            cb.move(); //Updates the coordinates
            cb.repaint(); //Calls the paint method
            Thread.sleep(10); //Pauses for a moment
            //Thread.sleep(1); //Pauses for a moment
        }
         */
    }

}
