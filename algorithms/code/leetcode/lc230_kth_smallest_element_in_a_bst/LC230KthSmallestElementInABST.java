package leetcode.lc230_kth_smallest_element_in_a_bst;

/**
 * https://leetcode.com/problems/kth-smallest-element-in-a-bst/
 * #medium
 */
public final class LC230KthSmallestElementInABST {
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(final int x) {
            val = x;
        }
    }

    public int kthSmallest(final TreeNode root, final int k, final TreeNode counter) {
        if (root.left != null) {
            int result = kthSmallest(root.left, k, counter);
            if (counter.val == k) {
                return result;
            }
        }
        counter.val++;
        if (counter.val == k) {
            return root.val;
        }
        if (root.right != null) {
            int result = kthSmallest(root.right, k, counter);
            if (counter.val == k) {
                return result;
            }
        }
        return 0;
    }

    public int kthSmallest(final TreeNode root, final int k) {
        TreeNode counter = new TreeNode(0);
        return kthSmallest(root, k, counter);
    }
}
