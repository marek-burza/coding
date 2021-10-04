package leetcode.lc002_add_two_numbers;

import static leetcode.lc002_add_two_numbers.LC002AddTwoNumbers.ListNode;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public final class LC002AddTwoNumbersTests {
    private ListNode thaw(final int[] array) {
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

    private int[] freeze(final ListNode list) {
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
    public void testExample() throws Exception {
        LC002AddTwoNumbers solution = new LC002AddTwoNumbers();
        int[] array1 = { 2, 4, 3 };
        int[] array2 = { 5, 6, 4 };
        ListNode list = solution.addTwoNumbers(thaw(array1), thaw(array2));
        int[] expected = { 7, 0, 8 };
        assertArrayEquals(expected, freeze(list));
    }

    @Test
    public void testUneven() throws Exception {
        LC002AddTwoNumbers solution = new LC002AddTwoNumbers();
        int[] array1 = { 2, 4 };
        int[] array2 = { 5, 6, 4 };
        ListNode list = solution.addTwoNumbers(thaw(array1), thaw(array2));
        int[] expected = { 7, 0, 5 };
        assertArrayEquals(expected, freeze(list));
    }

    @Test
    public void testCarry() throws Exception {
        LC002AddTwoNumbers solution = new LC002AddTwoNumbers();
        int[] array1 = { 2, 4 };
        int[] array2 = { 5, 6 };
        ListNode list = solution.addTwoNumbers(thaw(array1), thaw(array2));
        int[] expected = { 7, 0, 1 };
        assertArrayEquals(expected, freeze(list));
    }
}
