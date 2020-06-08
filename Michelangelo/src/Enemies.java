import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.*;

public class Enemies {
    private int xt,yt;
    private int x,y;
    private int speedX = 0, speedY = 0;
    private int ratMoveDelay = 100;
    private int i;
    private Player p;
    private Collision c = new Collision("Map System/Level 1 Var 1_Wall.csv");

    private int enemyNumber;

    private int hitboxX, hitboxY, hitboxXT, hitboxYT;
    private int enemyDamage;
    private int damage;
    private int health;
    private int hitInvincibility = 100;
    private boolean invulnerable = false;
    private boolean hit = false;
    private boolean alive = true;


    private BufferedImage[] ratrunning = {SpriteRetrival.getSprite(0, 0, 3), SpriteRetrival.getSprite(3,0,3), SpriteRetrival.getSprite(0,1, 3), SpriteRetrival.getSprite(3,1,3), SpriteRetrival.getSprite(0,2,3), SpriteRetrival.getSprite(1,2,3)};
    private animation ratRunning = new animation(ratrunning, 10);

    private BufferedImage[] flyingBook = {SpriteRetrival.getSprite(4,2,3), SpriteRetrival.getSprite(7, 2,3),SpriteRetrival.getSprite(4,3,3), SpriteRetrival.getSprite(7, 3,3), SpriteRetrival.getSprite(4,4,3), SpriteRetrival.getSprite(7, 4,3), SpriteRetrival.getSprite(4,5,3), SpriteRetrival.getSprite(7, 5,3)};
    private animation flyingBookMoving = new animation(flyingBook, 10);

    private BufferedImage[] gaintSpider = {SpriteRetrival.getSprite(0,6,3), SpriteRetrival.getSprite(3,6,3), SpriteRetrival.getSprite(0,7,3), SpriteRetrival.getSprite(3,7,3), SpriteRetrival.getSprite(0,8,3), SpriteRetrival.getSprite(3,8,3), SpriteRetrival.getSprite(0,9,3), SpriteRetrival.getSprite(3,9,3)};
    private animation gaintSpiderJumping = new animation(gaintSpider, 10);

    private BufferedImage[] ghost = {SpriteRetrival.getSprite(0, 3, 3), SpriteRetrival.getSprite(3,3,3), SpriteRetrival.getSprite(0,4, 3), SpriteRetrival.getSprite(3,4,3), SpriteRetrival.getSprite(0,5,3), SpriteRetrival.getSprite(1,5,3)};
    private animation ghostFloating = new animation(ghost, 10);

    private BufferedImage[] courtWizardAway = {SpriteRetrival.getSprite(4, 6, 3), SpriteRetrival.getSprite(7,6,3), SpriteRetrival.getSprite(4,7, 3), SpriteRetrival.getSprite(7,7,3), SpriteRetrival.getSprite(4,8,3)};
    private animation courtWizardTeleportAway = new animation(courtWizardAway, 10);
    private BufferedImage[] courtWizardBack = {SpriteRetrival.getSprite(5, 8, 3), SpriteRetrival.getSprite(7,8,3), SpriteRetrival.getSprite(4,9, 3), SpriteRetrival.getSprite(7,9,3), SpriteRetrival.getSprite(4,10,3), SpriteRetrival.getSprite(5, 10, 3)};
    private animation courtWizardTeleportBack= new animation(courtWizardBack,10);
    private BufferedImage[] courtWizard = {SpriteRetrival.getSprite(4,20,3), SpriteRetrival.getSprite(7,20,3)};
    private animation courtWizardIdle = new animation(courtWizard, 25);

    private BufferedImage[] bowKnight = {SpriteRetrival.getSprite(0, 10, 3), SpriteRetrival.getSprite(3,10,3), SpriteRetrival.getSprite(0,11, 3), SpriteRetrival.getSprite(3,11,3)};
    private animation bowKnightIdle = new animation(bowKnight, 10);
    private BufferedImage[] bowKnightWalk = {SpriteRetrival.getSprite(0, 12, 3), SpriteRetrival.getSprite(3,12,3), SpriteRetrival.getSprite(0,13, 3), SpriteRetrival.getSprite(1,13,3)};
    private animation bowKnightWalking = new animation(bowKnightWalk, 10);

