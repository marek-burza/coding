package leetcode.lc107_binary_tree_level_order_traversal_i_i;

import org.junit.jupiter.api.Test;


import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * https://leetcode.com/problems/binary-tree-level-order-traversal-ii/
 * #medium
 */
public final class LC107BinaryTreeLevelOrderTraversalII {
    public final class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(final int x) {
            val = x;
        }
    }

    public final class Solution {
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

    @Test
    public void test_empty() {
        assertEquals(0, new Solution().levelOrderBottom(null).size());
    }

    @Test
    public void test_example() {
        TreeNode n3 = new TreeNode(3);
        TreeNode n9 = new TreeNode(9);
        TreeNode n20 = new TreeNode(20);
        TreeNode n15 = new TreeNode(15);
        TreeNode n7 = new TreeNode(7);
        n3.left = n9;
        n3.right = n20;
        n20.left = n15;
        n20.right = n7;
        int[][] expected = {{15, 7}, {9, 20}, {3}};
        List<List<Integer>> result = new Solution().levelOrderBottom(n3);
        assertEquals(expected.length, result.size());
        for (int i = 0; i < expected.length; i++) {
            List<Integer> level = result.get(i);
            assertEquals(expected[i].length, level.size());
            for (int j = 0; j < expected[i].length; j++) {
                assertEquals(expected[i][j], level.get(j).intValue());
            }
        }
    }
}
