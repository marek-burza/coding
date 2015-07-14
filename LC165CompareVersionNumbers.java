package leetcode;

/**
 * https://leetcode.com/problemset/algorithms/
 */
public class LC165CompareVersionNumbers {
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

    public void test() {
        System.out.println("1 ? 1 = " + compareVersion("1", "1"));
        System.out.println("1 ? 1.0 = " + compareVersion("1", "1.0"));
        System.out.println("2 ? 1 = " + compareVersion("2", "1"));
        System.out.println("1 ? 13.1 = " + compareVersion("1", "13.1"));
    }

    public static void main(String[] arguments) {
        new LC165CompareVersionNumbers().test();
    }
}
