import java.awt.*;
import java.awt.image.BufferedImage;

public class Enemies {
    private int xt,yt;
    private int px, py;
    private int cX,cY;
    private double xa, ya;
    private double xd, yd;
    private double magnitude;
    private static int diameter = 50;
    private boolean collision = false;
    private int x,y;
    private int speedX = 0, speedY = 0;
    private int ratMoveDelay = 100;
    private int i;
    private Player p;
    private Weapons w;
    private Cowabunga cb;
    private WorldDrops wd;
    private EnemyProjectile[] enemyProjectileList = new EnemyProjectile[20];
    private int width, height;


    private Collision collisionClass;
    private PlayerCursor playerCursor;
    private WeaponProjectile[] weaponProjectileList;

    private EnemyProjectile ep;


    private int gold;

    private int enemyNumber;

    private int hitboxX, hitboxY, hitboxXT, hitboxYT;
    private int inRangeX, inRangeY, inRangeXT, inRangeYT;
    private boolean canMove;
    private int enemyDamage;
    private int damage;
    private int health;
    private int hitInvincibility = 50;
    private boolean invulnerable = false;
    private boolean hit = false;
    private boolean alive = true;
    private boolean fireProjectile = false;
    private int projectileCount = 0;
    private int shotCooldownInit = 50;
    private int shotCooldown = shotCooldownInit;

    private int secondMovment;
    private int firstMovment = 0;
    private int ranNum;
    private boolean goldAdded;

    private BufferedImage[] ratrunning = {SpriteRetrival.getSprite(0, 0, 3), SpriteRetrival.getSprite(3,0,3), SpriteRetrival.getSprite(0,1, 3), SpriteRetrival.getSprite(3,1,3), SpriteRetrival.getSprite(0,2,3), SpriteRetrival.getSprite(1,2,3)};
    private Animation ratRunning = new Animation(ratrunning, 10);

    private BufferedImage[] flyingBook = {SpriteRetrival.getSprite(4,2,3), SpriteRetrival.getSprite(7, 2,3),SpriteRetrival.getSprite(4,3,3), SpriteRetrival.getSprite(7, 3,3), SpriteRetrival.getSprite(4,4,3), SpriteRetrival.getSprite(7, 4,3), SpriteRetrival.getSprite(4,5,3), SpriteRetrival.getSprite(7, 5,3)};
    private Animation flyingBookMoving = new Animation(flyingBook, 10);

    private BufferedImage[] gaintSpider = {SpriteRetrival.getSprite(0,6,3), SpriteRetrival.getSprite(3,6,3), SpriteRetrival.getSprite(0,7,3), SpriteRetrival.getSprite(3,7,3), SpriteRetrival.getSprite(0,8,3), SpriteRetrival.getSprite(3,8,3), SpriteRetrival.getSprite(0,9,3), SpriteRetrival.getSprite(3,9,3)};
    private Animation gaintSpiderJumping = new Animation(gaintSpider, 10);

    private BufferedImage[] ghost = {SpriteRetrival.getSprite(0, 3, 3), SpriteRetrival.getSprite(3,3,3), SpriteRetrival.getSprite(0,4, 3), SpriteRetrival.getSprite(3,4,3), SpriteRetrival.getSprite(0,5,3), SpriteRetrival.getSprite(1,5,3)};
    private Animation ghostFloating = new Animation(ghost, 10);

    private BufferedImage[] courtWizardAway = {SpriteRetrival.getSprite(4, 6, 3), SpriteRetrival.getSprite(7,6,3), SpriteRetrival.getSprite(4,7, 3), SpriteRetrival.getSprite(7,7,3), SpriteRetrival.getSprite(4,8,3)};
    private Animation courtWizardTeleportAway = new Animation(courtWizardAway, 10);
    private BufferedImage[] courtWizardBack = {SpriteRetrival.getSprite(5, 8, 3), SpriteRetrival.getSprite(7,8,3), SpriteRetrival.getSprite(4,9, 3), SpriteRetrival.getSprite(7,9,3), SpriteRetrival.getSprite(4,10,3), SpriteRetrival.getSprite(5, 10, 3)};
    private Animation courtWizardTeleportBack= new Animation(courtWizardBack,10);
    private BufferedImage[] courtWizard = {SpriteRetrival.getSprite(4,20,3), SpriteRetrival.getSprite(7,20,3)};
    private Animation courtWizardIdle = new Animation(courtWizard, 25);

