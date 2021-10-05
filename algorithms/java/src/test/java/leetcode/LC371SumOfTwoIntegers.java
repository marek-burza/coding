package leetcode;

import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.assertEquals;

/**
 * https://leetcode.com/problems/sum-of-two-integers/
 * #medium
 */
public class LC371SumOfTwoIntegers {
    public class Solution {
        public int getSum(int a, int b) {
            int result = 0, carry = 0;
            for (int mask = 1; mask != 0; mask <<= 1) {
                int am = a & mask;
                int bm = b & mask;
                result |= am ^ bm ^ carry;
                carry = (am & bm) | (bm & carry) | (am & carry);
                carry <<= 1;
            }
            return result;
        }
    }

    @Test
    public void test_example() throws Exception {
        assertEquals(3, new Solution().getSum(1, 2));
    }


    @Test
    public void test_random() throws Exception {
        Random random = new Random();
        int a = random.nextInt();
        int b = random.nextInt();
        assertEquals(a + b, new Solution().getSum(a, b));
    }
}
