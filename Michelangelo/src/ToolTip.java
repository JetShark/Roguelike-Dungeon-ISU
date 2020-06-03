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
        x = 800;
        y = 1000;
    }
    public void paint(Graphics2D g2d){
        float thickness = 2;
        Stroke oldStroke = g2d.getStroke();
        g2d.setColor(Color.BLACK);
        g2d.setStroke(new BasicStroke(thickness));
        g2d.drawRect(x,y, 200, 100);
        g2d.setStroke(oldStroke);
        g2d.setColor(new Color(0,0,225,150));
        g2d.fillRect(x + 1, y + 1, 199, 99);
        g2d.setColor(Color.WHITE);
        g2d.drawString(ii.getName(), x + 2, y + 12);
        g2d.drawString("Damage: " + ii.getDamage(), x + 2, y + 24);
        g2d.drawString("Number of Shots: " + ii.getNumOfShots(), x + 2, y + 36);
        g2d.drawString("Reload Time: " + ii.getReloadTime() + " Seconds", x + 2, y + 48);
        g2d.drawString("Special Effect: Knocks enemies \nback", x + 2, y + 60);
    }
}
