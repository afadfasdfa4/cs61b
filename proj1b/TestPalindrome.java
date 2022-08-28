import org.junit.Test;
import static org.junit.Assert.*;

public class TestPalindrome {
    ///*// You must use this palindrome, and not instantiate
    // new Palindromes, or the autograder might be upset.
    static Palindrome palindrome = new Palindrome();

    @Test
    public void testWordToDeque() {
        Deque d = palindrome.wordToDeque("persiflage");
        String actual = "";
        for (int i = 0; i < "persiflage".length(); i++) {
            actual += d.removeFirst();
        }
        assertEquals("persiflage", actual);
    }
    //Uncomment this class once you've created your Palindrome class. */
    @Test
    public void testIspalindrome(){
        Palindrome p = new Palindrome();
        assertFalse(p.isPalindrome("cat"));
        assertFalse(p.isPalindrome("cttcd"));
        assertTrue(p.isPalindrome("abbba"));
        assertFalse(p.isPalindrome("Abbba"));
        assertTrue(p.isPalindrome("taat"));

    }
    @Test
    public void testIsPalindrome_OffByOne(){
        OffByOne cc = new OffByOne();
        Palindrome p = new Palindrome();
        assertTrue(p.isPalindrome("aeb",cc));
        assertTrue(p.isPalindrome("flake",cc));
        assertTrue(p.isPalindrome("flke",cc));
        assertFalse(p.isPalindrome("aea",cc));
        assertFalse(p.isPalindrome("boob",cc));

    }
}
