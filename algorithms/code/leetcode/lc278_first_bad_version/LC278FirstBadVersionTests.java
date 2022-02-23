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
