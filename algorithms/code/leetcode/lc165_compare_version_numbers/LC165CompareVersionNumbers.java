package leetcode.lc165_compare_version_numbers;

/**
 * https://leetcode.com/problems/compare-version-numbers/
 * #medium
 */
public final class LC165CompareVersionNumbers {
    public int compareVersion(final String version1, final String version2) {
        String[] parts1 = version1.split("\\.");
        String[] parts2 = version2.split("\\.");
        for (int i = 0; i < Math.max(parts1.length, parts2.length); i++) {
            int level1 = 0;
            if (i < parts1.length) {
                level1 = Integer.parseInt(parts1[i]);
            }
            int level2 = 0;
            if (i < parts2.length) {
                level2 = Integer.parseInt(parts2[i]);
            }
            if (level1 < level2) {
                return -1;
            }
            if (level1 > level2) {
                return 1;
            }
        }
        return 0;
    }
}
