package leetcode.lc217_contains_duplicate;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public final class LC217ContainsDuplicateTests {
    @Test
    public void test057() throws Exception {
        assertFalse(new LC217ContainsDuplicate().containsDuplicate(new int[] { 0, 5, 7 }));
    }

    @Test
    public void test057510() throws Exception {
        assertTrue(new LC217ContainsDuplicate().containsDuplicate(new int[] { 0, 5, 7, 5, 10 }));
    }
}
