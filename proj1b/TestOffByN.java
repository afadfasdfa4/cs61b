import org.junit.Test;
import static org.junit.Assert.*;

public class TestOffByN {
    static OffByN ofbn = new OffByN(5);
    @Test
    public void testEqualChars(){

        assertTrue(ofbn.equalChars('a','f'));
        assertFalse(ofbn.equalChars('a','e'));


    }



}
