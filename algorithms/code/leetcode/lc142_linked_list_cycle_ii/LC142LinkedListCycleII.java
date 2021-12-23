package leetcode.lc142_linked_list_cycle_ii;

/**
 * https://leetcode.com/problems/linked-list-cycle-ii/
 * #medium
 */
public final class LC142LinkedListCycleII {
    public static class ListNode {
        int val;
        ListNode next;

        ListNode(final int x) {
            val = x;
            next = null;
        }
    }

    public ListNode detectCycle(final ListNode head) {
        ListNode p1 = head;
        ListNode p2 = head;
        while (p2 != null && p2.next != null) {
            p1 = p1.next;
            p2 = p2.next.next;
            if (p1 == p2) {
                break;
            }
        }
        if (p2 == null || p2.next == null) {
            return null;
        }
        p1 = head;
        while (p1 != p2) {
            p1 = p1.next;
            p2 = p2.next;
        }
        return p2;
    }
}
