package leetcode.lc206_reverse_linked_list;

/**
 * https://leetcode.com/problems/reverse-linked-list/ #easy
 */
public final class LC206ReverseLinkedList {
    public static class ListNode {
        public int val;
        public ListNode next;

        ListNode(final int x) {
            val = x;
        }
    }

    public ListNode reverseList(final ListNode headNode) {
        ListNode head = headNode;
        if (head == null) {
            return (null);
        }
        ListNode ante = null;
        while (head != null) {
            ListNode post = head.next;
            head.next = ante;
            ante = head;
            head = post;
        }
        return ante;
    }
}
