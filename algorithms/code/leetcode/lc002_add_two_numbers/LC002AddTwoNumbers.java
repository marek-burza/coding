package leetcode.lc002_add_two_numbers;

/**
 * https://leetcode.com/problems/add-two-numbers/
 * #medium
 */
public final class LC002AddTwoNumbers {
    public static class ListNode {
        int val;
        ListNode next;

        ListNode(final int x) {
            val = x;
        }
    }

    public ListNode addTwoNumbers(final ListNode l1, final ListNode l2) {
        ListNode n1 = l1;
        ListNode n2 = l2;
        ListNode handle = new ListNode(0);
        handle.next = null;
        ListNode tail = handle;
        int carry = 0;
        int value1;
        int value2;
        int sum;
        while (n1 != null || n2 != null || carry != 0) {
            if (n1 == null) {
                value1 = 0;
            } else {
                value1 = n1.val;
                n1 = n1.next;
            }
            if (n2 == null) {
                value2 = 0;
            } else {
                value2 = n2.val;
                n2 = n2.next;
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
