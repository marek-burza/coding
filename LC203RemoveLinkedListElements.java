package leetcode;

/**
 * https://leetcode.com/problems/remove-linked-list-elements/
 */
public class LC203RemoveLinkedListElements {
    public class ListNode {
        public int val;
        public ListNode next;

        public ListNode(int x) {
            val = x;
        }
    }

    public ListNode convert(int[] array) {
        ListNode list = null;
        ListNode last = null;
        for (int i = 0; i < array.length; i++) {
            ListNode node = new ListNode(array[i]);
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

    public void print(ListNode list) {
        while (list != null) {
            System.out.print(list.val + " ");
            list = list.next;
        }
        System.out.println();
    }

    public ListNode removeElements(ListNode head, int val) {
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

    public static void main(String[] arguments) {
        LC203RemoveLinkedListElements eraser = new LC203RemoveLinkedListElements();
        int[] array = {6, 1, 2, 3, 4, 5, 6, 7, 6};
        ListNode list = eraser.convert(array);
        eraser.print(list);
        eraser.print(eraser.removeElements(list, 6));
    }
}
