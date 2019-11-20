package leetcode;

import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;

/**
 * https://leetcode.com/problems/add-two-numbers/
 */
public class LC002AddTwoNumbers {
    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public class Solution {
        public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
            ListNode handle = new ListNode(0);
            handle.next = null;
            ListNode tail = handle;
            int carry = 0, value1, value2, sum;
            while (l1 != null || l2 != null || carry != 0) {
                if (l1 == null) {
                    value1 = 0;
                } else {
                    value1 = l1.val;
                    l1 = l1.next;
                }
                if (l2 == null) {
                    value2 = 0;
                } else {
                    value2 = l2.val;
                    l2 = l2.next;
                }
                sum = carry + value1 + value2;
                tail.next = new ListNode(sum % 10);
                tail.next.next = null;
                tail = tail.next;
                carry = sum / 10;
            }
            return handle.next;
        }
    }

    private ListNode thaw(int[] array) {
        ListNode handle = new ListNode(0);
        handle.next = null;
        ListNode tail = handle;
        for (int value : array) {
            tail.next = new ListNode(value);
            tail.next.next = null;
            tail = tail.next;
        }
        return handle.next;
    }

    private int[] freeze(ListNode list) {
        int count = 0;
        ListNode copy = list;
        while (copy != null) {
            count++;
            copy = copy.next;
        }
        int[] frozen = new int[count];
        copy = list;
        for (int i = 0; i < count; i++) {
            frozen[i] = copy.val;
            copy = copy.next;
        }
        return frozen;
    }

    @Test
    public void test_example() throws Exception {
        int[] array1 = {2, 4, 3};
        int[] array2 = {5, 6, 4};
        ListNode list = new Solution().addTwoNumbers(thaw(array1), thaw(array2));
        int[] expected = {7, 0, 8};
        assertArrayEquals(expected, freeze(list));
    }

    @Test
    public void test_uneven() throws Exception {
        int[] array1 = {2, 4};
        int[] array2 = {5, 6, 4};
        ListNode list = new Solution().addTwoNumbers(thaw(array1), thaw(array2));
        int[] expected = {7, 0, 5};
        assertArrayEquals(expected, freeze(list));
    }

    @Test
    public void test_carry() throws Exception {
        int[] array1 = {2, 4};
        int[] array2 = {5, 6};
        ListNode list = new Solution().addTwoNumbers(thaw(array1), thaw(array2));
        int[] expected = {7, 0, 1};
        assertArrayEquals(expected, freeze(list));
    }
}
