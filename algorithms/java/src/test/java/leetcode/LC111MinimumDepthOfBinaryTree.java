package leetcode;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * https://leetcode.com/problems/minimum-depth-of-binary-tree/
 * #easy
 */
public class LC111MinimumDepthOfBinaryTree {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public class Solution {
        private class AnnotatedNode {
            public TreeNode node;
            public int depth;

            public AnnotatedNode(TreeNode node, int depth) {
                this.node = node;
                this.depth = depth;
            }
        }

        public int minDepth(TreeNode root) {
            if (root == null) {
                return 0;
            }
            List<AnnotatedNode> queue = new ArrayList<>();
            queue.add(new AnnotatedNode(root, 1));
            while (queue.size() > 0) {
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
            return -1;
        }
    }

    @Test
    public void test_example() throws Exception {
        TreeNode n3 = new TreeNode(3);
        TreeNode n7 = new TreeNode(7);
        TreeNode n9 = new TreeNode(9);
        TreeNode n15 = new TreeNode(15);
        TreeNode n20 = new TreeNode(20);
        n3.left = n9;
        n3.right = n20;
        n20.left = n15;
        n20.right = n7;
        n7.left = n7.right = n9.left = n9.right = n15.left = n15.right = null;
        assertEquals(2, new Solution().minDepth(n3));
    }
}
