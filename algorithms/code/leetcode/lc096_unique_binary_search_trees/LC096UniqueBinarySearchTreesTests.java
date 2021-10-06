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
