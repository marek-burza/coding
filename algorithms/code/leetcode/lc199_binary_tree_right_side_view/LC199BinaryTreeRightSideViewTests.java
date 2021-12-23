package leetcode.lc199_binary_tree_right_side_view;

import java.util.List;

import org.junit.jupiter.api.Test;

import leetcode.lc199_binary_tree_right_side_view.LC199BinaryTreeRightSideView.TreeNode;

import static org.junit.jupiter.api.Assertions.assertEquals;

public final class LC199BinaryTreeRightSideViewTests {
    @Test
    public void testExample() throws Exception {
        TreeNode n1 = new TreeNode(1);
        TreeNode n2 = new TreeNode(2);
        TreeNode n3 = new TreeNode(3);
        TreeNode n4 = new TreeNode(4);
        TreeNode n5 = new TreeNode(5);
        TreeNode n6 = new TreeNode(6);
        n1.left = n2;
        n1.right = n3;
        n2.left = null;
        n2.right = n5;
        n3.left = null;
        n3.right = n4;
        n4.left = null;
        n4.right = null;
        n5.left = n6;
        n5.right = null;
        n6.left = null;
        n6.right = null;
        int[] expected = { 1, 3, 4, 6 };
        List<Integer> result = new LC199BinaryTreeRightSideView().rightSideView(n1);
        for (int i = 0; i < expected.length; i++) {
            assertEquals(expected[i], result.get(i).intValue());
        }
    }
}
