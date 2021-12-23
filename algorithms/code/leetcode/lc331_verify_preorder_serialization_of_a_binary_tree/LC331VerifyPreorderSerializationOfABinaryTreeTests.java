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
