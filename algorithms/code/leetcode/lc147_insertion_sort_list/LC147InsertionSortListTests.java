package leetcode.lc147_insertion_sort_list;

import org.junit.jupiter.api.Test;

import leetcode.lc147_insertion_sort_list.LC147InsertionSortList.ListNode;

import static org.junit.jupiter.api.Assertions.assertEquals;

public final class LC147InsertionSortListTests {
    @Test
    public void testExample() {
        ListNode n1 = new ListNode(1);
        ListNode n2 = new ListNode(2);
        ListNode n3 = new ListNode(3);
        ListNode n4 = new ListNode(4);
        ListNode n5 = new ListNode(5);
        ListNode n6 = new ListNode(6);
        n6.next = n3;
        n3.next = n4;
        n4.next = n5;
        n5.next = n2;
        n2.next = n1;
        ListNode result = new LC147InsertionSortList().insertionSortList(n6);
        assertEquals(n1, result);
        assertEquals(n2, n1.next);
        assertEquals(n3, n2.next);
        assertEquals(n4, n3.next);
        assertEquals(n5, n4.next);
        assertEquals(n6, n5.next);
        assertEquals(null, n6.next);
    }

    @Test
    public void test11() {
        ListNode n1A = new ListNode(1);
        ListNode n1B = new ListNode(1);
        n1A.next = n1B;
        ListNode result = new LC147InsertionSortList().insertionSortList(n1A);
        assertEquals(n1B, result);
        assertEquals(n1A, n1B.next);
        assertEquals(null, n1A.next);
    }
}
