package leetcode.lc337_house_robber_iii;

import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.com/problems/house-robber-iii/ #medium
 */
public final class LC337HouseRobberIII {
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(final int x) {
            val = x;
        }
    }

    public int rob(final TreeNode root, final Map<TreeNode, Integer> cache) {
        Integer cached = cache.get(root);
        if (cached != null) {
            return cached;
        }
        if (root == null) {
            return 0;
        }
        int without = rob(root.left, cache) + rob(root.right, cache);
        int with = root.val;
        if (root.left != null) {
            with += rob(root.left.left, cache) + rob(root.left.right, cache);
        }
        if (root.right != null) {
            with += rob(root.right.left, cache) + rob(root.right.right, cache);
        }
        int maximum = Math.max(with, without);
        cache.put(root, maximum);
        return maximum;
    }

    public int rob(final TreeNode root) {
        return rob(root, new HashMap<>());
    }
}
