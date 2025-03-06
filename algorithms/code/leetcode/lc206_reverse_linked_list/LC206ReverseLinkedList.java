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
package leetcode.lc206_reverse_linked_list;

import org.junit.jupiter.api.Test;

import leetcode.lc206_reverse_linked_list.LC206ReverseLinkedList.ListNode;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public final class LC206ReverseLinkedListTests {
    public ListNode generate(final int length) {
        ListNode list = null;
        ListNode last = null;
        for (int i = 0; i < length; i++) {
            ListNode node = new ListNode(i);
            node.next = null;
            if (list == null) {
                list = node;
            } else {
                last.next = node;
            }
            last = node;
        }
        return list;
    }

    public int[] freeze(final ListNode listNode) {
        ListNode list = listNode;
        int count = 0;
        ListNode copy = list;
        while (copy != null) {
            count++;
            copy = copy.next;
        }
        int[] frozen = new int[count];
        for (int i = 0; i < count; i++) {
            frozen[i] = list.val;
            list = list.next;
        }
        return frozen;
    }

    private void generic(final ListNode list) throws Exception {
        int[] original = freeze(list);
        int[] result = freeze(new LC206ReverseLinkedList().reverseList(list));
        assertEquals(original.length, result.length);
        for (int i = 0; i < original.length; i++) {
            assertEquals(original[original.length - 1 - i], result[i]);
        }
    }

    @Test
    public void test15() throws Exception {
        ListNode list = generate(15);
        generic(list);
    }

    @Test
    public void test1() throws Exception {
        ListNode list = generate(1);
        generic(list);
    }

    @Test
    public void testNothing() throws Exception {
        assertNull(new LC206ReverseLinkedList().reverseList(null));
    }
}
