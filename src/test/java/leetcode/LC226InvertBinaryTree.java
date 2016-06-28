package leetcode;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * https://leetcode.com/problems/invert-binary-tree/
 */
public class LC226InvertBinaryTree {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public class Solution {
        public TreeNode invertTree(TreeNode root) {
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

    @Test
    public void test_example() throws Exception {
        TreeNode n1 = new TreeNode(1);
        TreeNode n2 = new TreeNode(2);
        TreeNode n3 = new TreeNode(3);
        TreeNode n4 = new TreeNode(4);
        TreeNode n6 = new TreeNode(6);
        TreeNode n7 = new TreeNode(7);
        TreeNode n9 = new TreeNode(9);
        n4.left = n2;
        n4.right = n7;
        n2.left = n1;
        n2.right = n3;
        n7.left = n6;
        n7.right = n9;
        n1.left = n1.right = n3.left = n3.right = n6.left = n6.right = n9.left = n9.right = null;
        TreeNode inverted = new Solution().invertTree(n4);
        assertEquals(4, inverted.val);
        assertEquals(7, inverted.left.val);
        assertEquals(9, inverted.left.left.val);
        assertEquals(6, inverted.left.right.val);
        assertEquals(2, inverted.right.val);
        assertEquals(3, inverted.right.left.val);
        assertEquals(1, inverted.right.right.val);
    }
}
