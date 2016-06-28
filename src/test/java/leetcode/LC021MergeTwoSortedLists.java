package leetcode;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * https://leetcode.com/problems/merge-two-sorted-lists/
 */
public class LC021MergeTwoSortedLists {
    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public class Solution {
        public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
            ListNode handle = new ListNode(0);
            ListNode current = handle;
            current.next = null;
            while (l1 != null && l2 != null) {
                if (l1.val < l2.val) {
                    current.next = l1;
                    l1 = l1.next;
                } else {
                    current.next = l2;
                    l2 = l2.next;
                }
                current = current.next;
            }
            if (l1 != null) {
                current.next = l1;
            }
            if (l2 != null) {
                current.next = l2;
            }
            return handle.next;
        }
    }

    @Test
    public void test_1_3_5_7_9__2_4_6() throws Exception {
        ListNode n9 = new ListNode(9);
        n9.next = null;
        ListNode n7 = new ListNode(7);
        n7.next = n9;
        ListNode n5 = new ListNode(5);
        n5.next = n7;
        ListNode n3 = new ListNode(3);
        n3.next = n5;
        ListNode n1 = new ListNode(1);
        n1.next = n3;
        ListNode n6 = new ListNode(6);
        n6.next = null;
        ListNode n4 = new ListNode(4);
        n4.next = n6;
        ListNode n2 = new ListNode(2);
        n2.next = n4;
        ListNode n0 = new Solution().mergeTwoLists(n1, n2);
        assertEquals(1, n0.val);
        assertEquals(2, n0.next.val);
        assertEquals(3, n0.next.next.val);
        assertEquals(4, n0.next.next.next.val);
        assertEquals(5, n0.next.next.next.next.val);
        assertEquals(6, n0.next.next.next.next.next.val);
        assertEquals(7, n0.next.next.next.next.next.next.val);
        assertEquals(9, n0.next.next.next.next.next.next.next.val);
    }
}
