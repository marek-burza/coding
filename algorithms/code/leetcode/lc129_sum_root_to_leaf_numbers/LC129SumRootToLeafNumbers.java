package leetcode.lc129_sum_root_to_leaf_numbers;

/**
 * https://leetcode.com/problems/sum-root-to-leaf-numbers/
 * #medium
 */
public final class LC129SumRootToLeafNumbers {
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(final int x) {
            val = x;
        }
    }

    private int sumNumbers(final TreeNode root, final int prefixValue) {
        int prefix = prefixValue;
        if (root == null) {
            return 0;
        }
        prefix = prefix * 10 + root.val;
        if (root.left == null && root.right == null) {
            return prefix;
        }
        return sumNumbers(root.left, prefix) + sumNumbers(root.right, prefix);
    }

    public int sumNumbers(final TreeNode root) {
        return sumNumbers(root, 0);
    }
}
