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
package leetcode.lc111_minimum_depth_of_binary_tree;

import org.junit.jupiter.api.Test;

import leetcode.lc111_minimum_depth_of_binary_tree.LC111MinimumDepthOfBinaryTree.TreeNode;

import static org.junit.jupiter.api.Assertions.assertEquals;

public final class LC111MinimumDepthOfBinaryTreeTests {
    @Test
    public void testExample() throws Exception {
        TreeNode n3 = new TreeNode(3);
        TreeNode n7 = new TreeNode(7);
        TreeNode n9 = new TreeNode(9);
        TreeNode n15 = new TreeNode(15);
        TreeNode n20 = new TreeNode(20);
        n3.left = n9;
        n3.right = n20;
        n20.left = n15;
        n20.right = n7;
        assertEquals(2, new LC111MinimumDepthOfBinaryTree().minDepth(n3));
    }

    @Test
    public void testLeftNothing() throws Exception {
        TreeNode root = new TreeNode(3);
        TreeNode right = new TreeNode(7);
        root.right = right;
        assertEquals(2, new LC111MinimumDepthOfBinaryTree().minDepth(root));
    }

    @Test
    public void testRightNothing() throws Exception {
        TreeNode root = new TreeNode(3);
        TreeNode left = new TreeNode(7);
        root.left = left;
        assertEquals(2, new LC111MinimumDepthOfBinaryTree().minDepth(root));
    }

    @Test
    public void testNothing() throws Exception {
        assertEquals(0, new TreeNode(0).val);
        assertEquals(0, new LC111MinimumDepthOfBinaryTree().minDepth(null));
    }
}
