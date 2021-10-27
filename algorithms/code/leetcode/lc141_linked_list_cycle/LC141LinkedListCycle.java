package leetcode.lc141_linked_list_cycle;

/**
 * https://leetcode.com/problems/linked-list-cycle/ #easy
 */
public final class LC141LinkedListCycle {
    public static class ListNode {
        int val;
        ListNode next;

        ListNode(final int x) {
            val = x;
            next = null;
        }
    }

    public boolean hasCycle(final ListNode head) {
        if (head == null) {
            return false;
        }
        if (head.next == head) {
            return true;
        }
        ListNode previous = null;
        ListNode current = head;
        int count = 0;
        while (current != null) {
            if (current == head) {
                count++;
            }
            ListNode detached = current;
            current = current.next;
            detached.next = previous;
            previous = detached;
        }
        return count > 1;
    }
}
