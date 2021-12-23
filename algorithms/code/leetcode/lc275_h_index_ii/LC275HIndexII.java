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
