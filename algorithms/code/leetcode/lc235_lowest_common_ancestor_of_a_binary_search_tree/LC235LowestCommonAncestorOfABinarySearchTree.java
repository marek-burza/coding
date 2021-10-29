package leetcode.lc235_lowest_common_ancestor_of_a_binary_search_tree;

/**
 * https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-search-tree/
 * #easy
 */
public final class LC235LowestCommonAncestorOfABinarySearchTree {
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(final int x) {
            val = x;
        }
    }

    public TreeNode lowestCommonAncestor(final TreeNode root, final TreeNode p, final TreeNode q) {
        TreeNode left = null;
        TreeNode right = null;
        if (root.left != null) {
            left = lowestCommonAncestor(root.left, p, q);
        }
        if (root.right != null) {
            right = lowestCommonAncestor(root.right, p, q);
        }
        if (left != null && left != p && left != q) {
            return left;
        }
        if (right != null && right != p && right != q) {
            return right;
        }
        boolean gotP = root == p || left == p || right == p;
        boolean gotQ = root == q || left == q || right == q;
        if (gotP && gotQ) {
            return root;
        }
        if (gotP) {
            return p;
        }
        if (gotQ) {
            return q;
        }
        return null;
    }
}
