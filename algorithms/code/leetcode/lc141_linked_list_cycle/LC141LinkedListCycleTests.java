package leetcode.lc141_linked_list_cycle;

import org.junit.jupiter.api.Test;

import leetcode.lc141_linked_list_cycle.LC141LinkedListCycle.ListNode;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * https://leetcode.com/problems/linked-list-cycle/ #easy
 */
public final class LC141LinkedListCycleTests {
    @Test
    public void testEmpty() throws Exception {
        assertFalse(new LC141LinkedListCycle().hasCycle(null));
    }

    @Test
    public void testSingleCycle() throws Exception {
        ListNode node = new ListNode(1);
        node.next = node;
        assertTrue(new LC141LinkedListCycle().hasCycle(node));
    }

    @Test
    public void testSingleNoCycle() throws Exception {
        ListNode node = new ListNode(1);
        assertFalse(new LC141LinkedListCycle().hasCycle(node));
    }

    @Test
    public void testTwoCycle() throws Exception {
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        node1.next = node2;
        node2.next = node1;
        assertTrue(new LC141LinkedListCycle().hasCycle(node1));
    }

    @Test
    public void testTwoNoCycle() throws Exception {
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        node1.next = node2;
        assertFalse(new LC141LinkedListCycle().hasCycle(node1));
    }

    @Test
    public void testFieldUse() throws Exception {
        assertEquals(0, new ListNode(0).val);
    }
}
