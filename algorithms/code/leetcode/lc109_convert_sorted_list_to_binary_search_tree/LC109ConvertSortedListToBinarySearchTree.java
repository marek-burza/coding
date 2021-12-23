package leetcode.lc109_convert_sorted_list_to_binary_search_tree;

/**
 * https://leetcode.com/problems/convert-sorted-list-to-binary-search-tree/
 * #medium
 */
public final class LC109ConvertSortedListToBinarySearchTree {
    public static class ListNode {
        int val;
        ListNode next;

        ListNode(final int x) {
            val = x;
        }
    }

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(final int x) {
            val = x;
        }
    }

    private int length(final ListNode headValue) {
        ListNode head = headValue;
        int count = 0;
        while (head != null) {
            count++;
            head = head.next;
        }
        return count;
    }

    private TreeNode generate(final ListNode head, final int length) {
        if (head == null || length <= 0) {
            return null;
        }
        int half = length >> 1;
        ListNode middle = head;
        for (int i = 0; i < half; i++) {
            middle = middle.next;
        }
        TreeNode root = new TreeNode(middle.val);
        root.left = generate(head, half);
        root.right = generate(middle.next, length - half - 1);
        return root;
    }

    public TreeNode sortedListToBST(final ListNode head) {
        return generate(head, length(head));
    }
}
