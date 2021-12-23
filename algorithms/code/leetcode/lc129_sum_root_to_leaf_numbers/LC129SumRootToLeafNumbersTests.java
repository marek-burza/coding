package leetcode.lc129_sum_root_to_leaf_numbers;

import org.junit.jupiter.api.Test;

import leetcode.lc129_sum_root_to_leaf_numbers.LC129SumRootToLeafNumbers.TreeNode;

import static org.junit.jupiter.api.Assertions.assertEquals;

public final class LC129SumRootToLeafNumbersTests {
    @Test
    public void testExample() throws Exception {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        assertEquals(25, new LC129SumRootToLeafNumbers().sumNumbers(root));
    }

    @Test
    public void testNothing() throws Exception {
        assertEquals(0, new LC129SumRootToLeafNumbers().sumNumbers(null));
    }

    @Test
    public void testLeft() throws Exception {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        assertEquals(12, new LC129SumRootToLeafNumbers().sumNumbers(root));
    }

    @Test
    public void testRight() throws Exception {
        TreeNode root = new TreeNode(1);
        root.right = new TreeNode(3);
        assertEquals(13, new LC129SumRootToLeafNumbers().sumNumbers(root));
    }
}
