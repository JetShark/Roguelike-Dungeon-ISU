import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;

public class Weapons {
    private int x, y;
    private int mX, mY;
    private Player p;
    private Enemies et;
    private Cowabunga cb;
    private BufferedImage img = null;
    private double imageAngleRad = 0;
    private Point imagePosition;
    private PlayerCursor pc = new PlayerCursor();

    private boolean rightClick = false;
    private int meleeDelay = 500;
    private int hitboxX, hitboxY, hitboxXT, hitboxYT;
    private int damage;
    private int modifier;
    private int direction;

    private BufferedImage[] allen = {SpriteRetrival.getSprite(0,0,4)};
    private Animation idleAllen = new Animation(allen,10);
    private BufferedImage[] allenFiring = {SpriteRetrival.getSprite(0,3,4), SpriteRetrival.getSprite(3,3,4), SpriteRetrival.getSprite(0,4,4), SpriteRetrival.getSprite(3,4,4)};
    private Animation firingAllen = new Animation(allenFiring,10);

    private BufferedImage[] energyTurbine = {SpriteRetrival.getSprite(1,0,4)};
    private Animation idleEnergyTurbine = new Animation(energyTurbine,10);
    private BufferedImage[] energyTurbineFiring = {SpriteRetrival.getSprite(4,3,4), SpriteRetrival.getSprite(7,3,4), SpriteRetrival.getSprite(4,4,4), SpriteRetrival.getSprite(7,4,4),SpriteRetrival.getSprite(4,5,4), SpriteRetrival.getSprite(7,5,4), SpriteRetrival.getSprite(4,6,4), SpriteRetrival.getSprite(7,6,4), SpriteRetrival.getSprite(4,7,4), SpriteRetrival.getSprite(7,7,4), SpriteRetrival.getSprite(4,8,4), SpriteRetrival.getSprite(7,8,4), SpriteRetrival.getSprite(4,9,4), SpriteRetrival.getSprite(7,9,4), SpriteRetrival.getSprite(4,10,4), SpriteRetrival.getSprite(7,10,4)};
    private Animation firingEnergyTurbine = new Animation(energyTurbineFiring,10);

    private BufferedImage[] LoFi = {SpriteRetrival.getSprite(2,0,4)};
    private Animation idleLoFi = new Animation(LoFi,10);
    private BufferedImage[] LoFiFiring = {SpriteRetrival.getSprite(0,5,4), SpriteRetrival.getSprite(3,5,4), SpriteRetrival.getSprite(0,6,4), SpriteRetrival.getSprite(3,6,4), SpriteRetrival.getSprite(0,7,4), SpriteRetrival.getSprite(3,7,4), SpriteRetrival.getSprite(0,8,4), SpriteRetrival.getSprite(3,8,4)};
    private Animation firingLoFi = new Animation(LoFiFiring,10);

    private BufferedImage[] pathogen = {SpriteRetrival.getSprite(4,0,4)};
    private Animation idlePathogen = new Animation(pathogen,10);
    private BufferedImage[] pathogenFiring = {SpriteRetrival.getSprite(0,9,4), SpriteRetrival.getSprite(3,9,4), SpriteRetrival.getSprite(0,10,4), SpriteRetrival.getSprite(3,10,4), SpriteRetrival.getSprite(0,11,4), SpriteRetrival.getSprite(3,11,4), SpriteRetrival.getSprite(0,12,4), SpriteRetrival.getSprite(3,12,4)};
    private Animation firingPathogen = new Animation(pathogenFiring,10);

    private BufferedImage[] theProduce = {SpriteRetrival.getSprite(5,0,4)};
    private Animation idleProduce = new Animation(theProduce,10);
    private BufferedImage[] theProduceFiring = {SpriteRetrival.getSprite(4,11,4), SpriteRetrival.getSprite(7,11,4), SpriteRetrival.getSprite(4,12,4), SpriteRetrival.getSprite(7,12,4), SpriteRetrival.getSprite(4,13,4), SpriteRetrival.getSprite(7,13,4), SpriteRetrival.getSprite(4,14,4), SpriteRetrival.getSprite(7,14,4)};
    private Animation firingProduce = new Animation(theProduceFiring,10);

