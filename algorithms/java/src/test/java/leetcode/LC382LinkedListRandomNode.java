package leetcode.lc382_linked_list_random_node;

import org.junit.jupiter.api.Test;


import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * https://leetcode.com/problems/linked-list-random-node/
 * #medium
 */
public final class LC382LinkedListRandomNode {
    public final class ListNode {
        int val;
        ListNode next;

        ListNode(final int x) {
            val = x;
        }
    }

    public final class Solution {
        private final ListNode head;
        private final Random random;

        public Solution(final ListNode head) {
            this.head = head;
            random = new Random();
        }

        public int getRandom() {
            ListNode result = null, current = head;
            for (int i = 1; current != null; i++, current = current.next) {
                if (random.nextInt(i) == 0) {
                    result = current;
                }
            }
            return result.val;
        }
    }

    @Test
    public void test_example() throws Exception {
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
}
