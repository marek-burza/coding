package leetcode.lc042_trapping_rain_water;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * https://leetcode.com/problems/trapping-rain-water/ #hard
 */
public class LC042TrappingRainWaterTests {
    @Test
    public void testExample() throws Exception {
        int[] terrain = { 0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1 };
        assertEquals(6, new LC042TrappingRainWater().trap(terrain));
    }

    @Test
    public void testNothing() throws Exception {
        assertEquals(0, new LC042TrappingRainWater().trap(null));
        assertEquals(0, new LC042TrappingRainWater().trap(new int[] { 0, 1 }));
    }
}
