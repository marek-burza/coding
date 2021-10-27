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
