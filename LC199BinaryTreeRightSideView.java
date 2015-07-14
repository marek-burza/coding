package leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/binary-tree-right-side-view/
 */
public class LC199BinaryTreeRightSideView {
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public void rightSideView(TreeNode root, int level, List<Integer> list) {
        if (root != null) {
            level++;
            if (level > list.size()) {
                list.add(root.val);
            }
            rightSideView(root.right, level, list);
            rightSideView(root.left, level, list);
        }
    }

    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        rightSideView(root, 0, list);
        return list;
    }

    public static void main(String[] arguments) {
        LC199BinaryTreeRightSideView solution = new LC199BinaryTreeRightSideView();
        TreeNode n1 = new TreeNode(1);
        TreeNode n2 = new TreeNode(2);
        TreeNode n3 = new TreeNode(3);
        TreeNode n4 = new TreeNode(4);
        TreeNode n5 = new TreeNode(5);
        TreeNode n6 = new TreeNode(6);
        n1.left = n2;
        n1.right = n3;
        n2.left = null;
        n2.right = n5;
        n3.left = null;
        n3.right = n4;
        n4.left = null;
        n4.right = null;
        n5.left = n6;
        n5.right = null;
        n6.left = null;
        n6.right = null;
        System.out.println(solution.rightSideView(n1));
    }
}
