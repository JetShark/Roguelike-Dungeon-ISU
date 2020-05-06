import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Cowabunga extends JPanel {
    //@Override

    MapLayer currentMap;

    public Cowabunga() throws Exception {

        currentMap = new MapLayer("Map System/Test Map_Map.csv", "Map System/DawnLike/Objects/floor.png");



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
            }
            @Override
            public void keyPressed(KeyEvent e) {
                //Passes the KeyEvent e to the ball instance
                //list[0].keyPressed(e);
            }
        }); //NOTE THE SEMI-COLON!!!!

        //Making sure our JPanel has the focus and therefor it is the
        //instance that will receive the keyboard input
        setFocusable(true);
    }


    private void move() { //Have the ball move
        // FIXME: 2020-05-05 need to have the player move function
    }

    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.RED);
        g2d.setBackground(Color.black);

        currentMap.paint(g2d);

    }

    public static void main(String[] args) throws Exception {
        JFrame frame = new JFrame("Michelangelo");
        //Add our JPanel to the frame
        Cowabunga p = new Cowabunga();
        frame.add(p);
        frame.setSize(1020 ,640);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        while (true)
        {
            p.move(); //Updates the coordinates
            p.repaint(); //Calls the paint method
            Thread.sleep(10); //Pauses for a moment
            //Thread.sleep(1); //Pauses for a moment
        }
    }
}
