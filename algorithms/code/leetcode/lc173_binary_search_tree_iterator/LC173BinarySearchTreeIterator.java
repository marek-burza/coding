package leetcode.lc173_binary_search_tree_iterator;

import java.util.Stack;

/**
 * https://leetcode.com/problems/binary-search-tree-iterator/
 * #medium
 */
public final class LC173BinarySearchTreeIterator {
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(final int x) {
            val = x;
        }
    }

    public static class BSTIterator {
        private Stack<TreeNode> stack = new Stack<>();

        public BSTIterator(final TreeNode rootValue) {
            TreeNode root = rootValue;
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
        }

        /**
         * @return whether we have a next smallest number
         */
        public boolean hasNext() {
            return stack.size() != 0;
        }

        /**
         * @return the next smallest number
         */
        public int next() {
            TreeNode node = stack.pop();
            int result = node.val;
            node = node.right;
            while (node != null) {
                stack.push(node);
                node = node.left;
            }
            return result;
        }
    }
}
