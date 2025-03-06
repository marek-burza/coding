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
package leetcode.lc165_compare_version_numbers;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public final class LC165CompareVersionNumbersTests {
    @Test
    public void test1And1() throws Exception {
        assertEquals(0, new LC165CompareVersionNumbers().compareVersion("1", "1"));
    }

    @Test
    public void test1And1Point0() throws Exception {
        assertEquals(0, new LC165CompareVersionNumbers().compareVersion("1", "1.0"));
    }

    @Test
    public void test2And1() throws Exception {
        assertEquals(1, new LC165CompareVersionNumbers().compareVersion("2", "1"));
    }

    @Test
    public void test1And13Point1() throws Exception {
        assertEquals(-1, new LC165CompareVersionNumbers().compareVersion("1", "13.1"));
    }

    @Test
    public void test1Point0Point1And1() throws Exception {
        assertEquals(1, new LC165CompareVersionNumbers().compareVersion("1.0.1", "1"));
    }
}
