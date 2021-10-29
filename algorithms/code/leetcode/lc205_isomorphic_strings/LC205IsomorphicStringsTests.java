package leetcode.lc205_isomorphic_strings;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public final class LC205IsomorphicStringsTests {
    @Test
    public void testAaAndAb() throws Exception {
        assertFalse(new LC205IsomorphicStrings().isIsomorphic("aa", "ab"));
    }

    @Test
    public void testEggAndAdd() throws Exception {
        assertTrue(new LC205IsomorphicStrings().isIsomorphic("egg", "add"));
    }

    @Test
    public void testAcAndBb() throws Exception {
        assertFalse(new LC205IsomorphicStrings().isIsomorphic("ac", "bb"));
    }
}
