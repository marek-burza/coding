package leetcode;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * https://leetcode.com/problems/add-digits/
 * #easy
 */
public class LC258AddDigits {
    public class Solution {
        public int addDigits(int num) {
            while (num >= 10) {
                int sum = 0;
                while (num > 0) {
                    int digit = num % 10;
                    sum += digit;
                    num /= 10;
                }
                num = sum;
            }
            return num;
            //return (int) (num - 9 * Math.floor((num - 1) / 9));
        }
    }

    @Test
    public void test_example() throws Exception {
        assertEquals(2, new Solution().addDigits(38));
    }
}
