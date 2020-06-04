import java.awt.*;
import java.awt.image.BufferedImage;

public class HeadsUp {
    private int x, y;
    private Cowabunga cb;
    private Player p;

    private BufferedImage[] fullHeart = {SpriteRetrival.getSprite(0,0,6)};
    private animation idleFullHeart = new animation(fullHeart,10);
    private BufferedImage[] halfHeart = {SpriteRetrival.getSprite(6,2,6)};
    private animation idleHalfHeart = new animation(halfHeart,10);
    private BufferedImage[] quarterHeart = {SpriteRetrival.getSprite(1,4,6)};
    private animation idleQuarterHeart = new animation(quarterHeart,10);
    private BufferedImage[] threeQuarterHeart = {SpriteRetrival.getSprite(3,1,6)};
    private animation idleThreeQuarterHeart = new animation(threeQuarterHeart,10);

    private BufferedImage[] keys = {SpriteRetrival.getSprite(1, 6,6)};
    private animation idleKeys = new animation(keys,10);
    private BufferedImage[] bombs = {SpriteRetrival.getSprite(0, 6,6)};
    private animation idleBombs = new animation(bombs,10);
    private BufferedImage[] gold = {SpriteRetrival.getSprite(2, 6,6)};
    private animation idleGold = new animation(gold,10);

    private animation animationHearts;
    private animation kGBAnimation;

    public HeadsUp(Cowabunga cb, Player p){
        this.cb = cb;
        this.p = p;
        x = p.getCamX();
        y = p.getCamY();

    }
    public void move(){
        x = p.getCamX();
        y = p.getCamY();
    }
    public void paint(Graphics2D g2d){
        animationHearts = idleFullHeart;
        animationHearts.start();

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
        if(p.getPlayerHealth() == 12) {
            g2d.drawImage(animationHearts.getSprite(), x + 10, y + 5, (int) sizeW, (int) sizeH, null);
            g2d.drawImage(animationHearts.getSprite(), x + animationHearts.getSprite().getWidth() + 25, y + 5, (int) sizeW, (int) sizeH, null);
            g2d.drawImage(animationHearts.getSprite(), x + (animationHearts.getSprite().getWidth() * 2) + 40, y + 5, (int) sizeW, (int) sizeH, null);
        }
        if(p.getPlayerHealth() == 11) {
            g2d.drawImage(animationHearts.getSprite(), x + 10, y + 5, (int) sizeW, (int) sizeH, null);
            g2d.drawImage(animationHearts.getSprite(), x + animationHearts.getSprite().getWidth() + 25, y + 5, (int) sizeW, (int) sizeH, null);
            animationHearts = idleThreeQuarterHeart;
            g2d.drawImage(animationHearts.getSprite(), x + (animationHearts.getSprite().getWidth() * 2) + 40, y + 5, (int) sizeW, (int) sizeH, null);
        }

        if(p.getPlayerHealth() == 10) {
            g2d.drawImage(animationHearts.getSprite(), x + 10, y + 5, (int) sizeW, (int) sizeH, null);
            g2d.drawImage(animationHearts.getSprite(), x + animationHearts.getSprite().getWidth() + 25, y + 5, (int) sizeW, (int) sizeH, null);
            animationHearts = idleHalfHeart;
            g2d.drawImage(animationHearts.getSprite(), x + (animationHearts.getSprite().getWidth() * 2) + 40, y + 5, (int) sizeW, (int) sizeH, null);
        }

        if(p.getPlayerHealth() == 9) {
            g2d.drawImage(animationHearts.getSprite(), x + 10, y + 5, (int) sizeW, (int) sizeH, null);
            g2d.drawImage(animationHearts.getSprite(), x + animationHearts.getSprite().getWidth() + 25, y + 5, (int) sizeW, (int) sizeH, null);
            animationHearts = idleQuarterHeart;
            g2d.drawImage(animationHearts.getSprite(), x + (animationHearts.getSprite().getWidth() * 2) + 40, y + 5, (int) sizeW, (int) sizeH, null);
        }

        if(p.getPlayerHealth() == 8) {
            g2d.drawImage(animationHearts.getSprite(), x + 10, y + 5, (int) sizeW, (int) sizeH, null);
            g2d.drawImage(animationHearts.getSprite(), x + animationHearts.getSprite().getWidth() + 25, y + 5, (int) sizeW, (int) sizeH, null);
        }

        if(p.getPlayerHealth() == 7) {
            g2d.drawImage(animationHearts.getSprite(), x + 10, y + 5, (int) sizeW, (int) sizeH, null);
            animationHearts = idleThreeQuarterHeart;
            g2d.drawImage(animationHearts.getSprite(), x + animationHearts.getSprite().getWidth() + 25, y + 5, (int) sizeW, (int) sizeH, null);
        }

        if(p.getPlayerHealth() == 6) {
            g2d.drawImage(animationHearts.getSprite(), x + 10, y + 5, (int) sizeW, (int) sizeH, null);
            animationHearts = idleHalfHeart;
            g2d.drawImage(animationHearts.getSprite(), x + animationHearts.getSprite().getWidth() + 25, y + 5, (int) sizeW, (int) sizeH, null);
        }
        if(p.getPlayerHealth() == 5) {
            g2d.drawImage(animationHearts.getSprite(), x + 10, y + 5, (int) sizeW, (int) sizeH, null);
            animationHearts = idleQuarterHeart;
            g2d.drawImage(animationHearts.getSprite(), x + animationHearts.getSprite().getWidth() + 25, y + 5, (int) sizeW, (int) sizeH, null);
        }

        if(p.getPlayerHealth() == 4) {
            g2d.drawImage(animationHearts.getSprite(), x + 10, y + 5, (int) sizeW, (int) sizeH, null);
        }
        if(p.getPlayerHealth() == 3) {
            animationHearts = idleThreeQuarterHeart;
            g2d.drawImage(animationHearts.getSprite(), x + 10, y + 5, (int) sizeW, (int) sizeH, null);
        }

        if(p.getPlayerHealth() == 2) {
            animationHearts = idleHalfHeart;
            g2d.drawImage(animationHearts.getSprite(), x + 10, y + 5, (int) sizeW, (int) sizeH, null);
        }

        if(p.getPlayerHealth() == 1) {
            animationHearts = idleQuarterHeart;
            g2d.drawImage(animationHearts.getSprite(), x + 10, y + 5, (int) sizeW, (int) sizeH, null);
        }




        /*g2d.drawImage(animationHearts.getSprite(), x + (animationHearts.getSprite().getWidth() * 3) + 55, y + 5, (int) sizeW, (int) sizeH, null);
        g2d.drawImage(animationHearts.getSprite(), x + (animationHearts.getSprite().getWidth() * 4) + 70, y + 5, (int) sizeW, (int) sizeH,  null);
        g2d.drawImage(animationHearts.getSprite(), x + (animationHearts.getSprite().getWidth() * 5) + 85, y + 5, (int) sizeW, (int) sizeH,  null);
        animationHearts = idleHalfHeart;
        g2d.drawImage(animationHearts.getSprite(), x + (animationHearts.getSprite().getWidth() * 6) + 100, y + 5, (int) sizeW, (int) sizeH,  null);*/

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
}
