package leetcode;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * https://leetcode.com/problems/odd-even-linked-list/
 * #medium
 */
public class LC328OddEvenLinkedList {
    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public class Solution {
        public ListNode oddEvenList(ListNode head) {
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

    @Test
    public void test_example() throws Exception {
        ListNode head = new ListNode(0);
        ListNode tail = head;
        for (int i = 1; i <= 5; i++) {
            tail.next = new ListNode(i);
            tail = tail.next;
        }
        ListNode result = new Solution().oddEvenList(head.next);
        int[] expected = {1, 3, 5, 2, 4};
        for (int value : expected) {
            assertEquals(value, result.val);
            result = result.next;
        }
        assertEquals(null, result);
    }

    @Test
    public void test_null() throws Exception {
        assertEquals(null, new Solution().oddEvenList(null));
    }
}
