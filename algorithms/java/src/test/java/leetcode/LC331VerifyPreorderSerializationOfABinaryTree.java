package leetcode.lc331_verify_preorder_serialization_of_a_binary_tree;

import org.junit.jupiter.api.Test;


import java.util.Stack;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * https://leetcode.com/problems/verify-preorder-serialization-of-a-binary-tree/
 * #medium
 */
public final class LC331VerifyPreorderSerializationOfABinaryTree {
    public final class Solution {
        public boolean isValidSerialization(final String preorder) {
            if (null == preorder || preorder.length() == 0) return false;
            String[] items = preorder.split(",");
            Stack<Integer> kids = new Stack<>();
            kids.push(1);
            for (String node : items) {
                while (kids.peek() == 2) {
                    kids.pop();
                    if (kids.size() == 0) return false;
                }
                kids.push(kids.pop() + 1);
                if (!node.equals("#")) {
                    kids.push(0);
                }
            }
            while (kids.size() != 0 && kids.peek() == 2) kids.pop();
            return kids.size() == 0;
        }
    }

    @Test
    public void test_empty() throws Exception {
        assertTrue(new Solution().isValidSerialization("#"));
    }

    @Test
    public void test_empty_but_not_really() throws Exception {
        assertFalse(new Solution().isValidSerialization("#1"));
    }

    @Test
    public void test_example_1() throws Exception {
        assertTrue(new Solution().isValidSerialization("9,3,4,#,#,1,#,#,2,#,6,#,#"));
    }

    @Test
    public void test_example_2() throws Exception {
        assertFalse(new Solution().isValidSerialization("1,#"));
    }

    @Test
    public void test_example_3() throws Exception {
        assertFalse(new Solution().isValidSerialization("9,#,#,1"));
    }
}
