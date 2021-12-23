package leetcode.lc086_partition_list;

/**
 * https://leetcode.com/problems/partition-list/
 * #medium
 */
public final class LC086PartitionList {
    public static class ListNode {
        int val;
        ListNode next;

        ListNode(final int x) {
            val = x;
        }
    }

    public ListNode partition(final ListNode headValue, final int x) {
        ListNode head = headValue;
        if (head == null) {
            return null;
        }
        ListNode less = new ListNode(0);
        ListNode more = new ListNode(0);
        ListNode before = less;
        ListNode after = more;
        while (head != null) {
            if (head.val < x) {
                less.next = head;
                less = head;
            }
            if (head.val >= x) {
                more.next = head;
                more = head;
            }
            head = head.next;
        }
        less.next = after.next;
        more.next = null;
        return before.next;
    }
}
