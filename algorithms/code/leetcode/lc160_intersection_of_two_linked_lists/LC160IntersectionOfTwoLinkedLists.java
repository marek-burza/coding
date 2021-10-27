package leetcode.lc160_intersection_of_two_linked_lists;

/**
 * https://leetcode.com/problems/intersection-of-two-linked-lists/ #easy
 */
public final class LC160IntersectionOfTwoLinkedLists {
    public static class ListNode {
        int val;
        ListNode next;

        ListNode(final int x) {
            val = x;
            next = null;
        }
    }

    private ListNode detectCycle(final ListNode head) {
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

    private ListNode last(final ListNode list) {
        ListNode node = list;
        while (node.next != null) {
            node = node.next;
        }
        return node;
    }

    public ListNode getIntersectionNode(final ListNode headA, final ListNode headB) {
        if (headA == null || headB == null) {
            return null;
        }
        ListNode last = last(headA);
        last.next = headB;
        ListNode node = detectCycle(headA);
        last.next = null;
        return node;
    }

    // One can also measure the length of lists, skip their delta, iterate in
    // parallel until common node found
}
