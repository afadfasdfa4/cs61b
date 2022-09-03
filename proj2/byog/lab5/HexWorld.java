package byog.lab5;
import org.junit.Test;
import static org.junit.Assert.*;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import java.util.Random;

/**
 * Draws a world consisting of hexagonal regions.
 */
public class HexWorld {
    private static final int WIDTH = 90;
    private static final int HEIGHT = 40;

    public static void main(String[] args) {
        TERenderer ter = initialize_engine();
        // initialize tiles
        TETile[][] world = new TETile[WIDTH][HEIGHT];
        initialize_Tile(world);
        build_World(45,36,world,3);
        show(ter,world);
    }

    public static TERenderer initialize_engine(){
        TERenderer ter = new TERenderer();
        ter.initialize(WIDTH, HEIGHT);
        return ter;
    }
    public static void initialize_Tile(TETile[][] world){
        for (int x = 0; x < WIDTH; x += 1) {
            for (int y = 0; y < HEIGHT; y += 1) {
                world[x][y] = Tileset.NOTHING;
            }
        }
    }
    public static void addHexagon(int len,TETile[][] world,int x,int y,TETile t){
        int max_len = 2*(len-1) + len;
        //打印上半部分
        for(int i=1;i<=len;i++){
            int y_index = y+1-i;
            for(int j=1;j<=max_len;j++){
                int x_index = x+j-1;
                if(!(j<=(len-i)||(max_len-j<(len-i)))) {
                    world[x_index][y_index] = t;
                }
            }
        }
        //打印下半部分
        for(int i=1;i<=len;i++){
            int y_index = y+1-i-len;
            for(int j=1;j<=max_len;j++){
                int x_index = x+j-1;
                if(!((j<i)||(max_len-j)<i-1)){
                    world[x_index][y_index] = t;
                }
            }
        }
    }

    //没有指定就用默认的贴纸
    public static void addHexagon(int len,TETile[][] world,int x,int y){
        addHexagon(len,world,x,y,Tileset.LOCKED_DOOR);
    }

    public static void show(TERenderer ter,TETile[][] world){
        ter.renderFrame(world);
    }

    public static void build_World(int start_x,int start_y,TETile[][] world,int len){
        for(int i=0;i<5;i++){
            addHexagon(len,world,start_x,start_y-2*len*i);
        }
        int x1 = start_x - 2*len +1;
        int y1 = start_y - len;
        int x2 = start_x + 2*len -1;
        int y2 = start_y - len;
        for(int i=0;i<4;i++){
            addHexagon(len,world,x1,y1-2*len*i,Tileset.FLOWER);
            addHexagon(len,world,x2,y2-2*len*i,Tileset.FLOWER);
        }
        int x3 = x1 - 2*len +1;
        int y3 = y1 - len;
        int x4 = x2 + 2*len -1;
        int y4 = y2 - len;
        for(int i=0;i<3;i++){
            addHexagon(len,world,x3,y3-2*len*i,Tileset.TREE);
            addHexagon(len,world,x4,y4-2*len*i,Tileset.TREE);
        }
    }
}
