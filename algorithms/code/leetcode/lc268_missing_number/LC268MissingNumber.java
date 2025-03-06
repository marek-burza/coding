package leetcode.lc268_missing_number;

/**
 * https://leetcode.com/problems/missing-number/ #easy
 */
public final class LC268MissingNumber {
    public int missingNumber(final int[] nums) {
        long expected = nums.length * (nums.length + 1) / 2;
        long sum = 0;
        for (int value : nums) {
            sum += value;
        }
        return (int) (expected - sum);
    }
}
package leetcode.lc268_missing_number;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public final class LC268MissingNumberTests {
    @Test
    public void testExample() throws Exception {
        assertEquals(2, new LC268MissingNumber().missingNumber(new int[] { 0, 1, 3 }));
    }
}
