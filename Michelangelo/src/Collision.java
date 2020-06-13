import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
public class Collision {
    private Cowabunga ba;
    private Player p;
    private Enemies e;
    private Weapons w;

    private int width = 0;
    private int height = 0;
    private static final int tileWidth = 16;
    private static final int tileHeight = 16;
    private BufferedImage img = null;
    private int[][] collision;
    //private boolean any_collision, Collision, x_overlaps, y_overlaps;
    private boolean left = false, right = false, top = false, bottom;

    public Collision(Player p, Enemies e, Weapons w){
        this.p = p;
        this.e = e;
        this.w = w;
    }
    public void setClasses(Player p, Enemies e, Weapons w){
        this.p = p;
        this.e = e;
        this.w = w;
    }
    public void playerCollision(){
        boolean x_overlaps = (p.getHitboxX() < e.getHitboxXT() && p.getHitboxXT() > e.getHitboxX());
        boolean y_overlaps = (p.getHitboxY() < e.getHitboxYT() && p.getHitboxYT() > e.getHitboxY());
        boolean collision = x_overlaps && y_overlaps;
        if(collision){
            p.damage(e.getEnemyDamage());
        }
        System.out.println("px, ex: " + p.getHitboxX() + ", " + e.getHitboxX());
    }
    public void enemyCollision(){

    }
    public void weaponCollision(){

    }
    /*private void generateHitBox(){  //failed attempt at collision
        for(int i = 0; i < width; i++){
            for(int j = 0; j < height; j++){
                int id = collision[i][j];
                if(id != -1){
                    left = i;
                    right = left + (tileWidth * 4);

                }
            }
        }
    }*/
    /*public boolean checkCollision(int x, int y){
        boolean any_collision = false;
        for(int i = 0; i < width; i++){
            for(int j = 0; j < height; j++){
                int id = collision[i][j]; //Finds the id of the tiles
                if(id != -1){ //check if the tile is not -1
                    int xt = i * tileWidth * 4; // creates the x and y values for the tile
                    int yt = j * tileHeight * 4;
                    boolean x_overlaps = (x < xt + (tileWidth * 4)) && (x + 32 > xt); //checks if the x values overlap. right, left
                    boolean y_overlaps = (y < yt + (tileHeight * 4)) && (y + 32 > yt); //checks if the y values overlap. bottom, top
                    boolean Collision = x_overlaps && y_overlaps;
                    if(Collision){ //checks if there is collision
                        any_collision = true;
                    }
                }
            }
        }
        if(any_collision){
            return true; //returns that the player is colliding
        }
        return false;
    }*/

}
