import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Cowabunga extends JPanel{
    private Enemies[] et = new Enemies[14];
    private ShopItems[] si = new ShopItems[5];
    private Player p;
    private Audio a;
    private Bosses boss;
    private EnemySpawnPoints eps;
    private ShopItemsSpawn sis;
    private Bone b = new Bone();
    private Weapons w;
    private ToolTip tt;
    private HeadsUp hud;
    private Collection c;
    private Collision collision;
    private PlayerCursor pc = new PlayerCursor();
    private UserInterface ui;
    //private WeaponProjectile wp = new WeaponProjectile(p);

    //@Override

    MapLayer currentMap;
    MapLevel level1;

    public Cowabunga() throws Exception {
        //currentMap = new MapLayer("Map System/Test Map_Map.csv", "Map System/DawnLike/Objects/floor.png");
        level1 = new MapLevel("Map System/Levels.txt", "1");
        p = new Player(this, "Map System/Level 1 Var 1_Entity.csv");
        pc.setPlayer(p);
        CoordConversion.setPlayer(p);
        eps = new EnemySpawnPoints("Map System/Level 1 Var 1_Entity.csv");
        sis = new ShopItemsSpawn("Map System/Level 1 Var 1_Entity.csv");
        a = new Audio();
        ui = new UserInterface(p);
        c = new Collection(ui);
        a.setVolume(0.1f);
        for (int q = 0; q < et.length; q++) {
            et[q] = new Enemies(this, (int) (6 * Math.random() - 0), eps.getX(q), eps.getY(q), p);
            //collision = new Collision(p, et[q], w);
            //collision.setClasses(p,et[q],w);
        }
        for (int t = 0; t < si.length; t++) {
            si[t] = new ShopItems(sis.getX(t), sis.getY(t), sis.getInstanceName(t), p);
        }
        hud = new HeadsUp(this, p);
        w = new Weapons(p);
        tt = new ToolTip();
        collision = new Collision(p, w, this);
        boss = new Bosses(this);

        //w = new Weapons(p.getX(), p.getY());
        //collision = new Collision("Map System/Level 1 Var 1_Wall.csv");

        addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                super.mouseMoved(e);
                setCursor(PlayerCursor.customCursor);
                p.mouseMoved(e);
                pc.MouseMoved(e);
                w.mouseMoved(e);
                //wp.mouseMoved(e);
                //p.mouseMoved(e);
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
                if (ui.getNewGame()) {
                    pc.mouseReleased(e);
                }
            }
        });
        addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                ui.mouseClicked(e);
            }

            @Override
            public void mousePressed(MouseEvent e) {
                ui.mousePressed(e);
                p.mousePressed(e);
                w.mousePressed(e);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                ui.mouseReleased(e);
                w.mouseReleased(e);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                ui.mouseEntered(e);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                ui.mouseExited(e);
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
                c.keyReleased(e);
            }

            @Override
            public void keyPressed(KeyEvent e) {
                //Passes the KeyEvent e to the ball instance
                //list[0].keyPressed(e);
                p.keyPressed(e);
                ui.keyPressed(e);
                c.keyPressed(e);
            }
        }); //NOTE THE SEMI-COLON!!!!

        //Making sure our JPanel has the focus and therefor it is the
        //instance that will receive the keyboard input
        setFocusable(true);
    }

    public MapLevel getMapLevel(){
        return level1;
    }
    public Collision getCollision(){
        return collision;
    }
    public Weapons getWeapons(){
        return w;
    }
    private void move() throws InterruptedException {
        // FIXME: 2020-05-05 need to have the player move function
        if(!ui.getEsc()){
            if(p.getAlive()) {
                for (Enemies et : et) {
                    et.move();
                    et.collision(et);
                    et.inRange();
                }
                w.move();
                //w.collision();
                p.move();
                boss.move();
                //collision.playerCollision();
            }
            pc.move();
            hud.move();
            //wp.move();
        }
    }

    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        //g2d.setColor(Color.RED);
        //g2d.setBackground(Color.black);
        //currentMap.paint(g2d);
        if(ui.getNewGame() && !ui.getEsc()){
            g.translate(-p.getCamX(), -p.getCamY());
            level1.paint(g2d);
            for(Enemies et:et){
                et.paint(g2d);
            }
            for(ShopItems si:si){
                si.paint(g2d);
            }
            p.paint(g2d);
            pc.paint(g2d);
            w.paint(g2d);
            tt.paint(g2d);
            hud.paint(g2d);
            boss.paint(g2d);
            //wp.paint(g2d);
        }
        ui.paint(g2d);
        //b.paint(g2d);
        //wt.paint(g2d);
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
