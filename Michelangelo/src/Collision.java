public class Collision {
    private Cowabunga cb;
    private Player p;
    private Enemies e;
    private Weapons w;
    private WorldDrops wd;


    private int hitboxX, hitboxY, hitboxXT, hitboxYT;
    private int enemyDamage;
    private boolean hit;
    private boolean alive;

    public Collision(Player p, Weapons w, Cowabunga cb){
        this.p = p;
        this.cb = cb;
        this.w = w;

    }
    /*public void setClasses(Player p, Enemies e, Weapons w){
        this.p = p;
        this.e = e;
        this.w = w;
    }*/

    public void playerCollision(){
        boolean x_overlaps;
        boolean y_overlaps;
        x_overlaps = (p.getHitboxX() < hitboxXT) && (p.getHitboxXT() > hitboxX);
        y_overlaps = (p.getHitboxY() < hitboxYT) && (p.getHitboxYT() > hitboxY);
        boolean collision = x_overlaps && y_overlaps;
        if(alive) {
            if (collision) {
                p.damage(enemyDamage);
            }
        }
        //System.out.println("px, ex: " + p.getHitboxX() + ", " + e.getHitboxX());
    }
    public boolean weaponCollision(){
        boolean x_overlaps;
        boolean y_overlaps;
        x_overlaps = (w.getHitboxX() < hitboxXT) && (w.getHitboxXT() > hitboxX);
        y_overlaps = (w.getHitboxY() < hitboxYT) && (w.getHitboxYT() > hitboxY);
        boolean collision = x_overlaps && y_overlaps;
        if(collision){
            return true;
        } else {
            return false;
        }
    }
    public void enemyProjectileCollision(EnemyProjectile enemyProjectile){
        boolean x_overlaps;
        boolean y_overlaps;
        x_overlaps = (enemyProjectile.getX() < p.getHitboxXT()) && (enemyProjectile.getX() > p.getHitboxX());
        y_overlaps = (enemyProjectile.getY() < p.getHitboxYT()) && (enemyProjectile.getY() > p.getHitboxY());
        boolean collision = x_overlaps && y_overlaps;
        //if(alive) {
            if (collision) {
                //System.out.println("collision: " + collision);
                //System.out.println("damage: " + enemyProjectile.getDamage());
                p.damage(enemyProjectile.getDamage());
            }
        //}
    }
    public boolean weaponProjectileCollision(WeaponProjectile weaponProjectile){
        boolean x_overlaps;
        boolean y_overlaps;
        x_overlaps = (weaponProjectile.getX() < hitboxXT) && (weaponProjectile.getX() > hitboxX);
        y_overlaps = (weaponProjectile.getY() < hitboxYT) && (weaponProjectile.getY() > hitboxY);
        boolean collision = x_overlaps && y_overlaps;
        if(collision){
           return true;
        } else {
            return false;
        }
    }

    public void bossCollision(){
        //}
    }

    public void setEnemiesHitboxs(int hitboxX, int hitboxY, int hitboxXT, int hitboxYT){
        this.hitboxX = hitboxX;
        this.hitboxY = hitboxY;
        this.hitboxXT = hitboxXT;
        this.hitboxYT = hitboxYT;
    }
    public boolean worldDropCollision(){
        wd = cb.getWd();
        boolean x_overlaps;
        boolean y_overlaps;
        x_overlaps = (p.getHitboxX() < wd.getX() + (16 * 4)) && (p.getHitboxXT() > wd.getX());
        y_overlaps = (p.getHitboxY() < wd.getY() + (16 * 4)) && (p.getHitboxYT() > wd.getY());
        boolean collision = x_overlaps && y_overlaps;
        if(collision){
            //System.out.println("x,y: " + wd.getX() + ", " + wd.getY());
            return true;
        } else {
            return false;
        }
    }
    public void setEnemyDamage(int enemyDamage){
        this.enemyDamage = enemyDamage;
    }
    public boolean getHit(){
        return hit;
    }
    public void setAlive(boolean alive){
        this.alive = alive;
    }
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
