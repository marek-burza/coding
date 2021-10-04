package leetcode.lc027_remove_element;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public final class LC027RemoveElementTests {
    @Test
    public void test042124234And42() throws Exception {
        LC027RemoveElement solution;
        solution = new LC027RemoveElement();
        int[] nums = { 0, 42, 1, 2, 42, 3, 4 };
        int length = solution.removeElement(nums, 42);
        assertEquals(5, length);
        int[] expected = { 0, 1, 2, 3, 4 };
        assertArrayEquals(expected, Arrays.copyOfRange(nums, 0, length));
    }

    @Test
    public void testNothing() throws Exception {
        LC027RemoveElement solution;
        solution = new LC027RemoveElement();
        int length = solution.removeElement(null, 42);
        assertEquals(0, length);
    }
}
