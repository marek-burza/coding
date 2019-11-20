package leetcode;

import java.util.Random;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * https://leetcode.com/problems/reverse-linked-list/
 */
public class LC206ReverseLinkedList {
    public class ListNode {
        public int val;
        public ListNode next;

        public ListNode(int x) {
            val = x;
        }
    }

    public class Solution {
        public ListNode reverseList(ListNode head) {
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
            return (ante);
        }
    }

    public ListNode random() {
        Random random = new Random();
        ListNode list = null;
        ListNode last = null;
        for (int i = 0; i < 1 + Math.abs(random.nextInt()) % 15; i++) {
            ListNode node = new ListNode(random.nextInt());
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

    public int[] freeze(ListNode list) {
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

    @Test
    public void test_random() throws Exception {
        ListNode list = random();
        int[] original = freeze(list);
        int[] result = freeze(new Solution().reverseList(list));
        assertEquals(original.length, result.length);
        for (int i = 0; i < original.length; i++) {
            assertEquals(original[original.length - 1 - i], result[i]);
        }
    }
}
