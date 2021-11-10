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
