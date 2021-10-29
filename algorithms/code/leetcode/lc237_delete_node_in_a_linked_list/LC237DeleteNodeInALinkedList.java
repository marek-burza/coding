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
