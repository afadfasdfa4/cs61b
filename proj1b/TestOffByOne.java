import org.junit.Test;
import static org.junit.Assert.*;

public class TestOffByOne {
    ///*
    // You must use this CharacterComparator and not instantiate
    // new ones, or the autograder might be upset.
    static CharacterComparator offByOne = new OffByOne();

    // Your tests go here.
    //Uncomment this class once you've created your CharacterComparator interface and OffByOne class. **/
    @Test
    public void testEqualChars(){
        OffByOne a = new OffByOne();
        assertTrue(a.equalChars('a','b'));
        assertTrue(a.equalChars('%','&'));
        assertTrue(a.equalChars('s','t'));
        assertFalse(a.equalChars('a','a'));
        assertFalse(a.equalChars('e','n'));
    }

}
