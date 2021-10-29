package leetcode.lc234_palindrome_linked_list;

/**
 * https://leetcode.com/problems/palindrome-linked-list/ #easy
 */
public final class LC234PalindromeLinkedList {
    public static class ListNode {
        int val;
        ListNode next;

        ListNode(final int x) {
            val = x;
        }
    }

    public boolean isPalindrome(final ListNode headNode) {
        ListNode head = headNode;
        int count = 0;
        ListNode node = head;
        while (node != null) {
            count++;
            node = node.next;
        }
        ListNode previous = null;
        for (int i = 0; i < count / 2; i++) {
            ListNode next = head.next;
            head.next = previous;
            previous = head;
            head = next;
        }
        ListNode forward = (count & 1) == 1 ? head.next : head;
        ListNode backward = previous;
        while (forward != null) {
            if (forward.val != backward.val) {
                return false;
            }
            forward = forward.next;
            backward = backward.next;
        }
        return true;
    }
}
