package leetcode;

import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * https://leetcode.com/problems/palindrome-linked-list/
 * #easy
 */
public final class LC234PalindromeLinkedList {
    public final class ListNode {
        int val;
        ListNode next;

        ListNode(final int x) {
            val = x;
        }
    }

    public final class Solution {
        public boolean isPalindrome(final ListNode head) {
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
            while (forward != null && backward != null) {
                if (forward.val != backward.val) return false;
                forward = forward.next;
                backward = backward.next;
            }
            return true;
        }
    }

    @Test
    public void test_palindrome_odd() throws Exception {
        ListNode list = new ListNode(0);
        list.next = new ListNode(1);
        list.next.next = new ListNode(2);
        list.next.next.next = new ListNode(1);
        list.next.next.next.next = new ListNode(0);
        assertEquals(true, new Solution().isPalindrome(list));
    }

    @Test
    public void test_palindrome_even() throws Exception {
        ListNode list = new ListNode(0);
        list.next = new ListNode(1);
        list.next.next = new ListNode(1);
        list.next.next.next = new ListNode(0);
        assertEquals(true, new Solution().isPalindrome(list));
    }

    @Test
    public void test_not_palindrome_odd() throws Exception {
        ListNode list = new ListNode(0);
        list.next = new ListNode(1);
        list.next.next = new ListNode(2);
        list.next.next.next = new ListNode(8);
        list.next.next.next.next = new ListNode(0);
        assertEquals(false, new Solution().isPalindrome(list));
    }

    @Test
    public void test_not_palindrome_even() throws Exception {
        ListNode list = new ListNode(0);
        list.next = new ListNode(1);
        list.next.next = new ListNode(8);
        list.next.next.next = new ListNode(0);
        assertEquals(false, new Solution().isPalindrome(list));
    }

    @Test
    public void test_empty() throws Exception {
        assertEquals(true, new Solution().isPalindrome(null));
    }

    @Test
    public void test_single() throws Exception {
        assertEquals(true, new Solution().isPalindrome(new ListNode(0)));
    }
}
