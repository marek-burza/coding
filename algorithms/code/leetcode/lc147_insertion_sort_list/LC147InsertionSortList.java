package leetcode.lc147_insertion_sort_list;

/**
 * https://leetcode.com/problems/insertion-sort-list/
 * #medium
 */
public final class LC147InsertionSortList {
    public static class ListNode {
        int val;
        ListNode next;

        ListNode(final int x) {
            val = x;
        }
    }

    public ListNode insertionSortList(final ListNode head) {
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
                // When a node with greater or equal value found then insert extracted node
                // before it
                if (node.val <= current.next.val) {
                    node.next = current.next;
                    current.next = node;
                    inserted = true;
                    break;
                }
                // Move on to the next ordered node
                current = current.next;
            }
            // If the extracted node was not inserted then insert it at the end of the
            // ordered list
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
