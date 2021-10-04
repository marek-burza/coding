package leetcode.lc019_remove_nth_node_from_end_of_list;

/**
 * https://leetcode.com/problems/remove-nth-node-from-end-of-list/
 */
public final class LC019RemoveNthNodeFromEndOfList {
    public static class ListNode {
        int val;
        ListNode next;

        ListNode(final int x) {
            val = x;
        }
    }

    public ListNode removeNthFromEnd(final ListNode head, final int n) {
        // Translate the index number from counted from the back to a one counted from
        // the front
        ListNode node = head;
        int counter = n;
        while (node != null) {
            counter--;
            node = node.next;
        }
        counter = -counter;
        // Do the deletion
        node = new ListNode(0);
        node.next = head;
        ListNode current = node;
        while (current != null) {
            if (counter == 0) {
                if (current.next != null) {
                    current.next = current.next.next;
                    break;
                }
            }
            current = current.next;
            counter--;
        }
        return node.next;
    }
}
