package leetcode;

/**
 * https://leetcode.com/problems/partition-list/
 */
public class LC086PartitionList {
    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    private ListNode build(int[] array) {
        ListNode head = null;
        ListNode tail = null;
        for (int value : array) {
            if (head == null) {
                head = tail = new ListNode(value);
            } else {
                tail.next = new ListNode(value);
                tail = tail.next;
            }
        }
        tail.next = null;
        return head;
    }

    private void print(ListNode head) {
        while (head != null) {
            System.out.print("" + head.val + " ");
            head = head.next;
        }
        System.out.println();
    }

    public ListNode partition(ListNode head, int x) {
        if (head == null) {
            return null;
        }
        ListNode less = new ListNode(0);
        ListNode more = new ListNode(0);
        ListNode before = less;
        ListNode after = more;
        while (head != null) {
            if (head.val < x) {
                less.next = head;
                less = head;
            }
            if (head.val >= x) {
                more.next = head;
                more = head;
            }
            head = head.next;
        }
        less.next = after.next;
        more.next = null;
        return before.next;
    }

    public static void main(String[] arguments) {
        LC086PartitionList splitter = new LC086PartitionList();
        ListNode list = splitter.build(new int[]{1, 4, 3, 2, 5, 2});
        splitter.print(splitter.partition(list, 3));
    }
}
