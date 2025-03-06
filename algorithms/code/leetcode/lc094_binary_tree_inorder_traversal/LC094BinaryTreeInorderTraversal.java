package leetcode.lc094_binary_tree_inorder_traversal;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/binary-tree-inorder-traversal/ #easy
 */
public final class LC094BinaryTreeInorderTraversal {
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(final int x) {
            val = x;
        }
    }

    private void preorderTraversal(final TreeNode root, final List<Integer> result) {
        if (root == null) {
            return;
        }
        preorderTraversal(root.left, result);
        result.add(root.val);
        preorderTraversal(root.right, result);
    }

    public List<Integer> inorderTraversal(final TreeNode root) {
        List<Integer> result = new ArrayList<>();
        preorderTraversal(root, result);
        return result;
    }
}
package leetcode.lc094_binary_tree_inorder_traversal;

import org.junit.jupiter.api.Test;

import leetcode.lc094_binary_tree_inorder_traversal.LC094BinaryTreeInorderTraversal.TreeNode;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public final class LC094BinaryTreeInorderTraversalTests {
    @Test
    public void testExample() throws Exception {
        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        node1.right = node2;
        node2.left = node3;
        List<Integer> result = new LC094BinaryTreeInorderTraversal().inorderTraversal(node1);
        int[] expected = { 1, 3, 2 };
        assertEquals(expected.length, result.size());
        for (int i = 0; i < expected.length; i++) {
            assertEquals(expected[i], result.get(i).intValue());
        }
    }
}
