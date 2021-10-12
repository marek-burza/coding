package leetcode;

import org.junit.jupiter.api.Test;


import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * https://leetcode.com/problems/convert-sorted-list-to-binary-search-tree/
 * #medium
 */
public final class LC109ConvertSortedListToBinarySearchTree {
    public final class ListNode {
        int val;
        ListNode next;

        ListNode(final int x) {
            val = x;
        }
    }

    public final class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(final int x) {
            val = x;
        }
    }

    public final class Solution {
        private int length(final ListNode head) {
            int count = 0;
            while (head != null) {
                count++;
                head = head.next;
            }
            return count;
        }

        private TreeNode generate(final ListNode head, final int length) {
            if (head == null || length <= 0) return null;
            int half = length >> 1;
            ListNode middle = head;
            for (int i = 0; i < half; i++) {
                middle = middle.next;
            }
            TreeNode root = new TreeNode(middle.val);
            root.left = generate(head, half);
            root.right = generate(middle.next, length - half - 1);
            return root;
        }

        public TreeNode sortedListToBST(final ListNode head) {
            return generate(head, length(head));
        }
    }

    private class MinMax {
        public int min = Integer.MAX_VALUE;
        public int max = Integer.MIN_VALUE;
    }

    private void depth(final TreeNode root, final int level, final MinMax depths) {
        if (root == null) {
            depths.max = depths.max < level ? level : depths.max;
            depths.min = depths.min > level ? level : depths.min;
        } else {
            depth(root.left, level + 1, depths);
            depth(root.right, level + 1, depths);
        }
    }

    private ListNode example(final int limit) {
        Random random = new Random();
        int length = random.nextInt(limit - 1) + 1;
        ListNode list = new ListNode(0);
        ListNode node = list;
        for (int i = 0; i < length; i++) {
            node.next = new ListNode(i + 1);
            node = node.next;
        }
        return list.next;
    }

    private ListNode iterated(final int min, final int max) {
        int[] array = new int[max - min + 1];
        for (int i = 0; i < array.length; i++) {
            array[i] = min + i;
        }
        int length = array.length;
        ListNode list = new ListNode(0);
        ListNode node = list;
        for (int i = 0; i < length; i++) {
            node.next = new ListNode(array[i]);
            node = node.next;
        }
        return list.next;
    }


    public ListNode test(final TreeNode root, final ListNode list) {
        if (root == null) return list;
        list = test(root.left, list);
        assertEquals(list.val, root.val);
        list = list.next;
        list = test(root.right, list);
        return list;
    }

    @Test
    public void test_random() throws Exception {
        ListNode list = example(100);
        TreeNode root = new Solution().sortedListToBST(list);
        MinMax depths = new MinMax();
        depth(root, 0, depths);
        assertEquals(true, depths.max - depths.min < 2);
        assertEquals(null, test(root, list));
    }

    @Test
    public void test_bigger() throws Exception {
        ListNode list = iterated(-999, 15340);
        TreeNode root = new Solution().sortedListToBST(list);
        MinMax depths = new MinMax();
        depth(root, 0, depths);
        assertEquals(true, depths.max - depths.min < 2);
        assertEquals(null, test(root, list));
    }
}
