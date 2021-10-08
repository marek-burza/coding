package leetcode;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 * https://leetcode.com/problems/flatten-binary-tree-to-linked-list/
 * #medium
 */
public class LC114FlattenBinaryTreeToLinkedList {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }

        public String toString() {
            StringBuilder builder = new StringBuilder();
            builder.append("(");
            builder.append(left);
            builder.append(" ");
            builder.append(val);
            builder.append(" ");
            builder.append(right);
            builder.append(")");
            return builder.toString();
        }
    }

    public final class Solution {
        public void flatten(TreeNode root) {
            while (root != null) {
                if (root.left != null) {
                    TreeNode node = root.left;
                    while (node.right != null) {
                        node = node.right;
                    }
                    node.right = root.right;
                    root.right = root.left;
                    root.left = null;
                }
                root = root.right;
            }
        }
    }

    private void test(TreeNode expected, TreeNode root) {
        if (null == expected) {
            assertEquals(null, root);
        } else {
            assertNotEquals(null, root);
            assertEquals(expected.val, root.val);
            test(expected.left, root.left);
            test(expected.right, root.right);
        }
    }

    @Test
    public void test_example() throws Exception {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.left.left = new TreeNode(3);
        root.left.right = new TreeNode(4);
        root.right = new TreeNode(5);
        root.right.right = new TreeNode(6);
        TreeNode expected = new TreeNode(1);
        expected.right = new TreeNode(2);
        expected.right.right = new TreeNode(3);
        expected.right.right.right = new TreeNode(4);
        expected.right.right.right.right = new TreeNode(5);
        expected.right.right.right.right.right = new TreeNode(6);
        new Solution().flatten(root);
        test(expected, root);
    }

    @Test
    public void test_other_1() throws Exception {
        TreeNode root = new TreeNode(1);
        root.right = new TreeNode(2);
        root.right.left = new TreeNode(3);
        TreeNode expected = new TreeNode(1);
        expected.right = new TreeNode(2);
        expected.right.right = new TreeNode(3);
        new Solution().flatten(root);
        test(expected, root);
    }

    @Test
    public void test_other_2() throws Exception {
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(1);
        root.left.left = new TreeNode(4);
        root.left.left.right = new TreeNode(2);
        TreeNode expected = new TreeNode(3);
        expected.right = new TreeNode(1);
        expected.right.right = new TreeNode(4);
        expected.right.right.right = new TreeNode(2);
        new Solution().flatten(root);
        test(expected, root);
    }
}
