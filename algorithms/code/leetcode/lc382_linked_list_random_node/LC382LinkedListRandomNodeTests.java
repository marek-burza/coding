package leetcode.lc382_linked_list_random_node;

import org.junit.jupiter.api.Test;

import leetcode.lc382_linked_list_random_node.LC382LinkedListRandomNode.ListNode;
import leetcode.lc382_linked_list_random_node.LC382LinkedListRandomNode.Solution;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public final class LC382LinkedListRandomNodeTests {
    @Test
    public void testExample() throws Exception {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        int[] counts = new int[3];
        Solution solution = new Solution(head);
        for (int i = 0; i < 10000; i++) {
            int value = solution.getRandom();
            assertTrue(1 <= value && value <= 3);
            counts[value - 1]++;
        }
        assertEquals(3, counts[0] / 1000);
        assertEquals(3, counts[1] / 1000);
        assertEquals(3, counts[2] / 1000);
        // Should use Chi-squared test
    }

    @Test
    public void testNothing() {
        assertNotNull(new LC382LinkedListRandomNode());
    }
}
