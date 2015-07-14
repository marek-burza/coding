package leetcode;

import java.util.Random;

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

    public void print(ListNode list) {
        while (list != null) {
            System.out.print(list.val + " ");
            list = list.next;
        }
        System.out.println();
    }

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

    public static void main(String[] arguments) {
        LC206ReverseLinkedList mirror = new LC206ReverseLinkedList();
        ListNode list = mirror.random();
        mirror.print(list);
        mirror.print(mirror.reverseList(list));
    }
}
