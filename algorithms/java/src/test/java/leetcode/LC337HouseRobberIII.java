package leetcode;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

/**
 * https://leetcode.com/problems/house-robber-iii/
 * #medium
 */
public class LC337HouseRobberIII {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public class Solution {
        public int rob(TreeNode root, Map<TreeNode, Integer> cache) {
            Integer cached = cache.get(root);
            if (cached != null) {
                return cached;
            }
            if (root == null) return 0;
            int with = root.val;
            if (root.left != null) {
                with += rob(root.left.left) + rob(root.left.right);
            }
            if (root.right != null) {
                with += rob(root.right.left) + rob(root.right.right);
            }
            int without = rob(root.left) + rob(root.right);
            int maximum = Math.max(with, without);
            cache.put(root, cached);
            return maximum;
        }

        public int rob(TreeNode root) {
            return rob(root, new HashMap<>());
        }
    }

    @Test
    public void test_example_1() throws Exception {
        TreeNode t3 = new TreeNode(3);
        TreeNode l2 = new TreeNode(2);
        TreeNode lr3 = new TreeNode(3);
        TreeNode r3 = new TreeNode(3);
        TreeNode rr1 = new TreeNode(1);
        t3.left = l2;
        t3.right = r3;
        t3.left.right = lr3;
        t3.right.right = rr1;
        assertEquals(7, new Solution().rob(t3));
    }

    @Test
    public void test_example_2() throws Exception {
        TreeNode t3 = new TreeNode(3);
        TreeNode l4 = new TreeNode(4);
        TreeNode ll1 = new TreeNode(1);
        TreeNode lr3 = new TreeNode(3);
        TreeNode r5 = new TreeNode(5);
        TreeNode rr1 = new TreeNode(1);
        t3.left = l4;
        t3.right = r5;
        t3.left.left = ll1;
        t3.left.right = lr3;
        t3.right.right = rr1;
        assertEquals(9, new Solution().rob(t3));
    }
}
