import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
public class ToolTip {
    private int x, y;
    private ItemsInformation ii;
    private String type = "null";
    private String amount = "0";
    private boolean equip = false, key= false, weapon = false, bomb = false, heart = false, item = false;
    public ToolTip(){
        ii = new ItemsInformation("src/ItemInformation/Item-Information.txt", "1");
        //x = 800;
        //y = 1000;
    }
    public void setX(int x) {
        this.x = x;
    }
    public void setY(int y) {
        this.y = y;
    }
    public void setItem(boolean item, String type){
        if(type.equals("heart")){
            heart = item;
            this.type = type;
            amount = "10";
        }
        if (type.equals("key")){
            key = item;
            this.type = type;
            amount = "10";
        }
        if(type.equals("bomb")){
            bomb = item;
            this.type = type;
            amount = "15";
        }
        if(type.equals("false")){
            this.type = type;
        }
        this.item = item;
    }
    public void setEquipment(boolean equip){

    }
    public void setWeapon(boolean weapon){

    }
    public void paint(Graphics2D g2d){
        //System.out.println("X,y: " + x + ", " + y);
        float thickness = 2;
        Stroke oldStroke = g2d.getStroke();
        int strWidth = g2d.getFontMetrics().stringWidth(type);
        if(!item) {
            g2d.setColor(Color.BLACK);
            g2d.setStroke(new BasicStroke(thickness));
            g2d.drawRect(x, y, 200, 100);
            g2d.setStroke(oldStroke);
            g2d.setColor(new Color(0, 0, 225, 150));
            g2d.fillRect(x + 1, y + 1, 198, 98);
            g2d.setColor(Color.WHITE);
            g2d.drawString(ii.getName(), x + 2, y + 12);
            g2d.drawString("Damage: " + ii.getDamage(), x + 2, y + 24);
            g2d.drawString("Number of Shots: " + ii.getNumOfShots(), x + 2, y + 36);
            g2d.drawString("Reload Time: " + ii.getReloadTime() + " Seconds", x + 2, y + 48);
            //g2d.drawString("Special Effect: Knocks enemies \nback", x + 2, y + 60);

            String text = "Special Effect: " + ii.getSpecialEffect();
            int lineWidth = 195;
            int xt = x, yt = y;
            FontMetrics m = g2d.getFontMetrics();
            if (m.stringWidth(text) < lineWidth) {
                g2d.drawString(text, xt + 2, yt + 60);
            } else {
                String[] words = text.split(" ");
                String currentLine = words[0];
                for (int i = 1; i < words.length; i++) {
                    if (m.stringWidth(currentLine + words[i]) < lineWidth) {
                        currentLine += " " + words[i];
                    } else {
                        g2d.drawString(currentLine, xt + 2, yt + 60);
                        yt += m.getHeight() - 2;
                        currentLine = words[i];
                    }
                }
                if (currentLine.trim().length() > 0) {
                    g2d.drawString(currentLine, xt + 2, yt + 60);
                }
            }
        }
        if(item){
            g2d.setColor(Color.BLACK);
            g2d.setStroke(new BasicStroke(thickness));
            g2d.drawRect(x + 45, y + 16, strWidth + 30, 15);
            g2d.setStroke(oldStroke);
            g2d.setColor(new Color(0, 0, 225, 150));
            g2d.fillRect(x + 46, y + 17, strWidth + 28, 13);
            g2d.setColor(Color.WHITE);
            g2d.drawString(type + ": " + amount + "$", x + 47, y + 28);
        }
    }
}
