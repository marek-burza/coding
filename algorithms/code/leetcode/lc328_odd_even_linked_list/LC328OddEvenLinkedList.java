package leetcode.lc328_odd_even_linked_list;

/**
 * https://leetcode.com/problems/odd-even-linked-list/
 * #medium
 */
public final class LC328OddEvenLinkedList {
    public static class ListNode {
        int val;
        ListNode next;

        ListNode(final int x) {
            val = x;
        }
    }

    public ListNode oddEvenList(final ListNode headValue) {
        ListNode head = headValue;
        ListNode evenHead = new ListNode(0);
        ListNode oddHead = new ListNode(0);
        ListNode evenTail = evenHead;
        ListNode oddTail = oddHead;
        boolean odd = true;
        while (head != null) {
            if (odd) {
                oddTail.next = head;
                oddTail = oddTail.next;
                odd = false;
            } else {
                evenTail.next = head;
                evenTail = evenTail.next;
                odd = true;
            }
            head = head.next;
        }
        evenTail.next = null;
        oddTail.next = evenHead.next;
        return oddHead.next;
    }
}
package leetcode.lc328_odd_even_linked_list;

import org.junit.jupiter.api.Test;

import leetcode.lc328_odd_even_linked_list.LC328OddEvenLinkedList.ListNode;

import static org.junit.jupiter.api.Assertions.assertEquals;

public final class LC328OddEvenLinkedListTests {
    @Test
    public void testExample() throws Exception {
        ListNode head = new ListNode(0);
        ListNode tail = head;
        for (int i = 1; i <= 5; i++) {
            tail.next = new ListNode(i);
            tail = tail.next;
        }
        ListNode result = new LC328OddEvenLinkedList().oddEvenList(head.next);
        int[] expected = { 1, 3, 5, 2, 4 };
        for (int value : expected) {
            assertEquals(value, result.val);
            result = result.next;
        }
        assertEquals(null, result);
    }

    @Test
    public void testNull() throws Exception {
        assertEquals(null, new LC328OddEvenLinkedList().oddEvenList(null));
    }
}
