package leetcode.lc114_flatten_binary_tree_to_linked_list;

/**
 * https://leetcode.com/problems/flatten-binary-tree-to-linked-list/
 * #medium
 */
public final class LC114FlattenBinaryTreeToLinkedList {
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(final int x) {
            val = x;
        }
    }

    public void flatten(final TreeNode rootValue) {
        TreeNode root = rootValue;
        while (root != null) {
            if (root.left != null) {
                TreeNode node = root.left;
                while (node.right != null) {
                    node = node.right;
                }
                node.right = root.right;
                root.right = root.left;
                root.left = null;
            }
            root = root.right;
        }
    }
}
