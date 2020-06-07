import java.awt.*;
import java.awt.image.BufferedImage;

public class HeadsUp {
    private int x, y;
    private Cowabunga cb;
    private Player p;
    private Weapons w;
    private int numOfHearts;

    private BufferedImage[] fullHeart = {SpriteRetrival.getSprite(0,0,6)};
    private animation idleFullHeart = new animation(fullHeart,10);
    private BufferedImage[] halfHeart = {SpriteRetrival.getSprite(6,2,6)};
    private animation idleHalfHeart = new animation(halfHeart,10);
    private BufferedImage[] quarterHeart = {SpriteRetrival.getSprite(1,4,6)};
    private animation idleQuarterHeart = new animation(quarterHeart,10);
    private BufferedImage[] threeQuarterHeart = {SpriteRetrival.getSprite(3,1,6)};
    private animation idleThreeQuarterHeart = new animation(threeQuarterHeart,10);

    private BufferedImage[] emptyHeartImg = {SpriteRetrival.getSprite(2,5,6)};
    private animation emptyHeart = new animation(emptyHeartImg, 10);

    private BufferedImage[] keys = {SpriteRetrival.getSprite(1, 6,6)};
    private animation idleKeys = new animation(keys,10);
    private BufferedImage[] bombs = {SpriteRetrival.getSprite(0, 6,6)};
    private animation idleBombs = new animation(bombs,10);
    private BufferedImage[] gold = {SpriteRetrival.getSprite(2, 6,6)};
    private animation idleGold = new animation(gold,10);

    private animation animationHearts;
    private animation emptyHeartA = emptyHeart;
    private animation kGBAnimation;

    public HeadsUp(Cowabunga cb, Player p){
        this.cb = cb;
        this.p = p;
        w = new Weapons(p);
        x = p.getCamX();
        y = p.getCamY();
        numOfHearts = p.getNumOfHearts();
    }
    public void move(){
        x = p.getCamX();
        y = p.getCamY();
    }
    public void paint(Graphics2D g2d){
        animationHearts = idleFullHeart;
        animationHearts.start();

        weaponSwapOuts(g2d);
        float thickness = 4;
        Stroke oldStroke = g2d.getStroke();
        g2d.setColor(Color.BLACK);
        g2d.setStroke(new BasicStroke(thickness));
        g2d.drawRect(x+5,y+5, 350, 100);
        g2d.setStroke(oldStroke);
        g2d.setColor(new Color(0,0,225,150));
        g2d.fillRect(x + 6, y + 6, 347, 97);
        double sizeW = animationHearts.getSprite().getWidth() * 1.5;
        double sizeH = animationHearts.getSprite().getHeight() * 1.5;
        for(int i = 0; i < numOfHearts; i++) {
            emptyHeartA = emptyHeart;
            g2d.drawImage(emptyHeart.getSprite(), x + 10 + (47 * i) ,y + 5,(int) sizeW, (int) sizeH, null); //draws empty hearts behind the actuall hearts
            if (i < p.getNumOfHearts()) {
                if (p.getPlayerHealth() == 12 || p.getPlayerHealth() == 16 || p.getPlayerHealth() == 20 || p.getPlayerHealth() == 24 || p.getPlayerHealth() == 28 || p.getPlayerHealth() == 8 || p.getPlayerHealth() == 4) {
                    g2d.drawImage(animationHearts.getSprite(), x + 10 + (47 * i), y + 5, (int) sizeW, (int) sizeH, null); //draws full hearts in a ll see slots;
                }
            }
            if (i <= p.getNumOfHearts()-2) {
                g2d.drawImage(animationHearts.getSprite(), x + 10 + (47 * i), y + 5, (int) sizeW, (int) sizeH, null); // draws full hearts in the secounds two slots
            }
            if (i == p.getNumOfHearts() - 1) {
                if (p.getPlayerHealth() == 3 || p.getPlayerHealth() == 7 || p.getPlayerHealth() == 11 || p.getPlayerHealth() == 15 || p.getPlayerHealth() == 19 || p.getPlayerHealth() == 23 || p.getPlayerHealth() == 27) {
                    animationHearts = idleThreeQuarterHeart;
                    g2d.drawImage(animationHearts.getSprite(), x + 10 + (47 * i), y + 5, (int) sizeW, (int) sizeH, null); //draws three quarter hearts
                }
                if (p.getPlayerHealth() == 2 || p.getPlayerHealth() == 6 || p.getPlayerHealth() == 10 || p.getPlayerHealth() == 14 || p.getPlayerHealth() == 18 || p.getPlayerHealth() == 22 || p.getPlayerHealth() == 26) {
                    animationHearts = idleHalfHeart;
                    g2d.drawImage(animationHearts.getSprite(), x + 10 + (47 * i), y + 5, (int) sizeW, (int) sizeH, null); // draws half hearts
                }
                if (p.getPlayerHealth() == 1 || p.getPlayerHealth() == 5 || p.getPlayerHealth() == 9 || p.getPlayerHealth() == 13 || p.getPlayerHealth() == 17 || p.getPlayerHealth() == 21 || p.getPlayerHealth() == 25) {
                    animationHearts = idleQuarterHeart;
                    g2d.drawImage(animationHearts.getSprite(), x + 10 + (47 * i), y + 5, (int) sizeW, (int) sizeH, null); // draws quater hearts.
                }
            }
        }
        kGBAnimation = idleKeys;
        kGBAnimation.start();
        g2d.drawImage(kGBAnimation.getSprite(), x + 5, y + 63, null);
        g2d.setColor(Color.WHITE);
        Font currentFont = g2d.getFont();
        Font newFont = currentFont.deriveFont(currentFont.getSize() * 2f);
        g2d.setFont(newFont);
        g2d.drawString("0", x + 30, y + 89);

        kGBAnimation = idleGold;
        kGBAnimation.start();
        g2d.drawImage(kGBAnimation.getSprite(), x + 85, y + 63, null);
        g2d.drawString("1", x + 120, y + 88);

        kGBAnimation = idleBombs;
        kGBAnimation.start();
        g2d.drawImage(kGBAnimation.getSprite(), x + 165, y + 63, null);
        g2d.drawString("55", x + 200, y + 89);

        animationHearts.update();
        kGBAnimation.update();
    }
    private void map(Graphics2D g2d){

    }
    private void weaponSwapOuts(Graphics2D g2d){
        float thickness = 4;
        Stroke oldStroke = g2d.getStroke();
        g2d.setColor(Color.BLACK);
        g2d.setStroke(new BasicStroke(thickness));
        g2d.drawRect(x + 20,y + 500, 100,70);
        g2d.drawRect(x + 680,y + 500, 140,70);
        g2d.drawRect(x + 840,y + 500, 140,70);
        g2d.setStroke(oldStroke);

        g2d.setColor(new Color(0,0,225,150));
        g2d.fillRect(x + 21, y + 501, 97, 67);
        g2d.fillRect(x + 681,y + 501, 137,67);
        g2d.fillRect(x + 841,y + 501, 137,67);

        w.drawIdleWeaponsSprite(g2d,"sword");
    }
}
