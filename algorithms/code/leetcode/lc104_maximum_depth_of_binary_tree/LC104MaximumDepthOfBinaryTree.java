package leetcode.lc104_maximum_depth_of_binary_tree;

/**
 * https://leetcode.com/problems/maximum-depth-of-binary-tree/ #easy
 */
public final class LC104MaximumDepthOfBinaryTree {
    public static class TreeNode {
        public int val;
        public TreeNode left;
        public TreeNode right;

        TreeNode(final int x) {
            val = x;
        }
    }

    public int maxDepth(final TreeNode root) {
        if (root == null) {
            return 0;
        } else {
            int left = maxDepth(root.left);
            int right = maxDepth(root.right);
            return 1 + Math.max(left, right);
        }
    }
}