    private BufferedImage[] shotgun = {SpriteRetrival.getSprite(6,0,4)};
    private Animation idleShotgun = new Animation(shotgun,10);
    private BufferedImage[] shotgunFiring = {SpriteRetrival.getSprite(0,13,4), SpriteRetrival.getSprite(3,13,4), SpriteRetrival.getSprite(0,14,4), SpriteRetrival.getSprite(3,14,4), SpriteRetrival.getSprite(0,15,4), SpriteRetrival.getSprite(3,15,4)};
    private Animation firingShotgun = new Animation(shotgunFiring,10);

    private BufferedImage[] tearGun = {SpriteRetrival.getSprite(7,0,4)};
    private Animation idleTearGun = new Animation(tearGun, 10);

    private BufferedImage[] theCatalyst = {SpriteRetrival.getSprite(0,1,4)};
    private Animation idleCatalyst = new Animation(theCatalyst,10);
    private BufferedImage[] theCatalystFiring = {SpriteRetrival.getSprite(4,15,4), SpriteRetrival.getSprite(7,15,4), SpriteRetrival.getSprite(4,16,4), SpriteRetrival.getSprite(7,16,4)};
    private Animation firingCatalyst = new Animation(theCatalystFiring,10);

    private BufferedImage[] theController = {SpriteRetrival.getSprite(1,1,4)};
    private Animation idleController = new Animation(theController,10);
    private BufferedImage[] theControllerFiring = {SpriteRetrival.getSprite(0,16,4), SpriteRetrival.getSprite(3,16,4), SpriteRetrival.getSprite(0,17,4), SpriteRetrival.getSprite(3,17,4), SpriteRetrival.getSprite(0,18,4), SpriteRetrival.getSprite(3,18,4), SpriteRetrival.getSprite(0,19,4), SpriteRetrival.getSprite(3,19,4), SpriteRetrival.getSprite(0,20,4), SpriteRetrival.getSprite(3,20,4), SpriteRetrival.getSprite(0,21,4), SpriteRetrival.getSprite(3,21,4)};
    private Animation firingController = new Animation(theControllerFiring,10);

    private BufferedImage[] theDisassembler = {SpriteRetrival.getSprite(2,1,4)};
    private Animation idleDisassembler = new Animation(theDisassembler,10);
    private BufferedImage[] theDisassemblerFiring = {SpriteRetrival.getSprite(4,17,4), SpriteRetrival.getSprite(7,17,4), SpriteRetrival.getSprite(4,18,4), SpriteRetrival.getSprite(7,18,4), SpriteRetrival.getSprite(4,19,4), SpriteRetrival.getSprite(7,19,4)};
    private Animation firingDisassembler = new Animation(theDisassemblerFiring,10);

    private BufferedImage[] theDriver = {SpriteRetrival.getSprite(3,1,4)};
    private Animation idleDriver = new Animation(theDriver,10);
    private BufferedImage[] theDriverFiring = {SpriteRetrival.getSprite(4,20,4), SpriteRetrival.getSprite(7,20,4), SpriteRetrival.getSprite(4,21,4), SpriteRetrival.getSprite(7,21,4), SpriteRetrival.getSprite(4,22,4), SpriteRetrival.getSprite(7,22,4), SpriteRetrival.getSprite(4,23,4), SpriteRetrival.getSprite(7,23,4)};
    private Animation firingDriver = new Animation(theDriverFiring,10);

    private BufferedImage[] theEconomy = {SpriteRetrival.getSprite(4,1,4)};
    private Animation idleEconomy = new Animation(theEconomy,10);
    private BufferedImage[] theEconomyFiring = {SpriteRetrival.getSprite(0,22,4), SpriteRetrival.getSprite(3,22,4), SpriteRetrival.getSprite(0,23,4), SpriteRetrival.getSprite(3,23,4), SpriteRetrival.getSprite(0,24,4), SpriteRetrival.getSprite(3,24,4), SpriteRetrival.getSprite(0,25,4), SpriteRetrival.getSprite(3,25,4)};
    private Animation firingEconomy = new Animation(theEconomyFiring,10);

