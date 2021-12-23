package leetcode.lc200_number_of_islands;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public final class LC200NumberOfIslandsTests {
    @Test
    public void testExample() throws Exception {
        char[][] grid = { { '1' } };
        assertEquals(1, new LC200NumberOfIslands().numIslands(grid));
    }

    @Test
    public void testOther() throws Exception {
        char[][] grid = {
                { '1', '1', '0', '0', '0' },
                { '1', '1', '0', '0', '0' },
                { '0', '0', '1', '0', '0' },
                { '0', '0', '0', '1', '1' } };
        assertEquals(3, new LC200NumberOfIslands().numIslands(grid));
    }

    @Test
    public void testNothing() throws Exception {
        assertEquals(0, new LC200NumberOfIslands().numIslands(null));
        assertEquals(0, new LC200NumberOfIslands().numIslands(new char[][] {}));
        assertEquals(0, new LC200NumberOfIslands().numIslands(new char[][] { null }));
        assertEquals(0, new LC200NumberOfIslands().numIslands(new char[][] { {} }));
    }
}
