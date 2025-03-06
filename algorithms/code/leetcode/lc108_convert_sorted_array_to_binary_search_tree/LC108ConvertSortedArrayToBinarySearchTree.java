package leetcode.lc108_convert_sorted_array_to_binary_search_tree;

/**
 * https://leetcode.com/problems/convert-sorted-array-to-binary-search-tree/
 * #easy
 */
public final class LC108ConvertSortedArrayToBinarySearchTree {
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(final int x) {
            val = x;
        }
    }

    private TreeNode sortedArrayToBST(final int[] nums, final int from, final int to) {
        if (from > to) {
            return null;
        }
        int at = (from + to) >>> 1;
        TreeNode node = new TreeNode(nums[at]);
        node.left = sortedArrayToBST(nums, from, at - 1);
        node.right = sortedArrayToBST(nums, at + 1, to);
        return node;
    }

    public TreeNode sortedArrayToBST(final int[] nums) {
        return sortedArrayToBST(nums, 0, nums.length - 1);
    }
}
package leetcode.lc108_convert_sorted_array_to_binary_search_tree;

import org.junit.jupiter.api.Test;

import leetcode.lc108_convert_sorted_array_to_binary_search_tree.LC108ConvertSortedArrayToBinarySearchTree.TreeNode;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public final class LC108ConvertSortedArrayToBinarySearchTreeTests {
    private int findExtreme(final TreeNode root, final int init, final Comparator<Integer> relation) {
        if (root == null) {
            return 0;
        }
        Queue<TreeNode> nodes = new LinkedList<>();
        nodes.add(root);
        Queue<Integer> levels = new LinkedList<>();
        levels.add(1);
        int extremum = init;
        while (nodes.size() > 0) {
            TreeNode node = nodes.poll();
            int level = levels.poll();
            if (node.left == null && node.right == null) {
                if (relation.compare(level, extremum) > 0) {
                    extremum = level;
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
        return extremum;
    }

    private static final Comparator<Integer> LESS = Comparator.<Integer>naturalOrder();
    private static final Comparator<Integer> MORE = Comparator.<Integer>naturalOrder().reversed();

    private int minHeight(final TreeNode root) {
        return findExtreme(root, Integer.MAX_VALUE, LESS);
    }

    private int maxHeight(final TreeNode root) {
        return findExtreme(root, Integer.MIN_VALUE, MORE);
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
