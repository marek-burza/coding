package leetcode.lc083_remove_duplicates_from_sorted_list;

/**
 * https://leetcode.com/problems/remove-duplicates-from-sorted-list/ #easy
 */
public final class LC083RemoveDuplicatesFromSortedList {
    public static class ListNode {
        int val;
        ListNode next;

        ListNode(final int x) {
            val = x;
        }
    }

    public ListNode deleteDuplicates(final ListNode headValue) {
        ListNode head = headValue;
        ListNode anchor = head;
        while (head != null) {
            while (head.next != null && head.val == head.next.val) {
                head.next = head.next.next;
            }
            head = head.next;
        }
        return anchor;
    }
}
