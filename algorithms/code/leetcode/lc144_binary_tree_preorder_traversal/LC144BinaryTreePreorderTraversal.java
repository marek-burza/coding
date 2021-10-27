package leetcode.lc144_binary_tree_preorder_traversal;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/binary-tree-preorder-traversal/ #easy
 */
public final class LC144BinaryTreePreorderTraversal {
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
        result.add(root.val);
        preorderTraversal(root.left, result);
        preorderTraversal(root.right, result);
    }

    public List<Integer> preorderTraversal(final TreeNode root) {
        List<Integer> result = new ArrayList<>();
        preorderTraversal(root, result);
        return result;
    }
}
