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
package leetcode.lc278_first_bad_version;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public final class LC278FirstBadVersionTests {
    private void generic(final int version, final int badVersion) {
        LC278FirstBadVersion solution = new LC278FirstBadVersion();
        solution.badVersion = badVersion;
        assertEquals(badVersion, solution.firstBadVersion(version));
    }

    @Test
    public void testExample() {
        generic(8000, 456);
    }

    @Test
    public void testBigExample() {
        generic(2126753390, 1702766719);
    }

    @Test
    public void testSmallExample() {
        generic(1, 1);
    }
}
