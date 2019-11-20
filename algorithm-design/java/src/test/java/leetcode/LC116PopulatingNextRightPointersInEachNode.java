package leetcode;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * https://leetcode.com/problems/populating-next-right-pointers-in-each-node/
 */
public class LC116PopulatingNextRightPointersInEachNode {
    public class TreeLinkNode {
        int val;
        TreeLinkNode left, right, next;

        TreeLinkNode(int x) {
            val = x;
        }
    }

    public class Solution {
        public void connect(TreeLinkNode root) {
            List<TreeLinkNode> current = new ArrayList();
            if (root != null) {
                current.add(root);
            }
            while (current.size() != 0) {
                List<TreeLinkNode> future = new ArrayList();
                int size = current.size();
                for (int i = 0; i < size; i++) {
                    TreeLinkNode node = current.get(i);
                    if (node.left != null) future.add(node.left);
                    if (node.right != null) future.add(node.right);
                    if (i < size - 1) {
                        node.next = current.get(i + 1);
                    }
                }
                current = future;
            }
        }
    }

    @Test
    public void test_empty() throws Exception {
        new Solution().connect(null);
    }

    @Test
    public void test_example() throws Exception {
        TreeLinkNode n4 = new TreeLinkNode(4);
        TreeLinkNode n5 = new TreeLinkNode(5);
        TreeLinkNode n6 = new TreeLinkNode(6);
        TreeLinkNode n7 = new TreeLinkNode(7);
        TreeLinkNode n2 = new TreeLinkNode(2);
        n2.left = n4;
        n2.right = n5;
        TreeLinkNode n3 = new TreeLinkNode(3);
        n3.left = n6;
        n3.right = n7;
        TreeLinkNode n1 = new TreeLinkNode(1);
        n1.left = n2;
        n1.right = n3;

        new Solution().connect(n1);

        assertEquals(1, n1.val);
        assertEquals(null, n1.next);
        assertEquals(n2, n1.left);
        assertEquals(n3, n1.right);

        assertEquals(2, n2.val);
        assertEquals(n3, n2.next);
        assertEquals(n4, n2.left);
        assertEquals(n5, n2.right);

        assertEquals(3, n3.val);
        assertEquals(null, n3.next);
        assertEquals(n6, n3.left);
        assertEquals(n7, n3.right);

        assertEquals(4, n4.val);
        assertEquals(n5, n4.next);
        assertEquals(null, n4.left);
        assertEquals(null, n4.right);

        assertEquals(5, n5.val);
        assertEquals(n6, n5.next);
        assertEquals(null, n5.left);
        assertEquals(null, n5.right);

        assertEquals(6, n6.val);
        assertEquals(n7, n6.next);
        assertEquals(null, n6.left);
        assertEquals(null, n6.right);

        assertEquals(7, n7.val);
        assertEquals(null, n7.next);
        assertEquals(null, n7.left);
        assertEquals(null, n7.right);
    }
}
