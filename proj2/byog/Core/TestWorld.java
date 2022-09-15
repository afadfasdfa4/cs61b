package byog.Core;

import org.junit.Test;

public class TestWorld {

    public static void main(String[] args) {
        World w = new World();
        w.set_N_Random_Rooms(20);
        w.set_All_Cross(w.arr);
        w.tr.renderFrame(w.tt);
    }


}
