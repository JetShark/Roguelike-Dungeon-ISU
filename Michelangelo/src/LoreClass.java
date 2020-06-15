import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class LoreClass {
    private String ItemNumber;
    private String Lore;
    private String name;
    private String type;
    private String lore;
    public LoreClass(){
        //ItemNumber = "1";
    }
    public void setItemLore(String Item){
        ItemNumber = Item;
    }
    public void setLore(String Lore){
        this.Lore = Lore;
    }
    public void lore(/*String Lore*/){
        try {
            BufferedReader levelReader = new BufferedReader(new FileReader(Lore));
            try {
                String row;
                int i=0;
                while ((row = levelReader.readLine()) != null) {
                    if (row.equals("["+ItemNumber+"]")) {
                        while ((row = levelReader.readLine()) != null) {
                            String[] layer = row.split("=");
                            if (layer.length<2) {break;}
                            String[] attribute = layer[1].split("\\?");
                            if(layer[0].equals("name")){
                                name = attribute[0];
                            }
                            if(layer[0].equals("type")){
                                type = attribute[0];
                            }
                            if(layer[0].equals("lore")){
                                lore = attribute[0];
                            }
                            i++;
                        }
                        break;
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println(e.toString());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println(e.toString());
        }
    }
    public String getName(){ return name; }
    public String getType(){ return type; }
    public String getLore(){ return lore; }
}
