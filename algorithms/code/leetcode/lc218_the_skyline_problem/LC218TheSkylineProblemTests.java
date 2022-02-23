package leetcode.lc218_the_skyline_problem;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

public class LC218TheSkylineProblemTests {
    private void generic(final int[][] buildings, final int[][] expected) throws Exception {
        LC218TheSkylineProblem solution;
        solution = new LC218TheSkylineProblem();
        List<int[]> skyline = solution.getSkyline(buildings);
        assertEquals(expected.length, skyline.size());
        for (int i = 0; i < expected.length; i++) {
            assertEquals(expected[i].length, skyline.get(i).length);
            for (int j = 0; j < expected[i].length; j++) {
                assertEquals(expected[i][j], skyline.get(i)[j]);
            }
        }
    }

    @Test
    public void testExample1() throws Exception {
        int[][] buildings = { { 2, 9, 10 }, { 3, 7, 15 }, { 5, 12, 12 }, { 15, 20, 10 }, { 19, 24, 8 } };
        int[][] expected = { { 2, 10 }, { 3, 15 }, { 7, 12 }, { 12, 0 }, { 15, 10 }, { 20, 8 }, { 24, 0 } };
        generic(buildings, expected);
    }

    @Test
    public void testExample2() throws Exception {
        int[][] buildings = { { 0, 2, 3 }, { 2, 5, 3 } };
        int[][] expected = { { 0, 3 }, { 5, 0 } };
        generic(buildings, expected);
    }

    @Test
    public void testCoverageGaps() throws Exception {
        int[][] buildings = { { 2, 5, 3 }, { 0, 2, 3 }, { 0, 0, 10 } };
        int[][] expected = { { 0, 3 }, { 5, 0 } };
        generic(buildings, expected);
        LC218TheSkylineProblem solution;
        solution = new LC218TheSkylineProblem();
        List<int[]> skyline;
        skyline = solution.getSkyline(new int[][] {});
        assertEquals(0, skyline.size());
        skyline = solution.getSkyline(null);
        assertEquals(0, skyline.size());
    }
}
