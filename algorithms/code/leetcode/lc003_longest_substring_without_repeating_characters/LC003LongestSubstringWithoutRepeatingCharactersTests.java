package leetcode.lc003_longest_substring_without_repeating_characters;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public final class LC003LongestSubstringWithoutRepeatingCharactersTests {
    @Test
    public void testAbcabcbb() throws Exception {
        LC003LongestSubstringWithoutRepeatingCharacters solution;
        solution = new LC003LongestSubstringWithoutRepeatingCharacters();
        assertEquals(3, solution.lengthOfLongestSubstring("abcabcbb"));
    }

    @Test
    public void testBbbbb() throws Exception {
        LC003LongestSubstringWithoutRepeatingCharacters solution;
        solution = new LC003LongestSubstringWithoutRepeatingCharacters();
        assertEquals(1, solution.lengthOfLongestSubstring("bbbbb"));
    }

    @Test
    public void testDvdf() throws Exception {
        LC003LongestSubstringWithoutRepeatingCharacters solution;
        solution = new LC003LongestSubstringWithoutRepeatingCharacters();
        assertEquals(3, solution.lengthOfLongestSubstring("dvdf"));
    }
}
