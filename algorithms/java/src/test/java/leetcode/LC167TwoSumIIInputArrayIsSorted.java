package leetcode;

import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertArrayEquals;

/**
 * https://leetcode.com/problems/two-sum-ii-input-array-is-sorted/
 * #easy
 */
public class LC167TwoSumIIInputArrayIsSorted {
    public class Solution {
        public int[] twoSum(int[] numbers, int target) {
            int[] indices = new int[2];
            if (numbers == null || numbers.length < 2) return indices;
            int a = 0;
            int z = numbers.length - 1;
            while (a < z) {
                int v = numbers[a] + numbers[z];
                if (v == target) {
                    indices[0] = a + 1;
                    indices[1] = z + 1;
                    break;
                } else if (v > target) {
                    z--;
                } else {
                    a++;
                }
            }
            return indices;
        }
    }

    @Test
    public void test_example() throws Exception {
        assertArrayEquals(new int[]{1, 2}, new Solution().twoSum(new int[]{2, 7, 11, 15}, 9));
    }
}
