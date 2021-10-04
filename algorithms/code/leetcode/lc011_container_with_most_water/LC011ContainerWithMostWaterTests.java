package leetcode.lc011_container_with_most_water;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public final class LC011ContainerWithMostWaterTests {
    @Test
    public void test121() throws Exception {
        LC011ContainerWithMostWater solution;
        solution = new LC011ContainerWithMostWater();
        int[] test = { 1, 2, 1 };
        assertEquals(2, solution.maxArea(test));
    }

    @Test
    public void test1352() throws Exception {
        LC011ContainerWithMostWater solution;
        solution = new LC011ContainerWithMostWater();
        int[] test = { 1, 3, 5, 2 };
        assertEquals(4, solution.maxArea(test));
    }

    @Test
    public void testOversized() throws Exception {
        LC011ContainerWithMostWater solution;
        solution = new LC011ContainerWithMostWater();
        int[] test = new int[15000];
        for (int i = 0; i < 15000; i++) {
            test[i] = i + 1;
        }
        assertEquals(56250000, solution.maxArea(test));
    }

    @Test
    public void testHuh() throws Exception {
        LC011ContainerWithMostWater solution;
        solution = new LC011ContainerWithMostWater();
        int[] test = { 1, 2, 1, 15, 15, 1, 2, 1 };
        assertEquals(15, solution.maxArea(test));
    }
}
