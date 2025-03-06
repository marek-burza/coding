package leetcode.lc383_ransom_note;

/**
 * https://leetcode.com/problems/ransom-note/ #easy
 */
public final class LC383RansomNote {
    public boolean canConstruct(final String ransomNote, final String magazine) {
        int[] counts = new int[256];
        for (char letter : magazine.toCharArray()) {
            counts[letter]++;
        }
        for (char letter : ransomNote.toCharArray()) {
            if (--counts[letter] < 0) {
                return false;
            }
        }
        return true;
    }
}
package leetcode.lc383_ransom_note;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public final class LC383RansomNoteTests {
    @Test
    public void testExampleAB() throws Exception {
        assertFalse(new LC383RansomNote().canConstruct("a", "b"));
    }

    @Test
    public void testExampleAaAb() throws Exception {
        assertFalse(new LC383RansomNote().canConstruct("aa", "ab"));
    }

    @Test
    public void testExampleAaAab() throws Exception {
        assertTrue(new LC383RansomNote().canConstruct("aa", "aab"));
    }
}
