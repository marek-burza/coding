package leetcode;

import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * https://leetcode.com/problems/compare-version-numbers/
 * #medium
 */
public class LC165CompareVersionNumbers {
    public class Solution {
        public int compareVersion(String version1, String version2) {
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

    @Test
    public void test_1__1() throws Exception {
        assertEquals(0, new Solution().compareVersion("1", "1"));
    }

    @Test
    public void test_1__1_0() throws Exception {
        assertEquals(0, new Solution().compareVersion("1", "1.0"));
    }

    @Test
    public void test_2__1() throws Exception {
        assertEquals(1, new Solution().compareVersion("2", "1"));
    }

    @Test
    public void test_1__13_1() throws Exception {
        assertEquals(-1, new Solution().compareVersion("1", "13.1"));
    }
}