    private BufferedImage[] bowKnight = {SpriteRetrival.getSprite(0, 10, 3), SpriteRetrival.getSprite(3,10,3), SpriteRetrival.getSprite(0,11, 3), SpriteRetrival.getSprite(3,11,3)};
    private Animation bowKnightIdle = new Animation(bowKnight, 10);
    private BufferedImage[] bowKnightWalk = {SpriteRetrival.getSprite(0, 12, 3), SpriteRetrival.getSprite(3,12,3), SpriteRetrival.getSprite(0,13, 3), SpriteRetrival.getSprite(1,13,3)};
    private Animation bowKnightWalking = new Animation(bowKnightWalk, 10);

    private BufferedImage[] spearKnight = {SpriteRetrival.getSprite(0, 14, 3), SpriteRetrival.getSprite(3,14,3), SpriteRetrival.getSprite(0,15, 3), SpriteRetrival.getSprite(3,15,3)};
    private Animation spearKnightIdle = new Animation(spearKnight, 10);
    private BufferedImage[] spearKnightWalk = {SpriteRetrival.getSprite(0, 16, 3), SpriteRetrival.getSprite(3,16,3), SpriteRetrival.getSprite(0,17, 3), SpriteRetrival.getSprite(1,17,3)};
    private Animation spearKnightWalking = new Animation(spearKnightWalk, 10);

    private BufferedImage[] swordKnight = {SpriteRetrival.getSprite(4, 14, 3), SpriteRetrival.getSprite(7,14,3), SpriteRetrival.getSprite(4,15, 3), SpriteRetrival.getSprite(7,15,3)};
    private Animation swordKnightIdle = new Animation(swordKnight, 10);
    private BufferedImage[] swordKnightWalk = {SpriteRetrival.getSprite(4, 16, 3), SpriteRetrival.getSprite(7,16,3), SpriteRetrival.getSprite(4,17, 3), SpriteRetrival.getSprite(5,17,3)};
    private Animation swordKnightWalking = new Animation(swordKnightWalk, 10);

    private BufferedImage[] shieldKnight = {SpriteRetrival.getSprite(4, 11, 3), SpriteRetrival.getSprite(7,11,3), SpriteRetrival.getSprite(4,12, 3), SpriteRetrival.getSprite(5,12,3)};
    private Animation shieldKnightIdle = new Animation(shieldKnight, 10);
    private BufferedImage[] shieldKnightWalk = {SpriteRetrival.getSprite(6, 12, 3), SpriteRetrival.getSprite(7,12,3), SpriteRetrival.getSprite(4,13, 3), SpriteRetrival.getSprite(7,13,3)};
    private Animation shieldKnightWalking = new Animation(shieldKnightWalk, 10);

    private Animation animation = ratRunning;

