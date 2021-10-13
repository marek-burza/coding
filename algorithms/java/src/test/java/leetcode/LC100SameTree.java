package leetcode.lc100_same_tree;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * https://leetcode.com/problems/same-tree/
 * #easy
 */
public final class LC100SameTree {
    public static class TreeNode {
        public int val;
        public TreeNode left;
        public TreeNode right;

        public TreeNode(final int x) {
            val = x;
        }
    }

    public final class Solution {
        public boolean isSameTree(final TreeNode tree1, final TreeNode tree2) {
            if (tree1 == null && tree2 == null) return true;
            if (tree1 == null || tree2 == null) return false;
            ArrayList<TreeNode> queue1 = new ArrayList<>();
            ArrayList<TreeNode> queue2 = new ArrayList<>();
            queue1.add(tree1);
            queue2.add(tree2);
            while (queue1.size() > 0 && queue2.size() > 0) {
                TreeNode node1 = queue1.remove(0);
                TreeNode node2 = queue2.remove(0);
                if (node1.val != node2.val) return false;
                if (node1.left != null && node2.left != null) {
                    queue1.add(node1.left);
                    queue2.add(node2.left);
                } else if (node1.left != null || node2.left != null) return false;
                if (node1.right != null && node2.right != null) {
                    queue1.add(node1.right);
                    queue2.add(node2.right);
                } else if (node1.right != null || node2.right != null) return false;
            }
            return queue1.size() == queue2.size();
        }
    }

    @Test
    public void test_different() throws Exception {
        TreeNode an0 = new TreeNode(0);
        TreeNode bn0 = new TreeNode(0);
        TreeNode an1 = new TreeNode(1);
        TreeNode bn1 = new TreeNode(1);
        TreeNode an2 = new TreeNode(2);
        TreeNode bn2 = new TreeNode(3);
        an0.left = an1;
        an0.right = an2;
        bn0.left = bn1;
        bn0.right = bn2;
        an1.left = null;
        an2.right = null;
        bn1.left = null;
        bn2.right = null;
        assertEquals(false, new Solution().isSameTree(an0, bn0));
    }

    @Test
    public void test_same() throws Exception {
        TreeNode an0 = new TreeNode(0);
        TreeNode bn0 = new TreeNode(0);
        TreeNode an1 = new TreeNode(1);
        TreeNode bn1 = new TreeNode(1);
        TreeNode an2 = new TreeNode(2);
        TreeNode bn2 = new TreeNode(2);
        an0.left = an1;
        an0.right = an2;
        bn0.left = bn1;
        bn0.right = bn2;
        an1.left = null;
        an2.right = null;
        bn1.left = null;
        bn2.right = null;
        assertEquals(true, new Solution().isSameTree(an0, bn0));
    }
}
