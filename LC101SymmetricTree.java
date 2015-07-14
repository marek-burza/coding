package leetcode;

import java.util.ArrayList;

/**
 * https://leetcode.com/problems/symmetric-tree/
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

    public static void main(String[] arguments) {
        LC101SymmetricTree solution = new LC101SymmetricTree();
        TreeNode n0a = new TreeNode(0);
        TreeNode n0b = new TreeNode(0);
        TreeNode n1a = new TreeNode(1);
        TreeNode n1b = new TreeNode(1);
        TreeNode n2 = new TreeNode(2);
        n0a.left = n1a;
        n0a.right = n1b;
        n0b.left = n1a;
        n0b.right = n2;
        n1a.left = null;
        n1a.right = null;
        n1b.left = null;
        n1b.right = null;
        n2.left = null;
        n2.right = null;
        System.out.println(solution.isSymmetric(n0a));
        System.out.println(solution.isSymmetric(n0b));
    }
}
