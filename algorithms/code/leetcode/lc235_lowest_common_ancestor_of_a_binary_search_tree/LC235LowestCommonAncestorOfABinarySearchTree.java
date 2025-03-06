package leetcode.lc235_lowest_common_ancestor_of_a_binary_search_tree;

/**
 * https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-search-tree/
 * #easy
 */
public final class LC235LowestCommonAncestorOfABinarySearchTree {
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(final int x) {
            val = x;
        }
    }

    public TreeNode lowestCommonAncestor(final TreeNode root, final TreeNode p, final TreeNode q) {
        TreeNode left = null;
        TreeNode right = null;
        if (root.left != null) {
            left = lowestCommonAncestor(root.left, p, q);
        }
        if (root.right != null) {
            right = lowestCommonAncestor(root.right, p, q);
        }
        if (left != null && left != p && left != q) {
            return left;
        }
        if (right != null && right != p && right != q) {
            return right;
        }
        boolean gotP = root == p || left == p || right == p;
        boolean gotQ = root == q || left == q || right == q;
        if (gotP && gotQ) {
            return root;
        }
        if (gotP) {
            return p;
        }
        if (gotQ) {
            return q;
        }
        return null;
    }
}
package leetcode.lc235_lowest_common_ancestor_of_a_binary_search_tree;

import org.junit.jupiter.api.Test;

import leetcode.lc235_lowest_common_ancestor_of_a_binary_search_tree.LC235LowestCommonAncestorOfABinarySearchTree.TreeNode;

import static org.junit.jupiter.api.Assertions.assertEquals;

public final class LC235LowestCommonAncestorOfABinarySearchTreeTests {
    private TreeNode genericExample() {
        TreeNode tree = new TreeNode(6);
        tree.left = new TreeNode(2);
        tree.right = new TreeNode(8);
        tree.left.left = new TreeNode(0);
        tree.left.right = new TreeNode(4);
        return tree;
    }

    @Test
    public void testExample() throws Exception {
        TreeNode tree = genericExample();
        tree.left.right.left = new TreeNode(3);
        tree.left.right.right = new TreeNode(5);
        tree.right.left = new TreeNode(7);
        tree.right.right = new TreeNode(9);
        assertEquals(tree,
                new LC235LowestCommonAncestorOfABinarySearchTree().lowestCommonAncestor(tree, tree.left, tree.right));
        assertEquals(tree.left, new LC235LowestCommonAncestorOfABinarySearchTree().lowestCommonAncestor(tree, tree.left,
                tree.left.right));
    }

    @Test
    public void testExample1() throws Exception {
        TreeNode tree = new TreeNode(5);
        tree.left = new TreeNode(3);
        tree.right = new TreeNode(6);
        tree.left.left = new TreeNode(2);
        tree.left.right = new TreeNode(4);
        tree.left.left.left = new TreeNode(1);
        assertEquals(3, new LC235LowestCommonAncestorOfABinarySearchTree().lowestCommonAncestor(tree,
                tree.left.left.left, tree.left.right).val);
    }

    @Test
    public void testExample2() throws Exception {
        TreeNode tree = genericExample();
        tree.left.left = new TreeNode(7);
        tree.right.right = new TreeNode(9);
        tree.left.right.left = new TreeNode(3);
        tree.left.right.right = new TreeNode(5);
        assertEquals(4, new LC235LowestCommonAncestorOfABinarySearchTree().lowestCommonAncestor(tree,
                tree.left.right.left, tree.left.right.right).val);
    }

    @Test
    public void testExample3() throws Exception {
        TreeNode tree = new TreeNode(2);
        tree.left = new TreeNode(1);
        assertEquals(2,
                new LC235LowestCommonAncestorOfABinarySearchTree().lowestCommonAncestor(tree, tree, tree.left).val);
    }

    @Test
    public void testExample4() throws Exception {
        TreeNode tree = new TreeNode(2);
        tree.right = new TreeNode(3);
        assertEquals(2,
                new LC235LowestCommonAncestorOfABinarySearchTree().lowestCommonAncestor(tree, tree.right, tree).val);
    }
}
