package leetcode;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * https://leetcode.com/problems/unique-binary-search-trees/
 */
public class LC096UniqueBinarySearchTrees {
    public class Solution {
        public int numTrees(int n) {
            int[] cache = new int[n + 1];
            cache[0] = cache[1] = 1;
            for (int i = 2; i <= n; i++) {
                for (int j = 0; j < i; j++) {
                    cache[i] += cache[j] * cache[i - j - 1];
                }
            }
            return cache[n];
        }
    }

    @Test
    public void test_2() throws Exception {
        assertEquals(2, new Solution().numTrees(2));
    }

    @Test
    public void test_3() throws Exception {
        assertEquals(5, new Solution().numTrees(3));
    }

    @Test
    public void test_19() throws Exception {
        assertEquals(1767263190, new Solution().numTrees(19));
    }
}
