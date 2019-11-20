package leetcode;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * https://leetcode.com/problems/remove-nth-node-from-end-of-list/
 */
public class LC019RemoveNthNodeFromEndOfList {
    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public class Solution {
        public ListNode removeNthFromEnd(ListNode head, int n) {
            // Translate the index number from counted from the back to a one counted from the front
            ListNode node = head;
            while (node != null) {
                n--;
                node = node.next;
            }
            n = -n;
            // Do the deletion
            node = new ListNode(0);
            node.next = head;
            head = node;
            while (head != null) {
                if (n == 0) {
                    if (head.next != null) {
                        head.next = head.next.next;
                        break;
                    }
                }
                head = head.next;
                n--;
            }
            return node.next;
        }
    }

    @Test
    public void test_1_2() throws Exception {
        ListNode n1 = new ListNode(1);
        ListNode n2 = new ListNode(2);
        n1.next = n2;
        n2.next = null;
        ListNode n = n1;
        Solution solution = new Solution();
        assertEquals(1, n.val);
        assertEquals(2, n.next.val);
        n = solution.removeNthFromEnd(n, 1);
        assertEquals(1, n.val);
        n = solution.removeNthFromEnd(n, 1);
        assertEquals(null, n);
    }
}
