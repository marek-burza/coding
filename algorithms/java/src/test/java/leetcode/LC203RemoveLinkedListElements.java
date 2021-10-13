package leetcode.lc203_remove_linked_list_elements;

import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * https://leetcode.com/problems/remove-linked-list-elements/
 * #easy
 */
public final class LC203RemoveLinkedListElements {
    public final class ListNode {
        public int val;
        public ListNode next;

        public ListNode(final int x) {
            val = x;
        }
    }

    public final class Solution {
        public ListNode removeElements(final ListNode head, final int val) {
            if (head == null) {
                return (null);
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
            return (head);
        }
    }

    public ListNode convert(final int[] array) {
        ListNode list = null;
        ListNode last = null;
        for (int value : array) {
            ListNode node = new ListNode(value);
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

    @Test
    public void test_6_1_2_3_4_5_6_7_6__6() throws Exception {
        int[] array = {6, 1, 2, 3, 4, 5, 6, 7, 6};
        ListNode list = convert(array);
        list = new Solution().removeElements(list, 6);
        int[] expected = {1, 2, 3, 4, 5, 7};
        for (int value : expected) {
            assertEquals(value, list.val);
            list = list.next;
        }
    }
}
