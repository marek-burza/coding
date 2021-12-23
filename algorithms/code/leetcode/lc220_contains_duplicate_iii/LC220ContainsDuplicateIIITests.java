package leetcode.lc220_contains_duplicate_iii;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public final class LC220ContainsDuplicateIIITests {
    @Test
    public void test110202() throws Exception {
        int[] nums = { 1, 10, 20, 2 };
        assertTrue(new LC220ContainsDuplicateIII().containsNearbyAlmostDuplicate(nums, 3, 2));
    }

    @Test
    public void test110204() throws Exception {
        int[] nums = { 1, 10, 20, 4 };
        assertFalse(new LC220ContainsDuplicateIII().containsNearbyAlmostDuplicate(nums, 3, 2));
    }

    @Test
    public void test11020302() throws Exception {
        int[] nums = { 1, 10, 20, 30, 2 };
        assertFalse(new LC220ContainsDuplicateIII().containsNearbyAlmostDuplicate(nums, 3, 2));
    }

    @Test
    public void test8715161915And1And3() throws Exception {
        int[] nums = { 8, 7, 15, 1, 6, 1, 9, 15 };
        assertTrue(new LC220ContainsDuplicateIII().containsNearbyAlmostDuplicate(nums, 1, 3));
    }

    @Test
    public void test21474836402147483641And1And100() throws Exception {
        int[] nums = { 2147483640, 2147483641 };
        assertTrue(new LC220ContainsDuplicateIII().containsNearbyAlmostDuplicate(nums, 1, 100));
    }
}
