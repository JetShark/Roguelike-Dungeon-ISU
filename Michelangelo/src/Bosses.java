import java.awt.*;
import java.awt.image.BufferedImage;

public class Bosses {
    private Cowabunga cb;
    private EnemySpawnPoints eps;
    private Player p;
    private MapLevel ml;
    private int x,y;
    private int sX, sY;
    private int speedX, speedY;
    private int width, height;
    private int direction = 1;
    private Boolean canMove = false;
    private boolean firstMove = false;
    private Point point1 = new Point(4416,256);
    private Point point2 = new Point(4864,256);
    private Point point3 = new Point(4352,768);
    private Point point4 = new Point(4864,768);
    private int health;
    private int damage;
    private int defense = 0;
    private int damageCount = 0;
    private int hitInvincibility = 100;
    private boolean invulnerable = false;
    private boolean hit = false;
    private boolean alive;
    public int healthMinus = 0;
    private PlayerCursor playerCursor;
    private WeaponProjectile[] weaponProjectileList;

    private BufferedImage[] neromancerIdle = {SpriteRetrival.getSprite(0,0, 7), SpriteRetrival.getSprite(3,0,7), SpriteRetrival.getSprite(0,1,7), SpriteRetrival.getSprite(3,1,7)};
    private Animation necroIdle = new Animation(neromancerIdle, 50);
    private BufferedImage[] neromancerWalk = {SpriteRetrival.getSprite(0,2, 7), SpriteRetrival.getSprite(3,2,7), SpriteRetrival.getSprite(0,3,7), SpriteRetrival.getSprite(3,3,7)};
    private Animation necroWalking = new Animation(neromancerWalk, 50);


    private Animation bossAnimation = necroIdle;
    public Bosses(Cowabunga cb){
        this.cb = cb;
        this.p = cb.getP();

        this.playerCursor = cb.getPlayerCursor();
        this.weaponProjectileList = playerCursor.getProjectileList();
        eps = new EnemySpawnPoints("Map System/Level 1 Var 1_Entity.csv");
        for(int i = 0; i < eps.getWidth(); i++ ){
            for(int j = 0; j < eps.getHeight(); j++){
                if(eps.getLevel(i,j) == 2){
                    x = i * 64;
                    y = j * 64;
                }
            }
        }
        width = bossAnimation.getSprite().getWidth() * 3 / 2;
        height = bossAnimation.getSprite().getHeight() * 3 / 2;
        alive = true;
        health = 20;
    }

    public void necromancerMovement(){
        if(!firstMove) {
            speedY = -1;
            speedX = -1;
            firstMove = true;
        }
        if(sX < point1.x + 64 && sY < point1.y + 128 && sX > point1.x && sY > point1.y){
            speedX = 1;
            speedY = 0;
            direction = -1;
        }
        if(sX  < point2.x + 64 && sY < point2.y + 128 && sX  > point2.x && sY > point2.y){
            speedX = - 1;
            speedY = 1;
            direction = 1;
        }
        if(sX < point3.x + 128 && sY < point3.y + 64 && sX  > point3.x && sY > point3.y){
            speedX = 1;
            speedY = 0;
            direction = -1;
        }
        if(sX  < point4.x + 64 && sY < point4.y + 64 && sX  > point4.x && sY > point4.y){
            speedX = - 1;
            speedY = 1;
            direction = 1;
            firstMove = false;
        }
    }

    public void paint(Graphics2D g2d){
        float thickness = 4;
        Stroke oldStroke = g2d.getStroke();
        bossAnimation = necroIdle;
        bossAnimation.start();
        bossAnimation.update();
        if(alive) {
            if (direction == 1) {
                g2d.drawImage(bossAnimation.getSprite(), x, y, direction * bossAnimation.getSprite().getWidth() * 3, bossAnimation.getSprite().getHeight() * 3, null);
            }
            if (direction == -1) {
                g2d.drawImage(bossAnimation.getSprite(), x + bossAnimation.getSprite().getWidth(), y, direction * bossAnimation.getSprite().getWidth() * 3, bossAnimation.getSprite().getHeight() * 3, null);
            }
            if (canMove) {
                int px = cb.getP().getCamX();
                int py = cb.getP().getCamY();
                g2d.setColor(Color.BLACK);
                g2d.setStroke(new BasicStroke(thickness));
                g2d.drawRect(px + 380, py + 20, 403, 20);
                g2d.setStroke(oldStroke);
                g2d.setColor(new Color(206, 0, 24));
                g2d.fillRect(px + 382, py + 21, 400 - healthMinus, 17);

            }
        }
        if(!alive){
            Font currentFont = g2d.getFont();
            Font newFont = currentFont.deriveFont(currentFont.getSize() * 12f);
            g2d.setFont(newFont);
            g2d.drawString("You Won", cb.getP().getCamX() + 225, cb.getP().getCamY() + 160);
        }
        //g2d.fillRect(sX,sY, 10,10);
        //g2d.drawLine(4640,480,sX,sY);
        //System.out.println("sx, sy: " + sX + ", " + sY);
        //System.out.println("x, y: " + x + ", " + y);
        //g2d.setColor(Color.PINK);
        //g2d.fillRect(1000, 1000,200,200);
    }
    public void move() throws InterruptedException{
        int xt = x;
        int yt = y;
        sX = (int) (x + width);
        sY = (int) (y + height);
        if(canMove) {
            necromancerMovement();
            x += speedX;
            y += speedY;
        }
        bossHealth();
        if(alive) {
            cb.getCollision().setEnemiesHitboxs(x, y, x + 192, y + 192);
            cb.getCollision().playerCollision();
            cb.getCollision().weaponCollision();
            for (WeaponProjectile weaponProjectile : weaponProjectileList) {
                if (weaponProjectile != null) {
                    if (cb.getCollision().weaponProjectileCollision(weaponProjectile)) {
                        //damage(2); probably unnecessary now
                        damage(weaponProjectile.getDamage());
                    }
                }
            }
        }
    }
    public void inRange(){
        boolean x_Overlaps = (p.getHitboxX() < x + 192 + 200) && (p.getHitboxXT() > x - 200);
        boolean y_overlaps = (p.getHitboxY() < y + 192 + 200) && (p.getHitboxYT() > y - 200);
        boolean in_Range = x_Overlaps && y_overlaps;
        if(in_Range){
            canMove = true;
        } else {
            canMove = false;
        }
    }
    public void damage(int damage){
        this.damage = damage;
        if(!invulnerable){
            health = health - damage;
            healthMinus += 40;
            hit = true;
            //System.out.println("ehealth, damage: " + health + ", " + damage);
        }
    }
    private void bossHealth() throws InterruptedException{
        if(hit){
            hitInvincibility--;
            invulnerable = true;
            if(hitInvincibility == 0){
                invulnerable = false;
                hitInvincibility = 100;
                hit = false;
                damage = 0;
            }
        }
        if(health <= 0){
            alive = false;
            Thread.sleep(1000);
            System.exit(0);
        }
    }

    public void setBossHealth(int health){
        this.health = this.health + health;
        if(this.health > 12){
            this.health = 12;
        }
        System.out.println("health: " + this.health);
    }
    public int getBossHealth(){
        return health;
    }
}
