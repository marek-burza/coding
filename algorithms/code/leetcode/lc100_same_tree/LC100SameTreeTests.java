package leetcode.lc100_same_tree;

import org.junit.jupiter.api.Test;

import leetcode.lc100_same_tree.LC100SameTree.TreeNode;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public final class LC100SameTreeTests {
    private TreeNode exampleTree() {
        TreeNode tree0 = new TreeNode(0);
        TreeNode tree1 = new TreeNode(1);
        TreeNode tree2 = new TreeNode(2);
        tree0.left = tree1;
        tree0.right = tree2;
        tree1.left = null;
        tree2.right = null;
        return tree0;
    }

    @Test
    public void testDifferent() throws Exception {
        TreeNode an0 = exampleTree();
        TreeNode bn0 = exampleTree();
        bn0.right.val = -bn0.right.val;
        assertFalse(new LC100SameTree().isSameTree(an0, bn0));
    }

    @Test
    public void testSame() throws Exception {
        TreeNode an0 = exampleTree();
        TreeNode bn0 = exampleTree();
        assertTrue(new LC100SameTree().isSameTree(an0, bn0));
    }

    @Test
    public void testOneEmpty() throws Exception {
        TreeNode tree = new TreeNode(0);
        assertFalse(new LC100SameTree().isSameTree(tree, null));
        assertFalse(new LC100SameTree().isSameTree(null, tree));
    }

    @Test
    public void testBothEmpty() throws Exception {
        assertTrue(new LC100SameTree().isSameTree(null, null));
    }

    @Test
    public void testLeft() throws Exception {
        TreeNode an0 = new TreeNode(0);
        TreeNode bn0 = new TreeNode(0);
        TreeNode an1 = new TreeNode(1);
        an0.left = an1;
        assertFalse(new LC100SameTree().isSameTree(an0, bn0));
    }

    @Test
    public void testRight() throws Exception {
        TreeNode an0 = new TreeNode(0);
        TreeNode bn0 = new TreeNode(0);
        TreeNode an1 = new TreeNode(1);
        an0.right = an1;
        assertFalse(new LC100SameTree().isSameTree(an0, bn0));
    }
}
