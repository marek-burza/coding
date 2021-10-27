package leetcode.lc112_path_sum;

/**
 * https://leetcode.com/problems/path-sum/ #easy
 */
public final class LC112PathSum {
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

    public boolean hasPathSum(final TreeNode root, final int sum) {
        if (root == null) {
            return false;
        }
        int reduced = sum - root.val;
        if (root.left == null && root.right == null) {
            return reduced == 0;
        }
        return hasPathSum(root.left, reduced) || hasPathSum(root.right, reduced);
    }
}
