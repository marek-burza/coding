package leetcode;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * https://leetcode.com/problems/linked-list-cycle/
 */
public class LC141LinkedListCycle {
    class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }

    public class Solution {
        public boolean hasCycle(ListNode head) {
            if (head == null) return false;
            if (head.next == head) return true;
            ListNode previous = null, current = head;
            int count = 0;
            while (current != null) {
                if (current == head) {
                    count++;
                }
                ListNode detached = current;
                current = current.next;
                detached.next = previous;
                previous = detached;
            }
            return count != 1;
        }
    }

    @Test
    public void test_empty() throws Exception {
        assertEquals(false, new Solution().hasCycle(null));
    }

    @Test
    public void test_single_cycle() throws Exception {
        ListNode node = new ListNode(1);
        node.next = node;
        assertEquals(true, new Solution().hasCycle(node));
    }

    @Test
    public void test_single_no_cycle() throws Exception {
        ListNode node = new ListNode(1);
        assertEquals(false, new Solution().hasCycle(node));
    }
}
