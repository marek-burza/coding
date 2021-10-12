package leetcode;

import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * https://leetcode.com/problems/first-bad-version/
 * #easy
 */
public final class LC278FirstBadVersion {
    public final class VersionControl {
        public int badVersion = 0;

        public boolean isBadVersion(final int version) {
            return badVersion <= version;
        }
    }

    public final class Solution extends VersionControl {
        public int firstBadVersion(final int n) {
            long a = 1, z = n;
            while (a != z) {
                int i = (int) ((a + z) / 2);
                if (!isBadVersion(i)) {
                    a = i + 1;
                } else {
                    z = i;
                }
            }
            return (int) a;
        }
    }

    @Test
    public void test_example() {
        Solution solution = new Solution();
        solution.badVersion = 456;
        assertEquals(456, solution.firstBadVersion(8000));
    }

    @Test
    public void test_big_example() {
        Solution solution = new Solution();
        solution.badVersion = 1702766719;
        assertEquals(1702766719, solution.firstBadVersion(2126753390));
    }

    @Test
    public void test_small_example() {
        Solution solution = new Solution();
        solution.badVersion = 1;
        assertEquals(1, solution.firstBadVersion(1));
    }
}
