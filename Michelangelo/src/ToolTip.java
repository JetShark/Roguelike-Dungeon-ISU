import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
public class ToolTip {
    private int x, y;
    public ToolTip(){

    }
    public void paint(Graphics2D g2d){
        float thickness = 2;
        Stroke oldStroke = g2d.getStroke();
        g2d.setColor(Color.BLACK);
        g2d.setStroke(new BasicStroke(thickness));
        g2d.drawRect(1500,1000, 100, 50);
        g2d.setStroke(oldStroke);
        g2d.setColor(new Color(0,0,225,150));
        g2d.fillRect(1501, 1001, 99, 49);
        g2d.setColor(Color.WHITE);
        g2d.drawString("Test String", 1524, 1025);
    }
}
