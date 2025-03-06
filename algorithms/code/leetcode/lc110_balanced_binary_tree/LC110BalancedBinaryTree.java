package leetcode.lc110_balanced_binary_tree;

/**
 * https://leetcode.com/problems/balanced-binary-tree/ #easy
 */
public final class LC110BalancedBinaryTree {
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(final int x) {
            val = x;
        }

        TreeNode(final int x, final TreeNode leftNode, final TreeNode rightNode) {
            this(x);
            left = leftNode;
            right = rightNode;
        }
    }

    private int balancedHeight(final TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = balancedHeight(root.left);
        if (left == -1) {
            return -1;
        }
        int right = balancedHeight(root.right);
        if (right == -1) {
            return -1;
        }
        if (Math.abs(left - right) > 1) {
            return -1;
        } else {
            return 1 + Math.max(left, right);
        }
    }

    public boolean isBalanced(final TreeNode root) {
        return balancedHeight(root) != -1;
    }
}
package leetcode.lc110_balanced_binary_tree;

import org.junit.jupiter.api.Test;

import leetcode.lc110_balanced_binary_tree.LC110BalancedBinaryTree.TreeNode;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public final class LC110BalancedBinaryTreeTests {
    @Test
    public void testBalanced() throws Exception {
        TreeNode left = new TreeNode(2, new TreeNode(1, null, null), new TreeNode(3, null, null));
        TreeNode right = new TreeNode(6, new TreeNode(5, null, null), new TreeNode(7, null, null));
        TreeNode root = new TreeNode(4, left, right);
        assertTrue(new LC110BalancedBinaryTree().isBalanced(root));
    }

    @Test
    public void testImbalancedRight() throws Exception {
        TreeNode right = new TreeNode(6, new TreeNode(5, null, null), new TreeNode(7, null, null));
        TreeNode root = new TreeNode(4, null, right);
        assertFalse(new LC110BalancedBinaryTree().isBalanced(root));
    }

    @Test
    public void testImbalancedLeft() throws Exception {
        TreeNode left = new TreeNode(2, new TreeNode(1, null, null), new TreeNode(3, null, null));
        TreeNode root = new TreeNode(4, left, null);
        assertFalse(new LC110BalancedBinaryTree().isBalanced(root));
    }

    @Test
    public void testImbalancedDeepLeft() throws Exception {
        TreeNode left = new TreeNode(2, new TreeNode(1, new TreeNode(3, null, null), null), null);
        TreeNode right = new TreeNode(6, new TreeNode(5, null, null), new TreeNode(7, null, null));
        TreeNode root = new TreeNode(4, left, right);
        assertFalse(new LC110BalancedBinaryTree().isBalanced(root));
    }

    @Test
    public void testImbalancedDeepRight() throws Exception {
        TreeNode left = new TreeNode(2, new TreeNode(1, null, null), new TreeNode(3, null, null));
        TreeNode right = new TreeNode(6, new TreeNode(5, null, new TreeNode(7, null, null)), null);
        TreeNode root = new TreeNode(4, left, right);
        assertFalse(new LC110BalancedBinaryTree().isBalanced(root));
    }

    @Test
    public void testValue() throws Exception {
        assertEquals(0, new TreeNode(0, null, null).val);
    }
}
