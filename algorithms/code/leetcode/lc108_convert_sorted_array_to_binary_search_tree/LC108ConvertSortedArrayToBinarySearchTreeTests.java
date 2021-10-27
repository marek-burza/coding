package leetcode.lc108_convert_sorted_array_to_binary_search_tree;

import org.junit.jupiter.api.Test;

import leetcode.lc108_convert_sorted_array_to_binary_search_tree.LC108ConvertSortedArrayToBinarySearchTree.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public final class LC108ConvertSortedArrayToBinarySearchTreeTests {
    private int minHeight(final TreeNode root) {
        if (root == null) {
            return 0;
        }
        Queue<TreeNode> nodes = new LinkedList<>();
        nodes.add(root);
        Queue<Integer> levels = new LinkedList<>();
        levels.add(1);
        int minimum = Integer.MAX_VALUE;
        while (nodes.size() > 0) {
            TreeNode node = nodes.poll();
            int level = levels.poll();
            if (node.left == null && node.right == null) {
                if (level < minimum) {
                    minimum = level;
                }
            } else {
                if (node.left != null) {
                    nodes.add(node.left);
                    levels.add(level + 1);
                }
                if (node.right != null) {
                    nodes.add(node.right);
                    levels.add(level + 1);
                }
            }
        }
        return minimum;
    }

    private int maxHeight(final TreeNode root) {
        if (root == null) {
            return 0;
        }
        Queue<TreeNode> nodes = new LinkedList<>();
        nodes.add(root);
        Queue<Integer> levels = new LinkedList<>();
        levels.add(1);
        int maximum = Integer.MIN_VALUE;
        while (nodes.size() > 0) {
            TreeNode node = nodes.poll();
            int level = levels.poll();
            if (node.left == null && node.right == null) {
                if (level > maximum) {
                    maximum = level;
                }
            } else {
                if (node.left != null) {
                    nodes.add(node.left);
                    levels.add(level + 1);
                }
                if (node.right != null) {
                    nodes.add(node.right);
                    levels.add(level + 1);
                }
            }
        }
        return maximum;
    }

    private void reconstruct(final TreeNode root, final List<Integer> list) {
        if (root == null) {
            return;
        }
        if (root.left != null) {
            reconstruct(root.left, list);
        }
        list.add(root.val);
        if (root.right != null) {
            reconstruct(root.right, list);
        }
    }

    private boolean isBST(final TreeNode root) {
        List<Integer> list = new ArrayList<>();
        reconstruct(root, list);
        int previous = Integer.MIN_VALUE;
        for (int value : list) {
            if (previous > value) {
                return false;
            }
            previous = value;
        }
        return true;
    }

    @Test
    public void testEmpty() throws Exception {
        assertEquals(null, new LC108ConvertSortedArrayToBinarySearchTree().sortedArrayToBST(new int[0]));
    }

    @Test
    public void testDepthAndOrdering() throws Exception {
        int[] nums = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
        TreeNode root = new LC108ConvertSortedArrayToBinarySearchTree().sortedArrayToBST(nums);
        int difference = maxHeight(root) - minHeight(root);
        assertTrue(0 <= difference && difference <= 1);
        assertTrue(isBST(root));
    }
}
