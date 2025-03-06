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
package leetcode.lc331_verify_preorder_serialization_of_a_binary_tree;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public final class LC331VerifyPreorderSerializationOfABinaryTreeTests {
    @Test
    public void testEmpty() throws Exception {
        assertTrue(new LC331VerifyPreorderSerializationOfABinaryTree().isValidSerialization("#"));
    }

    @Test
    public void testEmptyButNotReally() throws Exception {
        assertFalse(new LC331VerifyPreorderSerializationOfABinaryTree().isValidSerialization("#1"));
    }

    @Test
    public void testExample1() throws Exception {
        assertTrue(new LC331VerifyPreorderSerializationOfABinaryTree().isValidSerialization(
                "9,3,4,#,#,1,#,#,2,#,6,#,#"));
    }

    @Test
    public void testExample2() throws Exception {
        assertFalse(new LC331VerifyPreorderSerializationOfABinaryTree().isValidSerialization("1,#"));
    }

    @Test
    public void testExample3() throws Exception {
        assertFalse(new LC331VerifyPreorderSerializationOfABinaryTree().isValidSerialization("9,#,#,1"));
    }

    @Test
    public void testNothing() throws Exception {
        assertFalse(new LC331VerifyPreorderSerializationOfABinaryTree().isValidSerialization(null));
        assertFalse(new LC331VerifyPreorderSerializationOfABinaryTree().isValidSerialization(""));
    }
}
