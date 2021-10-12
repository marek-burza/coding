package leetcode;

import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * https://leetcode.com/problems/balanced-binary-tree/
 * #easy
 */
public final class LC110BalancedBinaryTree {
    public final class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        public TreeNode(final int x) {
            val = x;
        }

        public TreeNode(final TreeNode left, final int x, final TreeNode right) {
            this(x);
            this.left = left;
            this.right = right;
        }
    }

    public final class Solution {
        private int balancedHeight(final TreeNode root) {
            if (root == null) {
                return 0;
            }
            int left = balancedHeight(root.left);
            if (left == -1) {
                return -1;
            }
            int right = balancedHeight(root.right);
            if (right == -1) {
                return -1;
            }
            if (Math.abs(left - right) > 1) {
                return -1;
            } else {
                return 1 + Math.max(left, right);
            }
        }

        public boolean isBalanced(TreeNode root) {
            return balancedHeight(root) != -1;
        }
    }

    @Test
    public void test_balanced() throws Exception {
        assertEquals(true, new Solution().isBalanced(new TreeNode(new TreeNode(new TreeNode(null, 1, null), 2, new TreeNode(null, 3, null)), 4, new TreeNode(new TreeNode(null, 5, null), 6, new TreeNode(null, 7, null)))));
    }

    @Test
    public void test_imbalanced() throws Exception {
        assertEquals(false, new Solution().isBalanced(new TreeNode(null, 4, new TreeNode(new TreeNode(null, 5, null), 6, new TreeNode(null, 7, null)))));
    }
}
