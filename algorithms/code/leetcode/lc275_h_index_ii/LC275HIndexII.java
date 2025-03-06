package leetcode.lc275_h_index_ii;

/**
 * https://leetcode.com/problems/h-index-ii/
 * #medium
 */
public final class LC275HIndexII {
    public int hIndex(final int[] citations) {
        int n = citations.length;
        int a = 0;
        int z = n;
        while (a < z) {
            int m = (a + z) >>> 1;
            if (citations[m] == n - m) {
                return n - m;
            } else if (citations[m] < n - m) {
                a = m + 1;
            } else {
                z = m;
            }
        }
        return n - a;
    }
}
package leetcode.lc275_h_index_ii;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public final class LC275HIndexIITests {
    @Test
    public void testExample() throws Exception {
        int[] citations = { 0, 1, 3, 5, 6 };
        assertEquals(3, new LC275HIndexII().hIndex(citations));
    }

    @Test
    public void testNone() throws Exception {
        int[] citations = { 0, 0, 0, 0, 0 };
        assertEquals(0, new LC275HIndexII().hIndex(citations));
    }

    @Test
    public void test100() throws Exception {
        int[] citations = { 100 };
        assertEquals(1, new LC275HIndexII().hIndex(citations));
    }
}
