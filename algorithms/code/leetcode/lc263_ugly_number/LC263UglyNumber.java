package leetcode.lc263_ugly_number;

/**
 * https://leetcode.com/problems/ugly-number/ #easy
 */
public final class LC263UglyNumber {
    public boolean isUgly(final int numValue) {
        int num = numValue;
        if (num <= 0) {
            return false;
        }
        if (num == 1) {
            return true;
        }
        int original = num;
        while (num % 2 == 0) {
            num /= 2;
        }
        while (num % 3 == 0) {
            num /= 3;
        }
        while (num % 5 == 0) {
            num /= 5;
        }
        return num != original && num == 1;
    }
}
package leetcode.lc263_ugly_number;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public final class LC263UglyNumberTests {
    @Test
    public void testMinus() throws Exception {
        assertFalse(new LC263UglyNumber().isUgly(-1));
    }

    @Test
    public void test0() throws Exception {
        assertFalse(new LC263UglyNumber().isUgly(0));
    }

    @Test
    public void test1() throws Exception {
        assertTrue(new LC263UglyNumber().isUgly(1));
    }

    @Test
    public void test2() throws Exception {
        assertTrue(new LC263UglyNumber().isUgly(2));
    }

    @Test
    public void test3() throws Exception {
        assertTrue(new LC263UglyNumber().isUgly(3));
    }

    @Test
    public void test7() throws Exception {
        assertFalse(new LC263UglyNumber().isUgly(7));
    }

    @Test
    public void test11() throws Exception {
        assertFalse(new LC263UglyNumber().isUgly(11));
    }

    @Test
    public void test14() throws Exception {
        assertFalse(new LC263UglyNumber().isUgly(14));
    }

    @Test
    public void test16() throws Exception {
        assertTrue(new LC263UglyNumber().isUgly(16));
    }

    @Test
    public void test27() throws Exception {
        assertTrue(new LC263UglyNumber().isUgly(27));
    }

    @Test
    public void test937351770() throws Exception {
        assertFalse(new LC263UglyNumber().isUgly(937351770));
    }

    @Test
    public void test905391974() throws Exception {
        assertFalse(new LC263UglyNumber().isUgly(905391974));
    }
}
