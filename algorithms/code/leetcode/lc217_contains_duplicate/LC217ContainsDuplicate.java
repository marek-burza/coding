package leetcode.lc217_contains_duplicate;

import java.util.HashSet;
import java.util.Set;

/**
 * https://leetcode.com/problems/contains-duplicate/ #easy
 */
public final class LC217ContainsDuplicate {
    public boolean containsDuplicate(final int[] nums) {
        Set<Integer> seen = new HashSet<>();
        for (int num : nums) {
            if (seen.contains(num)) {
                return true;
            }
            seen.add(num);
        }
        return false;
    }
}
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
