package leetcode.lc104_maximum_depth_of_binary_tree;

import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * https://leetcode.com/problems/maximum-depth-of-binary-tree/
 * #easy
 */
public final class LC104MaximumDepthOfBinaryTree {
    public final class TreeNode {
        public int val;
        public TreeNode left;
        public TreeNode right;

        public TreeNode(final int x) {
            val = x;
        }
    }

    public final class Solution {
        public int maxDepth(final TreeNode root) {
            if (root == null) {
                return 0;
            } else {
                int left = maxDepth(root.left);
                int right = maxDepth(root.right);
                return 1 + Math.max(left, right);
            }
        }
    }

    @Test
    public void test_example() throws Exception {
        TreeNode n0 = new TreeNode(0);
        TreeNode n1 = new TreeNode(1);
        TreeNode n2 = new TreeNode(2);
        TreeNode n3 = new TreeNode(3);
        n0.left = n1;
        n0.right = n2;
        n1.left = null;
        n1.right = n3;
        n2.left = null;
        n2.right = null;
        n3.left = null;
        n3.right = null;
        assertEquals(3, new Solution().maxDepth(n0));
    }
}
