package leetcode.lc290_word_pattern;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public final class LC290WordPatternTests {
    @Test
    public void testAbbaDogCatCatDog() throws Exception {
        assertTrue(new LC290WordPattern().wordPattern("abba", "dog cat cat dog"));
    }

    @Test
    public void testAbbaDogCatCatFish() throws Exception {
        assertFalse(new LC290WordPattern().wordPattern("abba", "dog cat cat fish"));
    }

    @Test
    public void testAaaaDogCatCatDog() throws Exception {
        assertFalse(new LC290WordPattern().wordPattern("aaaa", "dog cat cat dog"));
    }

    @Test
    public void testAbbaDogDogDogDog() throws Exception {
        assertFalse(new LC290WordPattern().wordPattern("abba", "dog dog dog dog"));
    }

    @Test
    public void testAbBC() throws Exception {
        assertTrue(new LC290WordPattern().wordPattern("ab", "b c"));
    }

    @Test
    public void testMismatched() throws Exception {
        assertFalse(new LC290WordPattern().wordPattern("ab", "c"));
    }
}