    private BufferedImage[] theHat = {SpriteRetrival.getSprite(5,1,4)};
    private Animation idleHat = new Animation(theHat,10);
    private BufferedImage[] theHatFiring = {SpriteRetrival.getSprite(4,24,4), SpriteRetrival.getSprite(7,24,4), SpriteRetrival.getSprite(4,25,4), SpriteRetrival.getSprite(7,25,4), SpriteRetrival.getSprite(4,26,4), SpriteRetrival.getSprite(7,16,4), SpriteRetrival.getSprite(4,27,4), SpriteRetrival.getSprite(7,27,4)};
    private Animation firingHat = new Animation(theHatFiring,10);

    private BufferedImage[] theJudge = {SpriteRetrival.getSprite(6,1,4)};
    private Animation idleJudge = new Animation(theJudge, 10);
    private BufferedImage[] theJudgeFiring = {SpriteRetrival.getSprite(0,26,4), SpriteRetrival.getSprite(3,26,4), SpriteRetrival.getSprite(0,27,4), SpriteRetrival.getSprite(3,27,4), SpriteRetrival.getSprite(0,28,4), SpriteRetrival.getSprite(3,28,4), SpriteRetrival.getSprite(0,29,4), SpriteRetrival.getSprite(3,29,4), SpriteRetrival.getSprite(0,30,4), SpriteRetrival.getSprite(3,30,4)};
    private Animation firingJudge = new Animation(theJudgeFiring,10);

    private BufferedImage[] thePDC = {SpriteRetrival.getSprite(7,1,4)};
    private Animation idlePDC = new Animation(thePDC,10);
    private BufferedImage[] thePDCFiring = {SpriteRetrival.getSprite(4,28,4), SpriteRetrival.getSprite(7,28,4), SpriteRetrival.getSprite(4,29,4), SpriteRetrival.getSprite(7,29,4), SpriteRetrival.getSprite(4,30,4), SpriteRetrival.getSprite(7,30,4)};
    private Animation firingPDC = new Animation(thePDCFiring,10);

    private BufferedImage[] theSequencer = {SpriteRetrival.getSprite(0,2,4)};
    private Animation idleSequencer = new Animation(theSequencer,10);
    private BufferedImage[] theSequencerFiring = {SpriteRetrival.getSprite(0,31,4), SpriteRetrival.getSprite(3,31,4), SpriteRetrival.getSprite(0,32,4), SpriteRetrival.getSprite(3,32,4)};
    private Animation firingSequencer = new Animation(theSequencerFiring,10);

    private BufferedImage[] theSolvent = {SpriteRetrival.getSprite(1,2,4)};
    private Animation idleSolvent = new Animation(theSolvent,10);
    private BufferedImage[] theSolventFiring = {SpriteRetrival.getSprite(4,31,4), SpriteRetrival.getSprite(7,31,4), SpriteRetrival.getSprite(4,32,4), SpriteRetrival.getSprite(7,32,4), SpriteRetrival.getSprite(4,33,4), SpriteRetrival.getSprite(7,33,4), SpriteRetrival.getSprite(4,34,4), SpriteRetrival.getSprite(7,34,4)};
    private Animation firingSolvent = new Animation(theSolventFiring,10);

    private BufferedImage[] triggerTwins = {SpriteRetrival.getSprite(2,2,4)};
    private Animation idleTriggerTwins = new Animation(triggerTwins,10);
    private BufferedImage[] triggerTwinsFiring = {SpriteRetrival.getSprite(0,33,4), SpriteRetrival.getSprite(3,33,4)};
    private Animation firingTriggerTwins = new Animation(triggerTwinsFiring,10);

    private BufferedImage[] vacPac = {SpriteRetrival.getSprite(3,2,4)};
    private Animation idleVacPac = new Animation(vacPac,10);
    private BufferedImage[] vacPacFiring = {SpriteRetrival.getSprite(0,34,4), SpriteRetrival.getSprite(3,34,4), SpriteRetrival.getSprite(0,35,4), SpriteRetrival.getSprite(3,35,4),SpriteRetrival.getSprite(0,36,4), SpriteRetrival.getSprite(3,36,4)};
    private Animation firingVacPac = new Animation(vacPacFiring,10);

