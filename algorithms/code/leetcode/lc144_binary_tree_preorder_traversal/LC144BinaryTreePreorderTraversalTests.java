package leetcode.lc144_binary_tree_preorder_traversal;

import org.junit.jupiter.api.Test;

import leetcode.lc144_binary_tree_preorder_traversal.LC144BinaryTreePreorderTraversal.TreeNode;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public final class LC144BinaryTreePreorderTraversalTests {
    @Test
    public void testExample() throws Exception {
        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        node1.right = node2;
        node2.left = node3;
        List<Integer> result = new LC144BinaryTreePreorderTraversal().preorderTraversal(node1);
        int[] expected = { 1, 2, 3 };
        for (int i = 0; i < expected.length; i++) {
            assertEquals(expected[i], result.get(i).intValue());
        }
    }
}
