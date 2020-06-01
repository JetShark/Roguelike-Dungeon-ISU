import java.awt.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
public class ItemsInformation {

    private String mapPath;
    private String name;
    private String damage;
    private String numOfShots;
    private String reloadTime;
    private String specialEffect;
    private String damageModifier;
    private String range;
    public ItemsInformation(String ItemPath, String ItemName){
        try {
            BufferedReader levelReader = new BufferedReader(new FileReader(ItemPath));
            try {
                String row;
                int i=0;

                while ((row = levelReader.readLine()) != null) {
                    if (row.equals("["+ItemName+"]")) {
                        while ((row = levelReader.readLine()) != null) {
                            String[] layer = row.split("=");
                            if (layer.length<2) {break;}
                            String[] attribute = layer[1].split("\\?");
                            name = attribute[0];
                            if(layer[0].equals("RangedWeapon")){
                                damage = attribute[1];
                                specialEffect = attribute[2];
                                numOfShots = attribute[3];
                                reloadTime = attribute[4];
                            }
                            if(layer[0].equals("MeleeWeapon")){
                                damage = attribute[1];
                                specialEffect = attribute[2];
                                damageModifier = attribute[3];
                                range = attribute[4];
                            }
                            if(layer[0].equals("Equipment")){

                            }

                            i++;
                        }
                        break;
                    }
                }
                /*
                System.out.println("Map Width & Height = " + width + ", " + height);
                img = ImageIO.read(new File(tilePath));
                tilesAcross = img.getWidth() / tileWidth;
                tilesDown = img.getHeight() / tileHeight;
                */
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println(e.toString());
            }
            //this.ba = ba;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println(e.toString());
        }
    }

    public String getName(){
        return name;
    }
    public String getDamage(){
        return damage;
    }
    public String getNumOfShots(){
        return numOfShots;
    }
    public String getReloadTime(){
        return reloadTime;
    }
    public String getSpecialEffect(){
        return specialEffect;
    }
    public String getDamageModifier(){
        return damageModifier;
    }
    public String getRange() {
        return range;
    }
}