    private BufferedImage[] spearKnight = {SpriteRetrival.getSprite(0, 14, 3), SpriteRetrival.getSprite(3,14,3), SpriteRetrival.getSprite(0,15, 3), SpriteRetrival.getSprite(3,15,3)};
    private animation spearKnightIdle = new animation(spearKnight, 10);
    private BufferedImage[] spearKnightWalk = {SpriteRetrival.getSprite(0, 16, 3), SpriteRetrival.getSprite(3,16,3), SpriteRetrival.getSprite(0,17, 3), SpriteRetrival.getSprite(1,17,3)};
    private animation spearKnightWalking = new animation(spearKnightWalk, 10);

    private BufferedImage[] swordKnight = {SpriteRetrival.getSprite(4, 14, 3), SpriteRetrival.getSprite(7,14,3), SpriteRetrival.getSprite(4,15, 3), SpriteRetrival.getSprite(7,15,3)};
    private animation swordKnightIdle = new animation(swordKnight, 10);
    private BufferedImage[] swordKnightWalk = {SpriteRetrival.getSprite(4, 16, 3), SpriteRetrival.getSprite(7,16,3), SpriteRetrival.getSprite(4,17, 3), SpriteRetrival.getSprite(5,17,3)};
    private animation swordKnightWalking = new animation(swordKnightWalk, 10);

    private BufferedImage[] shieldKnight = {SpriteRetrival.getSprite(4, 11, 3), SpriteRetrival.getSprite(7,11,3), SpriteRetrival.getSprite(4,12, 3), SpriteRetrival.getSprite(5,12,3)};
    private animation shieldKnightIdle = new animation(shieldKnight, 10);
    private BufferedImage[] shieldKnightWalk = {SpriteRetrival.getSprite(6, 13, 3), SpriteRetrival.getSprite(7,13,3), SpriteRetrival.getSprite(4,14, 3), SpriteRetrival.getSprite(7,14,3)};
    private animation shieldKnightWalking = new animation(shieldKnightWalk, 10);

    private animation animation = ratRunning;

    public Enemies(Cowabunga cb, int enemyNumber, int x, int y, Player p){
        this.enemyNumber = enemyNumber;
        this.p = p;
        this.x = x;
        this.y = y;
    }