    public Enemies(Cowabunga cb, int enemyNumber, int x, int y, Player p){

        this.enemyNumber = enemyNumber;
        this.p = p;
        this.cb = cb;
        this.x = x;
        this.y = y;
        this.w = new Weapons(p);



        this.collisionClass = cb.getCollision();
        this.playerCursor = cb.getPlayerCursor();
        this.weaponProjectileList = playerCursor.getProjectileList();

        width = (int)(animation.getSprite().getWidth() / 2);
        height = (int)(animation.getSprite().getHeight() / 2);
        canMove = false;
        goldAdded = false;
        if (enemyNumber == 0) {
            enemyDamage = 1;
            health = 4;
            int ranGold = (int) (6 * Math.random() - 1);
            if(ranGold == 1){
                gold = 0;
            }
            if(ranGold == 2 || ranGold == 3){
                gold = 1;
            }
            if(ranGold == 4 || ranGold == 5){
                gold = 2;
            }
            if(ranGold == 6){
                gold = 3;
            }
        }
        if (enemyNumber == 1) {
            health = 8;
            enemyDamage = 2;
            shotCooldownInit = 75;
            int ranGold = (int) (6 * Math.random() - 1);
            if(ranGold == 1){
                gold = 0;
            }
            if(ranGold == 2 || ranGold == 3){
                gold = 1;
            }
            if(ranGold == 4 || ranGold == 5){
                gold = 2;
            }
            if(ranGold == 6){
                gold = 3;
            }
        }
        if (enemyNumber == 2) {
            shotCooldownInit = 150;
            health = 10;
            enemyDamage = 2;
            int ranGold = (int) (6 * Math.random() - 1);
            if(ranGold == 1){
                gold = 0;
            }
            if(ranGold == 2 || ranGold == 3){
                gold = 1;
            }
            if(ranGold == 4 || ranGold == 5){
                gold = 2;
            }
            if(ranGold == 6){
                gold = 3;
            }
        }
        if (enemyNumber == 3) {
            health = 12;
            enemyDamage = 4;
            int ranGold = (int) (6 * Math.random() - 1);
            if(ranGold == 1){
                gold = 0;
            }
            if(ranGold == 2 || ranGold == 3){
                gold = 1;
            }
            if(ranGold == 4 || ranGold == 5){
                gold = 2;
            }
            if(ranGold == 6){
                gold = 3;
            }
        }
        if (enemyNumber == 4) {
            shotCooldownInit = 60;
            health = 12;
            enemyDamage = 2;
            int ranGold = (int) (6 * Math.random() - 1);
            if(ranGold == 1){
                gold = 0;
            }
            if(ranGold == 2 || ranGold == 3){
                gold = 1;
            }
            if(ranGold == 4 || ranGold == 5){
                gold = 2;
            }
            if(ranGold == 6){
                gold = 3;
            }
        }
        if (enemyNumber == 5) {
            health = 12;
            enemyDamage = 4;
            int ranGold = (int) (6 * Math.random() - 1);
            if(ranGold == 1){
                gold = 0;
            }
            if(ranGold == 2 || ranGold == 3){
                gold = 1;
            }
            if(ranGold == 4 || ranGold == 5){
                gold = 2;
            }
            if(ranGold == 6){
                gold = 3;
            }
        }
        if (enemyNumber == 6){
            health = 12;
            enemyDamage = 2;
            int ranGold = (int) (6 * Math.random() - 1);
            if(ranGold == 1){
                gold = 0;
            }
            if(ranGold == 2 || ranGold == 3){
                gold = 1;
            }
            if(ranGold == 4 || ranGold == 5){
                gold = 2;
            }
            if(ranGold == 6){
                gold = 3;
            }
        }
    }
    public void move(){
        wd = cb.getWd();
        if(fireProjectile){
            if(shotCooldown <= 0) {
                fireProjectile();
                shotCooldown = shotCooldownInit;
            }
            shotCooldown--;
        }
        animation.start();
        animation.update();
        xt = x;
        yt = y;

        px = p.getX();
        py = p.getY();
        xd = x;
        yd = y;

        if(alive) {
            if (enemyNumber == 0) {
                animation = ratRunning;
                if(canMove) {
                    int ranNum = (int) (4 * Math.random() - 0);
                    if (ranNum == 0) {
                        speedX = -1;
                    } else if (ranNum == 1) {
                        speedY = -1;
                    } else if (ranNum == 2) {
                        speedX = 1;
                    } else if (ranNum == 3) {
                        speedY = 1;
                    }
                }
                hitboxX = x + 9;
                hitboxXT = x + animation.getSprite().getWidth() - 11;
                hitboxY = y + 9;
                hitboxYT = y + animation.getSprite().getHeight() - 9;
            }
            if (enemyNumber == 1) {
                animation = flyingBookMoving;
                hitboxX = x + 6;
                hitboxXT = x + animation.getSprite().getWidth() - 7;
                hitboxY = y + 8;
                hitboxYT = y + animation.getSprite().getHeight() - 11;
                if(firstMovment == 0) {
                    ranNum = (int) (4 * Math.random() - 0);
                    if (ranNum == 0) {
                        speedX = -1;
                        secondMovment = 50;
                    } else if (ranNum == 1) {
                        speedY = -1;
                        secondMovment = 50;
                    } else if (ranNum == 2) {
                        speedX = 1;
                        secondMovment = 50;
                    } else if (ranNum == 3) {
                        speedY = 1;
                        secondMovment = 50;
                    }
                    secondMovment--;
                }
                if(secondMovment == 0){
                    int ranNum2 = (int) (2 * Math.random() - 0);
                    if(ranNum == 0 || ranNum == 2) {
                        if (ranNum2 == 0) {
                            speedY = -1;
                            firstMovment = 50;
                        }
                        if (ranNum2 == 1) {
                            speedY = 1;
                            firstMovment = 50;
                        }
                        firstMovment--;
                    }
                    if(ranNum == 1 || ranNum == 3) {
                        if (ranNum2 == 0) {
                            speedX = -1;
                            firstMovment = 50;
                        }
                        if (ranNum2 == 1) {
                            speedX = 1;
                            firstMovment = 50;
                        }
                        firstMovment--;
                    }
                }
        }
            if (enemyNumber == 2) {
                animation = courtWizardIdle;
                hitboxX = x + 9;
                hitboxXT = x + animation.getSprite().getWidth() - 9;
                hitboxY = y + 8;
                hitboxYT = y + animation.getSprite().getHeight();

            }
            if (enemyNumber == 3) {
                //kinght sheild
                animation = shieldKnightIdle;
                hitboxX = x + 5;
                hitboxXT = x + animation.getSprite().getWidth() - 6;
                hitboxY = y + 5;
                hitboxYT = y + animation.getSprite().getHeight() - 5;
                xa = (px - xd);
                ya = (py - yd);
                magnitude = Math.sqrt(xa * xa + ya * ya);
                if(magnitude > 0){
                    xa = xa / magnitude * 1.25;
                    ya = ya / magnitude * 1.25;
                }
                if(canMove){
                    animation = shieldKnightWalking;
                }
            }
            if (enemyNumber == 4) {
                //knight bow
                animation = bowKnightIdle;
                hitboxX = x + 10;
                hitboxXT = x + animation.getSprite().getWidth() - 6;
                hitboxY = y + 5;
                hitboxYT = y + animation.getSprite().getHeight() - 5;
                xa = (px - xd);
                ya = (py - yd);
                magnitude = Math.sqrt(xa * xa + ya * ya);
                if(magnitude > 0){
                    xa = xa / magnitude * 2;
                    ya = ya / magnitude * 2;
                }
            }
            if (enemyNumber == 5) {
                //knight spear
                animation = spearKnightIdle;
                hitboxX = x + 10;
                hitboxXT = x + animation.getSprite().getWidth() - 6;
                hitboxY = y + 5;
                hitboxYT = y + animation.getSprite().getHeight() - 5;
                xa = (px - xd);
                ya = (py - yd);
                magnitude = Math.sqrt(xa * xa + ya * ya);
                if(magnitude > 0){
                    xa = xa / magnitude * 2;
                    ya = ya / magnitude * 2;
                }
            }
            if (enemyNumber == 6){
                //knight sword
                animation = swordKnightIdle;
                hitboxX = x + 10;
                hitboxXT = x + animation.getSprite().getWidth() - 6;
                hitboxY = y + 5;
                hitboxYT = y + animation.getSprite().getHeight() - 5;
                xa = (px - xd);
                ya = (py - yd);
                magnitude = Math.sqrt(xa * xa + ya * ya);
                if(magnitude > 0){
                    xa = xa / magnitude * 2;
                    ya = ya / magnitude * 2;
                }
            }
            if (enemyNumber == 7) {
                //haunted armour
            }
            if (enemyNumber == 8) {
                //candle
            }
        }
        if(canMove) {
            if (speedX != 0 || speedY != 0){
                if(enemyNumber != 3 && enemyNumber != 4 && enemyNumber != 5 && enemyNumber != 6) {
                    xt += speedX;
                    yt += speedY;
                    if (cb.getMapLevel().checkCollision(xt, y)) {
                        speedX = 0;
                    } else {
                        x += speedX;
                    }
                    if (cb.getMapLevel().checkCollision(x, yt)) {
                        speedY = 0;
                    } else {
                        y += speedY;
                    }
                }
            }
            if (enemyNumber == 3 || enemyNumber == 4 || enemyNumber == 5 || enemyNumber == 6) {
                xt += (int) xa;
                yt += (int) ya;
                if (cb.getMapLevel().checkCollision(xt, (int) yd)) {
                    xa = 0;
                } else {
                    if(enemyNumber != 4) {
                        xd = xd + xa;
                    }
                    if(enemyNumber == 4){
                       xd = xd - xa;
                    }
                }
                if (cb.getMapLevel().checkCollision((int) xd, yt)) {
                    ya = 0;
                } else {
                    if(enemyNumber != 4) {
                        yd = yd + ya;
                    }
                    if(enemyNumber == 4){
                        yd = yd - ya;
                   }
                }
            }
            if(enemyNumber == 1 || enemyNumber == 2 || enemyNumber == 4) {
                fireProjectile = true;
            }
        }
        if(enemyNumber == 1 || enemyNumber == 2 || enemyNumber == 4) {
            for(EnemyProjectile ep: enemyProjectileList) {
                if(ep != null) {
                    ep.move();
                }
            }
        }
        //System.out.println("canMove: " + canMove);

        cb.getCollision().setEnemiesHitboxs(hitboxX, hitboxY, hitboxXT, hitboxYT);
        cb.getCollision().setEnemyDamage(enemyDamage);
        cb.getCollision().setAlive(alive);
        cb.getCollision().playerCollision();
        for(EnemyProjectile ep: enemyProjectileList){
            if(ep != null){
                cb.getCollision().enemyProjectileCollision(ep);
            }
        }
        if(alive) {
            if (cb.getCollision().weaponCollision() && !invulnerable && !hit) {
                damage(cb.getWeapons().getDamage());
            }
            for (WeaponProjectile weaponProjectile:weaponProjectileList) {
                if (weaponProjectile!=null) {
                    if (cb.getCollision().weaponProjectileCollision(weaponProjectile)) {
                        //damage(2); probably unnecessary now
                        damage(weaponProjectile.getDamage());
                    }
                }
            }
        }
        enemyHealth();
    }
    public void collision(Enemies e){
        boolean x_Overlaps = (e.hitboxX < hitboxXT) && (e.hitboxXT > hitboxX);
        boolean y_overlaps = (e.hitboxY < hitboxYT) && (e.hitboxYT > hitboxY);
        boolean any_Collision = x_Overlaps && y_overlaps;
        if(any_Collision){
            canMove = false;
        }
    }
    public void inRange(){
        inRangeX = hitboxX - 200;
        inRangeXT = hitboxXT + 200;
        inRangeY = hitboxY - 200;
        inRangeYT = hitboxYT + 200;
        boolean x_Overlaps = (p.getHitboxX() < inRangeXT) && (p.getHitboxXT() > inRangeX);
        boolean y_overlaps = (p.getHitboxY() < inRangeYT) && (p.getHitboxYT() > inRangeY);
        boolean in_Range = x_Overlaps && y_overlaps;
        if(in_Range){
            canMove = true;
        } else {
            canMove = false;
        }
    }
    public void paint(Graphics2D g2d){
        if(alive) {
            cX = (int)(x - width);
            cY = (int)(y - height);
            if(enemyNumber != 3 && enemyNumber != 5 && enemyNumber != 6 && enemyNumber != 4) {
                g2d.drawImage(animation.getSprite(), cX, cY, animation.getSprite().getHeight() * 2, animation.getSprite().getWidth() * 2, null);
            }
            if(enemyNumber == 3 || enemyNumber == 5 || enemyNumber == 6 || enemyNumber == 4) {
                x = (int) xd;
                y = (int) yd;
                g2d.drawImage(animation.getSprite(), cX, cY, animation.getSprite().getHeight() * 2, animation.getSprite().getWidth() * 2, null);
            }
            if(enemyNumber == 4 || enemyNumber == 1 || enemyNumber == 2) {
                //if(fireProjectile) {
                    for(EnemyProjectile ep: enemyProjectileList) {
                        if(ep != null) {
                            ep.paint(g2d);
                        }
                    }
                //}
            }
        }
    }
    public void fireProjectile(){
        if(enemyNumber == 1 || enemyNumber == 4) {
            if(fireProjectile) {
                enemyProjectileList[projectileCount++] = new EnemyProjectile(cb, x - width, y - height);
                if(projectileCount >= enemyProjectileList.length) { projectileCount = 0; }
            }
            //ep.setSpawn(x,y);
        }
        if(enemyNumber == 2){
            if(fireProjectile) {
                int ranNum = (int)(2 * Math.random() - 0);
                if(ranNum == 0) {
                    for (int i = 0; i < 5; i++) {
                        enemyProjectileList[projectileCount++] = new EnemyProjectile(cb, x - width, y - height + (i * 32));
                    }
                    for (int i = 0; i < 5; i++) {
                        enemyProjectileList[projectileCount++] = new EnemyProjectile(cb, x - width + (i * 32) - 64, y - height + 64);
                    }
                }
                if(ranNum == 1) {
                    for (int i = 0; i < 5; i++) {
                        enemyProjectileList[projectileCount++] = new EnemyProjectile(cb, x - width + (i * 16), y - height + (i * 16));
                    }
                    for (int i = 0; i < 5; i++) {
                        enemyProjectileList[projectileCount++] = new EnemyProjectile(cb, x - width - (i * 16) + 64, y - height + (i * 16));
                    }
                }
                if(projectileCount >= enemyProjectileList.length) { projectileCount = 0; }
            }
        }
        if(fireProjectile) {
            for(EnemyProjectile ep: enemyProjectileList) {
                if(ep != null) {
                    if(enemyNumber == 1) {
                        ep.setProjectile(1);
                    }
                    if(enemyNumber == 2) {
                        ep.setProjectile(3);
                    }
                    if(enemyNumber == 4) {
                        ep.setProjectile(2);
                    }
                }
            }
        }
    }
    public void damage(int damage){
        this.damage = damage;
        if(!invulnerable){
            health = health - damage;
            hit = true;
            System.out.println("ehealth, damage: " + health + ", " + damage);
        }

    }
    private void enemyHealth(){
        if(hit){
            hitInvincibility--;
            invulnerable = true;
            if(hitInvincibility == 0){
                invulnerable = false;
                hitInvincibility = 50;
                damage = 0;
                hit = false;
            }
        }
        if(health == 0){
            alive = false;
            if(!goldAdded) {
                System.out.println("gold: " + gold);
                wd.setGold(gold);
                if(cb.getEnemiesInRoom() == 0) {
                    wd.setSpawn();
                    wd.setItemDrop(/*(int) (4 * Math.random() - 0)*/);
                }
                goldAdded = true;
            }
        }
    }
    public int getHitboxX() {
        return hitboxX;
    }
    public int getHitboxY(){
        return hitboxY;
    }
    public int getHitboxXT() {
        return hitboxXT;
    }
    public int getHitboxYT() {
        return hitboxYT;
    }
    public int getEnemyDamage(){return enemyDamage;}
    public Boolean isAlive(){
        return alive;
    }


    /*public void collision(){
        boolean x_overlaps = (p.getHitboxX() < hitboxXT && p.getHitboxXT() > hitboxX);
        boolean y_overlaps = (p.getHitboxY() < hitboxYT && p.getHitboxYT() > hitboxY);
        boolean collision = x_overlaps && y_overlaps;
        if(collision){
          p.damage(enemyDamage);
        }
    }
    public void weaponCollision(){
        boolean x_overlaps = (w.getHitboxX() < hitboxXT && w.getHitboxXT() > hitboxX);
        boolean y_overlaps = (w.getHitboxY() < hitboxYT && w.getHitboxYT() > hitboxY);
        boolean collision = x_overlaps && y_overlaps;
        if(collision){
            damage(w.getDamage());
        }
    }*/
}
