package leetcode.lc382_linked_list_random_node;

import java.security.SecureRandom;
import java.util.Random;

/**
 * https://leetcode.com/problems/linked-list-random-node/
 * #medium
 */
public final class LC382LinkedListRandomNode {
    public static class ListNode {
        int val;
        ListNode next;

        ListNode(final int x) {
            val = x;
        }
    }

    public static class Solution {
        private final ListNode head;
        private static final Random RANDOM = new SecureRandom();

        Solution(final ListNode headValue) {
            head = headValue;
        }

        public final int getRandom() {
            ListNode result = null;
            ListNode current = head;
            for (int i = 1; current != null; i++, current = current.next) {
                if (RANDOM.nextInt(i) == 0) {
                    result = current;
                }
            }
            return result.val;
        }
    }
}
