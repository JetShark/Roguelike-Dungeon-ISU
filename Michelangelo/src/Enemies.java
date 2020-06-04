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
    private int damage;


    private BufferedImage[] ratrunning = {SpriteRetrival.getSprite(0, 0, 3), SpriteRetrival.getSprite(3,0,3), SpriteRetrival.getSprite(0,1, 3), SpriteRetrival.getSprite(3,1,3), SpriteRetrival.getSprite(0,2,3), SpriteRetrival.getSprite(1,2,3)};
    private animation ratRunning = new animation(ratrunning, 10);

    private BufferedImage[] flyingBook = {SpriteRetrival.getSprite(4,2,3), SpriteRetrival.getSprite(7, 2,3),SpriteRetrival.getSprite(4,3,3), SpriteRetrival.getSprite(7, 3,3), SpriteRetrival.getSprite(4,4,3), SpriteRetrival.getSprite(7, 4,3), SpriteRetrival.getSprite(4,5,3), SpriteRetrival.getSprite(7, 5,3)};
    private animation flyingBookMoving = new animation(flyingBook, 10);

    private BufferedImage[] gaintSpider = {SpriteRetrival.getSprite(0,6,3), SpriteRetrival.getSprite(3,6,3), SpriteRetrival.getSprite(0,7,3), SpriteRetrival.getSprite(3,7,3), SpriteRetrival.getSprite(0,8,3), SpriteRetrival.getSprite(3,8,3), SpriteRetrival.getSprite(0,9,3), SpriteRetrival.getSprite(3,9,3)};
    private animation gaintSpiderJumping = new animation(gaintSpider, 10);

    private BufferedImage[] ghost = {SpriteRetrival.getSprite(0, 3, 3), SpriteRetrival.getSprite(3,3,3), SpriteRetrival.getSprite(0,4, 3), SpriteRetrival.getSprite(3,4,3), SpriteRetrival.getSprite(0,5,3), SpriteRetrival.getSprite(1,5,3)};
    private animation ghostFloating = new animation(ghost, 10);

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
            damage = 1;
        }
        if(enemyNumber == 1){
            animation = flyingBookMoving;

        }
        if(enemyNumber == 2){
            animation = gaintSpiderJumping;
        }
        if(enemyNumber == 3){
            animation = ghostFloating;
        }
        if(enemyNumber == 4){

        }
        if(enemyNumber == 5){

        }
        if(enemyNumber == 6){

        }
        if(enemyNumber == 7){

        }
        if(enemyNumber == 8){

        }
        if(enemyNumber == 9){

        }
        if(enemyNumber == 9){

        }
        if(enemyNumber == 10){

        }
        if(enemyNumber == 11){

        }
        if(enemyNumber == 12){

        }
        if(enemyNumber == 13){

        }
        if(enemyNumber == 14){

        }
        if(enemyNumber == 15){

        }
        if(enemyNumber == 16){

        }
        if(enemyNumber == 17){

        }
        if(enemyNumber == 18){

        }
        if(enemyNumber == 19){

        }
        if(enemyNumber == 20){

        }
        if(enemyNumber == 21){

        }
        if(enemyNumber == 22){

        }
        if(enemyNumber == 23){

        }
        if(enemyNumber == 24){

        }
        if(enemyNumber == 25){

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
        g2d.drawImage(animation.getSprite(), x, y,animation.getSprite().getHeight() * 2, animation.getSprite().getWidth() * 2 , null);
    }
    public void collision(){
        boolean x_overlaps = (p.getHitboxX() < hitboxXT && p.getHitboxXT() > hitboxX);
        boolean y_overlaps = (p.getHitboxY() < hitboxYT && p.getHitboxYT() > hitboxY);
        boolean collision = x_overlaps && y_overlaps;
        if(collision){
          p.damage(damage);
        }
        /*if(p.getHitboxX() < hitboxXT && p.getHitboxXT() > hitboxX){
            p.setPlayerHealth(1);
        }
        if(p.getHitboxY() < hitboxYT && p.getHitboxYT() > hitboxY){
            p.setPlayerHealth(1);
        }*/
    }
}
