package leetcode;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * https://leetcode.com/problems/binary-tree-paths/
 */
public class LC257BinaryTreePaths {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public class Solution {
        public void binaryTreePaths(TreeNode root, String prefix, List<String> result) {
            prefix += ((prefix.length() == 0) ? "" : "->") + root.val;
            if (root.left == null && root.right == null) {
                result.add(prefix);
            } else {
                if (root.left != null) {
                    binaryTreePaths(root.left, prefix, result);
                }
                if (root.right != null) {
                    binaryTreePaths(root.right, prefix, result);
                }
            }
        }

        public List<String> binaryTreePaths(TreeNode root) {
            List<String> result = new ArrayList<>();
            if (root != null) {
                binaryTreePaths(root, "", result);
            }
            return result;
        }
    }

    @Test
    public void test_example() throws Exception {
        TreeNode n1 = new TreeNode(1);
        TreeNode n2 = new TreeNode(2);
        TreeNode n3 = new TreeNode(3);
        TreeNode n5 = new TreeNode(5);
        n1.left = n2;
        n1.right = n3;
        n2.right = n5;
        String[] expected = {"1->2->5", "1->3"};
        List<String> result = new Solution().binaryTreePaths(n1);
        Collections.sort(result);
        assertEquals(expected.length, result.size());
        for (int i = 0; i < expected.length; i++) {
            assertEquals(expected[i], result.get(i));
        }
    }
}
