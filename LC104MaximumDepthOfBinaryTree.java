package leetcode;

/**
 * https://leetcode.com/problems/maximum-depth-of-binary-tree/
 */
public class LC104MaximumDepthOfBinaryTree {
    public static class TreeNode {
        public int val;
        public TreeNode left;
        public TreeNode right;

        public TreeNode(int x) {
            val = x;
        }
    }

    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        } else {
            int left = maxDepth(root.left);
            int right = maxDepth(root.right);
            return 1 + Math.max(left, right);
        }
    }

    public static void main(String[] arguments) {
        LC104MaximumDepthOfBinaryTree solution = new LC104MaximumDepthOfBinaryTree();
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
        System.out.println(solution.maxDepth(n0));
    }
}
