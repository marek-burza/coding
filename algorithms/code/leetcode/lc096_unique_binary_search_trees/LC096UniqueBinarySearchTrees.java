package leetcode.lc096_unique_binary_search_trees;

/**
 * https://leetcode.com/problems/unique-binary-search-trees/ #medium
 */
public final class LC096UniqueBinarySearchTrees {
    public int numTrees(final int n) {
        int[] cache = new int[n + 1];
        cache[0] = 1;
        cache[1] = 1;
        for (int i = 2; i <= n; i++) {
            for (int j = 0; j < i; j++) {
                cache[i] += cache[j] * cache[i - j - 1];
            }
        }
        return cache[n];
    }
}
package leetcode.lc096_unique_binary_search_trees;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public final class LC096UniqueBinarySearchTreesTests {
    @Test
    public void test2() throws Exception {
        assertEquals(2, new LC096UniqueBinarySearchTrees().numTrees(2));
    }

    @Test
    public void test3() throws Exception {
        assertEquals(5, new LC096UniqueBinarySearchTrees().numTrees(3));
    }

    @Test
    public void test19() throws Exception {
        assertEquals(1767263190, new LC096UniqueBinarySearchTrees().numTrees(19));
    }
}
