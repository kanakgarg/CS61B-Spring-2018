import org.junit.Test;
import static org.junit.Assert.*;

public class TestPalindrome {
    // You must use this palindrome, and not instantiate
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

    @Test
    public void testIfPalindrome() {
        assertEquals(palindrome.isPalindrome("a"), true);
        assertEquals(palindrome.isPalindrome(""), true);
        assertEquals(palindrome.isPalindrome("racecar"), true);
        assertEquals(palindrome.isPalindrome("persiflage"), false);
        assertNotEquals(palindrome.isPalindrome("kanak"), false);
    }

    @Test
    public void testIfPalindromeCC() {
        OffByOne obo = new OffByOne();
        assertEquals(palindrome.isPalindrome("a", obo), true);
        assertEquals(palindrome.isPalindrome("", obo), true);
        assertEquals(palindrome.isPalindrome("racecbr", obo), false);
        assertEquals(palindrome.isPalindrome("persiflage", obo), false);
        assertNotEquals(palindrome.isPalindrome("jbnak", obo), false);
    }

}
