package leetcode.lc328_odd_even_linked_list;

/**
 * https://leetcode.com/problems/odd-even-linked-list/
 * #medium
 */
public final class LC328OddEvenLinkedList {
    public static class ListNode {
        int val;
        ListNode next;

        ListNode(final int x) {
            val = x;
        }
    }

    public ListNode oddEvenList(final ListNode headValue) {
        ListNode head = headValue;
        ListNode evenHead = new ListNode(0);
        ListNode oddHead = new ListNode(0);
        ListNode evenTail = evenHead;
        ListNode oddTail = oddHead;
        boolean odd = true;
        while (head != null) {
            if (odd) {
                oddTail.next = head;
                oddTail = oddTail.next;
                odd = false;
            } else {
                evenTail.next = head;
                evenTail = evenTail.next;
                odd = true;
            }
            head = head.next;
        }
        evenTail.next = null;
        oddTail.next = evenHead.next;
        return oddHead.next;
    }
}