    private BufferedImage[] youMonster = {SpriteRetrival.getSprite(4,2,4)};
    private Animation idleYouMonster = new Animation(youMonster,10);
    private BufferedImage[] youMonsterFiring = {SpriteRetrival.getSprite(4,35,4), SpriteRetrival.getSprite(7,35,4), SpriteRetrival.getSprite(4,36,4), SpriteRetrival.getSprite(7,36,4), SpriteRetrival.getSprite(4,37,4), SpriteRetrival.getSprite(7,37,4), SpriteRetrival.getSprite(4,38,4), SpriteRetrival.getSprite(7,38,4)};
    private Animation firingYouMonster = new Animation(youMonsterFiring,50);

    private BufferedImage[] zorMarUlla = {SpriteRetrival.getSprite(5,2,4)};
    private Animation idleZorMarUlla = new Animation(zorMarUlla,10);
    private BufferedImage[] zorMarUllaFiring = {SpriteRetrival.getSprite(0,37,4), SpriteRetrival.getSprite(3,37,4), SpriteRetrival.getSprite(0,38,4), SpriteRetrival.getSprite(3,38,4), SpriteRetrival.getSprite(0,39,4), SpriteRetrival.getSprite(3,39,4), SpriteRetrival.getSprite(0,40,4), SpriteRetrival.getSprite(3,40,4), SpriteRetrival.getSprite(0,41,4), SpriteRetrival.getSprite(3,41,4), SpriteRetrival.getSprite(0,42,4), SpriteRetrival.getSprite(3,42,4)};
    private Animation firingZorMarUlla = new Animation(zorMarUllaFiring,10);

    private BufferedImage[] sword = {SpriteRetrival.getSprite(5,45,4)};
    private Animation idleSword = new Animation(sword, 10);
    private BufferedImage[] swordSwinging = {SpriteRetrival.getSprite(4, 44, 4), SpriteRetrival.getSprite(5,44,4), SpriteRetrival.getSprite(6,44,4), SpriteRetrival.getSprite(7,44,4), SpriteRetrival.getSprite(4, 45,4)};
    private Animation firingSwordSwinging = new Animation(swordSwinging,100);

    private Animation rangedAnimation;
    private Animation swordAnimation;
    private Animation idleRanged;
    private Animation idleMelee;
    public void mouseDragged(MouseEvent e){

    }
    public Weapons(Player p){
        this.p = p;
        imagePosition = new Point(p.getcX(),p.getcY());
    }

