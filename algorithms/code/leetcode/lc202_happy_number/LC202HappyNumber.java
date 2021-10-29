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
