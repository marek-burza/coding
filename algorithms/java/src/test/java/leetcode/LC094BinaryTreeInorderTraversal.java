package leetcode;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * https://leetcode.com/problems/binary-tree-inorder-traversal/
 */
public class LC094BinaryTreeInorderTraversal {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public class Solution {
        private void preorderTraversal(TreeNode root, List<Integer> result) {
            if (root == null) return;
            preorderTraversal(root.left, result);
            result.add(root.val);
            preorderTraversal(root.right, result);
        }

        public List<Integer> inorderTraversal(TreeNode root) {
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
