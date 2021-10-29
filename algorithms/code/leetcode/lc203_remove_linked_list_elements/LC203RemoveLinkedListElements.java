package leetcode.lc203_remove_linked_list_elements;

/**
 * https://leetcode.com/problems/remove-linked-list-elements/ #easy
 */
public final class LC203RemoveLinkedListElements {
    public static class ListNode {
        public int val;
        public ListNode next;

        ListNode(final int x) {
            val = x;
        }
    }

    public ListNode removeElements(final ListNode headNode, final int val) {
        ListNode head = headNode;
        if (head == null) {
            return null;
        }
        ListNode previous = null;
        ListNode node = head;
        while (node != null) {
            if (node.val == val) {
                if (previous == null) {
                    head = node.next;
                } else {
                    previous.next = node.next;
                }
            } else {
                previous = node;
            }
            node = node.next;
        }
        return head;
    }
}
