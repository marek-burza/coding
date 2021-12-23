package leetcode.lc064_minimum_path_sum;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public final class LC064MinimumPathSumTests {
    @Test
    public void testExample() throws Exception {
        int[][] grid = {
                { 1, 1, 2, 2 },
                { 2, 1, 2, 2 },
                { 2, 1, 1, 2 },
                { 2, 2, 1, 1 }
        };
        assertEquals(7, new LC064MinimumPathSum().minPathSum(grid));
    }
}
