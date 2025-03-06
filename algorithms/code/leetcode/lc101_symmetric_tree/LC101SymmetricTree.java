package leetcode.lc101_symmetric_tree;

import java.util.ArrayList;

/**
 * https://leetcode.com/problems/symmetric-tree/ #easy
 */
public final class LC101SymmetricTree {
    public static class TreeNode {
        public int val;
        public TreeNode left;
        public TreeNode right;

        TreeNode(final int x) {
            val = x;
        }
    }

    private static class TreeNodePair {
        public TreeNode node1;
        public TreeNode node2;

        TreeNodePair(final TreeNode node1Value, final TreeNode node2Value) {
            node1 = node1Value;
            node2 = node2Value;
        }
    }

    private enum NullCheckResult {
        ALL_NULL, SOME_NULL, NONE_NULL
    }

    private static NullCheckResult nullCheck(final TreeNode tree1, final TreeNode tree2) {
        boolean allNull = tree1 == null && tree2 == null;
        boolean noneNull = tree1 != null && tree2 != null;
        boolean someNull = !allNull && !noneNull;
        if (allNull) {
            return NullCheckResult.ALL_NULL;
        }
        if (someNull) {
            return NullCheckResult.SOME_NULL;
        }
        return NullCheckResult.NONE_NULL;
    }

    public boolean isSymmetric(final TreeNode root) {
        if (root == null) {
            return true;
        }
        ArrayList<TreeNodePair> queue = new ArrayList<>();
        queue.add(new TreeNodePair(root, root));
        while (queue.size() > 0) {
            TreeNodePair pair = queue.remove(0);
            TreeNode node1 = pair.node1;
            TreeNode node2 = pair.node2;
            if (node1.val != node2.val) {
                return false;
            }
            NullCheckResult checkLeftToRight = nullCheck(node1.left, node2.right);
            if (checkLeftToRight == NullCheckResult.NONE_NULL) {
                queue.add(new TreeNodePair(node1.left, node2.right));
            } else {
                if (checkLeftToRight == NullCheckResult.SOME_NULL) {
                    return false;
                }
            }
            NullCheckResult checkRightToLeft = nullCheck(node1.right, node2.left);
            if (checkRightToLeft == NullCheckResult.NONE_NULL) {
                queue.add(new TreeNodePair(node1.right, node2.left));
            } else {
                if (checkRightToLeft == NullCheckResult.SOME_NULL) {
                    return false;
                }
            }
        }
        return true;
    }
}
package leetcode.lc101_symmetric_tree;

import org.junit.jupiter.api.Test;

import leetcode.lc101_symmetric_tree.LC101SymmetricTree.TreeNode;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public final class LC101SymmetricTreeTests {
    @Test
    public void testSymmetric() throws Exception {
        TreeNode n0 = new TreeNode(0);
        TreeNode n1a = new TreeNode(1);
        TreeNode n1b = new TreeNode(1);
        n0.left = n1a;
        n0.right = n1b;
        n1a.left = null;
        n1a.right = null;
        n1b.left = null;
        n1b.right = null;
        assertTrue(new LC101SymmetricTree().isSymmetric(n0));
    }

    @Test
    public void testAsymmetric() throws Exception {
        TreeNode n0 = new TreeNode(0);
        TreeNode n1 = new TreeNode(1);
        TreeNode n2 = new TreeNode(2);
        n0.left = n1;
        n0.right = n2;
        n1.left = null;
        n1.right = null;
        n2.left = null;
        n2.right = null;
        assertFalse(new LC101SymmetricTree().isSymmetric(n0));
    }

    @Test
    public void testEmpty() throws Exception {
        assertTrue(new LC101SymmetricTree().isSymmetric(null));
    }

    @Test
    public void testLeft() throws Exception {
        TreeNode an0 = new TreeNode(0);
        TreeNode an1 = new TreeNode(1);
        an0.left = an1;
        assertFalse(new LC101SymmetricTree().isSymmetric(an0));
    }

    @Test
    public void testRight() throws Exception {
        TreeNode an0 = new TreeNode(0);
        TreeNode an1 = new TreeNode(1);
        an0.right = an1;
        assertFalse(new LC101SymmetricTree().isSymmetric(an0));
    }

    @Test
    public void testOther() throws Exception {
        TreeNode n2 = new TreeNode(2);
        TreeNode n3l = new TreeNode(3);
        TreeNode n3r = new TreeNode(3);
        TreeNode n4ll = new TreeNode(4);
        TreeNode n5 = new TreeNode(5);
        TreeNode n4rr = new TreeNode(4);
        n2.left = n3l;
        n2.right = n3r;
        n3l.left = n4ll;
        n3l.right = n5;
        n3r.right = n4rr;
        assertFalse(new LC101SymmetricTree().isSymmetric(n2));
    }
}
