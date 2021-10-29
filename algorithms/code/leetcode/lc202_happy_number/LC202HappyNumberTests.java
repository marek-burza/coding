package leetcode.lc202_happy_number;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public final class LC202HappyNumberTests {
    @Test
    public void test19() throws Exception {
        assertTrue(new LC202HappyNumber().isHappy(19));
    }

    @Test
    public void test2() throws Exception {
        assertFalse(new LC202HappyNumber().isHappy(2));
    }
}