    public void move(){
        animation.start();
        animation.update();
        xt = x;
        yt = y;
        if(alive) {
            if (enemyNumber == 0) {
                animation = ratRunning;
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
                hitboxX = x + 9;
                hitboxXT = x + animation.getSprite().getWidth() - 11;
                hitboxY = y + 9;
                hitboxYT = y + animation.getSprite().getHeight() - 9;
                enemyDamage = 1;
                health = 4;
            }
            if (enemyNumber == 1) {
                animation = flyingBookMoving;
                health = 8;
                enemyDamage = 2;
                hitboxX = x + 6;
                hitboxXT = x + animation.getSprite().getWidth() - 7;
                hitboxY = y + 8;
                hitboxYT = y + animation.getSprite().getHeight() - 11;
            }
            if (enemyNumber == 2) {
                animation = courtWizardIdle;
                health = 10;
                enemyDamage = 2;
                hitboxX = x + 9;
                hitboxXT = x + animation.getSprite().getWidth() - 9;
                hitboxY = y + 8;
                hitboxYT = y + animation.getSprite().getHeight();
            }
            if (enemyNumber == 3) {
                //kinght sheild
                animation = shieldKnightIdle;
                health = 12;
                enemyDamage = 4;
                hitboxX = x + 5;
                hitboxXT = x + animation.getSprite().getWidth() - 6;
                hitboxY = y + 5;
                hitboxYT = y + animation.getSprite().getHeight() - 5;
            }
            if (enemyNumber == 4) {
                //knight bow
                animation = bowKnightIdle;
                health = 12;
                enemyDamage = 2;
                hitboxX = x + 10;
                hitboxXT = x + animation.getSprite().getWidth() - 6;
                hitboxY = y + 5;
                hitboxYT = y + animation.getSprite().getHeight() - 5;
            }
            if (enemyNumber == 5) {
                //knight spear
                animation = spearKnightIdle;
                health = 12;
                enemyDamage = 4;
                hitboxX = x + 10;
                hitboxXT = x + animation.getSprite().getWidth() - 6;
                hitboxY = y + 5;
                hitboxYT = y + animation.getSprite().getHeight() - 5;
            }
            if (enemyNumber == 6){
                //knight sword
                animation = swordKnightIdle;
                health = 12;
                enemyDamage = 2;
                hitboxX = x + 10;
                hitboxXT = x + animation.getSprite().getWidth() - 6;
                hitboxY = y + 5;
                hitboxYT = y + animation.getSprite().getHeight() - 5;
            }
            if (enemyNumber == 7) {
                //haunted armour
            }
            if (enemyNumber == 8) {
                //candle
            }
            if (enemyNumber == 9) {

            }
            if (enemyNumber == 9) {

            }
            if (enemyNumber == 10) {

            }
            if (enemyNumber == 11) {

            }
            if (enemyNumber == 12) {

            }
            if (enemyNumber == 13) {

            }
            if (enemyNumber == 14) {

            }
            if (enemyNumber == 15) {

            }
            if (enemyNumber == 16) {

            }
            if (enemyNumber == 17) {

            }
            if (enemyNumber == 18) {

            }
            if (enemyNumber == 19) {

            }
            if (enemyNumber == 20) {

            }
            if (enemyNumber == 21) {

            }
            if (enemyNumber == 22) {

            }
            if (enemyNumber == 23) {

            }
            if (enemyNumber == 24) {

            }
            if (enemyNumber == 25) {

            }
        }
        if(speedX != 0 || speedY != 0) {
            xt += speedX;
            yt += speedY;
            if (c.checkCollision(xt, y)) {
                speedX = 0;
            } else {
                x += speedX;
            }
            if (c.checkCollision(x, yt)) {
                speedY = 0;
            } else {
                y += speedY;
            }
        }
        enemyHealth();
    }
    public void paint(Graphics2D g2d){
        /*for(int i = 0; i < width; i++){
            for(int j = 0; j < height; j++){
                if(level[i][j] == 1){
                    this.x = i * tileWidth * 4;
                    this.y = j * tileHeight * 4;
                    g2d.drawImage(animation.getSprite(), x, y,animation.getSprite().getHeight() * 2, animation.getSprite().getWidth() * 2 , null);
                }
            }
        }
        for(int i = 0; i < spawnNumLocation; i++) {
            g2d.drawImage(animation.getSprite(), xt[i], yt[i], animation.getSprite().getHeight() * 2, animation.getSprite().getWidth() * 2, null);
        }*/
        if(alive) {
            g2d.drawImage(animation.getSprite(), x, y, animation.getSprite().getHeight() * 2, animation.getSprite().getWidth() * 2, null);
        }
    }
    public void collision(){
        boolean x_overlaps = (p.getHitboxX() < hitboxXT && p.getHitboxXT() > hitboxX);
        boolean y_overlaps = (p.getHitboxY() < hitboxYT && p.getHitboxYT() > hitboxY);
        boolean collision = x_overlaps && y_overlaps;
        if(collision){
          p.damage(enemyDamage);
        }
        /*if(p.getHitboxX() < hitboxXT && p.getHitboxXT() > hitboxX){
            p.setPlayerHealth(1);
        }
        if(p.getHitboxY() < hitboxYT && p.getHitboxYT() > hitboxY){
            p.setPlayerHealth(1);
        }*/
    }
    public void damage(int damage){
        this.damage = damage;
        if(!invulnerable){
            health = health - damage;
            hit = true;
        }
    }
    private void enemyHealth(){
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
        if(health == 0){
            alive = false;
        }
    }

}
