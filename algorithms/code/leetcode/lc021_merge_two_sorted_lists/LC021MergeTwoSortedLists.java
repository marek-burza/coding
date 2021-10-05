package leetcode.lc021_merge_two_sorted_lists;

/**
 * https://leetcode.com/problems/merge-two-sorted-lists/
 * #easy
 */
public final class LC021MergeTwoSortedLists {
    public static class ListNode {
        int val;
        ListNode next;

        ListNode(final int x) {
            val = x;
        }
    }

    public ListNode mergeTwoLists(final ListNode list1, final ListNode list2) {
        ListNode l1 = list1;
        ListNode l2 = list2;
        ListNode handle = new ListNode(0);
        ListNode current = handle;
        current.next = null;
        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                current.next = l1;
                l1 = l1.next;
            } else {
                current.next = l2;
                l2 = l2.next;
            }
            current = current.next;
        }
        if (l1 != null) {
            current.next = l1;
        }
        if (l2 != null) {
            current.next = l2;
        }
        return handle.next;
    }
}
