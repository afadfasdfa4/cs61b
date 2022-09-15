package byog.TileEngine;

public class testTileSet {
    public static void main(String[] args) {
        TETile[][] tt;
        tt = new TETile[3][3];
        tt[0][0] = Tileset.NOTHING;
        System.out.println(tt[0][0].equals(Tileset.NOTHING) );
        System.out.println(tt[0][1]);
    }


}
