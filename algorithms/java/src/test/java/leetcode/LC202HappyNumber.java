package leetcode;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * https://leetcode.com/problems/happy-number/
 * #easy
 */
public class LC202HappyNumber {
    public class Solution {
        private int re(int n) {
            int result = 0;
            while (n != 0) {
                int digit = n % 10;
                n = (int) Math.floor(n / 10);
                result += digit * digit;
            }
            return result;
        }

        public boolean isHappy(int n) {
            Set<Integer> seen = new HashSet<>();
            seen.add(n);
            while (n != 1) {
                n = re(n);
                if (seen.contains(n)) {
                    return false;
                } else {
                    seen.add(n);
                }
            }
            return true;
        }
    }

    @Test
    public void test_19() throws Exception {
        assertEquals(true, new Solution().isHappy(19));
    }
}
