package leetcode.lc094_binary_tree_inorder_traversal;

import org.junit.jupiter.api.Test;


import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * https://leetcode.com/problems/binary-tree-inorder-traversal/
 * #easy
 */
public final class LC094BinaryTreeInorderTraversal {
    public final class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(final int x) {
            val = x;
        }
    }

    public final class Solution {
        private void preorderTraversal(final TreeNode root, final List<Integer> result) {
            if (root == null) return;
            preorderTraversal(root.left, result);
            result.add(root.val);
            preorderTraversal(root.right, result);
        }

        public List<Integer> inorderTraversal(final TreeNode root) {
            List<Integer> result = new ArrayList<>();
            preorderTraversal(root, result);
            return result;
        }
    }

    @Test
    public void test_example() throws Exception {
        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        node1.right = node2;
        node2.left = node3;
        List<Integer> result = new Solution().inorderTraversal(node1);
        int[] expected = {1, 3, 2};
        for (int i = 0; i < expected.length; i++) {
            assertEquals(expected[i], result.get(i).intValue());
        }
    }
}
