package leetcode.lc024_swap_nodes_in_pairs;

import static leetcode.lc024_swap_nodes_in_pairs.LC024SwapNodesInPairs.ListNode;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public final class LC024SwapNodesInPairsTests {
    @Test
    public void testExample() throws Exception {
        LC024SwapNodesInPairs solution;
        solution = new LC024SwapNodesInPairs();
        ListNode n1 = new ListNode(1);
        ListNode n2 = new ListNode(2);
        ListNode n3 = new ListNode(3);
        ListNode n4 = new ListNode(4);
        n1.next = n2;
        n2.next = n3;
        n3.next = n4;
        int[] expected = { 2, 1, 4, 3 };
        ListNode result = solution.swapPairs(n1);
        for (int value : expected) {
            assertNotEquals(null, result);
            assertEquals(value, result.val);
            result = result.next;
        }
    }

    @Test
    public void testExampleImpaired() throws Exception {
        LC024SwapNodesInPairs solution;
        solution = new LC024SwapNodesInPairs();
        ListNode n1 = new ListNode(1);
        ListNode n2 = new ListNode(2);
        ListNode n3 = new ListNode(3);
        n1.next = n2;
        n2.next = n3;
        int[] expected = { 2, 1, 3 };
        ListNode result = solution.swapPairs(n1);
        for (int value : expected) {
            assertNotEquals(null, result);
            assertEquals(value, result.val);
            result = result.next;
        }
    }
}
