package leetcode.lc226_invert_binary_tree;

/**
 * https://leetcode.com/problems/invert-binary-tree/ #easy
 */
public final class LC226InvertBinaryTree {
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(final int x) {
            val = x;
        }
    }

    public TreeNode invertTree(final TreeNode root) {
        if (root != null) {
            TreeNode node = root.left;
            root.left = root.right;
            root.right = node;
            invertTree(root.left);
            invertTree(root.right);
        }
        return root;
    }
}
