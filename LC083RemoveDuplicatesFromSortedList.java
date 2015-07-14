package leetcode;

/**
 * https://leetcode.com/problems/remove-duplicates-from-sorted-list/
 */
public class LC083RemoveDuplicatesFromSortedList {
    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public ListNode deleteDuplicates(ListNode head) {
        ListNode anchor = head;
        while (head != null) {
            while (head.next != null && head.val == head.next.val) {
                head.next = head.next.next;
            }
            head = head.next;
        }
        return anchor;
    }

    private String toString(ListNode list) {
        StringBuffer buffer = new StringBuffer();
        while (list != null) {
            buffer.append(list.val + " ->");
            list = list.next;
        }
        return buffer.toString();
    }

    public static void main(String[] arguments) {
        LC083RemoveDuplicatesFromSortedList solution = new LC083RemoveDuplicatesFromSortedList();
        ListNode n1a = new ListNode(1);
        ListNode n1b = new ListNode(1);
        ListNode n2 = new ListNode(2);
        ListNode n3a = new ListNode(3);
        ListNode n3b = new ListNode(3);
        n1a.next = n1b;
        n1b.next = n2;
        n2.next = null;
        System.out.println(solution.toString(solution.deleteDuplicates(n1a)));
        n1a.next = n1b;
        n1b.next = n2;
        n2.next = n3a;
        n3a.next = n3b;
        n3b.next = null;
        System.out.println(solution.toString(solution.deleteDuplicates(n1a)));
    }
}
