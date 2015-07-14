package leetcode;

/**
 * https://leetcode.com/problems/linked-list-cycle-ii/
 */
public class LC142LinkedListCycleII {
    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }

    public ListNode detectCycle(ListNode head) {
        ListNode p1 = head;
        ListNode p2 = head;
        while (p2 != null && p2.next != null) {
            p1 = p1.next;
            p2 = p2.next.next;
            if (p1 == p2) break;
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

    // Instead of that once can break the cycle once detected and then search for common element by measuring lengths

    public static void main(String[] arguments) {
        ListNode a1 = new ListNode(0xA1);
        ListNode a2 = new ListNode(0xA2);
        ListNode b1 = new ListNode(0xB1);
        ListNode b2 = new ListNode(0xB2);
        ListNode b3 = new ListNode(0xB3);
        ListNode c1 = new ListNode(0xC1);
        ListNode c2 = new ListNode(0xC2);
        ListNode c3 = new ListNode(0xC3);
        a1.next = a2;
        a2.next = c1;
        b1.next = b2;
        b2.next = b3;
        b3.next = c1;
        c1.next = c2;
        c2.next = c3;
        c3.next = b1;
        LC142LinkedListCycleII solution = new LC142LinkedListCycleII();
        System.out.println(c1 == solution.detectCycle(a1));
    }
}
