package leetcode.lc062_unique_paths;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public final class LC062UniquePathsTests {
    @Test
    public void test37() throws Exception {
        assertEquals(28, new LC062UniquePaths().uniquePaths(3, 7));
    }

    @Test
    public void test595() throws Exception {
        assertEquals(557845, new LC062UniquePaths().uniquePaths(59, 5));
    }

    @Test
    public void test110() throws Exception {
        assertEquals(1, new LC062UniquePaths().uniquePaths(1, 10));
    }

    @Test
    public void testNothing() throws Exception {
        assertEquals(0, new LC062UniquePaths().uniquePaths(1, 0));
    }
}
