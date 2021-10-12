package leetcode;

import org.junit.jupiter.api.Test;


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * https://leetcode.com/problems/convert-sorted-array-to-binary-search-tree/
 * #easy
 */
public class LC108ConvertSortedArrayToBinarySearchTree {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public final class Solution {
        public TreeNode sortedArrayToBST(int[] nums, int from, int to) {
            if (from > to) return null;
            int at = (from + to) / 2;
            TreeNode node = new TreeNode(nums[at]);
            node.left = sortedArrayToBST(nums, from, at - 1);
            node.right = sortedArrayToBST(nums, at + 1, to);
            return node;
        }

        public TreeNode sortedArrayToBST(int[] nums) {
            return sortedArrayToBST(nums, 0, nums.length - 1);
        }
    }

    private int minHeight(TreeNode root) {
        if (root == null) return 0;
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

    private int maxHeight(TreeNode root) {
        if (root == null) return 0;
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

    private void reconstruct(TreeNode root, List<Integer> list) {
        if (root == null) return;
        if (root.left != null) {
            reconstruct(root.left, list);
        }
        list.add(root.val);
        if (root.right != null) {
            reconstruct(root.right, list);
        }
    }

    private boolean isBST(TreeNode root) {
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
    public void test_empty() throws Exception {
        assertEquals(null, new Solution().sortedArrayToBST(new int[0]));
    }

    @Test
    public void test_depth_and_ordering() throws Exception {
        int[] nums = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        TreeNode root = new Solution().sortedArrayToBST(nums);
        int difference = maxHeight(root) - minHeight(root);
        assertEquals(true, 0 <= difference && difference <= 1);
        assertEquals(true, isBST(root));
    }
}
