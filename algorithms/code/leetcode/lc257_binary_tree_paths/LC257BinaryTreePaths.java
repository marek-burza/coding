package leetcode.lc257_binary_tree_paths;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/binary-tree-paths/ #easy
 */
public final class LC257BinaryTreePaths {
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(final int x) {
            val = x;
        }
    }

    public void binaryTreePaths(final TreeNode root, final String prefixValue, final List<String> result) {
        String prefix = prefixValue;
        prefix += ((prefix.length() == 0) ? "" : "->") + root.val;
        if (root.left == null && root.right == null) {
            result.add(prefix);
        } else {
            if (root.left != null) {
                binaryTreePaths(root.left, prefix, result);
            }
            if (root.right != null) {
                binaryTreePaths(root.right, prefix, result);
            }
        }
    }

    public List<String> binaryTreePaths(final TreeNode root) {
        List<String> result = new ArrayList<>();
        if (root != null) {
            binaryTreePaths(root, "", result);
        }
        return result;
    }
}
