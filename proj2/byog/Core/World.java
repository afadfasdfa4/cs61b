package byog.Core;

import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;
import byog.TileEngine.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class World {
    TETile[][] tt;
    int[][] isUsed;
    TERenderer tr;
    Random random;
    ArrayList<Room> arr;
    public static final int WIDTH = 80;
    public static final int HEIGHT = 40;

    public World(){
        tt = new TETile[WIDTH][HEIGHT];
        //填充黑色Tile
        for(int i=0;i<WIDTH;i++){
            for(int j=0;j<HEIGHT;j++){
                tt[i][j] = Tileset.NOTHING;
            }
        }
        isUsed = new int[WIDTH][HEIGHT];
        tr = new TERenderer();
        //初始化引擎  0坐标在左下角
        tr.initialize(WIDTH,HEIGHT,0,0);
        arr = new ArrayList<>();
        random = new Random();

    }

    public static class Room{
        int x_position;
        int y_position;
        boolean isUnion;
        int x_len;
        int y_len;
        public Room(int xp,int yp,int xl,int yl){
            x_len = xl;
            y_len = yl;
            x_position = xp;
            y_position = yp;
        }
        public boolean equals(Room r){
            return x_position==r.x_position&&y_position== r.y_position;
        }
    }
    public void setRoom(Room room){
        int x = room.x_position;
        int y = room.y_position;
        for(int i=0;i<room.x_len;i++){
            for(int j=0;j<room.y_len;j++){
                if(i==0||i== room.x_len-1||j==0||j== room.y_len-1){
                    if(isUsed[x + i][y - j]!=2) {  // 原来如果是底板 则不覆盖
                        tt[x + i][y - j] = Tileset.LOCKED_DOOR;
                    }
                    isUsed[x+i][y-j] = 1; //1代表墙壁 2代表底板
                }else {
                    tt[x+i][y-j] = Tileset.TREE;
                    isUsed[x + i][y - j] = 2;
                }
            }
        }
        arr.add(room);   //加到列表中

    }
    public boolean can_setRoom(Room room){
        int x = room.x_position;
        int y = room.y_position;
        for(int i=0;i<room.x_len;i++){
            for(int j=0;j<room.y_len;j++){   //超过边界也要考虑

                if((x+i>=WIDTH)||
                        (y-j<=0)||(y-j>=HEIGHT)||(x+i<=0)||
                        isUsed[x+i][y-j]!=0) return false;

            }
        }
        return true;
    }

    public void set_N_Random_Rooms(int n){
        for(int i=0;i<n;i++) {
            Room r;
            do {
                int xp = RandomUtils.uniform(random, WIDTH);
                int yp = RandomUtils.uniform(random, HEIGHT);
                int xl = RandomUtils.uniform(random,5,(int)(WIDTH/6));
                int yl = RandomUtils.uniform(random,5,(int)(WIDTH/8));
                r = new Room(xp, yp, xl, yl);
            } while (!can_setRoom(r));
            setRoom(r);
        }
    }


     public void setCross(Room r1,Room r2){

        int xp1 = r1.x_position;
        int xp2 = r2.x_position;
        int yp1 = r1.y_position;
        int yp2 = r2.y_position;
        int xx1,yy1,xx2,yy2;
        xx1 = RandomUtils.uniform(random,xp1+1,xp1+r1.x_len-1);
        xx2 = RandomUtils.uniform(random,xp2+1,xp2+r2.x_len-1);
        yy1 = RandomUtils.uniform(random,yp1-r1.y_len+2,yp1);
        yy2 = RandomUtils.uniform(random,yp2-r2.y_len+2,yp2);
        int xlen = Math.abs(xx1-xx2);
        int ylen = Math.abs(yy1-yy2);
        int sign1,sign2;
        if(xx1>=xx2) sign1 = 1;
        else sign1 = -1;
        if(yy1>=yy2) sign2 = 1;
        else sign2 = -1;


        for(int i=0;i<xlen;i++){
            tt[xx1 - sign1*i][yy1] = Tileset.TREE;
            if(isUsed[xx1 - sign1*i][yy1]!=2){
                if(tt[xx1 - sign1*i][yy1+1].equals(Tileset.NOTHING)) {
                    tt[xx1 - sign1 * i][yy1 + 1] = Tileset.LOCKED_DOOR;
                }
                if(tt[xx1 - sign1*i][yy1-1].equals(Tileset.NOTHING)) {
                    tt[xx1 - sign1 * i][yy1 - 1] = Tileset.LOCKED_DOOR;
                }
            }
        }
        for(int i=0;i<ylen;i++){
            tt[xx2][yy1 - sign2*i] = Tileset.TREE;
            if(isUsed[xx2][yy1 - sign2*i]!=2){
                if(i==0) {
                    if(tt[xx2+1][yy1 - sign2*i].equals(Tileset.NOTHING)) {
                        tt[xx2 + 1][yy1 - sign2 * i] = Tileset.LOCKED_DOOR;
                    }
                    if(tt[xx2][yy1 - sign2*i-1].equals(Tileset.NOTHING)) {
                        tt[xx2][yy1 - sign2 * i - 1] = Tileset.LOCKED_DOOR;
                    }
                    if(tt[xx2+1][yy1 - sign2*i-1].equals(Tileset.NOTHING)) {
                        tt[xx2 + 1][yy1 - sign2 * i - 1] = Tileset.LOCKED_DOOR;
                    }
                }else {
                    if(tt[xx2 + 1][yy1 - sign2 * i].equals(Tileset.NOTHING)) {
                        tt[xx2 + 1][yy1 - sign2 * i] = Tileset.LOCKED_DOOR;
                    }
                    if(tt[xx2 - 1][yy1 - sign2 * i].equals(Tileset.NOTHING)) {
                        tt[xx2 - 1][yy1 - sign2 * i] = Tileset.LOCKED_DOOR;
                    }
                }
            }
        }

        tt[xx1][yy1] = Tileset.FLOWER;
         tt[xx2][yy2] = Tileset.FLOWER;

     }

     public void set_All_Cross(ArrayList<Room> arr){
        for(Room r1:arr){
            Room r2;
            do{
                r2 = arr.get(RandomUtils.uniform(random,arr.size()));
            }while (r1.equals(r2));
            setCross(r1,r2);
        }
     }












}
