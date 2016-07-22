package leetcode;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 * https://leetcode.com/problems/swap-nodes-in-pairs/
 */
public class LC024SwapNodesInPairs {
    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public class Solution {
        public ListNode swapPairs(ListNode head) {
            ListNode result = new ListNode(0);
            result.next = head;
            head = result;
            while (head.next != null && head.next.next != null) {
                ListNode first = head.next;
                ListNode second = head.next.next;
                ListNode after = head.next.next.next;
                head.next = second;
                second.next = first;
                first.next = after;
                head = head.next.next;
            }
            return result.next;
        }
    }

    @Test
    public void test_example() throws Exception {
        ListNode n1 = new ListNode(1);
        ListNode n2 = new ListNode(2);
        ListNode n3 = new ListNode(3);
        ListNode n4 = new ListNode(4);
        n1.next = n2;
        n2.next = n3;
        n3.next = n4;
        int[] expected = {2, 1, 4, 3};
        ListNode result = new Solution().swapPairs(n1);
        for (int value : expected) {
            assertNotEquals(null, result);
            assertEquals(value, result.val);
            result = result.next;
        }
    }
}
