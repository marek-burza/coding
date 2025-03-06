package leetcode.lc100_same_tree;

import java.util.ArrayList;

/**
 * https://leetcode.com/problems/same-tree/ #easy
 */
public final class LC100SameTree {
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

    public boolean isSameTree(final TreeNode tree1, final TreeNode tree2) {
        NullCheckResult check = nullCheck(tree1, tree2);
        if (check != NullCheckResult.NONE_NULL) {
            return check == NullCheckResult.ALL_NULL;
        }
        ArrayList<TreeNodePair> queue = new ArrayList<>();
        queue.add(new TreeNodePair(tree1, tree2));
        while (queue.size() > 0) {
            TreeNodePair pair = queue.remove(0);
            TreeNode node1 = pair.node1;
            TreeNode node2 = pair.node2;
            if (node1.val != node2.val) {
                return false;
            }
            NullCheckResult checkLeft = nullCheck(node1.left, node2.left);
            if (checkLeft == NullCheckResult.NONE_NULL) {
                queue.add(new TreeNodePair(node1.left, node2.left));
            } else {
                if (checkLeft == NullCheckResult.SOME_NULL) {
                    return false;
                }
            }
            NullCheckResult checkRight = nullCheck(node1.right, node2.right);
            if (checkRight == NullCheckResult.NONE_NULL) {
                queue.add(new TreeNodePair(node1.right, node2.right));
            } else {
                if (checkRight == NullCheckResult.SOME_NULL) {
                    return false;
                }
            }
        }
        return true;
    }
}
package leetcode.lc100_same_tree;

import org.junit.jupiter.api.Test;

import leetcode.lc100_same_tree.LC100SameTree.TreeNode;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public final class LC100SameTreeTests {
    private TreeNode exampleTree() {
        TreeNode tree0 = new TreeNode(0);
        TreeNode tree1 = new TreeNode(1);
        TreeNode tree2 = new TreeNode(2);
        tree0.left = tree1;
        tree0.right = tree2;
        tree1.left = null;
        tree2.right = null;
        return tree0;
    }

    @Test
    public void testDifferent() throws Exception {
        TreeNode an0 = exampleTree();
        TreeNode bn0 = exampleTree();
        bn0.right.val = -bn0.right.val;
        assertFalse(new LC100SameTree().isSameTree(an0, bn0));
    }

    @Test
    public void testSame() throws Exception {
        TreeNode an0 = exampleTree();
        TreeNode bn0 = exampleTree();
        assertTrue(new LC100SameTree().isSameTree(an0, bn0));
    }

    @Test
    public void testOneEmpty() throws Exception {
        TreeNode tree = new TreeNode(0);
        assertFalse(new LC100SameTree().isSameTree(tree, null));
        assertFalse(new LC100SameTree().isSameTree(null, tree));
    }

    @Test
    public void testBothEmpty() throws Exception {
        assertTrue(new LC100SameTree().isSameTree(null, null));
    }

    @Test
    public void testLeft() throws Exception {
        TreeNode an0 = new TreeNode(0);
        TreeNode bn0 = new TreeNode(0);
        TreeNode an1 = new TreeNode(1);
        an0.left = an1;
        assertFalse(new LC100SameTree().isSameTree(an0, bn0));
    }

    @Test
    public void testRight() throws Exception {
        TreeNode an0 = new TreeNode(0);
        TreeNode bn0 = new TreeNode(0);
        TreeNode an1 = new TreeNode(1);
        an0.right = an1;
        assertFalse(new LC100SameTree().isSameTree(an0, bn0));
    }
}
