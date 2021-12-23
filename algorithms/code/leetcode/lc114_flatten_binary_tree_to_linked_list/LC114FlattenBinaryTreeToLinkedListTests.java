package leetcode.lc114_flatten_binary_tree_to_linked_list;

import org.junit.jupiter.api.Test;

import leetcode.lc114_flatten_binary_tree_to_linked_list.LC114FlattenBinaryTreeToLinkedList.TreeNode;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public final class LC114FlattenBinaryTreeToLinkedListTests {
    private void test(final TreeNode expected, final TreeNode root) {
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
    public void testExample() throws Exception {
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
        new LC114FlattenBinaryTreeToLinkedList().flatten(root);
        test(expected, root);
    }

    @Test
    public void testOther1() throws Exception {
        TreeNode root = new TreeNode(1);
        root.right = new TreeNode(2);
        root.right.left = new TreeNode(3);
        TreeNode expected = new TreeNode(1);
        expected.right = new TreeNode(2);
        expected.right.right = new TreeNode(3);
        new LC114FlattenBinaryTreeToLinkedList().flatten(root);
        test(expected, root);
    }

    @Test
    public void testOther2() throws Exception {
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(1);
        root.left.left = new TreeNode(4);
        root.left.left.right = new TreeNode(2);
        TreeNode expected = new TreeNode(3);
        expected.right = new TreeNode(1);
        expected.right.right = new TreeNode(4);
        expected.right.right.right = new TreeNode(2);
        new LC114FlattenBinaryTreeToLinkedList().flatten(root);
        test(expected, root);
    }
}
