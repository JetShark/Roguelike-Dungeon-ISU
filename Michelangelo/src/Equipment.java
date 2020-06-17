import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.nio.Buffer;

public class Equipment {
    private BufferedImage equipment;
    private int x,y;
    private int ix, iy;
    private boolean inventory;
    private int on = 0;
    private Cowabunga cb;
    private Player p;
    private boolean head, chest, boots, gloves, accessory;
    public Equipment(Cowabunga cb){
        this.cb = cb;
        inventory = false;
    }
    public void keyPressed(KeyEvent e){
        if(e.getKeyCode() == KeyEvent.VK_I) {
            if (on == 0) {
                inventory = true;
            }
            if (on == 1) {
                inventory = false;
            }
        }
    }
    public void keyReleased(KeyEvent e){
        if(e.getKeyCode() == KeyEvent.VK_I){
            on = 1;
            if (!inventory) {
                on = 0;
            }
        }
    }
    public void setEquipment(int i){
        int j = 0;
        switch(i){
            case 0: case 1: case 2: case 3:
                j = 0; break;
            case 4: case 5: case 6: case 7:
                j = 1; break;
            case 8: case 9: case 10: case 11:
                j = 2; break;
            case 12: case 13: case 14: case 15:
                j = 3; break;
            case 16: case 17: case 18:
                j = 4; break;
            case 19: case 20:
                j = 5; break;
            case 21: case 22:
                j = 6; break;
            case 23: case 24:
                j = 7; break;
            default: break;
        }
        switch(i){
            case 0: head = true; case 4: head = true; case 8: head = true; case 12: head = true; case 16: accessory = true; case 19: accessory = true; case 21: accessory = true; case 23:
                equipment = SpriteRetrival.getSprite(j, 0, 8);break;
            case 1: boots = true; case 5: boots = true; case 9: boots = true; case 13: boots = true; case 17: accessory = true; case 20: accessory = true; case 22: accessory = true; case 24:
                equipment = SpriteRetrival.getSprite(j, 1, 8);break;
            case 2: chest = true; case 6: chest = true; case 10: chest = true; case 14: chest = true; case 18:
                equipment = SpriteRetrival.getSprite(j, 2, 8);break;
            case 3: gloves = true; case 7: gloves = true; case 11: gloves = true; case 15: gloves = true;
                equipment = SpriteRetrival.getSprite(j, 3, 8); gloves = true; break;
            default: break;
        }
        switch(i){
            case 23: case 24: case 18: accessory = true;
        }
    }
    public void setX(int x){
        this.x = x;
    }
    public void setY(int y) {
        this.y = y;
    }
    public void move(){
        p = cb.getP();
        ix = p.getCamX();
        iy = p.getCamY();
    }
    public void paint(Graphics2D g2d){
        float thickness = 4;
        Stroke oldStroke = g2d.getStroke();
        double width = equipment.getWidth() * 1.5;
        double height = equipment.getHeight() * 1.5;
        if(cb.getEnemiesInRoom() == 0) {
            g2d.drawImage(equipment, x, y, null);
        }
        if(inventory) {
            //System.out.println("inv; " + inventory);
            g2d.setColor(Color.BLACK);
            g2d.setStroke(new BasicStroke(thickness));
            g2d.drawRect(ix + 20, iy + 150, 150, 300);
            g2d.setStroke(oldStroke);
            g2d.setColor(new Color(0, 0, 225, 150));
            g2d.fillRect(ix + 21, iy + 151, 147, 297);
            if(head) {
                g2d.drawImage(equipment, ix + 25, iy + 155, (int) width, (int) height, null);
            }
            if(chest){
                g2d.drawImage(equipment, ix + 25, iy + 155 + ((int) height) + 10, (int) width, (int) height, null);
            }
            if(gloves){
                g2d.drawImage(equipment, ix + 25, iy + 155 + ((int) height * 2) + 20, (int) width, (int) height, null);
            }
            if(boots){
                g2d.drawImage(equipment, ix + 25, iy + 155 + ((int) height * 3) + 30, (int) width, (int) height, null);
            }
            if(accessory){
                g2d.drawImage(equipment, ix + 25, iy + 155 + ((int) height * 4) + 40, (int) width, (int) height, null);
            }
        }
    }
}
