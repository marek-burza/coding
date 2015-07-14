package leetcode;

/**
 * https://leetcode.com/problems/remove-nth-node-from-end-of-list/
 */
public class LC019RemoveNthNodeFromEndOfList {
    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public ListNode removeNthFromEnd(ListNode head, int n) {
        // Translate the index number from counted from the back to a one counted from the front
        ListNode node = head;
        while (node != null) {
            n--;
            node = node.next;
        }
        n = -n;
        // Do the deletion
        node = new ListNode(0);
        node.next = head;
        head = node;
        while (head != null) {
            if (n == 0) {
                if (head.next != null) {
                    head.next = head.next.next;
                    break;
                }
            }
            head = head.next;
            n--;
        }
        return node.next;
    }

    public static void print(ListNode head) {
        while (head != null) {
            System.out.println(head.val);
            head = head.next;
        }
    }

    public static void main(String[] arguments) {
        LC019RemoveNthNodeFromEndOfList solution = new LC019RemoveNthNodeFromEndOfList();
        ListNode n1 = new ListNode(1);
        ListNode n2 = new ListNode(2);
        n1.next = n2;
        n2.next = null;
        ListNode n = n1;
        print(n);
        n = solution.removeNthFromEnd(n, 1);
        print(n);
        n = solution.removeNthFromEnd(n, 1);
        print(n);
    }
}
