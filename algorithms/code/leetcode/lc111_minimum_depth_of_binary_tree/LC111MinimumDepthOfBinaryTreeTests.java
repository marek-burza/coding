package leetcode.lc111_minimum_depth_of_binary_tree;

import org.junit.jupiter.api.Test;

import leetcode.lc111_minimum_depth_of_binary_tree.LC111MinimumDepthOfBinaryTree.TreeNode;

import static org.junit.jupiter.api.Assertions.assertEquals;

public final class LC111MinimumDepthOfBinaryTreeTests {
    @Test
    public void testExample() throws Exception {
        TreeNode n3 = new TreeNode(3);
        TreeNode n7 = new TreeNode(7);
        TreeNode n9 = new TreeNode(9);
        TreeNode n15 = new TreeNode(15);
        TreeNode n20 = new TreeNode(20);
        n3.left = n9;
        n3.right = n20;
        n20.left = n15;
        n20.right = n7;
        assertEquals(2, new LC111MinimumDepthOfBinaryTree().minDepth(n3));
    }

    @Test
    public void testLeftNothing() throws Exception {
        TreeNode root = new TreeNode(3);
        TreeNode right = new TreeNode(7);
        root.right = right;
        assertEquals(2, new LC111MinimumDepthOfBinaryTree().minDepth(root));
    }

    @Test
    public void testRightNothing() throws Exception {
        TreeNode root = new TreeNode(3);
        TreeNode left = new TreeNode(7);
        root.left = left;
        assertEquals(2, new LC111MinimumDepthOfBinaryTree().minDepth(root));
    }

    @Test
    public void testNothing() throws Exception {
        assertEquals(0, new TreeNode(0).val);
        assertEquals(0, new LC111MinimumDepthOfBinaryTree().minDepth(null));
    }
}
