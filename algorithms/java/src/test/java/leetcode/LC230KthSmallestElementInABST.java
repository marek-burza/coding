package leetcode;

import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * https://leetcode.com/problems/kth-smallest-element-in-a-bst/
 * #medium
 */
public final class LC230KthSmallestElementInABST {
    public final class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(final int x) {
            val = x;
        }
    }

    public final class Solution {
        public int kthSmallest(final TreeNode root, final int k, final TreeNode counter) {
            if (root.left != null) {
                int result = kthSmallest(root.left, k, counter);
                if (counter.val == k) {
                    return result;
                }
            }
            counter.val++;
            if (counter.val == k) {
                return root.val;
            }
            if (root.right != null) {
                int result = kthSmallest(root.right, k, counter);
                if (counter.val == k) {
                    return result;
                }
            }
            return 0;
        }

        public int kthSmallest(TreeNode root, int k) {
            TreeNode counter = new TreeNode(0);
            return kthSmallest(root, k, counter);
        }
    }

    @Test
    public void test_left() throws Exception {
        TreeNode n1 = new TreeNode(1);
        TreeNode n2 = new TreeNode(2);
        TreeNode n3 = new TreeNode(3);
        TreeNode n4 = new TreeNode(4);
        n4.left = n3;
        n3.left = n2;
        n2.left = n1;
        assertEquals(2, new Solution().kthSmallest(n4, 2));
    }

    @Test
    public void test_right() throws Exception {
        TreeNode n1 = new TreeNode(1);
        TreeNode n2 = new TreeNode(2);
        TreeNode n3 = new TreeNode(3);
        TreeNode n4 = new TreeNode(4);
        n1.right = n2;
        n2.right = n3;
        n3.right = n4;
        assertEquals(2, new Solution().kthSmallest(n1, 2));
    }

    @Test
    public void test_coverage() throws Exception {
        TreeNode n1 = new TreeNode(1);
        TreeNode n2 = new TreeNode(2);
        TreeNode n3 = new TreeNode(3);
        TreeNode n4 = new TreeNode(4);
        TreeNode n5 = new TreeNode(5);
        TreeNode n6 = new TreeNode(6);
        n2.left = n1;
        n2.right = n3;
        n4.left = n2;
        n4.right = n5;
        n5.right = n6;
        assertEquals(5, new Solution().kthSmallest(n4, 5));
    }
}
