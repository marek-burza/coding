package leetcode.lc021_merge_two_sorted_lists;

import static leetcode.lc021_merge_two_sorted_lists.LC021MergeTwoSortedLists.ListNode;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public final class LC021MergeTwoSortedListsTests {
    @Test
    public void test13579And246() throws Exception {
        LC021MergeTwoSortedLists solution;
        solution = new LC021MergeTwoSortedLists();
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
        ListNode n0 = solution.mergeTwoLists(n1, n2);
        assertEquals(1, n0.val);
        assertEquals(2, n0.next.val);
        assertEquals(3, n0.next.next.val);
        assertEquals(4, n0.next.next.next.val);
        assertEquals(5, n0.next.next.next.next.val);
        assertEquals(6, n0.next.next.next.next.next.val);
        assertEquals(7, n0.next.next.next.next.next.next.val);
        assertEquals(9, n0.next.next.next.next.next.next.next.val);
    }

    @Test
    public void test123And456() throws Exception {
        LC021MergeTwoSortedLists solution;
        solution = new LC021MergeTwoSortedLists();
        ListNode n3 = new ListNode(3);
        n3.next = null;
        ListNode n2 = new ListNode(2);
        n2.next = n3;
        ListNode n1 = new ListNode(1);
        n1.next = n2;
        ListNode n6 = new ListNode(6);
        n6.next = null;
        ListNode n5 = new ListNode(5);
        n5.next = n6;
        ListNode n4 = new ListNode(4);
        n4.next = n5;
        ListNode n0 = solution.mergeTwoLists(n1, n4);
        assertEquals(1, n0.val);
        assertEquals(2, n0.next.val);
        assertEquals(3, n0.next.next.val);
        assertEquals(4, n0.next.next.next.val);
        assertEquals(5, n0.next.next.next.next.val);
        assertEquals(6, n0.next.next.next.next.next.val);
        assertNull(n0.next.next.next.next.next.next);
    }
}
