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
