import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.TimeUnit;
import javax.swing.*;

public class Enemytest {
    private int x = 80, y = 176;
    private int i;
    private RougeLikeTest rlt;
    private int acdtms = 0;
    private int moveDelay = 0;
    private int mD = 0;
    public Enemytest(RougeLikeTest rlt){
        this.rlt = rlt;
    }
    public void move() throws InterruptedException{
        //moveLeft();
        mD += 1;
        if(mD == 5){
            moveRight();
            mD = 0;
        }


    }
    public void moveLeft(){
        long accumulatedTimeMs = 0;
        while(true){
            long startTime =  System.currentTimeMillis();
            rlt.repaint();
            while(accumulatedTimeMs >= 200){
                if(x < rlt.getWidth() - 150) {
                    x = x + 96;

                }
                accumulatedTimeMs -= 200;
            }
            long endTime = System.currentTimeMillis();
            accumulatedTimeMs += endTime - startTime;
        }
    }
    public void moveRight(){
        moveDelay += 1;
        if(x < rlt.getWidth() - 150 && moveDelay <= 28) {
            x = x + 7;
        }
        if(moveDelay >= 29 && moveDelay <= 42  && y < rlt.getHeight() - 150){
            y = y + 7;
            if(moveDelay == 43){
                moveDelay = 0;
            }
        }
    }
    public void moveUp(){

    }
    public void moveDown(){

    }
    public void paint(Graphics2D g2d){
        g2d.setColor(Color.BLUE);
        g2d.fillRect(x,y,32,32);
    }
}
