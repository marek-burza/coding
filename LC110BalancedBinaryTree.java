package leetcode;

/**
 * https://leetcode.com/problems/balanced-binary-tree/
 */
public class LC110BalancedBinaryTree {
    public static class TreeNode {
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

    private int balancedHeight(TreeNode root) {
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

    public static void main(String[] arguments) {
        LC110BalancedBinaryTree checker = new LC110BalancedBinaryTree();
        System.out.println(checker.isBalanced(new TreeNode(new TreeNode(new TreeNode(null, 1, null), 2, new TreeNode(null, 3, null)), 4, new TreeNode(new TreeNode(null, 5, null), 6, new TreeNode(null, 7, null)))));
        System.out.println(checker.isBalanced(new TreeNode(null, 4, new TreeNode(new TreeNode(null, 5, null), 6, new TreeNode(null, 7, null)))));
    }
}
