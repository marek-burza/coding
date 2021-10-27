package leetcode.lc101_symmetric_tree;

import org.junit.jupiter.api.Test;

import leetcode.lc101_symmetric_tree.LC101SymmetricTree.TreeNode;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public final class LC101SymmetricTreeTests {
    @Test
    public void testSymmetric() throws Exception {
        TreeNode n0 = new TreeNode(0);
        TreeNode n1a = new TreeNode(1);
        TreeNode n1b = new TreeNode(1);
        n0.left = n1a;
        n0.right = n1b;
        n1a.left = null;
        n1a.right = null;
        n1b.left = null;
        n1b.right = null;
        assertTrue(new LC101SymmetricTree().isSymmetric(n0));
    }

    @Test
    public void testAsymmetric() throws Exception {
        TreeNode n0 = new TreeNode(0);
        TreeNode n1 = new TreeNode(1);
        TreeNode n2 = new TreeNode(2);
        n0.left = n1;
        n0.right = n2;
        n1.left = null;
        n1.right = null;
        n2.left = null;
        n2.right = null;
        assertFalse(new LC101SymmetricTree().isSymmetric(n0));
    }

    @Test
    public void testEmpty() throws Exception {
        assertTrue(new LC101SymmetricTree().isSymmetric(null));
    }

    @Test
    public void testLeft() throws Exception {
        TreeNode an0 = new TreeNode(0);
        TreeNode an1 = new TreeNode(1);
        an0.left = an1;
        assertFalse(new LC101SymmetricTree().isSymmetric(an0));
    }

    @Test
    public void testRight() throws Exception {
        TreeNode an0 = new TreeNode(0);
        TreeNode an1 = new TreeNode(1);
        an0.right = an1;
        assertFalse(new LC101SymmetricTree().isSymmetric(an0));
    }

    @Test
    public void testOther() throws Exception {
        TreeNode n2 = new TreeNode(2);
        TreeNode n3l = new TreeNode(3);
        TreeNode n3r = new TreeNode(3);
        TreeNode n4ll = new TreeNode(4);
        TreeNode n5 = new TreeNode(5);
        TreeNode n4rr = new TreeNode(4);
        n2.left = n3l;
        n2.right = n3r;
        n3l.left = n4ll;
        n3l.right = n5;
        n3r.right = n4rr;
        assertFalse(new LC101SymmetricTree().isSymmetric(n2));
    }
}
