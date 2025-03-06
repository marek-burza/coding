package leetcode.lc226_invert_binary_tree;

/**
 * https://leetcode.com/problems/invert-binary-tree/ #easy
 */
public final class LC226InvertBinaryTree {
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(final int x) {
            val = x;
        }
    }

    public TreeNode invertTree(final TreeNode root) {
        if (root != null) {
            TreeNode node = root.left;
            root.left = root.right;
            root.right = node;
            invertTree(root.left);
            invertTree(root.right);
        }
        return root;
    }
}
package leetcode.lc226_invert_binary_tree;

import org.junit.jupiter.api.Test;

import leetcode.lc226_invert_binary_tree.LC226InvertBinaryTree.TreeNode;

import static org.junit.jupiter.api.Assertions.assertEquals;

public final class LC226InvertBinaryTreeTests {
    @Test
    public void testExample() throws Exception {
        TreeNode n1 = new TreeNode(1);
        TreeNode n2 = new TreeNode(2);
        TreeNode n3 = new TreeNode(3);
        TreeNode n4 = new TreeNode(4);
        TreeNode n6 = new TreeNode(6);
        TreeNode n7 = new TreeNode(7);
        TreeNode n9 = new TreeNode(9);
        n4.left = n2;
        n4.right = n7;
        n2.left = n1;
        n2.right = n3;
        n7.left = n6;
        n7.right = n9;
        TreeNode inverted = new LC226InvertBinaryTree().invertTree(n4);
        assertEquals(4, inverted.val);
        assertEquals(7, inverted.left.val);
        assertEquals(9, inverted.left.left.val);
        assertEquals(6, inverted.left.right.val);
        assertEquals(2, inverted.right.val);
        assertEquals(3, inverted.right.left.val);
        assertEquals(1, inverted.right.right.val);
    }
}
