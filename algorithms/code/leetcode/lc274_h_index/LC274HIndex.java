package leetcode.lc274_h_index;

/**
 * https://leetcode.com/problems/h-index/
 * #medium
 */
public final class LC274HIndex {
    public int hIndex(final int[] citations) {
        int n = citations.length;
        int[] counts = new int[n + 1];
        for (int citation : citations) {
            if (citation > n) {
                counts[n]++;
            } else {
                counts[citation]++;
            }
        }
        int counted = 0;
        for (int i = n;; i--) {
            counted += counts[i];
            if (counted >= i) {
                return i;
            }
        }
    }
}
