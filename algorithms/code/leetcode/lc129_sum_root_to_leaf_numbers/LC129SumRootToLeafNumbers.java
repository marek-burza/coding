package leetcode.lc129_sum_root_to_leaf_numbers;

/**
 * https://leetcode.com/problems/sum-root-to-leaf-numbers/
 * #medium
 */
public final class LC129SumRootToLeafNumbers {
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(final int x) {
            val = x;
        }
    }

    private int sumNumbers(final TreeNode root, final int prefixValue) {
        int prefix = prefixValue;
        if (root == null) {
            return 0;
        }
        prefix = prefix * 10 + root.val;
        if (root.left == null && root.right == null) {
            return prefix;
        }
        return sumNumbers(root.left, prefix) + sumNumbers(root.right, prefix);
    }

    public int sumNumbers(final TreeNode root) {
        return sumNumbers(root, 0);
    }
}
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
