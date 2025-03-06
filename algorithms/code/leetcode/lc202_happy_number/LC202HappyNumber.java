package leetcode.lc202_happy_number;

import java.util.HashSet;
import java.util.Set;

/**
 * https://leetcode.com/problems/happy-number/ #easy
 */
public final class LC202HappyNumber {
    private int re(final int nValue) {
        int n = nValue;
        int result = 0;
        while (n != 0) {
            int digit = n % 10;
            n /= 10;
            result += digit * digit;
        }
        return result;
    }

    public boolean isHappy(final int nValue) {
        int n = nValue;
        Set<Integer> seen = new HashSet<>();
        seen.add(n);
        while (n != 1) {
            n = re(n);
            if (seen.contains(n)) {
                return false;
            }
            seen.add(n);

        }
        return true;
    }
}
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
