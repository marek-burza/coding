package leetcode.lc083_remove_duplicates_from_sorted_list;

import org.junit.jupiter.api.Test;

import leetcode.lc083_remove_duplicates_from_sorted_list.LC083RemoveDuplicatesFromSortedList.ListNode;

import static org.junit.jupiter.api.Assertions.assertEquals;

public final class LC083RemoveDuplicatesFromSortedListTests {
    @Test
    public void test11233() throws Exception {
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
        ListNode result = new LC083RemoveDuplicatesFromSortedList().deleteDuplicates(n1a);
        assertEquals(1, result.val);
        assertEquals(2, result.next.val);
        assertEquals(3, result.next.next.val);
        assertEquals(null, result.next.next.next);
    }
}
