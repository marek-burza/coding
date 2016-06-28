package leetcode;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * https://leetcode.com/problems/remove-duplicates-from-sorted-list/
 */
public class LC083RemoveDuplicatesFromSortedList {
    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public class Solution {
        public ListNode deleteDuplicates(ListNode head) {
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

    @Test
    public void test_1_1_2() throws Exception {
        ListNode n1a = new ListNode(1);
        ListNode n1b = new ListNode(1);
        ListNode n2 = new ListNode(2);
        n1a.next = n1b;
        n1b.next = n2;
        n2.next = null;
        ListNode result = new Solution().deleteDuplicates(n1a);
        assertEquals(1, result.val);
        assertEquals(2, result.next.val);
        assertEquals(null, result.next.next);
    }

    @Test
    public void test_1_1_2_3_3() throws Exception {
        ListNode n1a = new ListNode(1);
        ListNode n1b = new ListNode(1);
        ListNode n2 = new ListNode(2);
        ListNode n3a = new ListNode(3);
        ListNode n3b = new ListNode(3);
        n1a.next = n1b;
        n1b.next = n2;
        n2.next = n3a;
        n3a.next = n3b;
        n3b.next = null;
        ListNode result = new Solution().deleteDuplicates(n1a);
        assertEquals(1, result.val);
        assertEquals(2, result.next.val);
        assertEquals(3, result.next.next.val);
        assertEquals(null, result.next.next.next);
    }
}
