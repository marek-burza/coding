package leetcode;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-search-tree/
 * #easy
 */
public class LC235LowestCommonAncestorOfABinarySearchTree {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;


        TreeNode(int x) {
            val = x;
        }
    }

    public class Solution {
        public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
            TreeNode left = null, right = null;
            if (root.left != null) {
                left = lowestCommonAncestor(root.left, p, q);
            }
            if (root.right != null) {
                right = lowestCommonAncestor(root.right, p, q);
            }
            if (left != null && left != p && left != q) {
                return left;
            }
            if (right != null && right != p && right != q) {
                return right;
            }
            boolean gotP = root == p || left == p || right == p;
            boolean gotQ = root == q || left == q || right == q;
            if (gotP && gotQ) {
                return root;
            } else {
                if (gotP) {
                    return p;
                }
                if (gotQ) {
                    return q;
                }
            }
            return null;
        }
    }

    @Test
    public void test_example() throws Exception {
        TreeNode tree = new TreeNode(6);
        tree.left = new TreeNode(2);
        tree.right = new TreeNode(8);
        tree.left.left = new TreeNode(0);
        tree.left.right = new TreeNode(4);
        tree.left.right.left = new TreeNode(3);
        tree.left.right.right = new TreeNode(5);
        tree.right.left = new TreeNode(7);
        tree.right.right = new TreeNode(9);
        assertEquals(tree, new Solution().lowestCommonAncestor(tree, tree.left, tree.right));
        assertEquals(tree.left, new Solution().lowestCommonAncestor(tree, tree.left, tree.left.right));
    }
}
