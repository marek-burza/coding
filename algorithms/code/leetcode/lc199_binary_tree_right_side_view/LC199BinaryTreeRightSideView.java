package leetcode.lc199_binary_tree_right_side_view;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/binary-tree-right-side-view/
 * #medium
 */
public final class LC199BinaryTreeRightSideView {
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(final int x) {
            val = x;
        }
    }

    public void rightSideView(final TreeNode root, final int levelValue, final List<Integer> list) {
        int level = levelValue;
        if (root != null) {
            level++;
            if (level > list.size()) {
                list.add(root.val);
            }
            rightSideView(root.right, level, list);
            rightSideView(root.left, level, list);
        }
    }

    public List<Integer> rightSideView(final TreeNode root) {
        List<Integer> list = new ArrayList<>();
        rightSideView(root, 0, list);
        return list;
    }
}
