package leetcode;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * https://leetcode.com/problems/insertion-sort-list/
 * #medium
 */
public class LC147InsertionSortList {
    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }

        @Override
        public String toString() {
            return String.format("%d", val);
        }
    }

    public class Solution {
        public ListNode insertionSortList(ListNode head) {
            ListNode handle = new ListNode(0);
            handle.next = head;
            ListNode tail = handle;
            while (tail.next != null) {
                // Grab first node after the tail of already ordered nodes
                ListNode node = tail.next;
                // Remove (extract) that node from the list
                tail.next = tail.next.next;
                // Iterate from the node holding the head
                ListNode current = handle;
                boolean inserted = false;
                // Iterate until we reach the node beyond the extracted node
                while (current.next != tail.next) {
                    // When a node with greater or equal value found then insert extracted node before it
                    if (node.val <= current.next.val) {
                        node.next = current.next;
                        current.next = node;
                        inserted = true;
                        break;
                    }
                    // Move on to the next ordered node
                    current = current.next;
                }
                // If the extracted node was not inserted then insert it at the end of the ordered list
                if (!inserted) {
                    node.next = tail.next;
                    current.next = node;
                    // The extracted node becomes the tail of the ordered list
                    tail = node;
                }
            }
            return handle.next;
        }
    }

    @Test
    public void test_example() {
        ListNode n1 = new ListNode(1);
        ListNode n2 = new ListNode(2);
        ListNode n3 = new ListNode(3);
        ListNode n4 = new ListNode(4);
        ListNode n5 = new ListNode(5);
        ListNode n6 = new ListNode(6);
        n6.next = n3;
        n3.next = n4;
        n4.next = n5;
        n5.next = n2;
        n2.next = n1;
        ListNode result = new Solution().insertionSortList(n6);
        assertEquals(n1, result);
        assertEquals(n2, n1.next);
        assertEquals(n3, n2.next);
        assertEquals(n4, n3.next);
        assertEquals(n5, n4.next);
        assertEquals(n6, n5.next);
        assertEquals(null, n6.next);
    }

    @Test
    public void test_1_1() {
        ListNode n1_a = new ListNode(1);
        ListNode n1_b = new ListNode(1);
        n1_a.next = n1_b;
        ListNode result = new Solution().insertionSortList(n1_a);
        assertEquals(n1_b, result);
        assertEquals(n1_a, n1_b.next);
        assertEquals(null, n1_a.next);
    }
}
