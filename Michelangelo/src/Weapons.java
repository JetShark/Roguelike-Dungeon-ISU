import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Weapons {
    private int x, y;
    private int mX, mY;
    private Player p;
    private Cowabunga cb;
    private BufferedImage img = null;
    private double imageAngleRad = 0;
    private Point imagePosition;
    private PlayerCursor pc = new PlayerCursor();

    private boolean rightClick = false;

    private BufferedImage[] allen = {SpriteRetrival.getSprite(0,0,4)};
    private animation idleAllen = new animation(allen,10);
    private BufferedImage[] allenFiring = {SpriteRetrival.getSprite(0,3,4), SpriteRetrival.getSprite(3,3,4), SpriteRetrival.getSprite(0,4,4), SpriteRetrival.getSprite(3,4,4)};
    private animation firingAllen = new animation(allenFiring,10);

    private BufferedImage[] energyTurbine = {SpriteRetrival.getSprite(1,0,4)};
    private animation idleEnergyTurbine = new animation(energyTurbine,10);
    private BufferedImage[] energyTurbineFiring = {SpriteRetrival.getSprite(4,3,4), SpriteRetrival.getSprite(7,3,4), SpriteRetrival.getSprite(4,4,4), SpriteRetrival.getSprite(7,4,4),SpriteRetrival.getSprite(4,5,4), SpriteRetrival.getSprite(7,5,4), SpriteRetrival.getSprite(4,6,4), SpriteRetrival.getSprite(7,6,4), SpriteRetrival.getSprite(4,7,4), SpriteRetrival.getSprite(7,7,4), SpriteRetrival.getSprite(4,8,4), SpriteRetrival.getSprite(7,8,4), SpriteRetrival.getSprite(4,9,4), SpriteRetrival.getSprite(7,9,4), SpriteRetrival.getSprite(4,10,4), SpriteRetrival.getSprite(7,10,4)};
    private animation firingEnergyTurbine = new animation(energyTurbineFiring,10);

    private BufferedImage[] LoFi = {SpriteRetrival.getSprite(2,0,4)};
    private animation idleLoFi = new animation(LoFi,10);
    private BufferedImage[] LoFiFiring = {SpriteRetrival.getSprite(0,5,4), SpriteRetrival.getSprite(3,5,4), SpriteRetrival.getSprite(0,6,4), SpriteRetrival.getSprite(3,6,4), SpriteRetrival.getSprite(0,7,4), SpriteRetrival.getSprite(3,7,4), SpriteRetrival.getSprite(0,8,4), SpriteRetrival.getSprite(3,8,4)};
    private animation firingLoFi = new animation(LoFiFiring,10);

    private BufferedImage[] pathogen = {SpriteRetrival.getSprite(4,0,4)};
    private animation idlePathogen = new animation(pathogen,10);
    private BufferedImage[] pathogenFiring = {SpriteRetrival.getSprite(0,9,4), SpriteRetrival.getSprite(3,9,4), SpriteRetrival.getSprite(0,10,4), SpriteRetrival.getSprite(3,10,4), SpriteRetrival.getSprite(0,11,4), SpriteRetrival.getSprite(3,11,4), SpriteRetrival.getSprite(0,12,4), SpriteRetrival.getSprite(3,12,4)};
    private animation firingPathogen = new animation(pathogenFiring,10);

    private BufferedImage[] theProduce = {SpriteRetrival.getSprite(5,0,4)};
    private animation idleProduce = new animation(theProduce,10);
    private BufferedImage[] theProduceFiring = {SpriteRetrival.getSprite(4,11,4), SpriteRetrival.getSprite(7,11,4), SpriteRetrival.getSprite(4,12,4), SpriteRetrival.getSprite(7,12,4), SpriteRetrival.getSprite(4,13,4), SpriteRetrival.getSprite(7,13,4), SpriteRetrival.getSprite(4,14,4), SpriteRetrival.getSprite(7,14,4)};
    private animation firingProduce = new animation(theProduceFiring,10);

    private BufferedImage[] shotgun = {SpriteRetrival.getSprite(6,0,4)};
    private animation idleShotgun = new animation(shotgun,10);
    private BufferedImage[] shotgunFiring = {SpriteRetrival.getSprite(0,13,4), SpriteRetrival.getSprite(3,13,4), SpriteRetrival.getSprite(0,14,4), SpriteRetrival.getSprite(3,14,4), SpriteRetrival.getSprite(0,15,4), SpriteRetrival.getSprite(3,15,4)};
    private animation firingShotgun = new animation(shotgunFiring,10);

    private BufferedImage[] tearGun = {SpriteRetrival.getSprite(7,0,4)};
    private animation idleTearGun = new animation(tearGun, 10);

    private BufferedImage[] theCatalyst = {SpriteRetrival.getSprite(0,1,4)};
    private animation idleCatalyst = new animation(theCatalyst,10);
    private BufferedImage[] theCatalystFiring = {SpriteRetrival.getSprite(4,15,4), SpriteRetrival.getSprite(7,15,4), SpriteRetrival.getSprite(4,16,4), SpriteRetrival.getSprite(7,16,4)};
    private animation firingCatalyst = new animation(theCatalystFiring,10);

    private BufferedImage[] theController = {SpriteRetrival.getSprite(1,1,4)};
    private animation idleController = new animation(theController,10);
    private BufferedImage[] theControllerFiring = {SpriteRetrival.getSprite(0,16,4), SpriteRetrival.getSprite(3,16,4), SpriteRetrival.getSprite(0,17,4), SpriteRetrival.getSprite(3,17,4), SpriteRetrival.getSprite(0,18,4), SpriteRetrival.getSprite(3,18,4), SpriteRetrival.getSprite(0,19,4), SpriteRetrival.getSprite(3,19,4), SpriteRetrival.getSprite(0,20,4), SpriteRetrival.getSprite(3,20,4), SpriteRetrival.getSprite(0,21,4), SpriteRetrival.getSprite(3,21,4)};
    private animation firingController = new animation(theControllerFiring,10);

    private BufferedImage[] theDisassembler = {SpriteRetrival.getSprite(2,1,4)};
    private animation idleDisassembler = new animation(theDisassembler,10);
    private BufferedImage[] theDisassemblerFiring = {SpriteRetrival.getSprite(4,17,4), SpriteRetrival.getSprite(7,17,4), SpriteRetrival.getSprite(4,18,4), SpriteRetrival.getSprite(7,18,4), SpriteRetrival.getSprite(4,19,4), SpriteRetrival.getSprite(7,19,4)};
    private animation firingDisassembler = new animation(theDisassemblerFiring,10);

    private BufferedImage[] theDriver = {SpriteRetrival.getSprite(3,1,4)};
    private animation idleDriver = new animation(theDriver,10);
    private BufferedImage[] theDriverFiring = {SpriteRetrival.getSprite(4,20,4), SpriteRetrival.getSprite(7,20,4), SpriteRetrival.getSprite(4,21,4), SpriteRetrival.getSprite(7,21,4), SpriteRetrival.getSprite(4,22,4), SpriteRetrival.getSprite(7,22,4), SpriteRetrival.getSprite(4,23,4), SpriteRetrival.getSprite(7,23,4)};
    private animation firingDriver = new animation(theDriverFiring,10);

    private BufferedImage[] theEconomy = {SpriteRetrival.getSprite(4,1,4)};
    private animation idleEconomy = new animation(theEconomy,10);
    private BufferedImage[] theEconomyFiring = {SpriteRetrival.getSprite(0,22,4), SpriteRetrival.getSprite(3,22,4), SpriteRetrival.getSprite(0,23,4), SpriteRetrival.getSprite(3,23,4), SpriteRetrival.getSprite(0,24,4), SpriteRetrival.getSprite(3,24,4), SpriteRetrival.getSprite(0,25,4), SpriteRetrival.getSprite(3,25,4)};
    private animation firingEconomy = new animation(theEconomyFiring,10);

    private BufferedImage[] theHat = {SpriteRetrival.getSprite(5,1,4)};
    private animation idleHat = new animation(theHat,10);
    private BufferedImage[] theHatFiring = {SpriteRetrival.getSprite(4,24,4), SpriteRetrival.getSprite(7,24,4), SpriteRetrival.getSprite(4,25,4), SpriteRetrival.getSprite(7,25,4), SpriteRetrival.getSprite(4,26,4), SpriteRetrival.getSprite(7,16,4), SpriteRetrival.getSprite(4,27,4), SpriteRetrival.getSprite(7,27,4)};
    private animation firingHat = new animation(theHatFiring,10);

    private BufferedImage[] theJudge = {SpriteRetrival.getSprite(6,1,4)};
    private animation idleJudge = new animation(theJudge, 10);
    private BufferedImage[] theJudgeFiring = {SpriteRetrival.getSprite(0,26,4), SpriteRetrival.getSprite(3,26,4), SpriteRetrival.getSprite(0,27,4), SpriteRetrival.getSprite(3,27,4), SpriteRetrival.getSprite(0,28,4), SpriteRetrival.getSprite(3,28,4), SpriteRetrival.getSprite(0,29,4), SpriteRetrival.getSprite(3,29,4), SpriteRetrival.getSprite(0,30,4), SpriteRetrival.getSprite(3,30,4)};
    private animation firingJudge = new animation(theJudgeFiring,10);

    private BufferedImage[] thePDC = {SpriteRetrival.getSprite(7,1,4)};
    private animation idlePDC = new animation(thePDC,10);
    private BufferedImage[] thePDCFiring = {SpriteRetrival.getSprite(4,28,4), SpriteRetrival.getSprite(7,28,4), SpriteRetrival.getSprite(4,29,4), SpriteRetrival.getSprite(7,29,4), SpriteRetrival.getSprite(4,30,4), SpriteRetrival.getSprite(7,30,4)};
    private animation firingPDC = new animation(thePDCFiring,10);

    private BufferedImage[] theSequencer = {SpriteRetrival.getSprite(0,2,4)};
    private animation idleSequencer = new animation(theSequencer,10);
    private BufferedImage[] theSequencerFiring = {SpriteRetrival.getSprite(0,31,4), SpriteRetrival.getSprite(3,31,4), SpriteRetrival.getSprite(0,32,4), SpriteRetrival.getSprite(3,32,4)};
    private animation firingSequencer = new animation(theSequencerFiring,10);

    private BufferedImage[] theSolvent = {SpriteRetrival.getSprite(1,2,4)};
    private animation idleSolvent = new animation(theSolvent,10);
    private BufferedImage[] theSolventFiring = {SpriteRetrival.getSprite(4,31,4), SpriteRetrival.getSprite(7,31,4), SpriteRetrival.getSprite(4,32,4), SpriteRetrival.getSprite(7,32,4), SpriteRetrival.getSprite(4,33,4), SpriteRetrival.getSprite(7,33,4), SpriteRetrival.getSprite(4,34,4), SpriteRetrival.getSprite(7,34,4)};
    private animation firingSolvent = new animation(theSolventFiring,10);

    private BufferedImage[] triggerTwins = {SpriteRetrival.getSprite(2,2,4)};
    private animation idleTriggerTwins = new animation(triggerTwins,10);
    private BufferedImage[] triggerTwinsFiring = {SpriteRetrival.getSprite(0,33,4), SpriteRetrival.getSprite(3,33,4)};
    private animation firingTriggerTwins = new animation(triggerTwinsFiring,10);

    private BufferedImage[] vacPac = {SpriteRetrival.getSprite(3,2,4)};
    private animation idleVacPac = new animation(vacPac,10);
    private BufferedImage[] vacPacFiring = {SpriteRetrival.getSprite(0,34,4), SpriteRetrival.getSprite(3,34,4), SpriteRetrival.getSprite(0,35,4), SpriteRetrival.getSprite(3,35,4),SpriteRetrival.getSprite(0,36,4), SpriteRetrival.getSprite(3,36,4)};
    private animation firingVacPac = new animation(vacPacFiring,10);

    private BufferedImage[] youMonster = {SpriteRetrival.getSprite(4,2,4)};
    private animation idleYouMonster = new animation(youMonster,10);
    private BufferedImage[] youMonsterFiring = {SpriteRetrival.getSprite(4,35,4), SpriteRetrival.getSprite(7,35,4), SpriteRetrival.getSprite(4,36,4), SpriteRetrival.getSprite(7,36,4), SpriteRetrival.getSprite(4,37,4), SpriteRetrival.getSprite(7,37,4), SpriteRetrival.getSprite(4,38,4), SpriteRetrival.getSprite(7,38,4)};
    private animation firingYouMonster = new animation(youMonsterFiring,50);

    private BufferedImage[] zorMarUlla = {SpriteRetrival.getSprite(5,2,4)};
    private animation idleZorMarUlla = new animation(zorMarUlla,10);
    private BufferedImage[] zorMarUllaFiring = {SpriteRetrival.getSprite(0,37,4), SpriteRetrival.getSprite(3,37,4), SpriteRetrival.getSprite(0,38,4), SpriteRetrival.getSprite(3,38,4), SpriteRetrival.getSprite(0,39,4), SpriteRetrival.getSprite(3,39,4), SpriteRetrival.getSprite(0,40,4), SpriteRetrival.getSprite(3,40,4), SpriteRetrival.getSprite(0,41,4), SpriteRetrival.getSprite(3,41,4), SpriteRetrival.getSprite(0,42,4), SpriteRetrival.getSprite(3,42,4)};
    private animation firingZorMarUlla = new animation(zorMarUllaFiring,10);

    private BufferedImage[] sword = {SpriteRetrival.getSprite(5,45,4)};
    private animation idleSword = new animation(sword, 10);
    private BufferedImage[] swordSwinging = {SpriteRetrival.getSprite(4, 44, 4), SpriteRetrival.getSprite(5,44,4), SpriteRetrival.getSprite(6,44,4), SpriteRetrival.getSprite(7,44,4), SpriteRetrival.getSprite(4, 45,4)};
    private animation firingSwordSwinging = new animation(swordSwinging,100);

    private animation rangedAnimation;
    private animation swordAnimation;
    private animation idleRanged;
    private animation idleMelee;
    public void mouseDragged(MouseEvent e){

    }
    public Weapons(Player p){
        this.p = p;
        imagePosition = new Point(p.getX(),p.getY());
        swordAnimation = firingSwordSwinging;
    }

    public void mousePressed(MouseEvent e){
        if(e.getButton() == MouseEvent.BUTTON3){
            rightClick = false;
        }
    }
    public void mouseReleased(MouseEvent e){
        if(e.getButton() == MouseEvent.BUTTON3){
            rightClick = true;
            swordAnimation.start();
        }
    }
    public void move(){
        imagePosition.x = p.getX();
        imagePosition.y = p.getY();
    }
    public void mouseMoved(MouseEvent e){
        mX = e.getX() + p.getCamX(); //set the mX and mY to the location of the cursor on the screen
        mY = e.getY() + p.getCamY();
        double dx = mX - imagePosition.getX(); //find s the distance from the cursur to the weapon
        double dy = mY - imagePosition.getY();
        imageAngleRad = Math.atan2(dy,dx); //find the angle of the weapon to the cursor
    }
    private void weaponDirection(){

    }
    public void paint(Graphics2D g2d){
        rangedAnimation = firingYouMonster; //set what animation to draw
        rangedAnimation.start(); //starts the animation

        int cx = rangedAnimation.getSprite().getWidth() / 2 - 10;
        //int cx = animation.getSprite().getWidth() - 40;
        int cy = rangedAnimation.getSprite().getHeight() / 2 + 5;
        AffineTransform at = new AffineTransform();
        /*at.translate(x + 60, y + 30);
        at.rotate(Math.PI/-1.05);
        at.scale(2.0,2.0);
        at.translate(-animation.getSprite().getWidth() / 2, -animation.getSprite().getHeight() / 2);*/

        at.translate(cx + imagePosition.x + 35, cy + imagePosition.y + 20);
        at.rotate(imageAngleRad);
        at.scale(3.0,3.0);
        at.translate(-cx, -cy);

        //g2d.drawImage(animation.getSprite(), at, null);

        //g2d.rotate(Math.toRadians(45), animation.getSprite().getWidth()/2, animation.getSprite().getHeight() / 2);
        //g2d.drawImage(animation.getSprite(),x + 5,y, animation.getSprite().getHeight() * 2, animation.getSprite().getWidth() * 2 ,null);
        //at.translate(x + 5, y);
        if(rightClick){
            int cX = swordAnimation.getSprite().getWidth() / 2 - 12;
            int cY = swordAnimation.getSprite().getHeight() / 2 - 3;
            AffineTransform newAt = new AffineTransform();
            newAt.translate(cX + imagePosition.x + 35, cY + imagePosition.y + 20);
            newAt.rotate(imageAngleRad);
            newAt.scale(3.0,3.0);
            newAt.translate(-cX, -cY);
            g2d.drawImage(swordAnimation.getSprite(), newAt, null);
            //g2d.drawImage(swordAnimation.getSprite(), imagePosition.x, imagePosition.y, null);
        }
        rangedAnimation.update(); //updates the animation class so that it updates and will draw the new images.
        swordAnimation.update();
    }

    public void meleeWeapons(){

    }
    public void drawIdleWeaponsSprite(Graphics2D g2d, String name){
        int x = p.getCamX();
        int y = p.getCamY();
        if(name.equals("sword")){
            idleMelee = idleSword;
        }
        idleMelee.start();
        g2d.drawImage(idleMelee.getSprite(), x + 42,y + 500, idleMelee.getSprite().getWidth() * 2, idleMelee.getSprite().getHeight() * 2,null);
        idleMelee.update();
        //idleRanged.update();
    }
}
