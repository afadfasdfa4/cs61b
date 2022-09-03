package byog.lab6;

import org.junit.Test;

public class TestMemoryGame {
    @Test
    public void TestRandomString(){
        MemoryGame m = new MemoryGame(50,50);
        System.out.println(m.generateRandomString(5));
    }
    @Test
    public void TestDrawFrame(){
        MemoryGame m = new MemoryGame(500,500);
        m.drawFrame("AFV");
    }

    public static void main(String[] args) {
        MemoryGame m = new MemoryGame(500,500);
        //m.drawFrame("AFV");
        m.flashSequence("avbfe");
        m.solicitNCharsInput(5);

    }




}
