package leetcode.lc024_swap_nodes_in_pairs;

/**
 * https://leetcode.com/problems/swap-nodes-in-pairs/
 */
public final class LC024SwapNodesInPairs {
    public static class ListNode {
        int val;
        ListNode next;

        ListNode(final int x) {
            val = x;
        }
    }

    public ListNode swapPairs(final ListNode head) {
        ListNode result = new ListNode(0);
        result.next = head;
        ListNode current = result;
        while (current.next != null && current.next.next != null) {
            ListNode first = current.next;
            ListNode second = current.next.next;
            ListNode after = current.next.next.next;
            current.next = second;
            second.next = first;
            first.next = after;
            current = current.next.next;
        }
        return result.next;
    }
}
