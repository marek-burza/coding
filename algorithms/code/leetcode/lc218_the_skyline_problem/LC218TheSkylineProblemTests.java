package leetcode.lc218_the_skyline_problem;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

public class LC218TheSkylineProblemTests {
    @Test
    public void testExample1() throws Exception {
        LC218TheSkylineProblem solution;
        solution = new LC218TheSkylineProblem();
        int[][] buildings = { { 2, 9, 10 }, { 3, 7, 15 }, { 5, 12, 12 }, { 15, 20, 10 }, { 19, 24, 8 } };
        List<int[]> skyline = solution.getSkyline(buildings);
        int[][] expected = { { 2, 10 }, { 3, 15 }, { 7, 12 }, { 12, 0 }, { 15, 10 }, { 20, 8 }, { 24, 0 } };
        assertEquals(expected.length, skyline.size());
        for (int i = 0; i < expected.length; i++) {
            assertEquals(expected[i].length, skyline.get(i).length);
            for (int j = 0; j < expected[i].length; j++) {
                assertEquals(expected[i][j], skyline.get(i)[j]);
            }
        }
    }

    @Test
    public void testExample2() throws Exception {
        LC218TheSkylineProblem solution;
        solution = new LC218TheSkylineProblem();
        int[][] buildings = { { 0, 2, 3 }, { 2, 5, 3 } };
        List<int[]> skyline = solution.getSkyline(buildings);
        int[][] expected = { { 0, 3 }, { 5, 0 } };
        assertEquals(expected.length, skyline.size());
        for (int i = 0; i < expected.length; i++) {
            assertEquals(expected[i].length, skyline.get(i).length);
            for (int j = 0; j < expected[i].length; j++) {
                assertEquals(expected[i][j], skyline.get(i)[j]);
            }
        }
    }

    @Test
    public void testCoverageGaps() throws Exception {
        LC218TheSkylineProblem solution;
        solution = new LC218TheSkylineProblem();
        int[][] buildings = { { 2, 5, 3 }, { 0, 2, 3 }, { 0, 0, 10 } };
        List<int[]> skyline = solution.getSkyline(buildings);
        int[][] expected = { { 0, 3 }, { 5, 0 } };
        assertEquals(expected.length, skyline.size());
        for (int i = 0; i < expected.length; i++) {
            assertEquals(expected[i].length, skyline.get(i).length);
            for (int j = 0; j < expected[i].length; j++) {
                assertEquals(expected[i][j], skyline.get(i)[j]);
            }
        }
        skyline = solution.getSkyline(new int[][] {});
        assertEquals(0, skyline.size());
        skyline = solution.getSkyline(null);
        assertEquals(0, skyline.size());
    }
}
