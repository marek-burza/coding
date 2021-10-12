package leetcode;

import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * https://leetcode.com/problems/path-sum/
 * #easy
 */
public class LC112PathSum {
    private class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        public TreeNode(int x) {
            val = x;
        }

        public TreeNode(TreeNode left, int x, TreeNode right) {
            this(x);
            this.left = left;
            this.right = right;
        }
    }

    public final class Solution {
        public boolean hasPathSum(TreeNode root, int sum) {
            if (root == null) {
                return false;
            }
            int reduced = sum - root.val;
            if (root.left == null && root.right == null) {
                return reduced == 0;
            }
            return hasPathSum(root.left, reduced) || hasPathSum(root.right, reduced);
        }
    }

    @Test
    public void test_example() throws Exception {
        TreeNode n1 = new TreeNode(null, 1, null);
        TreeNode n2 = new TreeNode(null, 2, null);
        TreeNode n7 = new TreeNode(null, 7, null);
        TreeNode n13 = new TreeNode(null, 13, null);
        TreeNode n4a = new TreeNode(null, 4, n1);
        TreeNode n8 = new TreeNode(n13, 8, n4a);
        TreeNode n11 = new TreeNode(n7, 11, n2);
        TreeNode n4b = new TreeNode(n11, 4, null);
        TreeNode n5 = new TreeNode(n4b, 5, n8);
        assertEquals(true, new Solution().hasPathSum(n5, 22));
    }
}
