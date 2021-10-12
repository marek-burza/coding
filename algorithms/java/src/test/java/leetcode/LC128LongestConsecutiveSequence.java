package leetcode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * https://leetcode.com/problems/longest-consecutive-sequence/
 * #medium
 */
public final class LC128LongestConsecutiveSequence {
    public final class Solution {
        private class Range {
            private int a;
            private int z;

            Range(final int a, final int z) {
                this.a = a;
                this.z = z;
            }
        }

        public int longestConsecutive(final int[] nums) {
            Set<Integer> seen = new HashSet<>();
            Map<Integer, Range> map = new HashMap<>();
            int length = 0;
            for (int num : nums) {
                if (seen.contains(num)) {
                    continue;
                } else {
                    seen.add(num);
                }
                boolean less = num > Integer.MIN_VALUE && map.containsKey(num - 1);
                boolean more = num < Integer.MAX_VALUE && map.containsKey(num + 1);
                Range ante = null, post = null;
                if (less) {
                    ante = map.get(num - 1);
                }
                if (more) {
                    post = map.get(num + 1);
                }
                int a = num, z = num;
                if (less && more) {
                    a = ante.a;
                    z = post.z;
                }
                if (less) {
                    a = ante.a;
                }
                if (more) {
                    z = post.z;
                }
                Range range = new Range(a, z);
                map.put(a, range);
                map.put(z, range);
                int span = z - a + 1;
                if (span > length) {
                    length = span;
                }
            }
            return length;
            // This can be simplified by storing only the length of the range in the hash table instead of range itself
        }
    }

    @Test
    public void test_100_4_200_1_3_2() throws Exception {
        int[] nums1 = {100, 4, 200, 1, 3, 2};
        assertEquals(4, new Solution().longestConsecutive(nums1));
    }

    @Test
    public void test_longer() throws Exception {
        int[] nums2 = {4, 2, 2, -4, 0, -2, 4, -3, -4, -4, -5, 1, 4, -9, 5, 0, 6, -8, -1, -3, 6, 5, -8, -1, -5, -1, 2, -9, 1};
        assertEquals(8, new Solution().longestConsecutive(nums2));
    }
}