    public void mousePressed(MouseEvent e){
        if(e.getButton() == MouseEvent.BUTTON3){
            //rightClick = true;
            //swordAnimation.start();
        }
    }
    public void mouseReleased(MouseEvent e){
        if(e.getButton() == MouseEvent.BUTTON3){
            rightClick = true;
            //swordAnimation.stop();
        }
    }
    public void mouseMoved(MouseEvent e){
        mX = e.getX() + p.getCamX(); //set the mX and mY to the location of the cursor on the screen
        mY = e.getY() + p.getCamY();
        //System.out.println("mx, my: " + mX + ", " + mY);
        double dx = mX - imagePosition.getX(); //find s the distance from the cursur to the weapon
        double dy = mY - imagePosition.getY();
        imageAngleRad = Math.atan2(dy,dx); //find the angle of the weapon to the cursor
    }
    private void weaponDirection(){

    }
    public void paint(Graphics2D g2d){
        rangedAnimation = firingYouMonster; //set what Animation to draw
        rangedAnimation.start(); //starts the Animation
        meleeWeapons(g2d);
        if(mX < p.getX() + 7){
            direction = -1;
        } else {
            direction = 1;
        }

        int cx = rangedAnimation.getSprite().getWidth() / 2 - 10;
        //int cx = Animation.getSprite().getWidth() - 40;
        int cy = rangedAnimation.getSprite().getHeight() / 2 + 5;
        AffineTransform at = new AffineTransform();

        if(direction == 1) {
            at.translate(cx + imagePosition.x + 19, cy + imagePosition.y);
        }
        if(direction == -1){
            at.translate(cx + imagePosition.x - 21, cy + imagePosition.y);
            AffineTransformOp op = new AffineTransformOp(at, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
            op.filter(rangedAnimation.getSprite(), null);
        }
        at.rotate(imageAngleRad);
        at.scale(1.5,1.5);
        at.translate(-cx, -cy);
        if(!rightClick) {
            g2d.drawImage(rangedAnimation.getSprite(), at, null);
        }

        //g2d.rotate(Math.toRadians(45), Animation.getSprite().getWidth()/2, Animation.getSprite().getHeight() / 2);
        //g2d.drawImage(Animation.getSprite(),x + 5,y, Animation.getSprite().getHeight() * 2, Animation.getSprite().getWidth() * 2 ,null);
        //at.translate(x + 5, y);
        rangedAnimation.update(); //updates the Animation class so that it updates and will draw the new images.
    }

    public void meleeWeapons(Graphics2D g2d){
        swordAnimation = firingSwordSwinging;
        if(rightClick) {
            swordAnimation.start();
            if (meleeDelay == 0) {
                swordAnimation.stop();
                rightClick = false;
                meleeDelay = 500;
            }
            meleeDelay--;
        }
        int cX = swordAnimation.getSprite().getWidth() / 2 - 12;
        int cY = swordAnimation.getSprite().getHeight() / 2 - 3;


        if(rightClick) {
            //System.out.println("direction: " + direction);
            if(direction == 1) {
                hitboxX = imagePosition.x + 20;
                hitboxXT = imagePosition.x + 52;
                hitboxY = imagePosition.y - 20;
                hitboxYT = imagePosition.y + 75;
                g2d.setColor(new Color(227,177,48, 100));
                g2d.fillRect(hitboxX, hitboxY, 52, 75);
            }
            if(direction == -1) {
                hitboxX = imagePosition.x - 65;
                hitboxXT = imagePosition.x - 20;
                hitboxY = imagePosition.y - 20;
                hitboxYT = imagePosition.y + 75;
                g2d.setColor(new Color(227,177,48, 100));
                g2d.fillRect(hitboxX, hitboxY, 52, 75);
            }
            modifier = 0;
            damage = 2 + modifier;
            AffineTransform newAt = new AffineTransform();
            if(direction == 1){
                newAt.translate(cX + imagePosition.x + 20, cY + imagePosition.y + 5);
            }
            if(direction == -1){
                newAt.translate(cX + imagePosition.x - 20, cY + imagePosition.y + 5);
            }
            newAt.rotate(imageAngleRad);
            newAt.scale(3.0, 3.0);
            newAt.translate(-cX, -cY);
            g2d.drawImage(swordAnimation.getSprite(), newAt, null);
            //g2d.drawImage(swordAnimation.getSprite(), imagePosition.x, imagePosition.y, null);
            swordAnimation.update();
        }
    }
    public void move(){
        imagePosition.x = p.getX();
        imagePosition.y = p.getY();
    }
    /*public void collision(){
        //System.out.println("hitX, hitY, hitXT, hitYT: " + hitboxX + ", " + hitboxY + ", " + hitboxXT + ", " + hitboxYT);
        /*boolean x_overlaps = (et.getHitboxX() < hitboxXT && et.getHitboxXT() > hitboxX);
        boolean y_overlaps = (et.getHitboxY() < hitboxYT && et.getHitboxYT() > hitboxY);
        boolean x_overlaps = (hitboxX < et.getHitboxXT() && hitboxXT > et.getHitboxX());
        boolean y_overlaps = (hitboxY < et.getHitboxYT() && hitboxYT > et.getHitboxY());
        boolean collision = x_overlaps && y_overlaps;
        if(collision){
            et.damage(damage);
        }
    }*/
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
    public int getDamage(){
        return damage;
    }
}
