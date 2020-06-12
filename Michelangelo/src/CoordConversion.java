public class CoordConversion {

    private static Player p;

    public static void setPlayer(Player pInput) {
        p = pInput;
    }

    public static int screenToWorldX(int screen) {
        return p.getCamX() + screen;
    }

    public static int worldToScreenX (int world) {
        return world - p.getCamX();
    }

    public static int screenToWorldY(int screen) {
        return p.getCamY() + screen;
    }

    public static int worldToScreenY (int world) {
        return world - p.getCamY();
    }






}