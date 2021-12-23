package leetcode.lc331_verify_preorder_serialization_of_a_binary_tree;

import java.util.Stack;

/**
 * https://leetcode.com/problems/verify-preorder-serialization-of-a-binary-tree/
 * #medium
 */
public final class LC331VerifyPreorderSerializationOfABinaryTree {
    public boolean isValidSerialization(final String preorder) {
        if (null == preorder || preorder.length() == 0) {
            return false;
        }
        String[] items = preorder.split(",");
        Stack<Integer> kids = new Stack<>();
        kids.push(1);
        for (String node : items) {
            while (kids.peek() == 2) {
                kids.pop();
                if (kids.size() == 0) {
                    return false;
                }
            }
            kids.push(kids.pop() + 1);
            if (!node.equals("#")) {
                kids.push(0);
            }
        }
        while (kids.size() != 0 && kids.peek() == 2) {
            kids.pop();
        }
        return kids.size() == 0;
    }
}
