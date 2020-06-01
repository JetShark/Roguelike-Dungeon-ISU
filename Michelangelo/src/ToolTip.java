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
    public ToolTip(){
        ii = new ItemsInformation("src/ItemInformation/Item-Information.txt", "1");
    }
    public void paint(Graphics2D g2d){
        float thickness = 2;
        Stroke oldStroke = g2d.getStroke();
        g2d.setColor(Color.BLACK);
        g2d.setStroke(new BasicStroke(thickness));
        g2d.drawRect(1500,1000, 200, 100);
        g2d.setStroke(oldStroke);
        g2d.setColor(new Color(0,0,225,150));
        g2d.fillRect(1501, 1001, 199, 99);
        g2d.setColor(Color.WHITE);
        g2d.drawString(ii.getName(), 1502, 1012);
        g2d.drawString("Damage: " + ii.getDamage(), 1502, 1024);
        g2d.drawString("Number of Shots: " + ii.getNumOfShots(), 1502, 1036);
        g2d.drawString("Reload Time: " + ii.getReloadTime() + " Seconds", 1502, 1048);
        g2d.drawString("Special Effect: Knocks enemies \nback", 1502, 1060);
    }
}
