import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class RoomTest {
    private int[][] lightArray;
    private int w = 7, h = 12;
    public BufferedImage img = null;

    public RoomTest(){
        try {
            img = ImageIO.read(new File("src/SpriteSheets/tiletestsprite.png"));
        } catch (IOException e) {
            System.out.println("No Image");
        }
        lightArray = new int[w][h];
        for(int i = 0; i < w; i++){
            for(int j = 0; j < h; j++){
                if(Math.random() >= 0.25){
                    lightArray[i][j] = 1;
                } else if(Math.random() <= 0.25){
                    lightArray[i][j] = 0;
                }
            }
        }
    }

    public void paint(Graphics2D g){
        int i = 0, j = 0;
        g.setColor(Color.WHITE);
        g.drawRect(0, 96, 48,48);
        g.drawRect(1200, 96, 48,48);
        g.drawRect(96, 0, 48,48);
        g.drawRect(96, 720, 48,48);
        for(i = 0; i < w; i++){
            for(j = 0; j < h; j++){
                g.drawImage(img, 48 + 96*j, 48 + 96*i, img.getWidth() * 6, img.getHeight() * 6, null);
                //g.setColor(Color.WHITE);
                //g.drawRect(48 + 96 * j, 48 + 96*i, 96,96);
                //if(lightArray[i][j] == 1){
                    g.setColor(Color.YELLOW);
                    //g.drawRect(48 + 96*j, 48 + 96*i, 96,96);
                //} else {
                    g.setColor(new Color(189, 189, 189));
                    //g.drawRect(48 + 96*j, 48 + 96*i, 96,96);
               // }
            }
        }

    }
}
