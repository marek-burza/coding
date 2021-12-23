package leetcode.lc128_longest_consecutive_sequence;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * https://leetcode.com/problems/longest-consecutive-sequence/
 * #medium
 */
public final class LC128LongestConsecutiveSequence {
    private static class Range {
        private int a;
        private int z;

        Range(final int aValue, final int zValue) {
            a = aValue;
            z = zValue;
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
            Range ante = null;
            Range post = null;
            if (less) {
                ante = map.get(num - 1);
            }
            if (more) {
                post = map.get(num + 1);
            }
            int a = num;
            int z = num;
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
        // This can be simplified by storing only the length of the range in the hash
        // table instead of range itself
    }
}
