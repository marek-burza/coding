package leetcode.lc107_binary_tree_level_order_traversal_ii;

import org.junit.jupiter.api.Test;

import leetcode.lc107_binary_tree_level_order_traversal_ii.LC107BinaryTreeLevelOrderTraversalII.TreeNode;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public final class LC107BinaryTreeLevelOrderTraversalIITests {
    @Test
    public void testEmpty() {
        assertEquals(0, new LC107BinaryTreeLevelOrderTraversalII().levelOrderBottom(null).size());
    }

    @Test
    public void testExample() {
        TreeNode n3 = new TreeNode(3);
        TreeNode n9 = new TreeNode(9);
        TreeNode n20 = new TreeNode(20);
        TreeNode n15 = new TreeNode(15);
        TreeNode n7 = new TreeNode(7);
        n3.left = n9;
        n3.right = n20;
        n20.left = n15;
        n20.right = n7;
        int[][] expected = { { 15, 7 }, { 9, 20 }, { 3 } };
        List<List<Integer>> result = new LC107BinaryTreeLevelOrderTraversalII().levelOrderBottom(n3);
        assertEquals(expected.length, result.size());
        for (int i = 0; i < expected.length; i++) {
            List<Integer> level = result.get(i);
            assertEquals(expected[i].length, level.size());
            for (int j = 0; j < expected[i].length; j++) {
                assertEquals(expected[i][j], level.get(j).intValue());
            }
        }
    }
}
