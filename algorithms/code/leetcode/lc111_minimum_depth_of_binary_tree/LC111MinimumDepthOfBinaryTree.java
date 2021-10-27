package leetcode.lc111_minimum_depth_of_binary_tree;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/minimum-depth-of-binary-tree/ #easy
 */
public final class LC111MinimumDepthOfBinaryTree {
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

        AnnotatedNode(final TreeNode nodeHandle, final int depthValue) {
            node = nodeHandle;
            depth = depthValue;
        }
    }

    public int minDepth(final TreeNode root) {
        if (root == null) {
            return 0;
        }
        List<AnnotatedNode> queue = new ArrayList<>();
        queue.add(new AnnotatedNode(root, 1));
        while (true) {
            AnnotatedNode annotated = queue.remove(0);
            if (annotated.node.left == null && annotated.node.right == null) {
                return annotated.depth;
            } else {
                if (annotated.node.left != null) {
                    queue.add(new AnnotatedNode(annotated.node.left, annotated.depth + 1));
                }
                if (annotated.node.right != null) {
                    queue.add(new AnnotatedNode(annotated.node.right, annotated.depth + 1));
                }
            }
        }
    }
}
