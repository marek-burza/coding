package leetcode.lc242_valid_anagram;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public final class LC242ValidAnagramTests {
    @Test
    public void testAaA() throws Exception {
        assertFalse(new LC242ValidAnagram().isAnagram("aa", "a"));
    }

    @Test
    public void testAB() throws Exception {
        assertFalse(new LC242ValidAnagram().isAnagram("a", "b"));
    }

    @Test
    public void testAnagramNagaram() throws Exception {
        assertTrue(new LC242ValidAnagram().isAnagram("anagram", "nagaram"));
    }

    @Test
    public void testRatCar() throws Exception {
        assertFalse(new LC242ValidAnagram().isAnagram("rat", "car"));
    }
}
