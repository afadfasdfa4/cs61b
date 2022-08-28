import org.junit.Test;
import static org.junit.Assert.*;

public class TestOffByN {

    @Test
    public void testEqualChars(){
        OffByN ofbn = new OffByN(5);
        assertTrue(ofbn.equalChars('a','f'));
        assertFalse(ofbn.equalChars('a','e'));


    }



}
