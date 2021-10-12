package leetcode;

import org.junit.jupiter.api.Test;


import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * https://leetcode.com/problems/h-index/
 * #medium
 */
public class LC274HIndex {
    public class Solution {
        public int hIndexSlower(int[] citations) {
            Arrays.sort(citations);
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

        public int hIndex(int[] citations) {
            int n = citations.length;
            int[] counts = new int[n + 1];
            for (int citation : citations) {
                if (citation > n) {
                    counts[n]++;
                } else {
                    counts[citation]++;
                }
            }
            int counted = 0;
            for (int i = n; 0 <= i; i--) {
                counted += counts[i];
                if (counted >= i) {
                    return i;
                }
            }
            return 0;
        }
    }

    @Test
    public void test_example() throws Exception {
        int[] citations = {3, 0, 6, 1, 5};
        assertEquals(3, new Solution().hIndex(citations));
    }

    @Test
    public void test_none() throws Exception {
        int[] citations = {0, 0, 0, 0, 0};
        assertEquals(0, new Solution().hIndex(citations));
    }
}
