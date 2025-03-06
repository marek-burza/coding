package leetcode.lc257_binary_tree_paths;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/binary-tree-paths/ #easy
 */
public final class LC257BinaryTreePaths {
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(final int x) {
            val = x;
        }
    }

    public void binaryTreePaths(final TreeNode root, final String prefixValue, final List<String> result) {
        String prefix = prefixValue;
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

    public List<String> binaryTreePaths(final TreeNode root) {
        List<String> result = new ArrayList<>();
        if (root != null) {
            binaryTreePaths(root, "", result);
        }
        return result;
    }
}
package leetcode.lc257_binary_tree_paths;

import org.junit.jupiter.api.Test;

import leetcode.lc257_binary_tree_paths.LC257BinaryTreePaths.TreeNode;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public final class LC257BinaryTreePathsTests {
    private void generic(final TreeNode tree, final String[] expected) throws Exception {
        List<String> result = new LC257BinaryTreePaths().binaryTreePaths(tree);
        Collections.sort(result);
        assertEquals(expected.length, result.size());
        for (int i = 0; i < expected.length; i++) {
            assertEquals(expected[i], result.get(i));
        }
    }

    @Test
    public void testExample() throws Exception {
        TreeNode n1 = new TreeNode(1);
        TreeNode n2 = new TreeNode(2);
        TreeNode n3 = new TreeNode(3);
        TreeNode n5 = new TreeNode(5);
        n1.left = n2;
        n1.right = n3;
        n2.right = n5;
        String[] expected = { "1->2->5", "1->3" };
        generic(n1, expected);
    }

    @Test
    public void testExampleMirrored() throws Exception {
        TreeNode n1 = new TreeNode(1);
        TreeNode n2 = new TreeNode(2);
        TreeNode n3 = new TreeNode(3);
        TreeNode n5 = new TreeNode(5);
        n1.right = n2;
        n1.left = n3;
        n2.left = n5;
        String[] expected = { "1->2->5", "1->3" };
        generic(n1, expected);
    }

    @Test
    public void testNothing() throws Exception {
        assertEquals(0, new LC257BinaryTreePaths().binaryTreePaths(null).size());
    }
}
