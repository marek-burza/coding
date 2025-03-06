package leetcode.lc237_delete_node_in_a_linked_list;

/**
 * https://leetcode.com/problems/delete-node-in-a-linked-list/ #easy
 */
public final class LC237DeleteNodeInALinkedList {
    public static class ListNode {
        int val;
        ListNode next;

        ListNode(final int x) {
            val = x;
        }
    }

    public void deleteNode(final ListNode node) {
        node.val = node.next.val;
        node.next = node.next.next;
    }
}
package leetcode.lc237_delete_node_in_a_linked_list;

import org.junit.jupiter.api.Test;

import leetcode.lc237_delete_node_in_a_linked_list.LC237DeleteNodeInALinkedList.ListNode;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

public final class LC237DeleteNodeInALinkedListTests {
    @Test
    public void testExample() throws Exception {
        ListNode n1 = new ListNode(1);
        ListNode n2 = new ListNode(2);
        ListNode n3 = new ListNode(3);
        ListNode n4 = new ListNode(4);
        n1.next = n2;
        n2.next = n3;
        n3.next = n4;
        new LC237DeleteNodeInALinkedList().deleteNode(n3);
        int[] expected = { 1, 2, 4 };
        ListNode n = n1;
        for (int value : expected) {
            assertNotNull(n);
            assertEquals(value, n.val);
            n = n.next;
        }
        assertNull(n);
    }
}
