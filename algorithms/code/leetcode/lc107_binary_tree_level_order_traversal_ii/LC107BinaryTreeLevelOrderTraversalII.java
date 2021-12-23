package leetcode.lc107_binary_tree_level_order_traversal_ii;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/binary-tree-level-order-traversal-ii/
 * #medium
 */
public final class LC107BinaryTreeLevelOrderTraversalII {
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(final int x) {
            val = x;
        }
    }

    public List<List<Integer>> levelOrderBottom(final TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        List<TreeNode> current = new ArrayList<>();
        if (root != null) {
            current.add(root);
        }
        while (current.size() > 0) {
            List<Integer> level = new ArrayList<>();
            result.add(level);
            List<TreeNode> future = new ArrayList<>();
            for (TreeNode node : current) {
                level.add(node.val);
                if (node.left != null) {
                    future.add(node.left);
                }
                if (node.right != null) {
                    future.add(node.right);
                }
            }
            current = future;
        }
        int length = result.size();
        for (int i = 0; i < length / 2; i++) {
            List<Integer> exchange = result.get(i);
            result.set(i, result.get(length - 1 - i));
            result.set(length - 1 - i, exchange);
        }
        return result;
    }
}
