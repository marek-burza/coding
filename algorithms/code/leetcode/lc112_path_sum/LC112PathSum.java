package leetcode.lc112_path_sum;

/**
 * https://leetcode.com/problems/path-sum/ #easy
 */
public final class LC112PathSum {
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(final int x) {
            val = x;
        }

        TreeNode(final int x, final TreeNode leftNode, final TreeNode rightNode) {
            this(x);
            left = leftNode;
            right = rightNode;
        }
    }

    public boolean hasPathSum(final TreeNode root, final int sum) {
        if (root == null) {
            return false;
        }
        int reduced = sum - root.val;
        if (root.left == null && root.right == null) {
            return reduced == 0;
        }
        return hasPathSum(root.left, reduced) || hasPathSum(root.right, reduced);
    }
}
package leetcode.lc112_path_sum;

import org.junit.jupiter.api.Test;

import leetcode.lc112_path_sum.LC112PathSum.TreeNode;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public final class LC112PathSumTests {
    @Test
    public void testExample() throws Exception {
        TreeNode n1 = new TreeNode(1, null, null);
        TreeNode n2 = new TreeNode(2, null, null);
        TreeNode n7 = new TreeNode(7, null, null);
        TreeNode n13 = new TreeNode(13, null, null);
        TreeNode n4a = new TreeNode(4, null, n1);
        TreeNode n8 = new TreeNode(8, n13, n4a);
        TreeNode n11 = new TreeNode(11, n7, n2);
        TreeNode n4b = new TreeNode(4, n11, null);
        TreeNode n5 = new TreeNode(5, n4b, n8);
        assertTrue(new LC112PathSum().hasPathSum(n5, 22));
    }

    @Test
    public void testLeftBend() throws Exception {
        TreeNode right = new TreeNode(1, null, null);
        TreeNode left = new TreeNode(2, null, right);
        TreeNode root = new TreeNode(3, left, null);
        assertTrue(new LC112PathSum().hasPathSum(root, 6));
    }

    @Test
    public void testRightBend() throws Exception {
        TreeNode left = new TreeNode(1, null, null);
        TreeNode right = new TreeNode(2, left, null);
        TreeNode root = new TreeNode(3, null, right);
        assertTrue(new LC112PathSum().hasPathSum(root, 6));
    }

    @Test
    public void testNoPath() throws Exception {
        TreeNode left = new TreeNode(0, null, null);
        TreeNode right = new TreeNode(0, null, null);
        TreeNode root = new TreeNode(0, left, right);
        assertFalse(new LC112PathSum().hasPathSum(root, 6));
    }

    @Test
    public void testNothing() throws Exception {
        assertEquals(0, new TreeNode(0).val);
        assertFalse(new LC112PathSum().hasPathSum(null, 0));
    }
}
