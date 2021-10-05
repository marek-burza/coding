package leetcode;

import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.assertEquals;

/**
 * https://leetcode.com/problems/convert-sorted-list-to-binary-search-tree/
 * #medium
 */
public class LC109ConvertSortedListToBinarySearchTree {
    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public class Solution {
        private int length(ListNode head) {
            int count = 0;
            while (head != null) {
                count++;
                head = head.next;
            }
            return count;
        }

        private TreeNode generate(ListNode head, int length) {
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

        public TreeNode sortedListToBST(ListNode head) {
            return generate(head, length(head));
        }
    }

    private class MinMax {
        public int min = Integer.MAX_VALUE;
        public int max = Integer.MIN_VALUE;
    }

    private void depth(TreeNode root, int level, MinMax depths) {
        if (root == null) {
            depths.max = depths.max < level ? level : depths.max;
            depths.min = depths.min > level ? level : depths.min;
        } else {
            depth(root.left, level + 1, depths);
            depth(root.right, level + 1, depths);
        }
    }

    private ListNode example(int limit) {
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

    private ListNode iterated(int min, int max) {
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


    public ListNode test(TreeNode root, ListNode list) {
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
