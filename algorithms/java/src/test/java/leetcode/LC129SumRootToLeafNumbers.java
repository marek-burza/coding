package leetcode;

import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * https://leetcode.com/problems/sum-root-to-leaf-numbers/
 * #medium
 */
public class LC129SumRootToLeafNumbers {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public class Solution {
        private int sumNumbers(TreeNode root, int prefix) {
            if (root == null) return 0;
            prefix = prefix * 10 + root.val;
            if (root.left == null && root.right == null) return prefix;
            return sumNumbers(root.left, prefix) + sumNumbers(root.right, prefix);
        }

        public int sumNumbers(TreeNode root) {
            return sumNumbers(root, 0);
        }
    }

    @Test
    public void test_example() throws Exception {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        assertEquals(25, new Solution().sumNumbers(root));
    }
}
