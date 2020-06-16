import java.awt.*;
import java.awt.image.BufferedImage;
import java.nio.Buffer;

public class Equipment {
    private BufferedImage equipment;
    private int x,y;
    public Equipment(Cowabunga cb){
        //if(){

        //}
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
            case 0: case 4: case 8: case 12: case 16: case 19: case 21: case 23:
                equipment = SpriteRetrival.getSprite(j, 0, 8);break;
            case 1: case 5: case 9: case 13: case 17: case 20: case 22: case 24:
                equipment = SpriteRetrival.getSprite(j, 1, 8);break;
            case 2: case 6: case 10: case 14: case 18:
                equipment = SpriteRetrival.getSprite(j, 2, 8);break;
            case 3: case 7: case 11: case 15:
                equipment = SpriteRetrival.getSprite(j, 3, 8);break;
            default: break;
        }
    }
    public void setX(int x){
        this.x = x;
    }
    public void setY(int y) {
        this.y = y;
    }

    public void paint(Graphics2D g2d){
        g2d.drawImage(equipment, x, y, null);
    }
}
