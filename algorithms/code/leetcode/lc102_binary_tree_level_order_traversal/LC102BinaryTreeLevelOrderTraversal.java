package leetcode.lc102_binary_tree_level_order_traversal;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/binary-tree-level-order-traversal/
 * #medium
 */
public final class LC102BinaryTreeLevelOrderTraversal {
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(final int x) {
            val = x;
        }
    }

    private static class AnnotatedNode {
        public TreeNode node;
        public int depth;

        AnnotatedNode(final TreeNode nodeValue, final int depthValue) {
            node = nodeValue;
            depth = depthValue;
        }
    }

    public List<List<Integer>> levelOrder(final TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        List<AnnotatedNode> queue = new ArrayList<>();
        if (root != null) {
            queue.add(new AnnotatedNode(root, 1));
        }
        int depth = 0;
        List<Integer> line = null;
        while (queue.size() > 0) {
            AnnotatedNode annotated = queue.remove(0);
            if (depth != annotated.depth) {
                depth = annotated.depth;
                line = new ArrayList<>();
                result.add(line);
            }
            // if (line != null) {
            line.add(annotated.node.val);
            // }
            if (annotated.node.left != null) {
                queue.add(new AnnotatedNode(annotated.node.left, annotated.depth + 1));
            }
            if (annotated.node.right != null) {
                queue.add(new AnnotatedNode(annotated.node.right, annotated.depth + 1));
            }
        }
        return result;
    }
}
