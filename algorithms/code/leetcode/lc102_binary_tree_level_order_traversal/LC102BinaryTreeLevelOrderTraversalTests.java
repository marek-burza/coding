package leetcode.lc102_binary_tree_level_order_traversal;

import java.util.List;

import org.junit.jupiter.api.Test;

import leetcode.lc102_binary_tree_level_order_traversal.LC102BinaryTreeLevelOrderTraversal.TreeNode;

import static org.junit.jupiter.api.Assertions.assertEquals;

public final class LC102BinaryTreeLevelOrderTraversalTests {
    @Test
    public void testExample() throws Exception {
        TreeNode n3 = new TreeNode(3);
        TreeNode n7 = new TreeNode(7);
        TreeNode n9 = new TreeNode(9);
        TreeNode n15 = new TreeNode(15);
        TreeNode n20 = new TreeNode(20);
        n3.left = n9;
        n3.right = n20;
        n20.left = n15;
        n20.right = n7;
        int[][] expected = { { 3 }, { 9, 20 }, { 15, 7 } };
        List<List<Integer>> result = new LC102BinaryTreeLevelOrderTraversal().levelOrder(n3);
        assertEquals(expected.length, result.size());
        for (int i = 0; i < expected.length; i++) {
            assertEquals(expected[i].length, result.get(i).size());
            for (int j = 0; j < expected[i].length; j++) {
                assertEquals(expected[i][j], result.get(i).get(j).intValue());
            }
        }
    }

    @Test
    public void testNothing() throws Exception {
        List<List<Integer>> result = new LC102BinaryTreeLevelOrderTraversal().levelOrder(null);
        assertEquals(0, result.size());
    }
}
