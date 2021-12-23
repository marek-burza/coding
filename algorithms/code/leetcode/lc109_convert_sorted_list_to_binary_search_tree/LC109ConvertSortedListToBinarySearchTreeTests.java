package leetcode.lc109_convert_sorted_list_to_binary_search_tree;

import org.junit.jupiter.api.Test;

import leetcode.lc109_convert_sorted_list_to_binary_search_tree.LC109ConvertSortedListToBinarySearchTree.ListNode;
import leetcode.lc109_convert_sorted_list_to_binary_search_tree.LC109ConvertSortedListToBinarySearchTree.TreeNode;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public final class LC109ConvertSortedListToBinarySearchTreeTests {
    private static class MinMax {
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

    public ListNode test(final TreeNode root, final ListNode listValue) {
        ListNode list = listValue;
        if (root == null) {
            return list;
        }
        list = test(root.left, list);
        assertEquals(list.val, root.val);
        list = list.next;
        list = test(root.right, list);
        return list;
    }

    @Test
    public void testBigger() throws Exception {
        ListNode list = iterated(-999, 15340);
        TreeNode root = new LC109ConvertSortedListToBinarySearchTree().sortedListToBST(list);
        MinMax depths = new MinMax();
        depth(root, 0, depths);
        assertTrue(depths.max - depths.min < 2);
        assertEquals(null, test(root, list));
    }
}
