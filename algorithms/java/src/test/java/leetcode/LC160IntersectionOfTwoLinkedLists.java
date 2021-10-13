package leetcode.lc160_intersection_of_two_linked_lists;

import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * https://leetcode.com/problems/intersection-of-two-linked-lists/
 * #easy
 */
public final class LC160IntersectionOfTwoLinkedLists {
    public final class ListNode {
        int val;
        ListNode next;

        ListNode(final int x) {
            val = x;
            next = null;
        }
    }

    public final class Solution {
        public ListNode detectCycle(final ListNode head) {
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

        private ListNode last(final ListNode list) {
            while (list.next != null) {
                list = list.next;
            }
            return list;
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
    }

    // One can also measure the length of lists, skip their delta, iterate in parallel until common node found

    @Test
    public void test_example() throws Exception {
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
        c3.next = null;
        assertEquals(c1, new Solution().getIntersectionNode(a1, b1));
    }
}
