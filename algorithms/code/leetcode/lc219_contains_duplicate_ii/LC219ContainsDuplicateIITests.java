package leetcode.lc219_contains_duplicate_ii;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public final class LC219ContainsDuplicateIITests {
    @Test
    public void test057And2() throws Exception {
        assertEquals(false, new LC219ContainsDuplicateII().containsNearbyDuplicate(new int[] { 0, 5, 7 }, 2));
    }

    @Test
    public void test0575And2() throws Exception {
        assertEquals(true, new LC219ContainsDuplicateII().containsNearbyDuplicate(new int[] { 0, 5, 7, 5 }, 2));
    }

    @Test
    public void test057105And2() throws Exception {
        assertEquals(false, new LC219ContainsDuplicateII().containsNearbyDuplicate(new int[] { 0, 5, 7, 10, 5 }, 2));
    }
}
