package leetcode.lc116_populating_next_right_pointers_in_each_node;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/populating-next-right-pointers-in-each-node/
 * #medium
 */
public final class LC116PopulatingNextRightPointersInEachNode {
    public static class TreeLinkNode {
        int val;
        TreeLinkNode left;
        TreeLinkNode right;
        TreeLinkNode next;

        TreeLinkNode(final int x) {
            val = x;
        }
    }

    public void connect(final TreeLinkNode root) {
        List<TreeLinkNode> current = new ArrayList<>();
        if (root != null) {
            current.add(root);
        }
        while (current.size() != 0) {
            List<TreeLinkNode> future = new ArrayList<>();
            int size = current.size();
            for (int i = 0; i < size; i++) {
                TreeLinkNode node = current.get(i);
                if (node.left != null) {
                    future.add(node.left);
                }
                if (node.right != null) {
                    future.add(node.right);
                }
                if (i < size - 1) {
                    node.next = current.get(i + 1);
                }
            }
            current = future;
        }
    }
}
