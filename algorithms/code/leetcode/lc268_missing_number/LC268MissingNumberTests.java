package leetcode.lc268_missing_number;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public final class LC268MissingNumberTests {
    @Test
    public void testExample() throws Exception {
        assertEquals(2, new LC268MissingNumber().missingNumber(new int[] { 0, 1, 3 }));
    }
}
