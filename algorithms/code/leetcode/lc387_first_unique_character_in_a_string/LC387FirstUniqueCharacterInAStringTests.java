package leetcode.lc387_first_unique_character_in_a_string;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public final class LC387FirstUniqueCharacterInAStringTests {
    @Test
    public void testLeetcode() throws Exception {
        assertEquals(0, new LC387FirstUniqueCharacterInAString().firstUniqChar("leetcode"));
    }

    @Test
    public void testLoveleetcode() throws Exception {
        assertEquals(2, new LC387FirstUniqueCharacterInAString().firstUniqChar("loveleetcode"));
    }

    @Test
    public void testEmpty() throws Exception {
        assertEquals(-1, new LC387FirstUniqueCharacterInAString().firstUniqChar(""));
    }
}
