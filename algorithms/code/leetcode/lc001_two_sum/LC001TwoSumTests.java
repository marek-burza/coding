package leetcode.lc001_two_sum;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class LC001TwoSumTests {
    @Test
    public void test0() {
        assertNull(new LC001TwoSum().twoSum(null, 0));
    }

    @Test
    public void test00() {
        assertNull(new LC001TwoSum().twoSum(new int[] {}, 0));
    }

    @Test
    public void test1() {
        int[] expected = new int[] { 0, 1 };
        int[] result = new LC001TwoSum().twoSum(new int[] { 2, 7, 11, 15 }, 9);
        assertArrayEquals(expected, result);
    }

    @Test
    public void test2() {
        int[] expected = new int[] { 1, 2 };
        int[] result = new LC001TwoSum().twoSum(new int[] { 3, 2, 4 }, 6);
        assertArrayEquals(expected, result);
    }

    @Test
    public void test3() {
        int[] expected = new int[] { 0, 1 };
        int[] result = new LC001TwoSum().twoSum(new int[] { 3, 3 }, 6);
        assertArrayEquals(expected, result);
    }
}
