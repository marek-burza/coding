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
