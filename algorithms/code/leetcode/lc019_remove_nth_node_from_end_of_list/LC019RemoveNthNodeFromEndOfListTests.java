package leetcode.lc019_remove_nth_node_from_end_of_list;

import static leetcode.lc019_remove_nth_node_from_end_of_list.LC019RemoveNthNodeFromEndOfList.ListNode;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public final class LC019RemoveNthNodeFromEndOfListTests {
    @Test
    public void test12() throws Exception {
        LC019RemoveNthNodeFromEndOfList solution;
        solution = new LC019RemoveNthNodeFromEndOfList();
        ListNode n1 = new ListNode(1);
        ListNode n2 = new ListNode(2);
        n1.next = n2;
        n2.next = null;
        ListNode n = n1;
        assertEquals(1, n.val);
        assertEquals(2, n.next.val);
        n = solution.removeNthFromEnd(n, 1);
        assertEquals(1, n.val);
        n = solution.removeNthFromEnd(n, 1);
        assertEquals(null, n);
    }

    @Test
    public void testNull0() throws Exception {
        LC019RemoveNthNodeFromEndOfList solution;
        solution = new LC019RemoveNthNodeFromEndOfList();
        ListNode n = solution.removeNthFromEnd(null, 0);
        assertNull(n);
    }
}
