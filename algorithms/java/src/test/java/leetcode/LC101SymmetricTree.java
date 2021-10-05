package leetcode;

import java.util.ArrayList;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * https://leetcode.com/problems/symmetric-tree/
 * #easy
 */
public class LC101SymmetricTree {
    public static class TreeNode {
        public int val;
        public TreeNode left;
        public TreeNode right;

        public TreeNode(int x) {
            val = x;
        }
    }

    public class Solution {
        public boolean isSymmetric(TreeNode root) {
            if (root == null) {
                return true;
            }
            ArrayList<TreeNode> queue1 = new ArrayList<>();
            ArrayList<TreeNode> queue2 = new ArrayList<>();
            queue1.add(root);
            queue2.add(root);
            while (queue1.size() > 0 && queue2.size() > 0) {
                TreeNode node1 = queue1.remove(0);
                TreeNode node2 = queue2.remove(0);
                if (node1.val != node2.val) {
                    return false;
                }
                if (node1.left != null && node2.right != null) {
                    queue1.add(node1.left);
                    queue2.add(node2.right);
                } else {
                    if (node1.left != null || node2.right != null) {
                        return false;
                    }
                }
                if (node1.right != null && node2.left != null) {
                    queue1.add(node1.right);
                    queue2.add(node2.left);
                } else {
                    if (node1.right != null || node2.left != null) {
                        return false;
                    }
                }
            }
            return queue1.size() == queue2.size();
        }
    }

    @Test
    public void test_symmetric() throws Exception {
        TreeNode n0 = new TreeNode(0);
        TreeNode n1a = new TreeNode(1);
        TreeNode n1b = new TreeNode(1);
        n0.left = n1a;
        n0.right = n1b;
        n1a.left = null;
        n1a.right = null;
        n1b.left = null;
        n1b.right = null;
        assertEquals(true, new Solution().isSymmetric(n0));
    }

    @Test
    public void test_asymmetric() throws Exception {
        TreeNode n0 = new TreeNode(0);
        TreeNode n1 = new TreeNode(1);
        TreeNode n2 = new TreeNode(2);
        n0.left = n1;
        n0.right = n2;
        n1.left = null;
        n1.right = null;
        n2.left = null;
        n2.right = null;
        assertEquals(false, new Solution().isSymmetric(n0));
    }
}
