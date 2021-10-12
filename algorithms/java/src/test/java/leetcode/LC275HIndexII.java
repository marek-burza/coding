package leetcode;

import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * https://leetcode.com/problems/h-index-ii/
 * #medium
 */
public class LC275HIndexII {
    public class Solution {
        public int hIndex(int[] citations) {
            int n = citations.length;
            int a = 0;
            int z = n;
            while (a < z) {
                int m = (a + z) >> 1;
                if (citations[m] == n - m) {
                    return n - m;
                } else if (citations[m] < n - m) {
                    a = m + 1;
                } else {
                    z = m;
                }
            }
            return n - a;
        }
    }

    @Test
    public void test_example() throws Exception {
        int[] citations = {0, 1, 3, 5, 6};
        assertEquals(3, new Solution().hIndex(citations));
    }

    @Test
    public void test_none() throws Exception {
        int[] citations = {0, 0, 0, 0, 0};
        assertEquals(0, new Solution().hIndex(citations));
    }
}
