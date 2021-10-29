package leetcode.lc234_palindrome_linked_list;

import org.junit.jupiter.api.Test;

import leetcode.lc234_palindrome_linked_list.LC234PalindromeLinkedList.ListNode;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public final class LC234PalindromeLinkedListTests {
    @Test
    public void testPalindromeOdd() throws Exception {
        ListNode list = new ListNode(0);
        list.next = new ListNode(1);
        list.next.next = new ListNode(2);
        list.next.next.next = new ListNode(1);
        list.next.next.next.next = new ListNode(0);
        assertTrue(new LC234PalindromeLinkedList().isPalindrome(list));
    }

    @Test
    public void testPalindromeEven() throws Exception {
        ListNode list = new ListNode(0);
        list.next = new ListNode(1);
        list.next.next = new ListNode(1);
        list.next.next.next = new ListNode(0);
        assertTrue(new LC234PalindromeLinkedList().isPalindrome(list));
    }

    @Test
    public void testNotPalindromeOdd() throws Exception {
        ListNode list = new ListNode(0);
        list.next = new ListNode(1);
        list.next.next = new ListNode(2);
        list.next.next.next = new ListNode(8);
        list.next.next.next.next = new ListNode(0);
        assertFalse(new LC234PalindromeLinkedList().isPalindrome(list));
    }

    @Test
    public void testNotPalindromeEven() throws Exception {
        ListNode list = new ListNode(0);
        list.next = new ListNode(1);
        list.next.next = new ListNode(8);
        list.next.next.next = new ListNode(0);
        assertFalse(new LC234PalindromeLinkedList().isPalindrome(list));
    }

    @Test
    public void testEmpty() throws Exception {
        assertTrue(new LC234PalindromeLinkedList().isPalindrome(null));
    }

    @Test
    public void testSingle() throws Exception {
        assertTrue(new LC234PalindromeLinkedList().isPalindrome(new ListNode(0)));
    }
}
