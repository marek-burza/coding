package leetcode.lc278_first_bad_version;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public final class LC278FirstBadVersionTests {
    @Test
    public void testExample() {
        LC278FirstBadVersion solution = new LC278FirstBadVersion();
        solution.badVersion = 456;
        assertEquals(456, solution.firstBadVersion(8000));
    }

    @Test
    public void testBigExample() {
        LC278FirstBadVersion solution = new LC278FirstBadVersion();
        solution.badVersion = 1702766719;
        assertEquals(1702766719, solution.firstBadVersion(2126753390));
    }

    @Test
    public void testSmallExample() {
        LC278FirstBadVersion solution = new LC278FirstBadVersion();
        solution.badVersion = 1;
        assertEquals(1, solution.firstBadVersion(1));
    }
}
