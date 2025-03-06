package leetcode.lc387_first_unique_character_in_a_string;

/**
 * https://leetcode.com/problems/first-unique-character-in-a-string/ #easy
 */
public final class LC387FirstUniqueCharacterInAString {
    public int firstUniqChar(final String s) {
        int size = 'z' - 'a' + 1;
        int[] count = new int[size];
        int[] index = new int[size];
        int length = s.length();
        for (int i = length - 1; 0 <= i; i--) {
            int key = s.charAt(i) - 'a';
            index[key] = i;
            count[key]++;
        }
        int min = -1;
        for (int i = 0; i < size; i++) {
            if (count[i] == 1) {
                if (min == -1 || index[i] < min) {
                    min = index[i];
                }
            }
        }
        return min;
    }
}
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
