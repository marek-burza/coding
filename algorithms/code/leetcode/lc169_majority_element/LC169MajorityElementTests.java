package leetcode.lc169_majority_element;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public final class LC169MajorityElementTests {
    @Test
    public void test12315161() throws Exception {
        int[] nums = { 1, 2, 3, 1, 5, 1, 6, 1 };
        assertEquals(1, new LC169MajorityElement().majorityElement(nums));
    }
}
