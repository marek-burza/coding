package leetcode;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * https://leetcode.com/problems/binary-tree-level-order-traversal/
 */
public class LC102BinaryTreeLevelOrderTraversal {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    private class Solution {
        private class AnnotatedNode {
            public TreeNode node;
            public int depth;

            public AnnotatedNode(TreeNode node, int depth) {
                this.node = node;
                this.depth = depth;
            }
        }

        public List<List<Integer>> levelOrder(TreeNode root) {
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
                if (line != null) {
                    line.add(annotated.node.val);
                }
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
        int[][] expected = {{3}, {9, 20}, {15, 7}};
        List<List<Integer>> result = new Solution().levelOrder(n3);
        assertEquals(expected.length, result.size());
        for (int i = 0; i < expected.length; i++) {
            assertEquals(expected[i].length, result.get(i).size());
            for (int j = 0; j < expected[i].length; j++) {
                assertEquals(expected[i][j], result.get(i).get(j).intValue());
            }
        }
    }
}
