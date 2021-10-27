package leetcode.lc100_same_tree;

import org.junit.jupiter.api.Test;

import leetcode.lc100_same_tree.LC100SameTree.TreeNode;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public final class LC100SameTreeTests {
    @Test
    public void testDifferent() throws Exception {
        TreeNode an0 = new TreeNode(0);
        TreeNode bn0 = new TreeNode(0);
        TreeNode an1 = new TreeNode(1);
        TreeNode bn1 = new TreeNode(1);
        TreeNode an2 = new TreeNode(2);
        TreeNode bn2 = new TreeNode(3);
        an0.left = an1;
        an0.right = an2;
        bn0.left = bn1;
        bn0.right = bn2;
        an1.left = null;
        an2.right = null;
        bn1.left = null;
        bn2.right = null;
        assertFalse(new LC100SameTree().isSameTree(an0, bn0));
    }

    @Test
    public void testSame() throws Exception {
        TreeNode an0 = new TreeNode(0);
        TreeNode bn0 = new TreeNode(0);
        TreeNode an1 = new TreeNode(1);
        TreeNode bn1 = new TreeNode(1);
        TreeNode an2 = new TreeNode(2);
        TreeNode bn2 = new TreeNode(2);
        an0.left = an1;
        an0.right = an2;
        bn0.left = bn1;
        bn0.right = bn2;
        an1.left = null;
        an2.right = null;
        bn1.left = null;
        bn2.right = null;
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
