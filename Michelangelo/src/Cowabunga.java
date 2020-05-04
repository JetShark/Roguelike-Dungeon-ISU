import java.awt.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import org.mapeditor.*;
public class Cowabunga extends JPanel {
    //@Override
    /*
    private Building b1 = (new Building(10, 400, 80, 200));
    //private Building b2 = (new Building(200, 400, 80, 200));
    //private Building b2 = (new Building(200, 300, 160, 400));
    //private Building b3 = (new Building(450, 400, 120, 300));
    //private Building b4 = (new Building(720, 400, 80, 200));
    //private Building b5 = (new Building(850, 350, 120, 250));
    private Car car = (new Car(this));
    private Building b2 = (new Building(200, 300, 160, 300));
    private Building b3 = (new Building(450, 400, 120, 200));
    private Building b4 = (new Building(720, 400, 80, 200));
    private Building b5 = (new Building(850, 350, 120, 250));

    //private UFO b = new UFO(this); //Add an instance of the ball object
    //private UFO c = new UFO(this, 90, 90, 5, 5); //Add an instance of the ball object

    static UFO[] list = new UFO[5];
    */

    public Cowabunga() {
        //Randomly create each Ball in the list
        for (int i = 0; i< list.length; i++) {
            if (i==0) {
                list[i] = new UFO(this, 400, 40, 0, 0, true);;
            } else {
                list[i] = new UFO(this, i*55, i*55,
                        ((int)(10 * Math.random() - 5)+1),
                        ((int)(10 * Math.random() - 5)+1),
                        false);

            }
        }


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
                list[0].keyReleased(e);
            }
            @Override
            public void keyPressed(KeyEvent e) {
                //Passes the KeyEvent e to the ball instance
                list[0].keyPressed(e);
            }
        }); //NOTE THE SEMI-COLON!!!!

        //Making sure our JPanel has the focus and therefor it is the
        //instance that will receive the keyboard input
        setFocusable(true);
    }


    private void move() { //Have the ball move
        //b.move();
        //c.move();

        //Check for collisions between each of the balls
        for (int i = 0; i < list.length; i++)
            for (int j = i+1; j<list.length; j++)
                list[i].collision(list[j]);
        //Move each Ball in the list
        for (UFO b:list) {
            b.move();
        }

        if (car.beamed != true) {
            car.move();
            car.tractor();
        }
    }

    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.RED);
        g2d.setBackground(Color.black);

        b1.paint(g2d);
        b2.paint(g2d);
        b3.paint(g2d);
        b4.paint(g2d);
        b5.paint(g2d);

        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        //b.paint(g2d); //Have the ball draw itself
        //c.paint(g2d);
        /*
        for (UFO b:list) {
            b.paint(g2d);
        }
        if (car.beamed != true) {
            car.paint(g2d);
        }
        */

    }
    public static void main(String[] args) throws InterruptedException {
        JFrame frame = new JFrame("Michelangelo");
        //Add our JPanel to the frame
        frame.add(new Cowabunga());
        frame.setSize(1020 ,640);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Cowabunga p = new Cowabunga();
        frame.add(p);

        while (true)
        {
            p.move(); //Updates the coordinates
            p.repaint(); //Calls the paint method
            Thread.sleep(10); //Pauses for a moment
            //Thread.sleep(1); //Pauses for a moment
        }
    }
}
