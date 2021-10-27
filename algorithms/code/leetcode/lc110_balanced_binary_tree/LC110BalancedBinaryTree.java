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
