package leetcode.lc278_first_bad_version;

/**
 * https://leetcode.com/problems/first-bad-version/ #easy
 */
public final class LC278FirstBadVersion {
    public int badVersion = 0;

    public boolean isBadVersion(final int version) {
        return badVersion <= version;
    }

    public int firstBadVersion(final int n) {
        int a = 1;
        int z = n;
        while (a != z) {
            int i = (int) (((long) a + (long) z) >>> 1);
            if (!isBadVersion(i)) {
                a = i + 1;
            } else {
                z = i;
            }
        }
        return a;
    }
}
