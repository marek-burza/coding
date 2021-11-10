package leetcode.lc350_intersection_of_two_arrays_ii;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public final class LC350IntersectionOfTwoArraysIITests {
    @Test
    public void testExample() throws Exception {
        int[] expected = { 2, 2 };
        int[] result = new LC350IntersectionOfTwoArraysII().intersect(new int[] { 1, 2, 2, 1 }, new int[] { 2, 2 });
        Arrays.sort(result);
        assertArrayEquals(expected, result);
    }

    @Test
    public void testExampleFlipped() throws Exception {
        int[] expected = { 2, 2 };
        int[] result = new LC350IntersectionOfTwoArraysII().intersect(new int[] { 2, 2 }, new int[] { 1, 2, 2, 1 });
        Arrays.sort(result);
        assertArrayEquals(expected, result);
    }

    @Test
    public void test1And1() throws Exception {
        int[] expected = { 1 };
        int[] result = new LC350IntersectionOfTwoArraysII().intersect(new int[] { 1 }, new int[] { 1 });
        Arrays.sort(result);
        assertArrayEquals(expected, result);
    }

    @Test
    public void test12And11() throws Exception {
        int[] expected = { 1 };
        int[] result = new LC350IntersectionOfTwoArraysII().intersect(new int[] { 1, 2 }, new int[] { 1, 1 });
        Arrays.sort(result);
        assertArrayEquals(expected, result);
    }

    @Test
    public void test495And94984() throws Exception {
        int[] expected = { 4, 5, 9 };
        int[] result = new LC350IntersectionOfTwoArraysII().intersect(new int[] { 4, 9, 5 },
                new int[] { 9, 4, 9, 8, 5 });
        Arrays.sort(result);
        assertArrayEquals(expected, result);
    }
}
